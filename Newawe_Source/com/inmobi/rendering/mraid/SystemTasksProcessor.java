package com.inmobi.rendering.mraid;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import com.Newawe.notification.NotificationChecker;
import com.Newawe.storage.DatabaseOpenHelper;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.plus.PlusShare.Builder;
import com.inmobi.ads.AdConfig.AdConfig;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.InMobiAdActivity.C0698b;
import com.inmobi.rendering.RenderView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Calendar;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpHost;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.mraid.n */
public class SystemTasksProcessor {
    private static final String f1679a;
    private RenderView f1680b;
    private SystemTasksProcessor f1681c;

    /* renamed from: com.inmobi.rendering.mraid.n.a */
    static final class SystemTasksProcessor extends Handler {
        private static final String f1677a;
        private WeakReference<RenderView> f1678b;

        static {
            f1677a = SystemTasksProcessor.class.getSimpleName();
        }

        public SystemTasksProcessor(Looper looper, RenderView renderView) {
            super(looper);
            this.f1678b = new WeakReference(renderView);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    String str = (String) message.obj;
                    String str2 = "broadcastEvent('vibrateComplete');";
                    RenderView renderView = (RenderView) this.f1678b.get();
                    if (renderView != null) {
                        renderView.m1642a(str, "broadcastEvent('vibrateComplete');");
                    }
                default:
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1677a, "Unknown message type. Ignoring ...");
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.n.1 */
    class SystemTasksProcessor implements C0698b {
        final /* synthetic */ Context f3878a;
        final /* synthetic */ int f3879b;
        final /* synthetic */ String f3880c;
        final /* synthetic */ String f3881d;
        final /* synthetic */ String f3882e;
        final /* synthetic */ String f3883f;
        final /* synthetic */ SystemTasksProcessor f3884g;

        SystemTasksProcessor(SystemTasksProcessor systemTasksProcessor, Context context, int i, String str, String str2, String str3, String str4) {
            this.f3884g = systemTasksProcessor;
            this.f3878a = context;
            this.f3879b = i;
            this.f3880c = str;
            this.f3881d = str2;
            this.f3882e = str3;
            this.f3883f = str4;
        }

