package com.google.android.gms.internal;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import com.google.android.gms.ads.internal.zzr;
import com.immersion.hapticmediasdk.HapticContentSDK;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

@zzhb
public final class zzbt {
    public static final zzbp<String> zzvA;
    public static final zzbp<String> zzvB;
    public static final zzbp<Boolean> zzvC;
    public static final zzbp<String> zzvD;
    public static final zzbp<Boolean> zzvE;
    public static final zzbp<String> zzvF;
    public static final zzbp<Boolean> zzvG;
    public static final zzbp<Boolean> zzvH;
    public static final zzbp<Boolean> zzvI;
    public static final zzbp<String> zzvJ;
    public static final zzbp<String> zzvK;
    public static final zzbp<String> zzvL;
    public static final zzbp<Boolean> zzvM;
    public static final zzbp<String> zzvN;
    public static final zzbp<Integer> zzvO;
    public static final zzbp<Integer> zzvP;
    public static final zzbp<Integer> zzvQ;
    public static final zzbp<Long> zzvR;
    public static final zzbp<Long> zzvS;
    public static final zzbp<Integer> zzvT;
    public static final zzbp<Boolean> zzvU;
    public static final zzbp<String> zzvV;
    public static final zzbp<Long> zzvW;
    public static final zzbp<String> zzvX;
    public static final zzbp<Boolean> zzvY;
    public static final zzbp<String> zzvZ;
    public static final zzbp<Integer> zzwA;
    public static final zzbp<String> zzwB;
    public static final zzbp<String> zzwC;
    public static final zzbp<Boolean> zzwD;
    public static final zzbp<Boolean> zzwE;
    public static final zzbp<String> zzwF;
    public static final zzbp<Integer> zzwG;
    public static final zzbp<Integer> zzwH;
    public static final zzbp<Integer> zzwI;
    public static final zzbp<String> zzwJ;
    public static final zzbp<Boolean> zzwK;
    public static final zzbp<Boolean> zzwL;
    public static final zzbp<Long> zzwM;
    public static final zzbp<Boolean> zzwN;
    public static final zzbp<Boolean> zzwO;
    public static final zzbp<Boolean> zzwP;
    public static final zzbp<Boolean> zzwQ;
    public static final zzbp<Boolean> zzwR;
    public static final zzbp<Boolean> zzwS;
    public static final zzbp<Boolean> zzwT;
    public static final zzbp<Long> zzwU;
    public static final zzbp<Boolean> zzwV;
    public static final zzbp<Boolean> zzwW;
    public static final zzbp<Long> zzwX;
    public static final zzbp<Long> zzwY;
    public static final zzbp<Boolean> zzwZ;
    public static final zzbp<Boolean> zzwa;
    public static final zzbp<Boolean> zzwb;
    public static final zzbp<Boolean> zzwc;
    public static final zzbp<String> zzwd;
    public static final zzbp<String> zzwe;
    public static final zzbp<String> zzwf;
    public static final zzbp<Boolean> zzwg;
    public static final zzbp<String> zzwh;
    public static final zzbp<Boolean> zzwi;
    public static final zzbp<Boolean> zzwj;
    public static final zzbp<Integer> zzwk;
    public static final zzbp<Integer> zzwl;
    public static final zzbp<Integer> zzwm;
    public static final zzbp<Integer> zzwn;
    public static final zzbp<Integer> zzwo;
    public static final zzbp<Boolean> zzwp;
    public static final zzbp<Boolean> zzwq;
    public static final zzbp<Long> zzwr;
    public static final zzbp<String> zzws;
    public static final zzbp<String> zzwt;
    public static final zzbp<Boolean> zzwu;
    public static final zzbp<Boolean> zzwv;
    public static final zzbp<Boolean> zzww;
    public static final zzbp<String> zzwx;
    public static final zzbp<Boolean> zzwy;
    public static final zzbp<Boolean> zzwz;
    public static final zzbp<Boolean> zzxa;
    public static final zzbp<Boolean> zzxb;
    public static final zzbp<Boolean> zzxc;
    public static final zzbp<Boolean> zzxd;
    public static final zzbp<Boolean> zzxe;
    public static final zzbp<Long> zzxf;
    public static final zzbp<Boolean> zzxg;

    /* renamed from: com.google.android.gms.internal.zzbt.1 */
    static class C05561 implements Callable<Void> {
        final /* synthetic */ Context zzxh;

        C05561(Context context) {
            this.zzxh = context;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() {
            zzr.zzbL().initialize(this.zzxh);
            return null;
        }
    }

