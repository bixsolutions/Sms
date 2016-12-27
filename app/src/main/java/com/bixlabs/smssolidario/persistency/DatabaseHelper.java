package com.bixlabs.smssolidario.persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Database Helper class
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "sms_solidario.db";
  private static final int DATABASE_VERSION = 1;

  // Tables
  public static final String TABLE_HISTORY = "history";

  // Columns
  public static final String COLUMN_HISTORY_ID = "_id";
  public static final String COLUMN_HISTORY_ORG = "organization";
  public static final String COLUMN_HISTORY_DATE = "date";

  /*
  Table History:
  ------------------------------------------
  | _id | organization        | date
  ------------------------------------------
  |  1  | Animales sin hogar  | El 12/08/2016 a las 20:30 hrs.
  |  2  | Aldeas Infantiles   | El 12/09/2016 a las 20:30 hrs.
  ------------------------------------------
   */
  private static final String TABLE_HISTORY_CREATE =
    TABLE_HISTORY + "( " +
      COLUMN_HISTORY_ID + " integer primary key autoincrement, " +
      COLUMN_HISTORY_ORG + " text not null, " +
      COLUMN_HISTORY_DATE + " text not null" +
      ");";

  private static final String DATABASE_CREATE = "create table " + TABLE_HISTORY_CREATE;

  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");

    // Drop table if exists and re-create it
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

    onCreate(db);
  }

}