        public void m4571a(int i, Intent intent) {
            Logger.m1440a(InternalLogLevel.INTERNAL, SystemTasksProcessor.f1679a, "Result code: " + i);
            if (this.f3879b == CalendarUtil.m1762a(this.f3878a)) {
                Logger.m1440a(InternalLogLevel.INTERNAL, SystemTasksProcessor.f1679a, "User cancelled the create calendar event");
                return;
            }
            ContentValues contentValues = new ContentValues();
            String str = this.f3880c;
            int i2 = -1;
            switch (str.hashCode()) {
                case -1320822226:
                    if (str.equals("tentative")) {
                        i2 = 0;
                        break;
                    }
                    break;
                case -804109473:
                    if (str.equals("confirmed")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 476588369:
                    if (str.equals("cancelled")) {
                        i2 = 2;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case DurationDV.DURATION_TYPE /*0*/:
                    contentValues.put("eventStatus", Integer.valueOf(0));
                    break;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    contentValues.put("eventStatus", Integer.valueOf(1));
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    contentValues.put("eventStatus", Integer.valueOf(2));
                    break;
            }
            ContentResolver contentResolver = this.f3878a.getContentResolver();
            contentResolver.update(ContentUris.withAppendedId(Events.CONTENT_URI, (long) CalendarUtil.m1762a(this.f3878a)), contentValues, null, null);
            if (this.f3881d != null && !StringUtils.EMPTY.equals(this.f3881d)) {
                try {
                    if (this.f3881d.startsWith("+")) {
                        i2 = Integer.parseInt(this.f3881d.substring(1)) / DateUtils.MILLIS_IN_MINUTE;
                    } else {
                        i2 = Integer.parseInt(this.f3881d) / DateUtils.MILLIS_IN_MINUTE;
                    }
                } catch (NumberFormatException e) {
                    Calendar b = CalendarUtil.m1766b(this.f3881d);
                    if (b == null) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, SystemTasksProcessor.f1679a, "Invalid reminder date provided. date string: " + this.f3881d);
                        return;
                    }
                    i2 = ((int) (b.getTimeInMillis() - CalendarUtil.m1766b(this.f3882e).getTimeInMillis())) / DateUtils.MILLIS_IN_MINUTE;
                    if (i2 > 0) {
                        this.f3884g.f1680b.m1645a(this.f3883f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                        return;
                    }
                }
                i2 = -i2;
                contentResolver.delete(Reminders.CONTENT_URI, "event_id=?", new String[]{CalendarUtil.m1762a(this.f3878a) + StringUtils.EMPTY});
                if (i2 < 0) {
                    this.f3884g.f1680b.m1645a(this.f3883f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                    return;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Integer.valueOf(CalendarUtil.m1762a(this.f3878a)));
                contentValues2.put(OutputKeys.METHOD, Integer.valueOf(1));
                contentValues2.put("minutes", Integer.valueOf(i2));
                contentResolver.insert(Reminders.CONTENT_URI, contentValues2);
            }
        }
    }

    static {
        f1679a = SystemTasksProcessor.class.getSimpleName();
    }

    public SystemTasksProcessor(RenderView renderView) {
        this.f1680b = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.f1681c = new SystemTasksProcessor(handlerThread.getLooper(), renderView);
    }

    @TargetApi(14)
    public void m1844a(String str, Context context, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        int a = CalendarUtil.m1762a(context);
        Calendar b = CalendarUtil.m1766b(str3);
        if (b == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Invalid event start date provided. date string: " + str3);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Event start: " + b.get(1) + "-" + b.get(2) + "-" + b.get(5));
        Calendar b2 = CalendarUtil.m1766b(str4);
        if (b2 == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Invalid event end date provided. date string: " + str4);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Event end: " + b2.get(1) + "-" + b2.get(2) + "-" + b2.get(5));
        Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("calendar_id", str2).putExtra("beginTime", b.getTimeInMillis()).putExtra("endTime", b2.getTimeInMillis()).putExtra("allDay", false).putExtra(DatabaseOpenHelper.HISTORY_ROW_TITLE, str6).putExtra("eventLocation", str5).putExtra("description", str7);
        if (str9.equals("transparent")) {
            putExtra.putExtra("availability", 1);
        } else {
            if (str9.equals("opaque")) {
                putExtra.putExtra("availability", 0);
            }
        }
        String a2 = m1837a(str10);
        if (a2.length() != 0) {
            putExtra.putExtra("rrule", a2);
        }
        int a3 = InMobiAdActivity.m1581a(putExtra, new SystemTasksProcessor(this, context, a, str8, str11, str3, str));
        Intent intent = new Intent(context, InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", C0302R.styleable.Theme_checkedTextViewStyle);
        intent.putExtra("id", a3);
        context.startActivity(intent);
    }

    public void m1842a(String str, Context context, int i, String str2, String str3, String str4) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0 || !str3.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str4 == null || str4.length() == 0 || !str4.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || !str4.endsWith(".jpg")) {
            this.f1680b.m1645a(str, "Attempting to share with null/empty/invalid parameters", "postToSocial");
            return;
        }
        Intent intent = null;
        String str5;
        switch (i) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                str5 = StringUtils.EMPTY;
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (m1839b()) {
                    intent = new Builder(context).setType(HTTP.PLAIN_TEXT_TYPE).setText(str2 + " " + str3 + " " + str4).setContentUrl(Uri.parse(str4)).getIntent();
                    break;
                }
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                str5 = str2 + " " + str3 + " " + str4;
                intent = new Intent();
                intent.setType(HTTP.PLAIN_TEXT_TYPE);
                intent.setPackage("com.twitter.android");
                intent.putExtra("android.intent.extra.TEXT", str5);
                break;
            default:
                this.f1680b.m1645a(str, "Unsupported type of social network", "postToSocial");
                return;
        }
        if (intent != null) {
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                m1838a(context, i, str2, str3, str4);
                return;
            }
        }
        m1838a(context, i, str2, str3, str4);
    }

    private boolean m1839b() {
        try {
            return GooglePlayServicesUtil.isGooglePlayServicesAvailable(SdkContext.m1258b()) == 0;
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            return false;
        }
    }

