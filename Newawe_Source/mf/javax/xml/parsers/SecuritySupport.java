package mf.javax.xml.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

class SecuritySupport {

    /* renamed from: mf.javax.xml.parsers.SecuritySupport.1 */
    class C08241 implements PrivilegedAction {
        C08241() {
        }

        public Object run() {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl == null) {
                return ClassLoader.getSystemClassLoader();
            }
            return cl;
        }
    }

    /* renamed from: mf.javax.xml.parsers.SecuritySupport.2 */
    class C08252 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C08252(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    /* renamed from: mf.javax.xml.parsers.SecuritySupport.3 */
    class C08263 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C08263(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    /* renamed from: mf.javax.xml.parsers.SecuritySupport.4 */
    class C08274 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C08274(ClassLoader classLoader, String str) {
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

    /* renamed from: mf.javax.xml.parsers.SecuritySupport.5 */
    class C08285 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C08285(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Boolean(this.val$f.exists());
        }
    }

    SecuritySupport() {
    }

    ClassLoader getContextClassLoader() throws SecurityException {
        return (ClassLoader) AccessController.doPrivileged(new C08241());
    }

    String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C08252(propName));
    }

    FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C08263(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C08274(cl, name));
    }

    boolean doesFileExist(File f) {
        return ((Boolean) AccessController.doPrivileged(new C08285(f))).booleanValue();
    }
}
