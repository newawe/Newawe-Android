package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;

public class as extends ar {
    private aw f4234a;
    private Button f4235b;
    private bh f4236c;
    private C0321a f4237d;

    /* renamed from: com.chartboost.sdk.impl.as.1 */
    class C03891 implements OnClickListener {
        final /* synthetic */ as f450a;

        C03891(as asVar) {
            this.f450a = asVar;
        }

        public void onClick(View v) {
            this.f450a.m5104c();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.as.2 */
    class C03902 implements OnCompletionListener {
        final /* synthetic */ as f451a;

        C03902(as asVar) {
            this.f451a = asVar;
        }

        public void onCompletion(MediaPlayer arg0) {
            bi.m700a(false, this.f451a.f4236c);
        }
    }

    public as(aw awVar, Context context) {
        super(awVar, context);
        this.f4234a = awVar;
        this.f4235b = new Button(context);
        this.f4235b.setTextColor(-14571545);
        this.f4235b.setText("Preview");
        this.f4235b.setOnClickListener(new C03891(this));
        addView(this.f4235b, 2);
    }

    public void m5105a(C0321a c0321a, int i) {
        super.m3892a(c0321a, i);
        this.f4237d = c0321a;
    }

    private void m5104c() {
        CBLogging.m79c(this, "play the video");
        if (this.f4236c == null) {
            this.f4236c = new bh(getContext());
            this.f4234a.m479e().addView(this.f4236c, new LayoutParams(-1, -1));
            this.f4236c.setVisibility(8);
        }
        this.f4236c.m695a().m684a(new C03902(this));
        bi.m700a(true, this.f4236c);
        this.f4236c.m695a().m681a();
    }
}
