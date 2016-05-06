package com.inmobi.commons.core.p003c;

import android.content.ContentValues;
import com.inmobi.commons.core.p002b.DbStore;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.core.c.d */
public class TelemetryDao {
    private static final String f1183a;

    static {
        f1183a = TelemetryDao.class.getSimpleName();
    }

    public TelemetryDao() {
        DbStore a = DbStore.m1275a();
        a.m1280a("telemetry", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a.m1280a("metric", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL )");
        a.m1283b();
    }

    public void m1317a(TelemetryEvent telemetryEvent) {
        DbStore a = DbStore.m1275a();
        a.m1281a("telemetry", telemetryEvent.m1328e());
        a.m1283b();
    }

    public void m1318a(String str, String str2, String str3) {
        DbStore a = DbStore.m1275a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("componentType", str);
        contentValues.put("eventType", str2);
        contentValues.put("payload", str3);
        a.m1281a("metric", contentValues);
        a.m1283b();
    }

    public List<ContentValues> m1315a() {
        DbStore a = DbStore.m1275a();
        List<ContentValues> a2 = a.m1279a("metric", null, null, null, null, null, null, null);
        a.m1283b();
        m1320b();
        return a2;
    }

    public void m1320b() {
        DbStore a = DbStore.m1275a();
        a.m1278a("metric", null, null);
        a.m1283b();
    }

    public void m1319a(List<TelemetryEvent> list) {
        DbStore a = DbStore.m1275a();
        for (TelemetryEvent e : list) {
            a.m1281a("telemetry", e.m1328e());
        }
        a.m1283b();
    }

    public List<TelemetryEvent> m1316a(int i) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1183a, "Querying db for events");
        DbStore a = DbStore.m1275a();
        List<ContentValues> a2 = a.m1279a("telemetry", null, null, null, null, null, "ts ASC", String.valueOf(i));
        m1314b(a2);
        List<TelemetryEvent> arrayList = new ArrayList();
        a.m1283b();
        for (ContentValues a3 : a2) {
            arrayList.add(TelemetryEvent.m1322a(a3));
        }
        return arrayList;
    }

    private void m1314b(List<ContentValues> list) {
        if (!list.isEmpty()) {
            DbStore a = DbStore.m1275a();
            String str = StringUtils.EMPTY;
            int i = 0;
            while (i < list.size() - 1) {
                String str2 = str + ((ContentValues) list.get(i)).getAsString("id") + ",";
                i++;
                str = str2;
            }
            str = str + ((ContentValues) list.get(list.size() - 1)).getAsString("id");
            Logger.m1440a(InternalLogLevel.INTERNAL, f1183a, "Deleting events with id: " + str);
            int a2 = a.m1278a("telemetry", "id IN (" + str + ")", null);
            a.m1283b();
            Logger.m1440a(InternalLogLevel.INTERNAL, f1183a, "Deleted Count: " + a2);
        }
    }

    public int m1321c() {
        DbStore a = DbStore.m1275a();
        int a2 = a.m1276a("telemetry");
        a.m1283b();
        return a2;
    }
}
