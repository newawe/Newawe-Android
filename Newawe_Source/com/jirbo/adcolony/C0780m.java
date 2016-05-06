package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import com.Newawe.storage.DatabaseOpenHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import mf.org.apache.xml.serialize.Method;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.protocol.HTTP;

/* renamed from: com.jirbo.adcolony.m */
class C0780m {
    WebView f2248a;
    Activity f2249b;
    ADCVideo f2250c;
    Handler f2251d;
    Runnable f2252e;
    AdColonyAd f2253f;
    String f2254g;

    /* renamed from: com.jirbo.adcolony.m.1 */
    class C07781 implements Runnable {
        final /* synthetic */ C0780m f2246a;

        C07781(C0780m c0780m) {
            this.f2246a = c0780m;
        }

        public void run() {
            C0745a.f1973J = false;
        }
    }

    /* renamed from: com.jirbo.adcolony.m.2 */
    class C07792 implements OnScanCompletedListener {
        final /* synthetic */ C0780m f2247a;

        C07792(C0780m c0780m) {
            this.f2247a = c0780m;
        }

        public void onScanCompleted(String path, Uri uri) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            Toast.makeText(this.f2247a.f2249b, "Screenshot saved to Gallery!", 0).show();
        }
    }

    public C0780m(ADCVideo aDCVideo, WebView webView, Activity activity) {
        this.f2248a = webView;
        this.f2249b = activity;
        this.f2250c = aDCVideo;
        this.f2251d = new Handler();
        this.f2252e = new C07781(this);
    }

    void m2358a(String str) {
        String str2;
        String[] strArr;
        String replace = str.replace("mraid://", StringUtils.EMPTY);
        if (replace.contains("?")) {
            String[] split = replace.split("\\?");
            str2 = split[0];
            strArr = split;
        } else {
            str2 = replace;
            strArr = null;
        }
        if (strArr != null) {
            strArr = strArr[1].split("&");
        } else {
            strArr = new String[0];
        }
        HashMap hashMap = new HashMap();
        for (String str3 : r0) {
            hashMap.put(str3.split("=")[0], str3.split("=")[1]);
        }
        this.f2253f = C0745a.f1983T;
        this.f2254g = "{\"ad_slot\":" + C0745a.f2001l.f2145e.f2599j + "}";
        if (str2.equals("send_adc_event")) {
            m2361b((String) hashMap.get("type"));
        } else if (str2.equals("close")) {
            m2360b();
        } else if (str2.equals("open_store") && !C0745a.f1973J) {
            m2363c((String) hashMap.get("item"));
        } else if (str2.equals("open") && !C0745a.f1973J) {
            m2365d((String) hashMap.get(DatabaseOpenHelper.HISTORY_ROW_URL));
        } else if (str2.equals("expand")) {
            m2367e((String) hashMap.get(DatabaseOpenHelper.HISTORY_ROW_URL));
        } else if (str2.equals("create_calendar_event") && !C0745a.f1973J) {
            m2364c(hashMap);
        } else if (str2.equals("mail") && !C0745a.f1973J) {
            m2366d(hashMap);
        } else if (str2.equals("sms") && !C0745a.f1973J) {
            m2368e(hashMap);
        } else if (str2.equals("tel") && !C0745a.f1973J) {
            m2370f(hashMap);
        } else if (str2.equals("custom_event")) {
            m2372g(hashMap);
        } else if (str2.equals("launch_app") && !C0745a.f1973J) {
            m2373h(hashMap);
        } else if (str2.equals("check_app_presence")) {
            m2374i(hashMap);
        } else if (str2.equals("auto_play")) {
            m2375j(hashMap);
        } else if (str2.equals("save_screenshot")) {
            m2357a();
        } else if (str2.equals("social_post") && !C0745a.f1973J) {
            m2362b(hashMap);
        } else if (str2.equals("make_in_app_purchase") && !C0745a.f1973J) {
            m2359a(hashMap);
        }
        m2369f("adc_bridge.nativeCallComplete()");
    }

    void m2359a(HashMap hashMap) {
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        String g = m2371g((String) hashMap.get("product"));
        Integer.parseInt(m2371g((String) hashMap.get("quantity")));
        this.f2249b.finish();
        this.f2250c.f3945I.f1847n = g;
        this.f2250c.f3945I.f1859z = AdColonyIAPEngagement.END_CARD;
        C0745a.f1986W.m2133a(this.f2250c.f3945I);
    }

    void m2362b(HashMap hashMap) {
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g((String) hashMap.get(Method.TEXT));
        String g2 = m2371g((String) hashMap.get(DatabaseOpenHelper.HISTORY_ROW_URL));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        intent.putExtra("android.intent.extra.TEXT", g + " " + g2);
        this.f2249b.startActivity(Intent.createChooser(intent, "Share this post using..."));
    }

    void m2357a() {
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        String str = Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots/" + "AdColony_Screenshot_" + System.currentTimeMillis() + ".jpg";
        View rootView = this.f2248a.getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures");
        File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots");
        try {
            file.mkdir();
            file2.mkdir();
        } catch (Exception e) {
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str));
            createBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            MediaScannerConnection.scanFile(this.f2249b, new String[]{str}, null, new C07792(this));
        } catch (FileNotFoundException e2) {
            Toast.makeText(this.f2249b, "Error saving screenshot.", 0).show();
            C0777l.f2239a.m2349a("ADC [info] FileNotFoundException in MRAIDCommandTakeScreenshot");
        } catch (IOException e3) {
            Toast.makeText(this.f2249b, "Error saving screenshot.", 0).show();
            C0777l.f2239a.m2349a("ADC [info] IOException in MRAIDCommandTakeScreenshot");
        }
    }

    void m2361b(String str) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandSendADCEvent called with type: ").m2353b((Object) str);
        C0745a.m2144a(str, this.f2250c.f3945I);
    }

    void m2360b() {
        C0777l.f2239a.m2353b((Object) "ADC [info] MRAIDCommandClose called");
        this.f2249b.finish();
        C0745a.f1986W.m2133a(this.f2250c.f3945I);
    }

    void m2363c(String str) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandOpenStore called with item: ").m2353b((Object) str);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        try {
            this.f2249b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(m2371g(str))));
        } catch (Exception e) {
            Toast.makeText(this.f2249b, "Unable to open store.", 0).show();
        }
    }

    void m2365d(String str) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandOpen called with url: ").m2353b((Object) str);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g(str);
        if (g.startsWith("adcvideo")) {
            this.f2250c.m4678a(g.replace("adcvideo", HttpHost.DEFAULT_SCHEME_NAME));
        } else if (str.contains("youtube")) {
            try {
                this.f2249b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + g.substring(g.indexOf(118) + 2))));
            } catch (Exception e) {
                g = m2371g(str);
                if (g.contains("safari")) {
                    g = g.replace("safari", HttpHost.DEFAULT_SCHEME_NAME);
                }
                this.f2249b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(g)));
            }
        } else if (g.startsWith("browser")) {
            C0745a.m2144a("html5_interaction", this.f2250c.f3945I);
            this.f2249b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(g.replace("browser", HttpHost.DEFAULT_SCHEME_NAME))));
        } else {
            C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
            AdColonyBrowser.url = g;
            this.f2249b.startActivity(new Intent(this.f2249b, AdColonyBrowser.class));
        }
    }

    void m2367e(String str) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandExpand called with url: ").m2353b((Object) str);
        m2369f("adc_bridge.fireChangeEvent({state:'expanded'});");
    }

    void m2364c(HashMap hashMap) {
        Date parse;
        Date parse2;
        Date date;
        long time;
        long time2;
        long j;
        Intent putExtra;
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandCreateCalendarEvent called with parameters: ").m2353b((Object) hashMap);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ");
        DateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        DateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        String g = m2371g((String) hashMap.get("description"));
        m2371g((String) hashMap.get("location"));
        String g2 = m2371g((String) hashMap.get("start"));
        String g3 = m2371g((String) hashMap.get("end"));
        String g4 = m2371g((String) hashMap.get("summary"));
        String g5 = m2371g((String) hashMap.get("recurrence"));
        String str = StringUtils.EMPTY;
        String str2 = StringUtils.EMPTY;
        HashMap hashMap2 = new HashMap();
        String str3 = StringUtils.EMPTY;
        str3 = StringUtils.EMPTY;
        String str4 = "}";
        str3 = StringUtils.EMPTY;
        String replace = g5.replace("\"", r21).replace("{", r21).replace(replace, r21);
        if (!replace.equals(StringUtils.EMPTY)) {
            for (String str5 : replace.split(",")) {
                hashMap2.put(str5.substring(0, str5.indexOf(":")), str5.substring(str5.indexOf(":") + 1, str5.length()));
            }
            str = m2371g((String) hashMap2.get(ClientCookie.EXPIRES_ATTR));
            str2 = m2371g((String) hashMap2.get("frequency")).toUpperCase();
            C0777l.f2239a.m2349a("Calendar Recurrence - ").m2353b((Object) replace);
            C0777l.f2239a.m2349a("Calendar Recurrence - frequency = ").m2353b((Object) str2);
            C0777l.f2239a.m2349a("Calendar Recurrence - expires =  ").m2353b((Object) str);
        }
        g5 = str;
        str = str2;
        if (g4.equals(StringUtils.EMPTY)) {
            g4 = m2371g((String) hashMap.get("description"));
        }
        try {
            parse = simpleDateFormat.parse(g2);
            try {
                parse2 = simpleDateFormat.parse(g3);
            } catch (Exception e) {
                parse2 = null;
                if (parse == null) {
                    date = parse;
                } else {
                    try {
                        parse = simpleDateFormat2.parse(g2);
                        parse2 = simpleDateFormat2.parse(g3);
                        date = parse;
                    } catch (Exception e2) {
                        date = parse;
                    }
                }
                parse = simpleDateFormat.parse(g5);
                if (parse == null) {
                    try {
                        parse = simpleDateFormat3.parse(g5);
                    } catch (Exception e3) {
                    }
                }
                if (date == null) {
                    time = date.getTime();
                    time2 = parse2.getTime();
                    j = 0;
                    if (parse != null) {
                        j = (parse.getTime() - date.getTime()) / 1000;
                    }
                    if (!str.equals("DAILY")) {
                        j = (j / 86400) + 1;
                    } else if (!str.equals("WEEKLY")) {
                        j = (j / 604800) + 1;
                    } else if (!str.equals("MONTHLY")) {
                        j = (j / 2629800) + 1;
                    } else if (str.equals("YEARLY")) {
                        j = 0;
                    } else {
                        j = (j / 31557600) + 1;
                    }
                    if (replace.equals(StringUtils.EMPTY)) {
                        putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", "FREQ=" + str + ";" + "COUNT=" + j);
                        C0777l.f2239a.m2349a("Calendar Recurrence - count = ").m2351b((double) j);
                    } else {
                        putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2);
                    }
                    try {
                        this.f2249b.startActivity(putExtra);
                    } catch (Exception e4) {
                        Toast.makeText(this.f2249b, "Unable to create Calendar Event.", 0).show();
                        return;
                    }
                }
                Toast.makeText(this.f2249b, "Unable to create Calendar Event.", 0).show();
                return;
            }
        } catch (Exception e5) {
            parse = null;
            parse2 = null;
            if (parse == null) {
                parse = simpleDateFormat2.parse(g2);
                parse2 = simpleDateFormat2.parse(g3);
                date = parse;
            } else {
                date = parse;
            }
            parse = simpleDateFormat.parse(g5);
            if (parse == null) {
                parse = simpleDateFormat3.parse(g5);
            }
            if (date == null) {
                Toast.makeText(this.f2249b, "Unable to create Calendar Event.", 0).show();
                return;
            }
            time = date.getTime();
            time2 = parse2.getTime();
            j = 0;
            if (parse != null) {
                j = (parse.getTime() - date.getTime()) / 1000;
            }
            if (!str.equals("DAILY")) {
                j = (j / 86400) + 1;
            } else if (!str.equals("WEEKLY")) {
                j = (j / 604800) + 1;
            } else if (!str.equals("MONTHLY")) {
                j = (j / 2629800) + 1;
            } else if (str.equals("YEARLY")) {
                j = (j / 31557600) + 1;
            } else {
                j = 0;
            }
            if (replace.equals(StringUtils.EMPTY)) {
                putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", "FREQ=" + str + ";" + "COUNT=" + j);
                C0777l.f2239a.m2349a("Calendar Recurrence - count = ").m2351b((double) j);
            } else {
                putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2);
            }
            this.f2249b.startActivity(putExtra);
        }
        if (parse == null) {
            parse = simpleDateFormat2.parse(g2);
            parse2 = simpleDateFormat2.parse(g3);
            date = parse;
        } else {
            date = parse;
        }
        try {
            parse = simpleDateFormat.parse(g5);
        } catch (Exception e6) {
            parse = null;
        }
        if (parse == null) {
            parse = simpleDateFormat3.parse(g5);
        }
        if (date == null) {
            Toast.makeText(this.f2249b, "Unable to create Calendar Event.", 0).show();
            return;
        }
        time = date.getTime();
        time2 = parse2.getTime();
        j = 0;
        if (parse != null) {
            j = (parse.getTime() - date.getTime()) / 1000;
        }
        if (!str.equals("DAILY")) {
            j = (j / 86400) + 1;
        } else if (!str.equals("WEEKLY")) {
            j = (j / 604800) + 1;
        } else if (!str.equals("MONTHLY")) {
            j = (j / 2629800) + 1;
        } else if (str.equals("YEARLY")) {
            j = (j / 31557600) + 1;
        } else {
            j = 0;
        }
        if (replace.equals(StringUtils.EMPTY)) {
            putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", "FREQ=" + str + ";" + "COUNT=" + j);
            C0777l.f2239a.m2349a("Calendar Recurrence - count = ").m2351b((double) j);
        } else {
            putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, g4).putExtra("description", g).putExtra("beginTime", time).putExtra("endTime", time2);
        }
        this.f2249b.startActivity(putExtra);
    }

    void m2366d(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandMail called with parameters: ").m2353b((Object) hashMap);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g((String) hashMap.get("subject"));
        String g2 = m2371g((String) hashMap.get("body"));
        String g3 = m2371g((String) hashMap.get("to"));
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("plain/text");
            intent.putExtra("android.intent.extra.SUBJECT", g).putExtra("android.intent.extra.TEXT", g2).putExtra("android.intent.extra.EMAIL", new String[]{g3});
            this.f2249b.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.f2249b, "Unable to launch email client.", 0).show();
        }
    }

    void m2368e(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandSMS called with parameters: ").m2353b((Object) hashMap);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g((String) hashMap.get("to"));
        String g2 = m2371g((String) hashMap.get("body"));
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        try {
            this.f2249b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("sms:" + g)).putExtra("sms_body", g2));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.f2249b, "Failed to create sms.", 0).show();
        }
    }

    void m2370f(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandTel called with parameters: ").m2353b((Object) hashMap);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g((String) hashMap.get("number"));
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        try {
            this.f2249b.startActivity(new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + g)));
        } catch (Exception e) {
            Toast.makeText(this.f2249b, "Failed to dial number.", 0).show();
        }
    }

    void m2372g(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandSendCustomADCEvent called with parameters: ").m2353b((Object) hashMap);
        String str = "custom_event";
        C0745a.m2146a(str, "{\"event_type\":\"" + m2371g((String) hashMap.get("event_type")) + "\",\"ad_slot\":" + C0745a.f2001l.f2145e.f2599j + "}", this.f2250c.f3945I);
    }

    void m2373h(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandLaunchApp called with parameters: ").m2353b((Object) hashMap);
        C0745a.f1973J = true;
        this.f2251d.postDelayed(this.f2252e, 1000);
        String g = m2371g((String) hashMap.get("handle"));
        C0745a.m2146a("html5_interaction", this.f2254g, this.f2250c.f3945I);
        try {
            this.f2249b.startActivity(this.f2249b.getPackageManager().getLaunchIntentForPackage(g));
        } catch (Exception e) {
            Toast.makeText(this.f2249b, "Failed to launch external application.", 0).show();
        }
    }

    void m2374i(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandCheckAppPresence called with parameters: ").m2353b((Object) hashMap);
        String g = m2371g((String) hashMap.get("handle"));
        m2369f("adc_bridge.fireAppPresenceEvent('" + g + "'," + aa.m2176a(g) + ")");
    }

    void m2375j(HashMap hashMap) {
        C0777l.f2239a.m2349a("ADC [info] MRAIDCommandCheckAutoPlay called with parameters: ").m2353b((Object) hashMap);
    }

    void m2369f(String str) {
        this.f2248a.loadUrl("javascript:" + str);
    }

    String m2371g(String str) {
        if (str == null) {
            return StringUtils.EMPTY;
        }
        return URLDecoder.decode(str);
    }
}
