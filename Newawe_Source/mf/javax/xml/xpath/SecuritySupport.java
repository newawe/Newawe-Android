package mf.javax.xml.xpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;

class SecuritySupport {

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.1 */
    class C08481 implements PrivilegedAction {
        C08481() {
        }

        public Object run() {
            ClassLoader cl = null;
            try {
                cl = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException e) {
            }
            return cl;
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.2 */
    class C08492 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C08492(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.3 */
    class C08503 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C08503(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.4 */
    class C08514 implements PrivilegedExceptionAction {
        private final /* synthetic */ URL val$url;

        C08514(URL url) {
            this.val$url = url;
        }

        public Object run() throws IOException {
            return this.val$url.openStream();
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.5 */
    class C08525 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C08525(ClassLoader classLoader, String str) {
            this.val$cl = classLoader;
            this.val$name = str;
        }

        public Object run() {
            if (this.val$cl == null) {
                return Object.class.getResource(this.val$name);
            }
            return this.val$cl.getResource(this.val$name);
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.6 */
    class C08536 implements PrivilegedExceptionAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C08536(ClassLoader classLoader, String str) {
            this.val$cl = classLoader;
            this.val$name = str;
        }

        public Object run() throws IOException {
            if (this.val$cl == null) {
                return ClassLoader.getSystemResources(this.val$name);
            }
            return this.val$cl.getResources(this.val$name);
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.7 */
    class C08547 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C08547(ClassLoader classLoader, String str) {
            this.val$cl = classLoader;
            this.val$name = str;
        }

        public Object run() {
            if (this.val$cl == null) {
                return Object.class.getResourceAsStream(this.val$name);
            }
            return this.val$cl.getResourceAsStream(this.val$name);
        }
    }

    /* renamed from: mf.javax.xml.xpath.SecuritySupport.8 */
    class C08558 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C08558(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Boolean(this.val$f.exists());
        }
    }

    SecuritySupport() {
    }

    ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C08481());
    }

    String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C08492(propName));
    }

    FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C08503(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    InputStream getURLInputStream(URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new C08514(url));
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    URL getResourceAsURL(ClassLoader cl, String name) {
        return (URL) AccessController.doPrivileged(new C08525(cl, name));
    }

    Enumeration getResources(ClassLoader cl, String name) throws IOException {
        try {
            return (Enumeration) AccessController.doPrivileged(new C08536(cl, name));
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C08547(cl, name));
    }

    boolean doesFileExist(File f) {
        return ((Boolean) AccessController.doPrivileged(new C08558(f))).booleanValue();
    }
}
