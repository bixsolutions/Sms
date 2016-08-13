package com.bixlabs.smssolidario.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.bixlabs.smssolidario.R;
import com.bixlabs.smssolidario.adapters.HistoryCursorAdapter;
import com.bixlabs.smssolidario.persistency.DatabaseHelper;
import com.bixlabs.smssolidario.persistency.HistoryLoader;

public class HistoryActivity extends AppCompatActivity
  implements LoaderManager.LoaderCallbacks<Cursor> {

  private ListView historyListview;
  private HistoryCursorAdapter historyAdapter;
  private DatabaseHelper databaseHelper;

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

    historyListview = (ListView) this.findViewById(R.id.list_history);
    historyAdapter = new HistoryCursorAdapter(this, null);
    historyListview.setAdapter(historyAdapter);

    databaseHelper = new DatabaseHelper(getApplicationContext());

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
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    historyAdapter.swapCursor(data);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    historyAdapter.swapCursor(null);
  }

}
