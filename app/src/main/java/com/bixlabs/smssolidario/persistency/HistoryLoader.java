package com.bixlabs.smssolidario.persistency;

import android.content.Context;
import android.database.Cursor;

import com.bixlabs.smssolidario.classes.SimpleCursorLoader;

public final class HistoryLoader extends SimpleCursorLoader {
  private DatabaseHelper mDatabaseHelper;
  private String[] allColumns = { DatabaseHelper.COLUMN_HISTORY_ID,
    DatabaseHelper.COLUMN_HISTORY_ORG, DatabaseHelper.COLUMN_HISTORY_DATE};

  public HistoryLoader(Context context, DatabaseHelper helper) {
    super(context);
    this.mDatabaseHelper = helper;
  }

  @Override
  public Cursor loadInBackground() {
    return mDatabaseHelper.getReadableDatabase()
      .query(DatabaseHelper.TABLE_HISTORY, allColumns, null, null, null, null, null);
  }

  @Override
  public void deliverResult(Cursor cursor) {
    super.deliverResult(cursor);

    // Close the underlying DB connection and release it.
    mDatabaseHelper.close();
  }
}
