package com.bixlabs.smssolidario.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bixlabs.smssolidario.R;
import com.bixlabs.smssolidario.persistency.DatabaseHelper;

/**
 * History ListView Adapter
 */
public class HistoryCursorAdapter extends CursorAdapter {
  private int COL_ORGANIZATION = 0;
  private int COL_DATE = 0;

  public HistoryCursorAdapter(Context context, Cursor c) {
    super(context, c, 0);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
    ViewHolder holder = new ViewHolder(view);
    view.setTag(holder);
    return view;
  }

  @Override
  public Cursor swapCursor(Cursor c) {
    if (c != null) {
      COL_ORGANIZATION = c.getColumnIndex(DatabaseHelper.COLUMN_HISTORY_ORG);
      COL_DATE = c.getColumnIndex(DatabaseHelper.COLUMN_HISTORY_DATE);
    }
    return super.swapCursor(c);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    ViewHolder holder = (ViewHolder) view.getTag();

    String organizationName = cursor.getString(COL_ORGANIZATION);
    String timestamp = cursor.getString(COL_DATE);

    holder.organization.setText(organizationName);
    holder.date.setText(timestamp);
  }

  private static class ViewHolder {
    final TextView organization;
    final TextView date;

    ViewHolder(View view) {
      organization = (TextView) view.findViewById(R.id.item_history_organization);
      date = (TextView) view.findViewById(R.id.item_history_date);
    }
  }
}
