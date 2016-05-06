package com.inmobi.commons.core.p002b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.inmobi.commons.core.b.a */
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "com.im.db", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
