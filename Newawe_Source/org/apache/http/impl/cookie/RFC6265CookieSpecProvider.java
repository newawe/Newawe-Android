package org.apache.http.impl.cookie;

import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RFC6265CookieSpecProvider implements CookieSpecProvider {
    private final CompatibilityLevel compatibilityLevel;
    private volatile CookieSpec cookieSpec;
    private final PublicSuffixMatcher publicSuffixMatcher;

    /* renamed from: org.apache.http.impl.cookie.RFC6265CookieSpecProvider.2 */
    static /* synthetic */ class C09322 {
        static final /* synthetic */ int[] f3309x14f07ea0;

        static {
            f3309x14f07ea0 = new int[CompatibilityLevel.values().length];
            try {
                f3309x14f07ea0[CompatibilityLevel.STRICT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3309x14f07ea0[CompatibilityLevel.IE_MEDIUM_SECURITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum CompatibilityLevel {
        STRICT,
        RELAXED,
        IE_MEDIUM_SECURITY
    }

    /* renamed from: org.apache.http.impl.cookie.RFC6265CookieSpecProvider.1 */
    class C13441 extends BasicPathHandler {
        C13441() {
        }

        public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        }
    }

    public RFC6265CookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        if (compatibilityLevel == null) {
            compatibilityLevel = CompatibilityLevel.RELAXED;
        }
        this.compatibilityLevel = compatibilityLevel;
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    public RFC6265CookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.RELAXED, publicSuffixMatcher);
    }

    public RFC6265CookieSpecProvider() {
        this(CompatibilityLevel.RELAXED, null);
    }

    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec == null) {
            synchronized (this) {
                if (this.cookieSpec == null) {
                    switch (C09322.f3309x14f07ea0[this.compatibilityLevel.ordinal()]) {
                        case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                            this.cookieSpec = new RFC6265StrictSpec(new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS));
                            break;
                        case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                            this.cookieSpec = new RFC6265LaxSpec(new C13441(), PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS));
                            break;
                        default:
                            this.cookieSpec = new RFC6265LaxSpec(new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler());
                            break;
                    }
                }
            }
        }
        return this.cookieSpec;
    }
}
