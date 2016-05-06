package mf.org.apache.xml.resolver;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.resolver.helpers.FileURL;
import mf.org.apache.xml.resolver.helpers.PublicId;
import mf.org.apache.xml.resolver.readers.CatalogReader;
import mf.org.apache.xml.resolver.readers.OASISXMLCatalogReader;
import mf.org.apache.xml.resolver.readers.SAXCatalogReader;
import mf.org.apache.xml.resolver.readers.TR9401CatalogReader;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

public class Catalog {
    public static final int BASE;
    public static final int CATALOG;
    public static final int DELEGATE_PUBLIC;
    public static final int DELEGATE_SYSTEM;
    public static final int DELEGATE_URI;
    public static final int DOCTYPE;
    public static final int DOCUMENT;
    public static final int DTDDECL;
    public static final int ENTITY;
    public static final int LINKTYPE;
    public static final int NOTATION;
    public static final int OVERRIDE;
    public static final int PUBLIC;
    public static final int REWRITE_SYSTEM;
    public static final int REWRITE_URI;
    public static final int SGMLDECL;
    public static final int SYSTEM;
    public static final int SYSTEM_SUFFIX;
    public static final int URI;
    public static final int URI_SUFFIX;
    protected URL base;
    protected URL catalogCwd;
    protected Vector catalogEntries;
    protected Vector catalogFiles;
    protected CatalogManager catalogManager;
    protected Vector catalogs;
    protected boolean default_override;
    protected Vector localCatalogFiles;
    protected Vector localDelegate;
    protected Vector readerArr;
    protected Hashtable readerMap;

    static {
        BASE = CatalogEntry.addEntryType("BASE", 1);
        CATALOG = CatalogEntry.addEntryType("CATALOG", 1);
        DOCUMENT = CatalogEntry.addEntryType("DOCUMENT", 1);
        OVERRIDE = CatalogEntry.addEntryType("OVERRIDE", 1);
        SGMLDECL = CatalogEntry.addEntryType("SGMLDECL", 1);
        DELEGATE_PUBLIC = CatalogEntry.addEntryType("DELEGATE_PUBLIC", 2);
        DELEGATE_SYSTEM = CatalogEntry.addEntryType("DELEGATE_SYSTEM", 2);
        DELEGATE_URI = CatalogEntry.addEntryType("DELEGATE_URI", 2);
        DOCTYPE = CatalogEntry.addEntryType("DOCTYPE", 2);
        DTDDECL = CatalogEntry.addEntryType("DTDDECL", 2);
        ENTITY = CatalogEntry.addEntryType(SchemaSymbols.ATTVAL_ENTITY, 2);
        LINKTYPE = CatalogEntry.addEntryType("LINKTYPE", 2);
        NOTATION = CatalogEntry.addEntryType(SchemaSymbols.ATTVAL_NOTATION, 2);
        PUBLIC = CatalogEntry.addEntryType("PUBLIC", 2);
        SYSTEM = CatalogEntry.addEntryType("SYSTEM", 2);
        URI = CatalogEntry.addEntryType("URI", 2);
        REWRITE_SYSTEM = CatalogEntry.addEntryType("REWRITE_SYSTEM", 2);
        REWRITE_URI = CatalogEntry.addEntryType("REWRITE_URI", 2);
        SYSTEM_SUFFIX = CatalogEntry.addEntryType("SYSTEM_SUFFIX", 2);
        URI_SUFFIX = CatalogEntry.addEntryType("URI_SUFFIX", 2);
    }

    public Catalog() {
        this.catalogEntries = new Vector();
        this.default_override = true;
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogFiles = new Vector();
        this.localCatalogFiles = new Vector();
        this.catalogs = new Vector();
        this.localDelegate = new Vector();
        this.readerMap = new Hashtable();
        this.readerArr = new Vector();
    }

    public Catalog(CatalogManager manager) {
        this.catalogEntries = new Vector();
        this.default_override = true;
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogFiles = new Vector();
        this.localCatalogFiles = new Vector();
        this.catalogs = new Vector();
        this.localDelegate = new Vector();
        this.readerMap = new Hashtable();
        this.readerArr = new Vector();
        this.catalogManager = manager;
    }

    public CatalogManager getCatalogManager() {
        return this.catalogManager;
    }

    public void setCatalogManager(CatalogManager manager) {
        this.catalogManager = manager;
    }

