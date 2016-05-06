package com.Newawe.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import java.util.Date;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;

public class BrowsingHistoryStorage {
    private DatabaseOpenHelper _dbHelper;

    public BrowsingHistoryStorage(Context context) {
        this._dbHelper = new DatabaseOpenHelper(context);
    }

    public Cursor loadWeeklyHistory() {
        Date to = new Date();
        return loadHistory(new Date(to.getTime() - 604800000), to);
    }

    public Cursor loadHistory(Date from, Date to) {
        String selection = StringUtils.EMPTY;
        Vector<String> selectionArgsVector = new Vector();
        if (from != null) {
            selection = selection + "visitTime>= ?";
            selectionArgsVector.add(DatabaseOpenHelper.SQL_DATE_FORMAT.format(from));
        }
        if (to != null) {
            if (from != null) {
                selection = selection + " AND ";
            }
            selection = selection + "visitTime<= ?";
            selectionArgsVector.add(DatabaseOpenHelper.SQL_DATE_FORMAT.format(to));
        }
        String[] selectionArgs = new String[selectionArgsVector.size()];
        selectionArgsVector.toArray(selectionArgs);
        String[] projectionIn = new String[]{"rowId _id", DatabaseOpenHelper.HISTORY_ROW_TITLE, DatabaseOpenHelper.HISTORY_ROW_URL, DatabaseOpenHelper.HISTORY_ROW_VISIT_TIME};
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DatabaseOpenHelper.HISTORY_TABLE_NAME);
        Cursor cursor = builder.query(this._dbHelper.getReadableDatabase(), projectionIn, selection, selectionArgs, null, null, null);
        if (cursor == null) {
            return null;
        }
        if (cursor.moveToFirst()) {
            Log.wtf("CursorAllHistory", DatabaseUtils.dumpCursorToString(cursor));
            return cursor;
        }
        cursor.close();
        return null;
    }

    public Cursor getHistoryItemsGroupedByUrl(String urlPart) {
        String[] projectionIn = new String[]{"rowId _id", DatabaseOpenHelper.HISTORY_ROW_TITLE, DatabaseOpenHelper.HISTORY_ROW_URL};
        loadHistory(new Date("2014/01/01"), new Date("2016/09/09"));
        String selection = null;
        String[] selectionArgs = null;
        if (urlPart != null) {
            selection = "url MATCH ?";
            selectionArgs = new String[]{urlPart + "*"};
        }
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DatabaseOpenHelper.HISTORY_TABLE_NAME);
        return builder.query(this._dbHelper.getReadableDatabase(), projectionIn, selection, selectionArgs, DatabaseOpenHelper.HISTORY_ROW_URL, null, null, "6");
    }

    public void addHistoryItem(String title, String url, Date visitTime) {
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.HISTORY_ROW_TITLE, title);
        values.put(DatabaseOpenHelper.HISTORY_ROW_URL, url);
        values.put(DatabaseOpenHelper.HISTORY_ROW_VISIT_TIME, DatabaseOpenHelper.SQL_DATE_FORMAT.format(visitTime));
        this._dbHelper.getWritableDatabase().insert(DatabaseOpenHelper.HISTORY_TABLE_NAME, null, values);
    }

    public int removeHistoryItemById(long id) {
        try {
            return this._dbHelper.getWritableDatabase().delete(DatabaseOpenHelper.HISTORY_TABLE_NAME, "rowId=" + Long.toString(id), null);
        } catch (Exception e) {
            System.out.println();
            return -1;
        }
    }
}
