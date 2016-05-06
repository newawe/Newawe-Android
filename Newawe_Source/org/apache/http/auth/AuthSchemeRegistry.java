package org.apache.http.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.config.Lookup;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Deprecated
@ThreadSafe
public final class AuthSchemeRegistry implements Lookup<AuthSchemeProvider> {
    private final ConcurrentHashMap<String, AuthSchemeFactory> registeredSchemes;

    /* renamed from: org.apache.http.auth.AuthSchemeRegistry.1 */
    class C12751 implements AuthSchemeProvider {
        final /* synthetic */ String val$name;

        C12751(String str) {
            this.val$name = str;
        }

        public AuthScheme create(HttpContext context) {
            return AuthSchemeRegistry.this.getAuthScheme(this.val$name, ((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)).getParams());
        }
    }

    public AuthSchemeRegistry() {
        this.registeredSchemes = new ConcurrentHashMap();
    }

    public void register(String name, AuthSchemeFactory factory) {
        Args.notNull(name, SchemaSymbols.ATTVAL_NAME);
        Args.notNull(factory, "Authentication scheme factory");
        this.registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    public void unregister(String name) {
        Args.notNull(name, SchemaSymbols.ATTVAL_NAME);
        this.registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
    }

    public AuthScheme getAuthScheme(String name, HttpParams params) throws IllegalStateException {
        Args.notNull(name, SchemaSymbols.ATTVAL_NAME);
        AuthSchemeFactory factory = (AuthSchemeFactory) this.registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
        if (factory != null) {
            return factory.newInstance(params);
        }
        throw new IllegalStateException("Unsupported authentication scheme: " + name);
    }

    public List<String> getSchemeNames() {
        return new ArrayList(this.registeredSchemes.keySet());
    }

    public void setItems(Map<String, AuthSchemeFactory> map) {
        if (map != null) {
            this.registeredSchemes.clear();
            this.registeredSchemes.putAll(map);
        }
    }

    public AuthSchemeProvider lookup(String name) {
        return new C12751(name);
    }
}
