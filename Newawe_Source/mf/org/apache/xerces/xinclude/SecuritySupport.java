package mf.org.apache.xerces.xinclude;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

final class SecuritySupport {

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.1 */
    class C08991 implements PrivilegedAction {
        C08991() {
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

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.2 */
    class C09002 implements PrivilegedAction {
        C09002() {
        }

        public Object run() {
            ClassLoader cl = null;
            try {
                cl = ClassLoader.getSystemClassLoader();
            } catch (SecurityException e) {
            }
            return cl;
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.3 */
    class C09013 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;

        C09013(ClassLoader classLoader) {
            this.val$cl = classLoader;
        }

        public Object run() {
            ClassLoader parent = null;
            try {
                parent = this.val$cl.getParent();
            } catch (SecurityException e) {
            }
            return parent == this.val$cl ? null : parent;
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.4 */
    class C09024 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C09024(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.5 */
    class C09035 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C09035(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.6 */
    class C09046 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C09046(ClassLoader classLoader, String str) {
            this.val$cl = classLoader;
            this.val$name = str;
        }

        public Object run() {
            if (this.val$cl == null) {
                return ClassLoader.getSystemResourceAsStream(this.val$name);
            }
            return this.val$cl.getResourceAsStream(this.val$name);
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.7 */
    class C09057 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C09057(File file) {
            this.val$f = file;
        }

        public Object run() {
            return this.val$f.exists() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* renamed from: mf.org.apache.xerces.xinclude.SecuritySupport.8 */
    class C09068 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C09068(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Long(this.val$f.lastModified());
        }
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C08991());
    }

    static ClassLoader getSystemClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C09002());
    }

    static ClassLoader getParentClassLoader(ClassLoader cl) {
        return (ClassLoader) AccessController.doPrivileged(new C09013(cl));
    }

    static String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C09024(propName));
    }

    static FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C09035(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    static InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C09046(cl, name));
    }

    static boolean getFileExists(File f) {
        return ((Boolean) AccessController.doPrivileged(new C09057(f))).booleanValue();
    }

    static long getLastModified(File f) {
        return ((Long) AccessController.doPrivileged(new C09068(f))).longValue();
    }

    private SecuritySupport() {
    }
}
