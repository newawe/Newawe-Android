package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.C0485R;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;
import mf.org.apache.xerces.xinclude.XIncludeHandler;

@zzhb
public class zzfp extends zzfs {
    private final Context mContext;
    private final Map<String, String> zzxA;

    /* renamed from: com.google.android.gms.internal.zzfp.1 */
    class C05961 implements OnClickListener {
        final /* synthetic */ String zzDr;
        final /* synthetic */ String zzDs;
        final /* synthetic */ zzfp zzDt;

        C05961(zzfp com_google_android_gms_internal_zzfp, String str, String str2) {
            this.zzDt = com_google_android_gms_internal_zzfp;
            this.zzDr = str;
            this.zzDs = str2;
        }

        public void onClick(DialogInterface dialog, int which) {
            try {
                ((DownloadManager) this.zzDt.mContext.getSystemService("download")).enqueue(this.zzDt.zzf(this.zzDr, this.zzDs));
            } catch (IllegalStateException e) {
                this.zzDt.zzam("Could not store picture.");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzfp.2 */
    class C05972 implements OnClickListener {
        final /* synthetic */ zzfp zzDt;

        C05972(zzfp com_google_android_gms_internal_zzfp) {
            this.zzDt = com_google_android_gms_internal_zzfp;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.zzDt.zzam("User canceled the download.");
        }
    }

    public zzfp(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
        super(com_google_android_gms_internal_zzjp, "storePicture");
        this.zzxA = map;
        this.mContext = com_google_android_gms_internal_zzjp.zzhP();
    }

    public void execute() {
        if (this.mContext == null) {
            zzam("Activity context is not available");
        } else if (zzr.zzbC().zzM(this.mContext).zzdl()) {
            String str = (String) this.zzxA.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzam("Image url cannot be empty.");
            } else if (URLUtil.isValidUrl(str)) {
                String zzal = zzal(str);
                if (zzr.zzbC().zzaE(zzal)) {
                    Builder zzL = zzr.zzbC().zzL(this.mContext);
                    zzL.setTitle(zzr.zzbF().zzd(C0485R.string.store_picture_title, "Save image"));
                    zzL.setMessage(zzr.zzbF().zzd(C0485R.string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
                    zzL.setPositiveButton(zzr.zzbF().zzd(C0485R.string.accept, XIncludeHandler.HTTP_ACCEPT), new C05961(this, str, zzal));
                    zzL.setNegativeButton(zzr.zzbF().zzd(C0485R.string.decline, "Decline"), new C05972(this));
                    zzL.create().show();
                    return;
                }
                zzam("Image type not recognized: " + zzal);
            } else {
                zzam("Invalid image url: " + str);
            }
        } else {
            zzam("Feature is not supported by the device.");
        }
    }

    String zzal(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    Request zzf(String str, String str2) {
        Request request = new Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        zzr.zzbE().zza(request);
        return request;
    }
}