    public void setupReaders() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);
        SAXCatalogReader saxReader = new SAXCatalogReader(spf);
        saxReader.setCatalogParser(null, "XCatalog", "org.apache.xml.resolver.readers.XCatalogReader");
        saxReader.setCatalogParser(OASISXMLCatalogReader.namespaceName, "catalog", "org.apache.xml.resolver.readers.OASISXMLCatalogReader");
        addReader("application/xml", saxReader);
        addReader(HTTP.PLAIN_TEXT_TYPE, new TR9401CatalogReader());
    }

    public void addReader(String mimeType, CatalogReader reader) {
        if (this.readerMap.containsKey(mimeType)) {
            this.readerArr.set(((Integer) this.readerMap.get(mimeType)).intValue(), reader);
            return;
        }
        this.readerArr.add(reader);
        this.readerMap.put(mimeType, new Integer(this.readerArr.size() - 1));
    }

    protected void copyReaders(Catalog newCatalog) {
        int count;
        Vector mapArr = new Vector(this.readerMap.size());
        for (count = 0; count < this.readerMap.size(); count++) {
            mapArr.add(null);
        }
        Enumeration en = this.readerMap.keys();
        while (en.hasMoreElements()) {
            String mimeType = (String) en.nextElement();
            mapArr.set(((Integer) this.readerMap.get(mimeType)).intValue(), mimeType);
        }
        for (count = 0; count < mapArr.size(); count++) {
            mimeType = (String) mapArr.get(count);
            newCatalog.addReader(mimeType, (CatalogReader) this.readerArr.get(((Integer) this.readerMap.get(mimeType)).intValue()));
        }
    }

    protected Catalog newCatalog() {
        Catalog c;
        String catalogClass = getClass().getName();
        try {
            c = (Catalog) Class.forName(catalogClass).newInstance();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (ClassNotFoundException e) {
            this.catalogManager.debug.message(1, "Class Not Found Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (IllegalAccessException e2) {
            this.catalogManager.debug.message(1, "Illegal Access Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (InstantiationException e3) {
            this.catalogManager.debug.message(1, "Instantiation Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (ClassCastException e4) {
            this.catalogManager.debug.message(1, "Class Cast Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (Exception e5) {
            this.catalogManager.debug.message(1, "Other Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        }
    }

    public String getCurrentBase() {
        return this.base.toString();
    }

    public String getDefaultOverride() {
        if (this.default_override) {
            return "yes";
        }
        return "no";
    }

    public void loadSystemCatalogs() throws MalformedURLException, IOException {
        Vector catalogs = this.catalogManager.getCatalogFiles();
        if (catalogs != null) {
            for (int count = 0; count < catalogs.size(); count++) {
                this.catalogFiles.addElement(catalogs.elementAt(count));
            }
        }
        if (this.catalogFiles.size() > 0) {
            String catfile = (String) this.catalogFiles.lastElement();
            this.catalogFiles.removeElement(catfile);
            parseCatalog(catfile);
        }
    }

    public synchronized void parseCatalog(String fileName) throws MalformedURLException, IOException {
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse catalog: " + fileName);
        this.catalogFiles.addElement(fileName);
        parsePendingCatalogs();
    }

    public synchronized void parseCatalog(String mimeType, InputStream is) throws IOException, CatalogException {
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse " + mimeType + " catalog on input stream");
        CatalogReader reader = null;
        if (this.readerMap.containsKey(mimeType)) {
            reader = (CatalogReader) this.readerArr.get(((Integer) this.readerMap.get(mimeType)).intValue());
        }
        if (reader == null) {
            String msg = "No CatalogReader for MIME type: " + mimeType;
            this.catalogManager.debug.message(2, msg);
            throw new CatalogException(6, msg);
        }
        reader.readCatalog(this, is);
        parsePendingCatalogs();
    }

    public synchronized void parseCatalog(URL aUrl) throws IOException {
        DataInputStream dataInputStream;
        this.catalogCwd = aUrl;
        this.base = aUrl;
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse catalog: " + aUrl.toString());
        boolean parsed = false;
        int count = 0;
        DataInputStream inStream = null;
        while (!parsed && count < this.readerArr.size()) {
            CatalogReader reader = (CatalogReader) this.readerArr.get(count);
            try {
                InputStream inStream2 = new DataInputStream(aUrl.openStream());
                try {
                    reader.readCatalog(this, inStream2);
                    parsed = true;
                } catch (CatalogException ce) {
                    if (ce.getExceptionType() == 7) {
                        break;
                    }
                }
                try {
                    inStream2.close();
                } catch (IOException e) {
                }
                count++;
                InputStream inStream3 = inStream2;
            } catch (FileNotFoundException e2) {
                dataInputStream = inStream;
            }
        }
        dataInputStream = inStream;
        if (parsed) {
            parsePendingCatalogs();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized void parsePendingCatalogs() throws java.net.MalformedURLException, java.io.IOException {
        /*
        r9 = this;
        monitor-enter(r9);
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x002a;
    L_0x0009:
        r4 = new java.util.Vector;	 Catch:{ all -> 0x0062 }
        r4.<init>();	 Catch:{ all -> 0x0062 }
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r5 = r6.elements();	 Catch:{ all -> 0x0062 }
    L_0x0014:
        r6 = r5.hasMoreElements();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x005a;
    L_0x001a:
        r2 = 0;
    L_0x001b:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.size();	 Catch:{ all -> 0x0062 }
        if (r2 < r6) goto L_0x0065;
    L_0x0023:
        r9.catalogFiles = r4;	 Catch:{ all -> 0x0062 }
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r6.clear();	 Catch:{ all -> 0x0062 }
    L_0x002a:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 == 0) goto L_0x004b;
    L_0x0032:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x004b;
    L_0x003a:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r3 = r6.elements();	 Catch:{ all -> 0x0062 }
    L_0x0040:
        r6 = r3.hasMoreElements();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x0073;
    L_0x0046:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r6.clear();	 Catch:{ all -> 0x0062 }
    L_0x004b:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 == 0) goto L_0x007d;
    L_0x0053:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r6.clear();	 Catch:{ all -> 0x0062 }
        monitor-exit(r9);
        return;
    L_0x005a:
        r6 = r5.nextElement();	 Catch:{ all -> 0x0062 }
        r4.addElement(r6);	 Catch:{ all -> 0x0062 }
        goto L_0x0014;
    L_0x0062:
        r6 = move-exception;
        monitor-exit(r9);
        throw r6;
    L_0x0065:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r0 = r6.elementAt(r2);	 Catch:{ all -> 0x0062 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0062 }
        r4.addElement(r0);	 Catch:{ all -> 0x0062 }
        r2 = r2 + 1;
        goto L_0x001b;
    L_0x0073:
        r6 = r9.catalogEntries;	 Catch:{ all -> 0x0062 }
        r7 = r3.nextElement();	 Catch:{ all -> 0x0062 }
        r6.addElement(r7);	 Catch:{ all -> 0x0062 }
        goto L_0x0040;
    L_0x007d:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r7 = 0;
        r0 = r6.elementAt(r7);	 Catch:{ all -> 0x0062 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0062 }
        r6 = r9.catalogFiles;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0123 }
        r7 = 0;
        r6.remove(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0123 }
    L_0x008c:
        r6 = r9.catalogEntries;	 Catch:{ all -> 0x0062 }
        r6 = r6.size();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x00fd;
    L_0x0094:
        r6 = r9.catalogs;	 Catch:{ all -> 0x0062 }
        r6 = r6.size();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x00fd;
    L_0x009c:
        r9.parseCatalogFile(r0);	 Catch:{ CatalogException -> 0x00e3 }
    L_0x009f:
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x00c8;
    L_0x00a7:
        r4 = new java.util.Vector;	 Catch:{ all -> 0x0062 }
        r4.<init>();	 Catch:{ all -> 0x0062 }
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r5 = r6.elements();	 Catch:{ all -> 0x0062 }
    L_0x00b2:
        r6 = r5.hasMoreElements();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x0103;
    L_0x00b8:
        r2 = 0;
    L_0x00b9:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r6 = r6.size();	 Catch:{ all -> 0x0062 }
        if (r2 < r6) goto L_0x010b;
    L_0x00c1:
        r9.catalogFiles = r4;	 Catch:{ all -> 0x0062 }
        r6 = r9.localCatalogFiles;	 Catch:{ all -> 0x0062 }
        r6.clear();	 Catch:{ all -> 0x0062 }
    L_0x00c8:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x004b;
    L_0x00d0:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r3 = r6.elements();	 Catch:{ all -> 0x0062 }
    L_0x00d6:
        r6 = r3.hasMoreElements();	 Catch:{ all -> 0x0062 }
        if (r6 != 0) goto L_0x0119;
    L_0x00dc:
        r6 = r9.localDelegate;	 Catch:{ all -> 0x0062 }
        r6.clear();	 Catch:{ all -> 0x0062 }
        goto L_0x004b;
    L_0x00e3:
        r1 = move-exception;
        r6 = java.lang.System.out;	 Catch:{ all -> 0x0062 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0062 }
        r8 = "FIXME: ";
        r7.<init>(r8);	 Catch:{ all -> 0x0062 }
        r8 = r1.toString();	 Catch:{ all -> 0x0062 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0062 }
        r7 = r7.toString();	 Catch:{ all -> 0x0062 }
        r6.println(r7);	 Catch:{ all -> 0x0062 }
        goto L_0x009f;
    L_0x00fd:
        r6 = r9.catalogs;	 Catch:{ all -> 0x0062 }
        r6.addElement(r0);	 Catch:{ all -> 0x0062 }
        goto L_0x009f;
    L_0x0103:
        r6 = r5.nextElement();	 Catch:{ all -> 0x0062 }
        r4.addElement(r6);	 Catch:{ all -> 0x0062 }
        goto L_0x00b2;
    L_0x010b:
        r6 = r9.catalogFiles;	 Catch:{ all -> 0x0062 }
        r0 = r6.elementAt(r2);	 Catch:{ all -> 0x0062 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0062 }
        r4.addElement(r0);	 Catch:{ all -> 0x0062 }
        r2 = r2 + 1;
        goto L_0x00b9;
    L_0x0119:
        r6 = r9.catalogEntries;	 Catch:{ all -> 0x0062 }
        r7 = r3.nextElement();	 Catch:{ all -> 0x0062 }
        r6.addElement(r7);	 Catch:{ all -> 0x0062 }
        goto L_0x00d6;
    L_0x0123:
        r6 = move-exception;
        goto L_0x008c;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.Catalog.parsePendingCatalogs():void");
    }

    protected synchronized void parseCatalogFile(String fileName) throws MalformedURLException, IOException, CatalogException {
        DataInputStream dataInputStream;
        try {
            this.catalogCwd = FileURL.makeURL("basename");
        } catch (MalformedURLException e) {
            this.catalogManager.debug.message(1, "Malformed URL on cwd", System.getProperty("user.dir").replace('\\', '/'));
            this.catalogCwd = null;
        }
        try {
            this.base = new URL(this.catalogCwd, fixSlashes(fileName));
        } catch (MalformedURLException e2) {
            try {
                this.base = new URL("file:" + fixSlashes(fileName));
            } catch (MalformedURLException e3) {
                this.catalogManager.debug.message(1, "Malformed URL on catalog filename", fixSlashes(fileName));
                this.base = null;
            }
        }
        this.catalogManager.debug.message(2, "Loading catalog", fileName);
        this.catalogManager.debug.message(4, "Default BASE", this.base.toString());
        fileName = this.base.toString();
        boolean parsed = false;
        boolean notFound = false;
        int count = 0;
        DataInputStream inStream = null;
        while (!parsed && count < this.readerArr.size()) {
            CatalogReader reader = (CatalogReader) this.readerArr.get(count);
            notFound = false;
            try {
                InputStream inStream2 = new DataInputStream(this.base.openStream());
                try {
                    reader.readCatalog(this, inStream2);
                    parsed = true;
                } catch (CatalogException ce) {
                    if (ce.getExceptionType() == 7) {
                        break;
                    }
                }
                try {
                    inStream2.close();
                } catch (IOException e4) {
                }
                count++;
                InputStream inStream3 = inStream2;
            } catch (FileNotFoundException e5) {
                notFound = true;
                dataInputStream = inStream;
            }
        }
        dataInputStream = inStream;
        if (!parsed) {
            if (notFound) {
                this.catalogManager.debug.message(3, "Catalog does not exist", fileName);
            } else {
                this.catalogManager.debug.message(1, "Failed to parse catalog", fileName);
            }
        }
    }

    public void addEntry(CatalogEntry entry) {
        int type = entry.getEntryType();
        if (type == BASE) {
            URL newbase;
            String value = entry.getEntryArg(0);
            if (this.base == null) {
                this.catalogManager.debug.message(5, "BASE CUR", "null");
            } else {
                this.catalogManager.debug.message(5, "BASE CUR", this.base.toString());
            }
            this.catalogManager.debug.message(4, "BASE STR", value);
            try {
                value = fixSlashes(value);
                newbase = new URL(this.base, value);
            } catch (MalformedURLException e) {
                try {
                    newbase = new URL("file:" + value);
                } catch (MalformedURLException e2) {
                    this.catalogManager.debug.message(1, "Malformed URL on base", value);
                    newbase = null;
                }
            }
            if (newbase != null) {
                this.base = newbase;
            }
            this.catalogManager.debug.message(5, "BASE NEW", this.base.toString());
        } else if (type == CATALOG) {
            fsi = makeAbsolute(entry.getEntryArg(0));
            this.catalogManager.debug.message(4, "CATALOG", fsi);
            this.localCatalogFiles.addElement(fsi);
        } else if (type == PUBLIC) {
            String publicid = PublicId.normalize(entry.getEntryArg(0));
            systemid = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, publicid);
            entry.setEntryArg(1, systemid);
            this.catalogManager.debug.message(4, "PUBLIC", publicid, systemid);
            this.catalogEntries.addElement(entry);
        } else if (type == SYSTEM) {
            systemid = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "SYSTEM", systemid, fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == URI) {
            String uri = normalizeURI(entry.getEntryArg(0));
            String altURI = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, altURI);
            this.catalogManager.debug.message(4, "URI", uri, altURI);
            this.catalogEntries.addElement(entry);
        } else if (type == DOCUMENT) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(0)));
            entry.setEntryArg(0, fsi);
            this.catalogManager.debug.message(4, "DOCUMENT", fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == OVERRIDE) {
            this.catalogManager.debug.message(4, "OVERRIDE", entry.getEntryArg(0));
            this.catalogEntries.addElement(entry);
        } else if (type == SGMLDECL) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(0)));
            entry.setEntryArg(0, fsi);
            this.catalogManager.debug.message(4, "SGMLDECL", fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == DELEGATE_PUBLIC) {
            String ppi = PublicId.normalize(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, ppi);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_PUBLIC", ppi, fsi);
            addDelegate(entry);
        } else if (type == DELEGATE_SYSTEM) {
            psi = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, psi);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_SYSTEM", psi, fsi);
            addDelegate(entry);
        } else if (type == DELEGATE_URI) {
            pui = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_URI", pui, fsi);
            addDelegate(entry);
        } else if (type == REWRITE_SYSTEM) {
            psi = normalizeURI(entry.getEntryArg(0));
            String rpx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, psi);
            entry.setEntryArg(1, rpx);
            this.catalogManager.debug.message(4, "REWRITE_SYSTEM", psi, rpx);
            this.catalogEntries.addElement(entry);
        } else if (type == REWRITE_URI) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "REWRITE_URI", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == SYSTEM_SUFFIX) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "SYSTEM_SUFFIX", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == URI_SUFFIX) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "URI_SUFFIX", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == DOCTYPE) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            r2 = "DOCTYPE";
            this.catalogManager.debug.message(4, r22, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == DTDDECL) {
            String fpi = PublicId.normalize(entry.getEntryArg(0));
            entry.setEntryArg(0, fpi);
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DTDDECL", fpi, fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == ENTITY) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, SchemaSymbols.ATTVAL_ENTITY, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == LINKTYPE) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            r2 = "LINKTYPE";
            this.catalogManager.debug.message(4, r22, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == NOTATION) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, SchemaSymbols.ATTVAL_NOTATION, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else {
            this.catalogEntries.addElement(entry);
        }
    }

    public void unknownEntry(Vector strings) {
        if (strings != null && strings.size() > 0) {
            this.catalogManager.debug.message(2, "Unrecognized token parsing catalog", (String) strings.elementAt(0));
        }
    }

    public void parseAllCatalogs() throws MalformedURLException, IOException {
        Catalog c;
        for (int catPos = 0; catPos < this.catalogs.size(); catPos++) {
            try {
                c = (Catalog) this.catalogs.elementAt(catPos);
            } catch (ClassCastException e) {
                String catfile = (String) this.catalogs.elementAt(catPos);
                c = newCatalog();
                c.parseCatalog(catfile);
                this.catalogs.setElementAt(c, catPos);
                c.parseAllCatalogs();
            }
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e2 = (CatalogEntry) en.nextElement();
            if (e2.getEntryType() == DELEGATE_PUBLIC || e2.getEntryType() == DELEGATE_SYSTEM || e2.getEntryType() == DELEGATE_URI) {
                newCatalog().parseCatalog(e2.getEntryArg(1));
            }
        }
    }

    public String resolveDoctype(String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveDoctype(" + entityName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(DOCTYPE, entityName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == DOCTYPE && e.getEntryArg(0).equals(entityName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(DOCTYPE, entityName, publicId, systemId);
    }

    public String resolveDocument() throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveDocument");
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DOCUMENT) {
                return e.getEntryArg(0);
            }
        }
        return resolveSubordinateCatalogs(DOCUMENT, null, null, null);
    }

    public String resolveEntity(String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveEntity(" + entityName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(ENTITY, entityName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == ENTITY && e.getEntryArg(0).equals(entityName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(ENTITY, entityName, publicId, systemId);
    }

    public String resolveNotation(String notationName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveNotation(" + notationName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(NOTATION, notationName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == NOTATION && e.getEntryArg(0).equals(notationName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(NOTATION, notationName, publicId, systemId);
    }

    public String resolvePublic(String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolvePublic(" + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        resolved = resolveLocalPublic(PUBLIC, null, publicId, systemId);
        if (resolved != null) {
            return resolved;
        }
        return resolveSubordinateCatalogs(PUBLIC, null, publicId, systemId);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized java.lang.String resolveLocalPublic(int r15, java.lang.String r16, java.lang.String r17, java.lang.String r18) throws java.net.MalformedURLException, java.io.IOException {
        /*
        r14 = this;
        monitor-enter(r14);
        r17 = mf.org.apache.xml.resolver.helpers.PublicId.normalize(r17);	 Catch:{ all -> 0x00f8 }
        if (r18 == 0) goto L_0x0011;
    L_0x0007:
        r0 = r18;
        r9 = r14.resolveLocalSystem(r0);	 Catch:{ all -> 0x00f8 }
        if (r9 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r14);
        return r9;
    L_0x0011:
        r7 = r14.default_override;	 Catch:{ all -> 0x00f8 }
        r10 = r14.catalogEntries;	 Catch:{ all -> 0x00f8 }
        r5 = r10.elements();	 Catch:{ all -> 0x00f8 }
    L_0x0019:
        r10 = r5.hasMoreElements();	 Catch:{ all -> 0x00f8 }
        if (r10 != 0) goto L_0x006d;
    L_0x001f:
        r7 = r14.default_override;	 Catch:{ all -> 0x00f8 }
        r10 = r14.catalogEntries;	 Catch:{ all -> 0x00f8 }
        r5 = r10.elements();	 Catch:{ all -> 0x00f8 }
        r2 = new java.util.Vector;	 Catch:{ all -> 0x00f8 }
        r2.<init>();	 Catch:{ all -> 0x00f8 }
    L_0x002c:
        r10 = r5.hasMoreElements();	 Catch:{ all -> 0x00f8 }
        if (r10 != 0) goto L_0x00a7;
    L_0x0032:
        r10 = r2.size();	 Catch:{ all -> 0x00f8 }
        if (r10 <= 0) goto L_0x0125;
    L_0x0038:
        r6 = r2.elements();	 Catch:{ all -> 0x00f8 }
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00f8 }
        r10 = r10.debug;	 Catch:{ all -> 0x00f8 }
        r10 = r10.getDebug();	 Catch:{ all -> 0x00f8 }
        r11 = 1;
        if (r10 <= r11) goto L_0x0057;
    L_0x0047:
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00f8 }
        r10 = r10.debug;	 Catch:{ all -> 0x00f8 }
        r11 = 2;
        r12 = "Switching to delegated catalog(s):";
        r10.message(r11, r12);	 Catch:{ all -> 0x00f8 }
    L_0x0051:
        r10 = r6.hasMoreElements();	 Catch:{ all -> 0x00f8 }
        if (r10 != 0) goto L_0x00fb;
    L_0x0057:
        r1 = r14.newCatalog();	 Catch:{ all -> 0x00f8 }
        r6 = r2.elements();	 Catch:{ all -> 0x00f8 }
    L_0x005f:
        r10 = r6.hasMoreElements();	 Catch:{ all -> 0x00f8 }
        if (r10 != 0) goto L_0x011a;
    L_0x0065:
        r10 = 0;
        r0 = r17;
        r9 = r1.resolvePublic(r0, r10);	 Catch:{ all -> 0x00f8 }
        goto L_0x000f;
    L_0x006d:
        r4 = r5.nextElement();	 Catch:{ all -> 0x00f8 }
        r4 = (mf.org.apache.xml.resolver.CatalogEntry) r4;	 Catch:{ all -> 0x00f8 }
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00f8 }
        r11 = OVERRIDE;	 Catch:{ all -> 0x00f8 }
        if (r10 != r11) goto L_0x0087;
    L_0x007b:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        r11 = "YES";
        r7 = r10.equalsIgnoreCase(r11);	 Catch:{ all -> 0x00f8 }
        goto L_0x0019;
    L_0x0087:
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00f8 }
        r11 = PUBLIC;	 Catch:{ all -> 0x00f8 }
        if (r10 != r11) goto L_0x0019;
    L_0x008f:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        r0 = r17;
        r10 = r10.equals(r0);	 Catch:{ all -> 0x00f8 }
        if (r10 == 0) goto L_0x0019;
    L_0x009c:
        if (r7 != 0) goto L_0x00a0;
    L_0x009e:
        if (r18 != 0) goto L_0x0019;
    L_0x00a0:
        r10 = 1;
        r9 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        goto L_0x000f;
    L_0x00a7:
        r4 = r5.nextElement();	 Catch:{ all -> 0x00f8 }
        r4 = (mf.org.apache.xml.resolver.CatalogEntry) r4;	 Catch:{ all -> 0x00f8 }
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00f8 }
        r11 = OVERRIDE;	 Catch:{ all -> 0x00f8 }
        if (r10 != r11) goto L_0x00c2;
    L_0x00b5:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        r11 = "YES";
        r7 = r10.equalsIgnoreCase(r11);	 Catch:{ all -> 0x00f8 }
        goto L_0x002c;
    L_0x00c2:
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00f8 }
        r11 = DELEGATE_PUBLIC;	 Catch:{ all -> 0x00f8 }
        if (r10 != r11) goto L_0x002c;
    L_0x00ca:
        if (r7 != 0) goto L_0x00ce;
    L_0x00cc:
        if (r18 != 0) goto L_0x002c;
    L_0x00ce:
        r10 = 0;
        r8 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        r10 = r8.length();	 Catch:{ all -> 0x00f8 }
        r11 = r17.length();	 Catch:{ all -> 0x00f8 }
        if (r10 > r11) goto L_0x002c;
    L_0x00dd:
        r10 = 0;
        r11 = r8.length();	 Catch:{ all -> 0x00f8 }
        r0 = r17;
        r10 = r0.substring(r10, r11);	 Catch:{ all -> 0x00f8 }
        r10 = r8.equals(r10);	 Catch:{ all -> 0x00f8 }
        if (r10 == 0) goto L_0x002c;
    L_0x00ee:
        r10 = 1;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00f8 }
        r2.addElement(r10);	 Catch:{ all -> 0x00f8 }
        goto L_0x002c;
    L_0x00f8:
        r10 = move-exception;
        monitor-exit(r14);
        throw r10;
    L_0x00fb:
        r3 = r6.nextElement();	 Catch:{ all -> 0x00f8 }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00f8 }
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00f8 }
        r10 = r10.debug;	 Catch:{ all -> 0x00f8 }
        r11 = 2;
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f8 }
        r13 = "\t";
        r12.<init>(r13);	 Catch:{ all -> 0x00f8 }
        r12 = r12.append(r3);	 Catch:{ all -> 0x00f8 }
        r12 = r12.toString();	 Catch:{ all -> 0x00f8 }
        r10.message(r11, r12);	 Catch:{ all -> 0x00f8 }
        goto L_0x0051;
    L_0x011a:
        r3 = r6.nextElement();	 Catch:{ all -> 0x00f8 }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00f8 }
        r1.parseCatalog(r3);	 Catch:{ all -> 0x00f8 }
        goto L_0x005f;
    L_0x0125:
        r9 = 0;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.Catalog.resolveLocalPublic(int, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public String resolveSystem(String systemId) throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveSystem(" + systemId + ")");
        systemId = normalizeURI(systemId);
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            return resolvePublic(PublicId.decodeURN(systemId), null);
        }
        if (systemId != null) {
            String resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        return resolveSubordinateCatalogs(SYSTEM, null, null, systemId);
    }

    protected String resolveLocalSystem(String systemId) throws MalformedURLException, IOException {
        boolean windows = System.getProperty("os.name").indexOf("Windows") >= 0;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM && (e.getEntryArg(0).equals(systemId) || (windows && e.getEntryArg(0).equalsIgnoreCase(systemId)))) {
                return e.getEntryArg(1);
            }
        }
        en = this.catalogEntries.elements();
        String startString = null;
        String prefix = null;
        while (en.hasMoreElements()) {
            String p;
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == REWRITE_SYSTEM) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length()) {
                    if (p.equals(systemId.substring(0, p.length())) && (startString == null || p.length() > startString.length())) {
                        startString = p;
                        prefix = e.getEntryArg(1);
                    }
                }
            }
        }
        if (prefix != null) {
            return new StringBuilder(String.valueOf(prefix)).append(systemId.substring(startString.length())).toString();
        }
        en = this.catalogEntries.elements();
        String suffixString = null;
        String suffixURI = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM_SUFFIX) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length() && systemId.endsWith(p)) {
                    if (suffixString == null || p.length() > suffixString.length()) {
                        suffixString = p;
                        suffixURI = e.getEntryArg(1);
                    }
                }
            }
        }
        if (suffixURI != null) {
            return suffixURI;
        }
        en = this.catalogEntries.elements();
        Vector delCats = new Vector();
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DELEGATE_SYSTEM) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length()) {
                    if (p.equals(systemId.substring(0, p.length()))) {
                        delCats.addElement(e.getEntryArg(1));
                    }
                }
            }
        }
        if (delCats.size() <= 0) {
            return null;
        }
        Enumeration enCats = delCats.elements();
        if (this.catalogManager.debug.getDebug() > 1) {
            this.catalogManager.debug.message(2, "Switching to delegated catalog(s):");
            while (enCats.hasMoreElements()) {
                this.catalogManager.debug.message(2, "\t" + ((String) enCats.nextElement()));
            }
        }
        Catalog dcat = newCatalog();
        enCats = delCats.elements();
        while (enCats.hasMoreElements()) {
            dcat.parseCatalog((String) enCats.nextElement());
        }
        return dcat.resolveSystem(systemId);
    }

    public String resolveURI(String uri) throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveURI(" + uri + ")");
        uri = normalizeURI(uri);
        if (uri != null && uri.startsWith("urn:publicid:")) {
            return resolvePublic(PublicId.decodeURN(uri), null);
        }
        if (uri != null) {
            String resolved = resolveLocalURI(uri);
            if (resolved != null) {
                return resolved;
            }
        }
        return resolveSubordinateCatalogs(URI, null, null, uri);
    }

    protected String resolveLocalURI(String uri) throws MalformedURLException, IOException {
        String p;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == URI && e.getEntryArg(0).equals(uri)) {
                return e.getEntryArg(1);
            }
        }
        en = this.catalogEntries.elements();
        String startString = null;
        String prefix = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == REWRITE_URI) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length()) {
                    if (p.equals(uri.substring(0, p.length())) && (startString == null || p.length() > startString.length())) {
                        startString = p;
                        prefix = e.getEntryArg(1);
                    }
                }
            }
        }
        if (prefix != null) {
            return new StringBuilder(String.valueOf(prefix)).append(uri.substring(startString.length())).toString();
        }
        en = this.catalogEntries.elements();
        String suffixString = null;
        String suffixURI = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == URI_SUFFIX) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length() && uri.endsWith(p)) {
                    if (suffixString == null || p.length() > suffixString.length()) {
                        suffixString = p;
                        suffixURI = e.getEntryArg(1);
                    }
                }
            }
        }
        if (suffixURI != null) {
            return suffixURI;
        }
        en = this.catalogEntries.elements();
        Vector delCats = new Vector();
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DELEGATE_URI) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length()) {
                    if (p.equals(uri.substring(0, p.length()))) {
                        delCats.addElement(e.getEntryArg(1));
                    }
                }
            }
        }
        if (delCats.size() <= 0) {
            return null;
        }
        Enumeration enCats = delCats.elements();
        if (this.catalogManager.debug.getDebug() > 1) {
            this.catalogManager.debug.message(2, "Switching to delegated catalog(s):");
            while (enCats.hasMoreElements()) {
                this.catalogManager.debug.message(2, "\t" + ((String) enCats.nextElement()));
            }
        }
        Catalog dcat = newCatalog();
        enCats = delCats.elements();
        while (enCats.hasMoreElements()) {
            dcat.parseCatalog((String) enCats.nextElement());
        }
        return dcat.resolveURI(uri);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized java.lang.String resolveSubordinateCatalogs(int r12, java.lang.String r13, java.lang.String r14, java.lang.String r15) throws java.net.MalformedURLException, java.io.IOException {
        /*
        r11 = this;
        monitor-enter(r11);
        r1 = 0;
    L_0x0002:
        r8 = r11.catalogs;	 Catch:{ all -> 0x003a }
        r8 = r8.size();	 Catch:{ all -> 0x003a }
        if (r1 < r8) goto L_0x000d;
    L_0x000a:
        r7 = 0;
    L_0x000b:
        monitor-exit(r11);
        return r7;
    L_0x000d:
        r0 = 0;
        r8 = r11.catalogs;	 Catch:{ ClassCastException -> 0x0024 }
        r0 = r8.elementAt(r1);	 Catch:{ ClassCastException -> 0x0024 }
        r0 = (mf.org.apache.xml.resolver.Catalog) r0;	 Catch:{ ClassCastException -> 0x0024 }
    L_0x0016:
        r7 = 0;
        r8 = DOCTYPE;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x0061;
    L_0x001b:
        r7 = r0.resolveDoctype(r13, r14, r15);	 Catch:{ all -> 0x003a }
    L_0x001f:
        if (r7 != 0) goto L_0x000b;
    L_0x0021:
        r1 = r1 + 1;
        goto L_0x0002;
    L_0x0024:
        r3 = move-exception;
        r8 = r11.catalogs;	 Catch:{ all -> 0x003a }
        r2 = r8.elementAt(r1);	 Catch:{ all -> 0x003a }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x003a }
        r0 = r11.newCatalog();	 Catch:{ all -> 0x003a }
        r0.parseCatalog(r2);	 Catch:{ MalformedURLException -> 0x003d, FileNotFoundException -> 0x0049, IOException -> 0x0055 }
    L_0x0034:
        r8 = r11.catalogs;	 Catch:{ all -> 0x003a }
        r8.setElementAt(r0, r1);	 Catch:{ all -> 0x003a }
        goto L_0x0016;
    L_0x003a:
        r8 = move-exception;
        monitor-exit(r11);
        throw r8;
    L_0x003d:
        r6 = move-exception;
        r8 = r11.catalogManager;	 Catch:{ all -> 0x003a }
        r8 = r8.debug;	 Catch:{ all -> 0x003a }
        r9 = 1;
        r10 = "Malformed Catalog URL";
        r8.message(r9, r10, r2);	 Catch:{ all -> 0x003a }
        goto L_0x0034;
    L_0x0049:
        r4 = move-exception;
        r8 = r11.catalogManager;	 Catch:{ all -> 0x003a }
        r8 = r8.debug;	 Catch:{ all -> 0x003a }
        r9 = 1;
        r10 = "Failed to load catalog, file not found";
        r8.message(r9, r10, r2);	 Catch:{ all -> 0x003a }
        goto L_0x0034;
    L_0x0055:
        r5 = move-exception;
        r8 = r11.catalogManager;	 Catch:{ all -> 0x003a }
        r8 = r8.debug;	 Catch:{ all -> 0x003a }
        r9 = 1;
        r10 = "Failed to load catalog, I/O error";
        r8.message(r9, r10, r2);	 Catch:{ all -> 0x003a }
        goto L_0x0034;
    L_0x0061:
        r8 = DOCUMENT;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x006a;
    L_0x0065:
        r7 = r0.resolveDocument();	 Catch:{ all -> 0x003a }
        goto L_0x001f;
    L_0x006a:
        r8 = ENTITY;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x0073;
    L_0x006e:
        r7 = r0.resolveEntity(r13, r14, r15);	 Catch:{ all -> 0x003a }
        goto L_0x001f;
    L_0x0073:
        r8 = NOTATION;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x007c;
    L_0x0077:
        r7 = r0.resolveNotation(r13, r14, r15);	 Catch:{ all -> 0x003a }
        goto L_0x001f;
    L_0x007c:
        r8 = PUBLIC;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x0085;
    L_0x0080:
        r7 = r0.resolvePublic(r14, r15);	 Catch:{ all -> 0x003a }
        goto L_0x001f;
    L_0x0085:
        r8 = SYSTEM;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x008e;
    L_0x0089:
        r7 = r0.resolveSystem(r15);	 Catch:{ all -> 0x003a }
        goto L_0x001f;
    L_0x008e:
        r8 = URI;	 Catch:{ all -> 0x003a }
        if (r12 != r8) goto L_0x001f;
    L_0x0092:
        r7 = r0.resolveURI(r15);	 Catch:{ all -> 0x003a }
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.Catalog.resolveSubordinateCatalogs(int, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    protected String fixSlashes(String sysid) {
        return sysid.replace('\\', '/');
    }

    protected String makeAbsolute(String sysid) {
        URL local = null;
        sysid = fixSlashes(sysid);
        try {
            local = new URL(this.base, sysid);
        } catch (MalformedURLException e) {
            this.catalogManager.debug.message(1, "Malformed URL on system identifier", sysid);
        }
        if (local != null) {
            return local.toString();
        }
        return sysid;
    }

    protected String normalizeURI(String uriref) {
        String newRef = StringUtils.EMPTY;
        if (uriref == null) {
            return null;
        }
        try {
            byte[] bytes = uriref.getBytes(HTTP.UTF_8);
            for (int count = 0; count < bytes.length; count++) {
                int ch = bytes[count] & MotionEventCompat.ACTION_MASK;
                if (ch <= 32 || ch > TransportMediator.KEYCODE_MEDIA_PAUSE || ch == 34 || ch == 60 || ch == 62 || ch == 92 || ch == 94 || ch == 96 || ch == 123 || ch == 124 || ch == 125 || ch == TransportMediator.KEYCODE_MEDIA_PAUSE) {
                    newRef = new StringBuilder(String.valueOf(newRef)).append(encodedByte(ch)).toString();
                } else {
                    newRef = new StringBuilder(String.valueOf(newRef)).append((char) bytes[count]).toString();
                }
            }
            return newRef;
        } catch (UnsupportedEncodingException e) {
            this.catalogManager.debug.message(1, "UTF-8 is an unsupported encoding!?");
            return uriref;
        }
    }

    protected String encodedByte(int b) {
        String hex = Integer.toHexString(b).toUpperCase();
        if (hex.length() < 2) {
            return "%0" + hex;
        }
        return "%" + hex;
    }

    protected void addDelegate(CatalogEntry entry) {
        int pos = 0;
        String partial = entry.getEntryArg(0);
        Enumeration local = this.localDelegate.elements();
        while (local.hasMoreElements()) {
            String dp = ((CatalogEntry) local.nextElement()).getEntryArg(0);
            if (!dp.equals(partial)) {
                if (dp.length() > partial.length()) {
                    pos++;
                }
                if (dp.length() < partial.length()) {
                    break;
                }
            }
            return;
        }
        if (this.localDelegate.size() == 0) {
            this.localDelegate.addElement(entry);
        } else {
            this.localDelegate.insertElementAt(entry, pos);
        }
    }
}
