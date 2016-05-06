package com.Newawe.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.apache.commons.lang.StringUtils;

public class BookmarksManager extends SQLiteOpenHelper {
    private static final String BOOKMARKS_INDEX = "id";
    private static final String BOOKMARKS_NAME = "name";
    private static final String BOOKMARKS_URL = "url";
    private static final String DATABASE_INITIAL_NAME = "Bookmarks";
    private static final int DATABASE_VERSION = 1;
    private final String BOOKMARKS_PAGE_PREFIX;
    private String BOOKMARKS_TABLE_CREATE;
    private String BOOKMARKS_TABLE_NAME;
    private final String DATABASE_NAME;
    private Context _context;
    private String _name;

    public BookmarksManager(String storageName, Context context) {
        super(context, DATABASE_INITIAL_NAME + storageName, null, DATABASE_VERSION);
        this.BOOKMARKS_PAGE_PREFIX = "BookmarksPage";
        this._context = context;
        this.DATABASE_NAME = DATABASE_INITIAL_NAME + storageName;
        this.BOOKMARKS_TABLE_NAME = "BookmarksPage" + storageName;
        this.BOOKMARKS_TABLE_CREATE = "CREATE TABLE " + this.BOOKMARKS_TABLE_NAME + " (" + BOOKMARKS_INDEX + " integer primary key autoincrement, " + BOOKMARKS_NAME + " TEXT, " + BOOKMARKS_URL + " TEXT);";
        this._name = storageName;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.BOOKMARKS_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + this.BOOKMARKS_TABLE_NAME);
        onCreate(db);
    }

    public void addBookmark(String name, String url) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOKMARKS_NAME, name);
        values.put(BOOKMARKS_URL, url);
        db.insert(this.BOOKMARKS_TABLE_NAME, null, values);
    }

    public void removeBookmark(Integer index) {
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = index.toString();
        super.getWritableDatabase().delete(this.BOOKMARKS_TABLE_NAME, "id=?", strArr);
    }

    public Cursor getBookmarks() {
        return getBookmarks(Integer.valueOf(0), null);
    }

    public Cursor getBookmarks(Integer offset, Integer limit) {
        SQLiteDatabase db = super.getReadableDatabase();
        String limitAndOffset = StringUtils.EMPTY;
        if (!(limit == null || limit.intValue() == 0)) {
            limitAndOffset = limitAndOffset + " limit " + limit;
        }
        if (offset != null && offset.intValue() > 0) {
            limitAndOffset = limitAndOffset + " offset " + offset;
        }
        return db.query(this.BOOKMARKS_TABLE_NAME, null, null, null, null, null, null, limitAndOffset);
    }
}
