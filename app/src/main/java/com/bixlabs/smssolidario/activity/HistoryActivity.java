package com.bixlabs.smssolidario.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bixlabs.smssolidario.R;
import com.bixlabs.smssolidario.adapters.HistoryCursorAdapter;
import com.bixlabs.smssolidario.persistency.DatabaseHelper;
import com.bixlabs.smssolidario.persistency.HistoryLoader;

public class HistoryActivity extends AppCompatActivity
  implements LoaderManager.LoaderCallbacks<Cursor> {

  private ViewFlipper viewFlipper;
  private ListView historyListview;
  private HistoryCursorAdapter historyAdapter;
  private DatabaseHelper databaseHelper;
  private ProgressDialog progressDialog;

  // Dismiss the Progress Dialog and make the ViewFlipper visible
  // inside a Handler (which runs on the UI thread).
  private final Handler progressDialogHandler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message msg) {
      if (historyAdapter.getCount() == 0) {
        viewFlipper.setDisplayedChild(1);
      } else {
        viewFlipper.setDisplayedChild(0);
      }
      viewFlipper.setVisibility(View.VISIBLE);
      progressDialog.dismiss();
      return true;
    }
  });

  @SuppressWarnings("ConstantConditions")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history);

    // Set the actionbar title and home button
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
    toolbar.setTitle(R.string.donations_history);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    progressDialog = ProgressDialog.show(this, getString(R.string.history_progressdialog_title),
      getString(R.string.history_progressdialog_message), true, false);

    viewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper_history);
    historyListview = (ListView) this.findViewById(R.id.list_history);
    historyAdapter = new HistoryCursorAdapter(this, null);
    historyListview.setAdapter(historyAdapter);

    databaseHelper = new DatabaseHelper(getApplicationContext());

    // Make the ViewFlipper invisible until the loader has finished loading data
    viewFlipper.setVisibility(View.INVISIBLE);

    // Initialize the loader
    getSupportLoaderManager().initLoader(0, null, this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(menuItem);
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    return new HistoryLoader(getApplicationContext(), databaseHelper);
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
    // We can't swap the cursor inside the Handler because the DB
    // would be already closed by that time. The good thing is that
    // swapping takes a few milliseconds (tested with 10.000 rows of data) so
    // it's completely transparent to the user.
    historyAdapter.swapCursor(data);

    // Send a delayed message to the ProgressDialog handler to avoid dialog flickering
    // in case the data is loaded too fast. From a user perspective it introduces
    // consistency.
    progressDialogHandler.sendEmptyMessageDelayed(0, 1200); // 1.2 second
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    historyAdapter.swapCursor(null);
  }
}
