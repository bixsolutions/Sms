package com.bixlabs.smssolidario.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bixlabs.smssolidario.classes.Utils;

/**
 * Donations History DAO
 */
public class HistoryDataSource {
  private DatabaseHelper databaseHelper;

  public HistoryDataSource(Context ctx) {
    databaseHelper = new DatabaseHelper(ctx);
  }

  /**
   * Adds a new entry to the History table
   * @param organizationName The name of the organization which we donated
   */
  public void addToHistory(String organizationName) {
    SQLiteDatabase database = databaseHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.COLUMN_HISTORY_ORG, organizationName);
    values.put(DatabaseHelper.COLUMN_HISTORY_DATE, Utils.formatDate(System.currentTimeMillis()));
    database.insert(DatabaseHelper.TABLE_HISTORY, null, values);
    database.close();
  }

}
