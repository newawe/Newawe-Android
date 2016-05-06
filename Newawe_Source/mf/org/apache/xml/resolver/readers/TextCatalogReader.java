package mf.org.apache.xml.resolver.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;
import mf.org.apache.xml.resolver.Catalog;

public class TextCatalogReader implements CatalogReader {
    protected boolean caseSensitive;
    protected InputStream catfile;
    protected int[] stack;
    protected Stack tokenStack;
    protected int top;

    public TextCatalogReader() {
        this.catfile = null;
        this.stack = new int[3];
        this.tokenStack = new Stack();
        this.top = -1;
        this.caseSensitive = false;
    }

    public void setCaseSensitive(boolean isCaseSensitive) {
        this.caseSensitive = isCaseSensitive;
    }

    public boolean getCaseSensitive() {
        return this.caseSensitive;
    }

    public void readCatalog(Catalog catalog, String fileUrl) throws MalformedURLException, IOException {
        URL catURL;
        try {
            catURL = new URL(fileUrl);
        } catch (MalformedURLException e) {
            catURL = new URL("file:///" + fileUrl);
        }
        try {
            readCatalog(catalog, catURL.openConnection().getInputStream());
        } catch (FileNotFoundException e2) {
            catalog.getCatalogManager().debug.message(1, "Failed to load catalog, file not found", catURL.toString());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readCatalog(mf.org.apache.xml.resolver.Catalog r14, java.io.InputStream r15) throws java.net.MalformedURLException, java.io.IOException {
        /*
        r13 = this;
        r13.catfile = r15;
        r10 = r13.catfile;
        if (r10 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r8 = 0;
    L_0x0008:
        r6 = r13.nextToken();	 Catch:{ CatalogException -> 0x001d }
        if (r6 != 0) goto L_0x0035;
    L_0x000e:
        if (r8 == 0) goto L_0x0014;
    L_0x0010:
        r14.unknownEntry(r8);	 Catch:{ CatalogException -> 0x001d }
        r8 = 0;
    L_0x0014:
        r10 = r13.catfile;	 Catch:{ CatalogException -> 0x001d }
        r10.close();	 Catch:{ CatalogException -> 0x001d }
        r10 = 0;
        r13.catfile = r10;	 Catch:{ CatalogException -> 0x001d }
        goto L_0x0006;
    L_0x001d:
        r2 = move-exception;
    L_0x001e:
        r10 = r2.getExceptionType();
        r11 = 8;
        if (r10 != r11) goto L_0x0006;
    L_0x0026:
        r10 = r14.getCatalogManager();
        r10 = r10.debug;
        r11 = 1;
        r12 = r2.getMessage();
        r10.message(r11, r12);
        goto L_0x0006;
    L_0x0035:
        r4 = 0;
        r10 = r13.caseSensitive;	 Catch:{ CatalogException -> 0x001d }
        if (r10 == 0) goto L_0x006e;
    L_0x003a:
        r4 = r6;
    L_0x003b:
        r7 = mf.org.apache.xml.resolver.CatalogEntry.getEntryType(r4);	 Catch:{ CatalogException -> 0x005a }
        r5 = mf.org.apache.xml.resolver.CatalogEntry.getEntryArgCount(r7);	 Catch:{ CatalogException -> 0x005a }
        r0 = new java.util.Vector;	 Catch:{ CatalogException -> 0x005a }
        r0.<init>();	 Catch:{ CatalogException -> 0x005a }
        if (r8 == 0) goto L_0x004e;
    L_0x004a:
        r14.unknownEntry(r8);	 Catch:{ CatalogException -> 0x005a }
        r8 = 0;
    L_0x004e:
        r3 = 0;
    L_0x004f:
        if (r3 < r5) goto L_0x0073;
    L_0x0051:
        r10 = new mf.org.apache.xml.resolver.CatalogEntry;	 Catch:{ CatalogException -> 0x005a }
        r10.<init>(r4, r0);	 Catch:{ CatalogException -> 0x005a }
        r14.addEntry(r10);	 Catch:{ CatalogException -> 0x005a }
        goto L_0x0008;
    L_0x005a:
        r1 = move-exception;
        r9 = r8;
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00ac }
        r11 = 3;
        if (r10 != r11) goto L_0x007d;
    L_0x0063:
        if (r9 != 0) goto L_0x00b3;
    L_0x0065:
        r8 = new java.util.Vector;	 Catch:{ CatalogException -> 0x00ac }
        r8.<init>();	 Catch:{ CatalogException -> 0x00ac }
    L_0x006a:
        r8.addElement(r6);	 Catch:{ CatalogException -> 0x001d }
        goto L_0x0008;
    L_0x006e:
        r4 = r6.toUpperCase();	 Catch:{ CatalogException -> 0x001d }
        goto L_0x003b;
    L_0x0073:
        r10 = r13.nextToken();	 Catch:{ CatalogException -> 0x005a }
        r0.addElement(r10);	 Catch:{ CatalogException -> 0x005a }
        r3 = r3 + 1;
        goto L_0x004f;
    L_0x007d:
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00ac }
        r11 = 2;
        if (r10 != r11) goto L_0x0093;
    L_0x0084:
        r10 = r14.getCatalogManager();	 Catch:{ CatalogException -> 0x00ac }
        r10 = r10.debug;	 Catch:{ CatalogException -> 0x00ac }
        r11 = 1;
        r12 = "Invalid catalog entry";
        r10.message(r11, r12, r6);	 Catch:{ CatalogException -> 0x00ac }
        r8 = 0;
        goto L_0x0008;
    L_0x0093:
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00ac }
        r11 = 8;
        if (r10 != r11) goto L_0x00b0;
    L_0x009b:
        r10 = r14.getCatalogManager();	 Catch:{ CatalogException -> 0x00ac }
        r10 = r10.debug;	 Catch:{ CatalogException -> 0x00ac }
        r11 = 1;
        r12 = r1.getMessage();	 Catch:{ CatalogException -> 0x00ac }
        r10.message(r11, r12);	 Catch:{ CatalogException -> 0x00ac }
        r8 = r9;
        goto L_0x0008;
    L_0x00ac:
        r2 = move-exception;
        r8 = r9;
        goto L_0x001e;
    L_0x00b0:
        r8 = r9;
        goto L_0x0008;
    L_0x00b3:
        r8 = r9;
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.readers.TextCatalogReader.readCatalog(mf.org.apache.xml.resolver.Catalog, java.io.InputStream):void");
    }

    protected void finalize() {
        if (this.catfile != null) {
            try {
                this.catfile.close();
            } catch (IOException e) {
            }
        }
        this.catfile = null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String nextToken() throws java.io.IOException, mf.org.apache.xml.resolver.CatalogException {
        /*
        r12 = this;
        r6 = 0;
        r11 = 32;
        r10 = 1;
        r9 = 0;
        r8 = 45;
        r5 = "";
        r7 = r12.tokenStack;
        r7 = r7.empty();
        if (r7 != 0) goto L_0x001a;
    L_0x0011:
        r6 = r12.tokenStack;
        r6 = r6.pop();
        r6 = (java.lang.String) r6;
    L_0x0019:
        return r6;
    L_0x001a:
        r7 = r12.catfile;
        r0 = r7.read();
    L_0x0020:
        if (r0 <= r11) goto L_0x0046;
    L_0x0022:
        r7 = r12.catfile;
        r2 = r7.read();
        if (r2 < 0) goto L_0x0019;
    L_0x002a:
        if (r0 != r8) goto L_0x0055;
    L_0x002c:
        if (r2 != r8) goto L_0x0055;
    L_0x002e:
        r0 = 32;
        r2 = r12.nextChar();
    L_0x0034:
        if (r0 != r8) goto L_0x0038;
    L_0x0036:
        if (r2 == r8) goto L_0x003a;
    L_0x0038:
        if (r2 > 0) goto L_0x004f;
    L_0x003a:
        if (r2 >= 0) goto L_0x001a;
    L_0x003c:
        r6 = new mf.org.apache.xml.resolver.CatalogException;
        r7 = 8;
        r8 = "Unterminated comment in catalog file; EOF treated as end-of-comment.";
        r6.<init>(r7, r8);
        throw r6;
    L_0x0046:
        r7 = r12.catfile;
        r0 = r7.read();
        if (r0 >= 0) goto L_0x0020;
    L_0x004e:
        goto L_0x0019;
    L_0x004f:
        r0 = r2;
        r2 = r12.nextChar();
        goto L_0x0034;
    L_0x0055:
        r6 = r12.stack;
        r7 = r12.top;
        r7 = r7 + 1;
        r12.top = r7;
        r6[r7] = r2;
        r6 = r12.stack;
        r7 = r12.top;
        r7 = r7 + 1;
        r12.top = r7;
        r6[r7] = r0;
        r0 = r12.nextChar();
        r6 = 34;
        if (r0 == r6) goto L_0x0075;
    L_0x0071:
        r6 = 39;
        if (r0 != r6) goto L_0x00bb;
    L_0x0075:
        r3 = r0;
    L_0x0076:
        r0 = r12.nextChar();
        if (r0 != r3) goto L_0x007e;
    L_0x007c:
        r6 = r5;
        goto L_0x0019;
    L_0x007e:
        r1 = new char[r10];
        r6 = (char) r0;
        r1[r9] = r6;
        r4 = new java.lang.String;
        r4.<init>(r1);
        r5 = r5.concat(r4);
        goto L_0x0076;
    L_0x008d:
        r2 = r12.nextChar();
        if (r0 != r8) goto L_0x00ac;
    L_0x0093:
        if (r2 != r8) goto L_0x00ac;
    L_0x0095:
        r6 = r12.stack;
        r7 = r12.top;
        r7 = r7 + 1;
        r12.top = r7;
        r6[r7] = r0;
        r6 = r12.stack;
        r7 = r12.top;
        r7 = r7 + 1;
        r12.top = r7;
        r6[r7] = r2;
        r6 = r5;
        goto L_0x0019;
    L_0x00ac:
        r1 = new char[r10];
        r6 = (char) r0;
        r1[r9] = r6;
        r4 = new java.lang.String;
        r4.<init>(r1);
        r5 = r5.concat(r4);
        r0 = r2;
    L_0x00bb:
        if (r0 > r11) goto L_0x008d;
    L_0x00bd:
        r6 = r5;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.readers.TextCatalogReader.nextToken():java.lang.String");
    }

    protected int nextChar() throws IOException {
        if (this.top < 0) {
            return this.catfile.read();
        }
        int[] iArr = this.stack;
        int i = this.top;
        this.top = i - 1;
        return iArr[i];
    }
}