    private void m1838a(Context context, int i, String str, String str2, String str3) {
        String str4 = str + " " + str2 + " " + str3;
        String str5 = null;
        switch (i) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                try {
                    str5 = "https://www.facebook.com/dialog/feed?app_id=181821551957328&link=" + URLEncoder.encode(str2, HTTP.UTF_8) + "&picture=" + URLEncoder.encode(str3, HTTP.UTF_8) + "&name=&description=" + URLEncoder.encode(str, HTTP.UTF_8) + "&redirect_uri=" + URLEncoder.encode(str2, HTTP.UTF_8);
                    break;
                } catch (UnsupportedEncodingException e) {
                    return;
                }
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                str5 = "https://m.google.com/app/plus/x/?v=compose&content=" + URLEncoder.encode(str4, HTTP.UTF_8);
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                str5 = "http://twitter.com/home?status=" + URLEncoder.encode(str4, HTTP.UTF_8);
                break;
        }
        if (str5 != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str5));
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e2) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, e2.getMessage());
                return;
            }
        }
        Intent intent2 = new Intent();
        intent2.setType(HTTP.PLAIN_TEXT_TYPE);
        intent2.putExtra("android.intent.extra.TEXT", str4);
        try {
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e22) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, e22.getMessage());
        }
    }

    public void m1841a(String str, Context context) {
        ((Vibrator) context.getSystemService("vibrator")).vibrate(2000);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = str;
        this.f1681c.sendMessageDelayed(obtain, 2000);
    }

    public void m1843a(String str, Context context, String str2, int i) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        String replaceAll = str2.replaceAll("\\[", StringUtils.EMPTY).replaceAll("\\]", StringUtils.EMPTY);
        if (replaceAll == null || StringUtils.EMPTY.equals(replaceAll.trim())) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Vibration canceled");
            m1840a(context);
            return;
        }
        String[] split = replaceAll.split(",");
        int length = split.length;
        AdConfig renderingConfig = this.f1680b.getRenderingConfig();
        if (length > renderingConfig.m1179f()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "vibration pattern exceeds max length. Will be truncated to max " + renderingConfig.m1179f());
            length = renderingConfig.m1179f();
        }
        long[] jArr = new long[length];
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            try {
                jArr[i3] = Long.parseLong(split[i3]);
                if (jArr[i3] > ((long) (renderingConfig.m1178e() * DateUtils.MILLIS_IN_SECOND))) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "vibration duration exceeds max. Will only vibrate for max " + renderingConfig.m1178e() + "ms");
                    jArr[i3] = (long) renderingConfig.m1178e();
                }
                if (jArr[i3] < 0) {
                    this.f1680b.m1645a(str, "Negative duration not allowed in vibration .", "vibrate");
                }
                i2 = (int) (((long) i2) + jArr[i3]);
                i3++;
            } catch (NumberFormatException e) {
                this.f1680b.m1645a(str, "Invalid values of pattern in vibration .", "vibrate");
                return;
            }
        }
        if (jArr.length != 0) {
            m1840a(context);
            vibrator.vibrate(jArr, i);
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = str;
            this.f1681c.sendMessageDelayed(obtain, (long) i2);
        }
    }

    public void m1840a(Context context) {
        if (this.f1681c != null && this.f1681c.hasMessages(1)) {
            this.f1681c.removeMessages(1);
            ((Vibrator) context.getSystemService("vibrator")).cancel();
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Canceling any pending/ongoing vibrate requests");
        }
    }

    private String m1837a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str.length() == 0) {
            return StringUtils.EMPTY;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("frequency");
            if (optString == null || StringUtils.EMPTY.equals(optString)) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Error Parsing recurrence string. Frequency supplied is null");
                return StringUtils.EMPTY;
            } else if ("daily".equals(optString) || "weekly".equals(optString) || "monthly".equals(optString) || "yearly".equals(optString)) {
                stringBuilder.append("freq=").append(optString).append(";");
                String optString2 = jSONObject.optString(NotificationChecker.INTERVAL_KEY);
                if (!(optString2 == null || StringUtils.EMPTY.equals(optString2))) {
                    stringBuilder.append("interval=").append(Integer.parseInt(optString2)).append(";");
                }
                optString2 = CalendarUtil.m1763a(jSONObject.optString(ClientCookie.EXPIRES_ATTR));
                if (optString2 != null) {
                    stringBuilder.append("until=").append(optString2.replace("+", "Z+").replace("-", "Z-")).append(";");
                }
                if (optString.equals("weekly")) {
                    optString2 = CalendarUtil.m1764a(jSONObject.optJSONArray("daysInWeek"));
                    if (optString2 != null) {
                        stringBuilder.append("byday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("monthly")) {
                    optString2 = CalendarUtil.m1765a(jSONObject.optJSONArray("daysInMonth"), -31, 31);
                    if (optString2 != null) {
                        stringBuilder.append("bymonthday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("yearly")) {
                    optString2 = CalendarUtil.m1765a(jSONObject.optJSONArray("daysInYear"), -366, 366);
                    if (optString2 != null) {
                        stringBuilder.append("byyearday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("monthly")) {
                    optString2 = CalendarUtil.m1765a(jSONObject.optJSONArray("weeksInMonth"), -4, 4);
                    if (optString2 != null) {
                        stringBuilder.append("byweekno=").append(optString2).append(";");
                    }
                }
                if (optString.equals("yearly")) {
                    String a = CalendarUtil.m1765a(jSONObject.optJSONArray("monthsInYear"), 1, 12);
                    if (a != null) {
                        stringBuilder.append("bymonth=").append(a).append(";");
                    }
                }
                return stringBuilder.toString();
            } else {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Error Parsing recurrence string. Invalid Frequency supplied");
                return StringUtils.EMPTY;
            }
        } catch (JSONException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1679a, "Error Parsing recurrence string" + e.toString());
            return StringUtils.EMPTY;
        }
    }
}
