package com.startapp.android.publish.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.BannerInterface;
import com.startapp.android.publish.banner.BannerListener;
import com.startapp.android.publish.banner.BannerOptions;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.adrules.AdRulesResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class Banner3D extends com.startapp.android.publish.banner.StartAppSDK implements AdEventListener, BannerInterface {
    private static final String TAG = "Banner3D";
    private static final int TIMEOUT_RESTORE = 200;
    private AdPreferences adPreferences;
    private boolean addedDisplayEvent;
    private com.startapp.android.publish.p028a.StartAppSDK ads;
    private List<AdDetails> adsItems;
    private boolean animation;
    private boolean attachedToWindow;
    private Camera camera;
    private int currentBannerIndex;
    private boolean defaultLoad;
    private List<Banner3DFace> faces;
    private boolean firstRotation;
    private boolean firstRotationFinished;
    private BannerListener listener;
    private boolean loaded;
    private boolean loading;
    private Runnable mAutoRotation;
    private Matrix matrix;
    private BannerOptions options;
    private com.startapp.android.publish.adinformation.StartAppSDK overrides;
    private Paint paint;
    private boolean rotating;
    private float rotation;
    private boolean rotationEnabled;
    private float startY;
    private boolean touchDown;
    private boolean visible;

    /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3D.1 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            if (Banner3D.this.loaded && Banner3D.this.faces.size() != 0) {
                if (Banner3D.this.visible && Banner3D.this.isShown() && Banner3D.this.drawn) {
                    ((Banner3DFace) Banner3D.this.faces.get(Banner3D.this.getCurrentBannerIndex())).m4760a(Banner3D.this.getContext());
                    if (!Banner3D.this.addedDisplayEvent) {
                        Banner3D.this.addedDisplayEvent = true;
                        Banner3D.this.addDisplayEventOnLoad();
                    }
                }
                if (Banner3D.this.rotationEnabled) {
                    Banner3D.this.rotate((float) ((!Banner3D.this.firstRotationFinished ? Banner3D.this.options.m2644q() : 1) * Banner3D.this.getBannerOptions().m2629b()));
                }
                if (Banner3D.this.rotation <= ((float) (90 - Banner3D.this.getBannerOptions().m2629b())) || Banner3D.this.rotation >= ((float) (Banner3D.this.getBannerOptions().m2629b() + 90)) || Banner3D.this.firstRotation) {
                    Banner3D.this.postDelayed(this, (long) Banner3D.this.getBannerOptions().m2627a());
                    Banner3D.this.rotating = true;
                } else {
                    if (Banner3D.this.attachedToWindow) {
                        Banner3D.this.postDelayed(this, (long) Banner3D.this.getBannerOptions().m2630c());
                    }
                    Banner3D.this.rotating = false;
                }
                if (Banner3D.this.getNextBannerIndex() == 0) {
                    Banner3D.this.firstRotation = false;
                }
            }
        }
    }

    /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3D.2 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            if (Banner3D.this.defaultLoad) {
                Banner3D.this.setHardwareAcceleration(Banner3D.this.adPreferences);
                Banner3D.this.loadBanner();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3D.3 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            Banner3D.this.rotationEnabled = true;
        }
    }

    /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3D.4 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            Banner3D.this.loadBanners(Banner3D.this.adsItems, false);
        }
    }

    /* compiled from: StartAppSDK */
    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public AdRulesResult adRulesResult;
        public boolean bDefaultLoad;
        public boolean bIsVisible;
        private int currentImage;
        private AdDetails[] details;
        public Banner3DFace[] faces;
        private int firstRotation;
        private int firstRotationFinished;
        public boolean loaded;
        public boolean loading;
        public BannerOptions options;
        public com.startapp.android.publish.adinformation.StartAppSDK overrides;
        private float rotation;

        /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3D.SavedState.1 */
        static class StartAppSDK implements Creator<SavedState> {
            StartAppSDK() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void setCurrentImage(int current) {
            this.currentImage = current;
        }

        public int getCurrentImage() {
            return this.currentImage;
        }

        public void setRotation(float deg) {
            this.rotation = deg;
        }

        public float getRotation() {
            return this.rotation;
        }

        public boolean isFirstRotation() {
            return this.firstRotation == 1;
        }

        public void setFirstRotation(boolean firstRotation) {
            this.firstRotation = firstRotation ? 1 : 0;
        }

        public boolean isFirstRotationFinished() {
            return this.firstRotationFinished == 1;
        }

        public void setFirstRotationFinished(boolean firstRotationFinished) {
            this.firstRotationFinished = firstRotationFinished ? 1 : 0;
        }

        public void setDetails(List<AdDetails> details) {
            this.details = new AdDetails[details.size()];
            for (int i = 0; i < details.size(); i++) {
                this.details[i] = (AdDetails) details.get(i);
            }
        }

        public List<AdDetails> getDetails() {
            return Arrays.asList(this.details);
        }

        private SavedState(Parcel in) {
            super(in);
            if (in.readInt() == 1) {
                this.bIsVisible = true;
                this.currentImage = in.readInt();
                this.rotation = in.readFloat();
                this.firstRotation = in.readInt();
                this.firstRotationFinished = in.readInt();
                Object readParcelableArray = in.readParcelableArray(AdDetails.class.getClassLoader());
                if (readParcelableArray != null) {
                    this.details = new AdDetails[readParcelableArray.length];
                    System.arraycopy(readParcelableArray, 0, this.details, 0, readParcelableArray.length);
                }
                int readInt = in.readInt();
                this.loaded = false;
                if (readInt == 1) {
                    this.loaded = true;
                }
                readInt = in.readInt();
                this.loading = false;
                if (readInt == 1) {
                    this.loading = true;
                }
                readInt = in.readInt();
                this.bDefaultLoad = false;
                if (readInt == 1) {
                    this.bDefaultLoad = true;
                }
                int readInt2 = in.readInt();
                if (readInt2 > 0) {
                    this.faces = new Banner3DFace[readInt2];
                    for (readInt = 0; readInt < readInt2; readInt++) {
                        this.faces[readInt] = (Banner3DFace) in.readParcelable(Banner3DFace.class.getClassLoader());
                    }
                }
                this.overrides = (com.startapp.android.publish.adinformation.StartAppSDK) in.readSerializable();
                this.options = (BannerOptions) in.readSerializable();
                this.adRulesResult = (AdRulesResult) in.readSerializable();
                return;
            }
            this.bIsVisible = false;
        }

        public void writeToParcel(Parcel out, int flags) {
            int i = 1;
            int i2 = 0;
            super.writeToParcel(out, flags);
            if (this.bIsVisible) {
                int i3;
                out.writeInt(1);
                out.writeInt(this.currentImage);
                out.writeFloat(this.rotation);
                out.writeInt(this.firstRotation);
                out.writeInt(this.firstRotationFinished);
                out.writeParcelableArray(this.details, flags);
                if (this.loaded) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                out.writeInt(i3);
                if (this.loading) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                out.writeInt(i3);
                if (!this.bDefaultLoad) {
                    i = 0;
                }
                out.writeInt(i);
                out.writeInt(this.faces.length);
                while (i2 < this.faces.length) {
                    out.writeParcelable(this.faces[i2], flags);
                    i2++;
                }
                out.writeSerializable(this.overrides);
                out.writeSerializable(this.options);
                out.writeSerializable(this.adRulesResult);
                return;
            }
            out.writeInt(0);
        }

        static {
            CREATOR = new StartAppSDK();
        }

        public int describeContents() {
            return 0;
        }
    }

    public Banner3D(Context context) {
        this(context, true, null);
    }

    public Banner3D(Context context, AdPreferences adPreferences) {
        this(context, true, adPreferences);
    }

    public Banner3D(Context context, BannerListener listener) {
        this(context, true, null);
        setBannerListener(listener);
    }

    public Banner3D(Context context, AdPreferences adPreferences, BannerListener listener) {
        this(context, true, adPreferences);
        setBannerListener(listener);
    }

    public Banner3D(Context context, boolean defaultLoad) {
        this(context, defaultLoad, new AdPreferences());
    }

    public Banner3D(Context context, boolean defaultLoad, AdPreferences adPreferences) {
        super(context);
        this.camera = null;
        this.matrix = null;
        this.paint = null;
        this.rotation = 45.0f;
        this.startY = 0.0f;
        this.rotationEnabled = true;
        this.rotating = false;
        this.firstRotation = true;
        this.firstRotationFinished = false;
        this.addedDisplayEvent = false;
        this.touchDown = false;
        this.animation = false;
        this.visible = true;
        this.defaultLoad = true;
        this.loaded = false;
        this.loading = false;
        this.attachedToWindow = false;
        this.faces = new ArrayList();
        this.currentBannerIndex = 0;
        this.mAutoRotation = new StartAppSDK();
        this.defaultLoad = defaultLoad;
        this.adPreferences = adPreferences;
        init();
    }

    public Banner3D(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.camera = null;
        this.matrix = null;
        this.paint = null;
        this.rotation = 45.0f;
        this.startY = 0.0f;
        this.rotationEnabled = true;
        this.rotating = false;
        this.firstRotation = true;
        this.firstRotationFinished = false;
        this.addedDisplayEvent = false;
        this.touchDown = false;
        this.animation = false;
        this.visible = true;
        this.defaultLoad = true;
        this.loaded = false;
        this.loading = false;
        this.attachedToWindow = false;
        this.faces = new ArrayList();
        this.currentBannerIndex = 0;
        this.mAutoRotation = new StartAppSDK();
        init();
    }

    public Banner3D(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.camera = null;
        this.matrix = null;
        this.paint = null;
        this.rotation = 45.0f;
        this.startY = 0.0f;
        this.rotationEnabled = true;
        this.rotating = false;
        this.firstRotation = true;
        this.firstRotationFinished = false;
        this.addedDisplayEvent = false;
        this.touchDown = false;
        this.animation = false;
        this.visible = true;
        this.defaultLoad = true;
        this.loaded = false;
        this.loading = false;
        this.attachedToWindow = false;
        this.faces = new ArrayList();
        this.currentBannerIndex = 0;
        this.mAutoRotation = new StartAppSDK();
        init();
    }

    public void hideBanner() {
        this.visible = false;
        setVisibility(8);
    }

    public void showBanner() {
        this.visible = true;
        setVisibility(0);
    }

    private BannerOptions getBannerOptions() {
        return this.options;
    }

    private void addAdInformationLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(getFaceWidth(), getFaceHeight());
        layoutParams.addRule(13);
        int faceStartLeft = getFaceStartLeft();
        layoutParams.rightMargin = faceStartLeft;
        layoutParams.leftMargin = faceStartLeft;
        faceStartLeft = getFaceStartTop();
        layoutParams.topMargin = faceStartLeft;
        layoutParams.bottomMargin = faceStartLeft;
        addView(relativeLayout, layoutParams);
        new com.startapp.android.publish.adinformation.StartAppSDK(getContext(), com.startapp.android.publish.adinformation.StartAppSDK.StartAppSDK.SMALL, Placement.INAPP_BANNER, this.overrides).m2548a(relativeLayout);
    }

    private void init() {
        if (isInEditMode()) {
            initDebug();
        } else {
            initRuntime();
        }
    }

    private void initRuntime() {
        if (!this.loading) {
            this.options = MetaData.getInstance().getBannerOptionsCopy();
            this.adsItems = new ArrayList();
            if (this.adPreferences == null) {
                this.adPreferences = new AdPreferences();
            }
            this.overrides = com.startapp.android.publish.adinformation.StartAppSDK.m2551a();
            this.faces = new ArrayList();
            this.loading = true;
            setBackgroundColor(0);
            new Handler().postDelayed(new StartAppSDK(), 200);
        }
    }

    private void initDebug() {
        setMinimumWidth(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) HttpStatus.SC_MULTIPLE_CHOICES));
        setMinimumHeight(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), 50));
        setBackgroundColor(Color.rgb(169, 169, 169));
        View textView = new TextView(getContext());
        textView.setText("StartApp Banner3D");
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(textView, layoutParams);
    }

    protected void reload() {
        this.loaded = false;
        this.loading = true;
        this.animation = false;
        this.rotationEnabled = true;
        this.firstRotation = true;
        this.firstRotationFinished = false;
        this.addedDisplayEvent = false;
        this.drawn = false;
        this.adRulesResult = null;
        this.faces = new ArrayList();
        this.ads = new com.startapp.android.publish.p028a.StartAppSDK(getContext(), getOffset());
        if (this.adPreferences == null) {
            this.adPreferences = new AdPreferences();
        }
        this.ads.load(this.adPreferences, this);
    }

    private void loadBanners(List<AdDetails> adsItems, boolean sendCallback) {
        this.adsItems = adsItems;
        if (adsItems != null) {
            com.startapp.android.publish.banner.StartAppSDK startAppSDK = new com.startapp.android.publish.banner.StartAppSDK();
            if (setBannerSize(startAppSDK)) {
                setMinimumWidth(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.options.m2631d()));
                setMinimumHeight(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.options.m2632e()));
                if (getLayoutParams() != null && getLayoutParams().width == -1) {
                    setMinimumWidth(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), startAppSDK.m2645a()));
                }
                if (getLayoutParams() != null && getLayoutParams().height == -1) {
                    setMinimumHeight(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), startAppSDK.m2648b()));
                }
                if (getLayoutParams() != null) {
                    if (getLayoutParams().width > 0) {
                        setMinimumWidth(getLayoutParams().width);
                    }
                    if (getLayoutParams().height > 0) {
                        setMinimumHeight(getLayoutParams().height);
                    }
                }
                initFaces(adsItems);
                addAdInformationLayout();
                if (this.paint == null) {
                    this.paint = new Paint();
                    this.paint.setAntiAlias(true);
                    this.paint.setFilterBitmap(true);
                }
                if (!this.animation) {
                    this.animation = true;
                    startRotation();
                }
                if (this.visible) {
                    setVisibility(0);
                }
                if (this.listener != null && sendCallback) {
                    this.listener.onReceiveAd(this);
                    return;
                }
                return;
            }
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 6, "Banner3DError in banner screen size");
            setVisibility(8);
            if (this.listener != null && sendCallback) {
                this.listener.onFailedToReceiveAd(this);
            }
        } else if (this.listener != null && sendCallback) {
            this.listener.onFailedToReceiveAd(this);
        }
    }

    private void initFaces(List<AdDetails> adsItems) {
        if (shouldCreateFaces()) {
            createFaces(adsItems);
        } else {
            initFacesViews();
        }
    }

    private void initFacesViews() {
        for (Banner3DFace a : this.faces) {
            a.m4761a(getContext(), getBannerOptions(), this);
        }
    }

    private boolean shouldCreateFaces() {
        return this.faces == null || this.faces.size() == 0;
    }

    private void createFaces(List<AdDetails> adsItems) {
        this.faces = new ArrayList();
        for (AdDetails adDetails : adsItems) {
            this.faces.add(new Banner3DFace(getContext(), this, adDetails, getBannerOptions(), new com.startapp.android.publish.p022h.StartAppSDK(getAdTag())));
        }
        this.currentBannerIndex = 0;
    }

    private boolean setBannerSize(com.startapp.android.publish.banner.StartAppSDK bannerSize) {
        return Banner3DSize.m2654a(getContext(), getParent(), getBannerOptions(), this, bannerSize);
    }

    private Bitmap getCurrentBitmap() {
        return ((Banner3DFace) this.faces.get(getCurrentBannerIndex())).m4763b();
    }

    private Bitmap getPreviousBitmap() {
        return ((Banner3DFace) this.faces.get(((getCurrentBannerIndex() - 1) + this.faces.size()) % this.faces.size())).m4763b();
    }

    private int getCurrentBannerIndex() {
        return this.currentBannerIndex;
    }

    private int getNextBannerIndex() {
        return (this.currentBannerIndex + 1) % getTotalBaners();
    }

    private int getTotalBaners() {
        return this.faces.size();
    }

    private void nextBanner() {
        this.currentBannerIndex = (this.currentBannerIndex + 1) % getTotalBaners();
    }

    private void prevBanner() {
        this.currentBannerIndex = ((this.currentBannerIndex - 1) + getTotalBaners()) % getTotalBaners();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!(this.drawn || this.loading)) {
            this.drawn = true;
            startRotation();
        }
        if (!isInEditMode() && this.visible && !shouldCreateFaces()) {
            drawFrame(canvas);
        }
    }

    private void drawFrame(Canvas canvas) {
        try {
            int faceWidth = getFaceWidth();
            int faceHeight = getFaceHeight();
            int faceStartLeft = getFaceStartLeft();
            int faceStartTop = getFaceStartTop();
            float m = this.options.m2640m() + (((float) Math.pow((double) (Math.abs(this.rotation - 45.0f) / 45.0f), (double) this.options.m2641n())) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.options.m2640m()));
            if (!this.firstRotationFinished) {
                m = this.options.m2640m();
            }
            Bitmap previousBitmap = getPreviousBitmap();
            Bitmap currentBitmap = getCurrentBitmap();
            if (currentBitmap != null && previousBitmap != null) {
                if (this.rotation < 45.0f) {
                    if (this.rotation > 3.0f) {
                        drawFace(canvas, currentBitmap, faceStartTop, faceStartLeft, faceWidth / 2, faceHeight / 2, m, (this.rotation - 90.0f) * ((float) this.options.m2642o().getRotationMultiplier()));
                    }
                    drawFace(canvas, previousBitmap, faceStartTop, faceStartLeft, faceWidth / 2, faceHeight / 2, m, this.rotation * ((float) this.options.m2642o().getRotationMultiplier()));
                    return;
                }
                if (this.rotation < 87.0f) {
                    drawFace(canvas, previousBitmap, faceStartTop, faceStartLeft, faceWidth / 2, faceHeight / 2, m, this.rotation * ((float) this.options.m2642o().getRotationMultiplier()));
                }
                drawFace(canvas, currentBitmap, faceStartTop, faceStartLeft, faceWidth / 2, faceHeight / 2, m, (this.rotation - 90.0f) * ((float) this.options.m2642o().getRotationMultiplier()));
                if (!this.firstRotation) {
                    this.firstRotationFinished = true;
                }
            }
        } catch (Exception e) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 6, "Exception onDraw Banner3D");
        }
    }

    private int getFaceStartTop() {
        return (getHeight() - getFaceHeight()) / 2;
    }

    private int getFaceStartLeft() {
        return (getWidth() - getFaceWidth()) / 2;
    }

    private int getFaceHeight() {
        return (int) (((float) com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.options.m2632e())) * this.options.m2639l());
    }

    private int getFaceWidth() {
        return (int) (((float) com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.options.m2631d())) * this.options.m2638k());
    }

    private void drawFace(Canvas canvas, Bitmap view, int top, int left, int centerX, int centerY, float scale, float rotation) {
        if (this.camera == null) {
            this.camera = new Camera();
        }
        this.camera.save();
        this.camera.translate(0.0f, 0.0f, (float) centerY);
        this.camera.rotateX(rotation);
        this.camera.translate(0.0f, 0.0f, (float) (-centerY));
        if (this.matrix == null) {
            this.matrix = new Matrix();
        }
        this.camera.getMatrix(this.matrix);
        this.camera.restore();
        this.matrix.preTranslate((float) (-centerX), (float) (-centerY));
        this.matrix.postScale(scale, scale);
        this.matrix.postTranslate((float) (left + centerX), (float) (top + centerY));
        canvas.drawBitmap(view, this.matrix, this.paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEventInsideBanner(event) || this.faces == null || this.faces.size() == 0) {
            return false;
        }
        switch (event.getAction()) {
            case DurationDV.DURATION_TYPE /*0*/:
                this.touchDown = true;
                this.startY = event.getY();
                break;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (this.touchDown) {
                    if (this.rotation < 45.0f) {
                        prevBanner();
                    }
                    this.touchDown = false;
                    this.rotationEnabled = false;
                    setClicked(true);
                    postDelayed(new StartAppSDK(), MetaData.getInstance().getSmartRedirectTimeout());
                    ((Banner3DFace) this.faces.get(getCurrentBannerIndex())).m4764b(getContext());
                    if (this.listener != null) {
                        this.listener.onClick(this);
                        break;
                    }
                }
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (this.startY - event.getY() >= 10.0f) {
                    this.touchDown = false;
                    this.startY = event.getY();
                    break;
                }
                break;
        }
        return true;
    }

    private boolean isEventInsideBanner(MotionEvent event) {
        int faceWidth = getFaceWidth();
        int faceHeight = getFaceHeight();
        int faceStartLeft = getFaceStartLeft();
        int faceStartTop = getFaceStartTop();
        return event.getX() >= ((float) faceStartLeft) && event.getY() >= ((float) faceStartTop) && event.getX() <= ((float) (faceWidth + faceStartLeft)) && event.getY() <= ((float) (faceHeight + faceStartTop));
    }

    private void rotate(float deg) {
        this.rotation += deg;
        if (this.rotation >= 90.0f) {
            nextBanner();
            this.rotation -= 90.0f;
        }
        if (this.rotation <= 0.0f) {
            prevBanner();
            this.rotation += 90.0f;
        }
        invalidate();
    }

    public void onReceiveAd(Ad ad) {
        this.loaded = true;
        this.loading = false;
        this.overrides = this.ads.getAdInfoOverride();
        loadBanners(((com.startapp.android.publish.p028a.StartAppSDK) ad).m4751b(), true);
    }

    public void onFailedToReceiveAd(Ad ad) {
        if (this.listener != null) {
            this.listener.onFailedToReceiveAd(this);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.bIsVisible = this.visible;
        savedState.setDetails(this.adsItems);
        savedState.setRotation(this.rotation);
        savedState.setFirstRotation(this.firstRotation);
        savedState.setFirstRotationFinished(this.firstRotationFinished);
        savedState.setCurrentImage(this.currentBannerIndex);
        savedState.options = this.options;
        savedState.faces = new Banner3DFace[this.faces.size()];
        savedState.loaded = this.loaded;
        savedState.loading = this.loading;
        savedState.overrides = this.overrides;
        for (int i = 0; i < this.faces.size(); i++) {
            savedState.faces[i] = (Banner3DFace) this.faces.get(i);
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.visible = savedState.bIsVisible;
            if (this.visible) {
                this.adsItems = savedState.getDetails();
                this.rotation = savedState.getRotation();
                this.firstRotation = savedState.isFirstRotation();
                this.firstRotationFinished = savedState.isFirstRotationFinished();
                this.currentBannerIndex = savedState.getCurrentImage();
                Banner3DFace[] banner3DFaceArr = savedState.faces;
                this.faces = new ArrayList();
                if (banner3DFaceArr != null) {
                    for (Object add : banner3DFaceArr) {
                        this.faces.add(add);
                    }
                }
                this.loaded = savedState.loaded;
                this.loading = savedState.loading;
                this.defaultLoad = savedState.bDefaultLoad;
                this.overrides = savedState.overrides;
                this.options = savedState.options;
                if (this.adsItems.size() == 0) {
                    this.defaultLoad = true;
                    init();
                    return;
                }
                post(new StartAppSDK());
                return;
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private void startRotation() {
        if (this.attachedToWindow && this.drawn) {
            removeCallbacks(this.mAutoRotation);
            post(this.mAutoRotation);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        if (this.options == null || !this.options.m2643p()) {
            this.firstRotation = false;
            this.firstRotationFinished = true;
        }
        startRotation();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        removeCallbacks(this.mAutoRotation);
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            this.attachedToWindow = true;
            if (this.options == null || !this.options.m2643p()) {
                this.firstRotation = false;
                this.firstRotationFinished = true;
            }
            startRotation();
            return;
        }
        this.attachedToWindow = false;
        if (!this.rotating) {
            removeCallbacks(this.mAutoRotation);
        }
    }

    public void setBannerListener(BannerListener listener) {
        this.listener = listener;
    }

    protected int getRefreshRate() {
        return MetaData.getInstance().getBannerOptions().m2635h();
    }

    protected int getOffset() {
        if (this.ads == null) {
            return 0;
        }
        return this.ads.m5198a();
    }
}
