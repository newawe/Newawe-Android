package com.inmobi.rendering.p005a;

import android.content.ContentValues;
import com.Newawe.storage.DatabaseOpenHelper;
import com.inmobi.commons.core.p002b.DbStore;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.inmobi.rendering.a.b */
public class ClickDao {
    public static final String[] f1528a;
    private static final String f1529b;

    static {
        f1529b = ClickDao.class.getSimpleName();
        f1528a = new String[]{"id", "pending_attempts", DatabaseOpenHelper.HISTORY_ROW_URL, "ping_in_webview", "follow_redirect", "ts"};
    }

    public ClickDao() {
        DbStore a = DbStore.m1275a();
        a.m1280a("click", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, ping_in_webview TEXT NOT NULL, follow_redirect TEXT NOT NULL, ts TEXT NOT NULL)");
        a.m1283b();
    }

    public boolean m1692a() {
        return DbStore.m1275a().m1276a("click") == 0;
    }

    public synchronized boolean m1693a(Click click, int i) {
        ContentValues c = m1695c(click);
        DbStore a = DbStore.m1275a();
        if (a.m1276a("click") >= i) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", "MaxDbLimitBreach");
            TelemetryComponent.m4448a().m4470a("ads", "PingDiscarded", hashMap);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1529b, "Pruning persistent store to remove the oldest entry ...");
            Click a2 = m1689a((ContentValues) a.m1279a("click", f1528a, "ts= (SELECT MIN(ts) FROM click LIMIT 1)", null, null, null, null, null).get(0));
            Logger.m1440a(InternalLogLevel.INTERNAL, f1529b, "Deleting click (" + a2.f1521a + ")");
            m1694b(a2);
        }
        a.m1281a("click", c);
        a.m1283b();
        return true;
    }

    public List<Click> m1690a(int i, int i2) {
        List<Click> arrayList = new ArrayList();
        DbStore a = DbStore.m1275a();
        if (a.m1276a("click") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.m1279a("click", f1528a, null, null, "ts", "ts < " + String.valueOf(System.currentTimeMillis() - ((long) i2)), "ts ASC ", -1 == i ? null : Integer.toString(i));
        a.m1283b();
        for (ContentValues a3 : a2) {
            arrayList.add(m1689a(a3));
        }
        return arrayList;
    }

    public void m1691a(Click click) {
        DbStore a = DbStore.m1275a();
        a.m1277a("click", m1695c(click), "id = ?", new String[]{String.valueOf(click.f1521a)});
        a.m1283b();
    }

    public void m1694b(Click click) {
        DbStore a = DbStore.m1275a();
        a.m1278a("click", "id = ?", new String[]{String.valueOf(click.f1521a)});
        a.m1283b();
    }

    public Click m1689a(ContentValues contentValues) {
        int intValue = contentValues.getAsInteger("id").intValue();
        int intValue2 = contentValues.getAsInteger("pending_attempts").intValue();
        return new Click(intValue, contentValues.getAsString(DatabaseOpenHelper.HISTORY_ROW_URL), Boolean.valueOf(contentValues.getAsString("follow_redirect")).booleanValue(), Boolean.valueOf(contentValues.getAsString("ping_in_webview")).booleanValue(), intValue2, Long.valueOf(contentValues.getAsString("ts")).longValue());
    }

    public ContentValues m1695c(Click click) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(click.f1521a));
        contentValues.put(DatabaseOpenHelper.HISTORY_ROW_URL, click.f1522b);
        contentValues.put("pending_attempts", Integer.valueOf(click.f1524d));
        contentValues.put("ts", Long.toString(click.f1523c));
        contentValues.put("follow_redirect", Boolean.toString(click.f1527g));
        contentValues.put("ping_in_webview", Boolean.toString(click.f1526f));
        return contentValues;
    }
}
