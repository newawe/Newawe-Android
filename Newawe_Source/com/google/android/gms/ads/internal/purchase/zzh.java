package com.google.android.gms.ads.internal.purchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzhb;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzhb
public class zzh {
    private static final String zzFV;
    private static zzh zzFX;
    private static final Object zzpV;
    private final zza zzFW;

    public class zza extends SQLiteOpenHelper {
        final /* synthetic */ zzh zzFY;

        public zza(zzh com_google_android_gms_ads_internal_purchase_zzh, Context context, String str) {
            this.zzFY = com_google_android_gms_ads_internal_purchase_zzh;
            super(context, str, null, 4);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(zzh.zzFV);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            zzb.zzaJ("Database updated from version " + oldVersion + " to version " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            onCreate(db);
        }
    }

    static {
        zzFV = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"});
        zzpV = new Object();
    }

    zzh(Context context) {
        this.zzFW = new zza(this, context, "google_inapp_purchase.db");
    }

    public static zzh zzy(Context context) {
        zzh com_google_android_gms_ads_internal_purchase_zzh;
        synchronized (zzpV) {
            if (zzFX == null) {
                zzFX = new zzh(context);
            }
            com_google_android_gms_ads_internal_purchase_zzh = zzFX;
        }
        return com_google_android_gms_ads_internal_purchase_zzh;
    }

    public int getRecordCount() {
        Cursor cursor = null;
        int i = 0;
        synchronized (zzpV) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
            } else {
                try {
                    cursor = writableDatabase.rawQuery("select count(*) from InAppPurchase", null);
                    if (cursor.moveToFirst()) {
                        i = cursor.getInt(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                    } else {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } catch (SQLiteException e) {
                    zzb.zzaK("Error getting record count" + e.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
        return i;
    }

    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzFW.getWritableDatabase();
        } catch (SQLiteException e) {
            zzb.zzaK("Error opening writable conversion tracking database");
            return null;
        }
    }

    public zzf zza(Cursor cursor) {
        return cursor == null ? null : new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public void zza(zzf com_google_android_gms_ads_internal_purchase_zzf) {
        if (com_google_android_gms_ads_internal_purchase_zzf != null) {
            synchronized (zzpV) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    return;
                }
                writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(com_google_android_gms_ads_internal_purchase_zzf.zzFP)}), null);
            }
        }
    }

    public void zzb(zzf com_google_android_gms_ads_internal_purchase_zzf) {
        if (com_google_android_gms_ads_internal_purchase_zzf != null) {
            synchronized (zzpV) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("product_id", com_google_android_gms_ads_internal_purchase_zzf.zzFR);
                contentValues.put("developer_payload", com_google_android_gms_ads_internal_purchase_zzf.zzFQ);
                contentValues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
                com_google_android_gms_ads_internal_purchase_zzf.zzFP = writableDatabase.insert("InAppPurchase", null, contentValues);
                if (((long) getRecordCount()) > 20000) {
                    zzfY();
                }
            }
        }
    }

    public void zzfY() {
        Cursor query;
        SQLiteException e;
        synchronized (zzpV) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
            try {
                query = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", SchemaSymbols.ATTVAL_TRUE_1);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            zza(zza(query));
                        }
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            zzb.zzaK("Error remove oldest record" + e.getMessage());
                            if (query != null) {
                                query.close();
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            if (query != null) {
                                query.close();
                            }
                            throw th2;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                zzb.zzaK("Error remove oldest record" + e.getMessage());
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th2 = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th2;
            }
        }
    }

    public List<zzf> zzg(long j) {
        Cursor query;
        SQLiteException e;
        Throwable th;
        synchronized (zzpV) {
            List<zzf> linkedList = new LinkedList();
            if (j <= 0) {
                return linkedList;
            }
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return linkedList;
            }
            try {
                query = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", String.valueOf(j));
                try {
                    if (query.moveToFirst()) {
                        do {
                            linkedList.add(zza(query));
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        zzb.zzaK("Error extracing purchase info: " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        return linkedList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                zzb.zzaK("Error extracing purchase info: " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                return linkedList;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            return linkedList;
        }
    }
}
