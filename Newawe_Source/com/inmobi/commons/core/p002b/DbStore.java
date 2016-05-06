package com.inmobi.commons.core.p002b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.inmobi.commons.core.b.b */
public final class DbStore {
    private static final String f1154a;
    private static volatile DbStore f1155b;
    private static final Object f1156c;
    private static final Object f1157d;
    private static int f1158e;
    private SQLiteDatabase f1159f;

    static {
        f1154a = DbStore.class.getSimpleName();
        f1156c = new Object();
        f1157d = new Object();
        f1158e = 0;
    }

    private DbStore() {
        try {
            this.f1159f = new DbHelper(SdkContext.m1258b()).getWritableDatabase();
            f1155b = this;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1154a, "Problem while getting writable database connection.", e);
        }
    }

    public static synchronized DbStore m1275a() {
        DbStore dbStore;
        synchronized (DbStore.class) {
            synchronized (f1157d) {
                f1158e++;
            }
            dbStore = f1155b;
            if (dbStore == null) {
                synchronized (f1156c) {
                    dbStore = f1155b;
                    if (dbStore == null) {
                        f1155b = new DbStore();
                        dbStore = f1155b;
                    }
                }
            }
        }
        return dbStore;
    }

    public boolean m1281a(String str, ContentValues contentValues) {
        return this.f1159f.insertWithOnConflict(str, null, contentValues, 4) != -1;
    }

    public int m1278a(String str, String str2, String[] strArr) {
        return this.f1159f.delete(str, str2, strArr);
    }

    public int m1277a(String str, ContentValues contentValues, String str2, String[] strArr) {
        return this.f1159f.updateWithOnConflict(str, contentValues, str2, strArr, 4);
    }

    public List<ContentValues> m1279a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        Cursor query = this.f1159f.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        List<ContentValues> arrayList = new ArrayList();
        if (query.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(query, contentValues);
                arrayList.add(contentValues);
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public int m1276a(String str) {
        Cursor rawQuery = this.f1159f.rawQuery("SELECT COUNT(*) FROM " + str + " ; ", null);
        rawQuery.moveToFirst();
        int i = rawQuery.getInt(0);
        rawQuery.close();
        return i;
    }

    public int m1282b(String str, String str2, String[] strArr) {
        Cursor rawQuery = this.f1159f.rawQuery("SELECT COUNT(*) FROM " + str + " WHERE " + str2 + " ; ", strArr);
        rawQuery.moveToFirst();
        int i = rawQuery.getInt(0);
        rawQuery.close();
        return i;
    }

    public void m1280a(String str, String str2) {
        this.f1159f.execSQL("CREATE TABLE IF NOT EXISTS " + str + str2 + ";");
    }

    public void m1283b() {
        synchronized (f1157d) {
            f1158e--;
            if (f1158e == 0) {
                this.f1159f.close();
                f1155b = null;
            }
        }
    }
}