    static {
        zzvA = zzbp.zza(0, "gads:sdk_core_experiment_id");
        zzvB = zzbp.zza(0, "gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
        zzvC = zzbp.zza(0, "gads:request_builder:singleton_webview", Boolean.valueOf(false));
        zzvD = zzbp.zza(0, "gads:request_builder:singleton_webview_experiment_id");
        zzvE = zzbp.zza(0, "gads:sdk_use_dynamic_module", Boolean.valueOf(false));
        zzvF = zzbp.zza(0, "gads:sdk_use_dynamic_module_experiment_id");
        zzvG = zzbp.zza(0, "gads:sdk_crash_report_enabled", Boolean.valueOf(false));
        zzvH = zzbp.zza(0, "gads:sdk_crash_report_full_stacktrace", Boolean.valueOf(false));
        zzvI = zzbp.zza(0, "gads:block_autoclicks", Boolean.valueOf(false));
        zzvJ = zzbp.zza(0, "gads:block_autoclicks_experiment_id");
        zzvK = zzbp.zzb(0, "gads:prefetch:experiment_id");
        zzvL = zzbp.zza(0, "gads:spam_app_context:experiment_id");
        zzvM = zzbp.zza(0, "gads:spam_app_context:enabled", Boolean.valueOf(false));
        zzvN = zzbp.zza(0, "gads:video_stream_cache:experiment_id");
        zzvO = zzbp.zza(0, "gads:video_stream_cache:limit_count", 5);
        zzvP = zzbp.zza(0, "gads:video_stream_cache:limit_space", (int) GravityCompat.RELATIVE_LAYOUT_DIRECTION);
        zzvQ = zzbp.zza(0, "gads:video_stream_exo_cache:buffer_size", (int) GravityCompat.RELATIVE_LAYOUT_DIRECTION);
        zzvR = zzbp.zza(0, "gads:video_stream_cache:limit_time_sec", 300);
        zzvS = zzbp.zza(0, "gads:video_stream_cache:notify_interval_millis", 1000);
        zzvT = zzbp.zza(0, "gads:video_stream_cache:connect_timeout_millis", (int) HapticContentSDK.f857b04440444044404440444);
        zzvU = zzbp.zza(0, "gads:video:metric_reporting_enabled", Boolean.valueOf(false));
        zzvV = zzbp.zza(0, "gads:video:metric_frame_hash_times", StringUtils.EMPTY);
        zzvW = zzbp.zza(0, "gads:video:metric_frame_hash_time_leniency", 500);
        zzvX = zzbp.zzb(0, "gads:spam_ad_id_decorator:experiment_id");
        zzvY = zzbp.zza(0, "gads:spam_ad_id_decorator:enabled", Boolean.valueOf(false));
        zzvZ = zzbp.zzb(0, "gads:looper_for_gms_client:experiment_id");
        zzwa = zzbp.zza(0, "gads:looper_for_gms_client:enabled", Boolean.valueOf(true));
        zzwb = zzbp.zza(0, "gads:sw_ad_request_service:enabled", Boolean.valueOf(true));
        zzwc = zzbp.zza(0, "gads:sw_dynamite:enabled", Boolean.valueOf(true));
        zzwd = zzbp.zza(0, "gad:mraid:url_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
        zzwe = zzbp.zza(0, "gad:mraid:url_expanded_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
        zzwf = zzbp.zza(0, "gad:mraid:url_interstitial", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
        zzwg = zzbp.zza(0, "gads:enabled_sdk_csi", Boolean.valueOf(false));
        zzwh = zzbp.zza(0, "gads:sdk_csi_server", "https://csi.gstatic.com/csi");
        zzwi = zzbp.zza(0, "gads:sdk_csi_write_to_file", Boolean.valueOf(false));
        zzwj = zzbp.zza(0, "gads:enable_content_fetching", Boolean.valueOf(true));
        zzwk = zzbp.zza(0, "gads:content_length_weight", 1);
        zzwl = zzbp.zza(0, "gads:content_age_weight", 1);
        zzwm = zzbp.zza(0, "gads:min_content_len", 11);
        zzwn = zzbp.zza(0, "gads:fingerprint_number", 10);
        zzwo = zzbp.zza(0, "gads:sleep_sec", 10);
        zzwp = zzbp.zza(0, "gad:app_index_enabled", Boolean.valueOf(true));
        zzwq = zzbp.zza(0, "gads:app_index:without_content_info_present:enabled", Boolean.valueOf(true));
        zzwr = zzbp.zza(0, "gads:app_index:timeout_ms", 1000);
        zzws = zzbp.zza(0, "gads:app_index:experiment_id");
        zzwt = zzbp.zza(0, "gads:kitkat_interstitial_workaround:experiment_id");
        zzwu = zzbp.zza(0, "gads:kitkat_interstitial_workaround:enabled", Boolean.valueOf(true));
        zzwv = zzbp.zza(0, "gads:interstitial_follow_url", Boolean.valueOf(true));
        zzww = zzbp.zza(0, "gads:interstitial_follow_url:register_click", Boolean.valueOf(true));
        zzwx = zzbp.zza(0, "gads:interstitial_follow_url:experiment_id");
        zzwy = zzbp.zza(0, "gads:analytics_enabled", Boolean.valueOf(true));
        zzwz = zzbp.zza(0, "gads:ad_key_enabled", Boolean.valueOf(false));
        zzwA = zzbp.zza(0, "gads:webview_cache_version", 0);
        zzwB = zzbp.zzb(0, "gads:pan:experiment_id");
        zzwC = zzbp.zza(0, "gads:native:engine_url", "//googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html");
        zzwD = zzbp.zza(0, "gads:ad_manager_creator:enabled", Boolean.valueOf(true));
        zzwE = zzbp.zza(1, "gads:interstitial_ad_pool:enabled", Boolean.valueOf(false));
        zzwF = zzbp.zza(1, "gads:interstitial_ad_pool:schema", "customTargeting");
        zzwG = zzbp.zza(1, "gads:interstitial_ad_pool:max_pools", 3);
        zzwH = zzbp.zza(1, "gads:interstitial_ad_pool:max_pool_depth", 2);
        zzwI = zzbp.zza(1, "gads:interstitial_ad_pool:time_limit_sec", 1200);
        zzwJ = zzbp.zza(1, "gads:interstitial_ad_pool:experiment_id");
        zzwK = zzbp.zza(0, "gads:log:verbose_enabled", Boolean.valueOf(false));
        zzwL = zzbp.zza(0, "gads:device_info_caching:enabled", Boolean.valueOf(true));
        zzwM = zzbp.zza(0, "gads:device_info_caching_expiry_ms:expiry", 300000);
        zzwN = zzbp.zza(0, "gads:gen204_signals:enabled", Boolean.valueOf(false));
        zzwO = zzbp.zza(0, "gads:webview:error_reporting_enabled", Boolean.valueOf(false));
        zzwP = zzbp.zza(0, "gads:adid_reporting:enabled", Boolean.valueOf(false));
        zzwQ = zzbp.zza(0, "gads:ad_settings_page_reporting:enabled", Boolean.valueOf(false));
        zzwR = zzbp.zza(0, "gads:adid_info_gmscore_upgrade_reporting:enabled", Boolean.valueOf(false));
        zzwS = zzbp.zza(0, "gads:request_pkg:enabled", Boolean.valueOf(true));
        zzwT = zzbp.zza(0, "gads:gmsg:disable_back_button:enabled", Boolean.valueOf(false));
        zzwU = zzbp.zza(0, "gads:network:cache_prediction_duration_s", 300);
        zzwV = zzbp.zza(0, "gads:mediation:dynamite_first:admobadapter", Boolean.valueOf(true));
        zzwW = zzbp.zza(0, "gads:mediation:dynamite_first:adurladapter", Boolean.valueOf(true));
        zzwX = zzbp.zza(0, "gads:ad_loader:timeout_ms", (long) DateUtils.MILLIS_PER_MINUTE);
        zzwY = zzbp.zza(0, "gads:rendering:timeout_ms", (long) DateUtils.MILLIS_PER_MINUTE);
        zzwZ = zzbp.zza(0, "gads:adshield:enable_adshield_instrumentation", Boolean.valueOf(false));
        zzxa = zzbp.zza(0, "gass:enabled", Boolean.valueOf(false));
        zzxb = zzbp.zza(0, "gass:enable_int_signal", Boolean.valueOf(true));
        zzxc = zzbp.zza(0, "gads:adid_notification:first_party_check:enabled", Boolean.valueOf(true));
        zzxd = zzbp.zza(0, "gads:edu_device_helper:enabled", Boolean.valueOf(true));
        zzxe = zzbp.zza(0, "gads:support_screen_shot", Boolean.valueOf(true));
        zzxf = zzbp.zza(0, "gads:js_flags:update_interval", TimeUnit.HOURS.toMillis(12));
        zzxg = zzbp.zza(0, "gads:custom_render:ping_on_ad_rendered", Boolean.valueOf(false));
    }

    public static void initialize(Context context) {
        zzjb.zzb(new C05561(context));
    }

    public static List<String> zzdr() {
        return zzr.zzbK().zzdr();
    }

    public static List<String> zzds() {
        return zzr.zzbK().zzds();
    }
}
