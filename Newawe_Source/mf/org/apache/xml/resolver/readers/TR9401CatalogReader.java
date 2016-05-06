package mf.org.apache.xml.resolver.readers;

public class TR9401CatalogReader extends TextCatalogReader {
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
        if (r10 == 0) goto L_0x0078;
    L_0x003a:
        r4 = r6;
    L_0x003b:
        r10 = "DELEGATE";
        r10 = r4.equals(r10);	 Catch:{ CatalogException -> 0x001d }
        if (r10 == 0) goto L_0x0045;
    L_0x0043:
        r4 = "DELEGATE_PUBLIC";
    L_0x0045:
        r7 = mf.org.apache.xml.resolver.CatalogEntry.getEntryType(r4);	 Catch:{ CatalogException -> 0x0064 }
        r5 = mf.org.apache.xml.resolver.CatalogEntry.getEntryArgCount(r7);	 Catch:{ CatalogException -> 0x0064 }
        r0 = new java.util.Vector;	 Catch:{ CatalogException -> 0x0064 }
        r0.<init>();	 Catch:{ CatalogException -> 0x0064 }
        if (r8 == 0) goto L_0x0058;
    L_0x0054:
        r14.unknownEntry(r8);	 Catch:{ CatalogException -> 0x0064 }
        r8 = 0;
    L_0x0058:
        r3 = 0;
    L_0x0059:
        if (r3 < r5) goto L_0x007d;
    L_0x005b:
        r10 = new mf.org.apache.xml.resolver.CatalogEntry;	 Catch:{ CatalogException -> 0x0064 }
        r10.<init>(r4, r0);	 Catch:{ CatalogException -> 0x0064 }
        r14.addEntry(r10);	 Catch:{ CatalogException -> 0x0064 }
        goto L_0x0008;
    L_0x0064:
        r1 = move-exception;
        r9 = r8;
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00b6 }
        r11 = 3;
        if (r10 != r11) goto L_0x0087;
    L_0x006d:
        if (r9 != 0) goto L_0x00bd;
    L_0x006f:
        r8 = new java.util.Vector;	 Catch:{ CatalogException -> 0x00b6 }
        r8.<init>();	 Catch:{ CatalogException -> 0x00b6 }
    L_0x0074:
        r8.addElement(r6);	 Catch:{ CatalogException -> 0x001d }
        goto L_0x0008;
    L_0x0078:
        r4 = r6.toUpperCase();	 Catch:{ CatalogException -> 0x001d }
        goto L_0x003b;
    L_0x007d:
        r10 = r13.nextToken();	 Catch:{ CatalogException -> 0x0064 }
        r0.addElement(r10);	 Catch:{ CatalogException -> 0x0064 }
        r3 = r3 + 1;
        goto L_0x0059;
    L_0x0087:
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00b6 }
        r11 = 2;
        if (r10 != r11) goto L_0x009d;
    L_0x008e:
        r10 = r14.getCatalogManager();	 Catch:{ CatalogException -> 0x00b6 }
        r10 = r10.debug;	 Catch:{ CatalogException -> 0x00b6 }
        r11 = 1;
        r12 = "Invalid catalog entry";
        r10.message(r11, r12, r6);	 Catch:{ CatalogException -> 0x00b6 }
        r8 = 0;
        goto L_0x0008;
    L_0x009d:
        r10 = r1.getExceptionType();	 Catch:{ CatalogException -> 0x00b6 }
        r11 = 8;
        if (r10 != r11) goto L_0x00ba;
    L_0x00a5:
        r10 = r14.getCatalogManager();	 Catch:{ CatalogException -> 0x00b6 }
        r10 = r10.debug;	 Catch:{ CatalogException -> 0x00b6 }
        r11 = 1;
        r12 = r1.getMessage();	 Catch:{ CatalogException -> 0x00b6 }
        r10.message(r11, r12);	 Catch:{ CatalogException -> 0x00b6 }
        r8 = r9;
        goto L_0x0008;
    L_0x00b6:
        r2 = move-exception;
        r8 = r9;
        goto L_0x001e;
    L_0x00ba:
        r8 = r9;
        goto L_0x0008;
    L_0x00bd:
        r8 = r9;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.readers.TR9401CatalogReader.readCatalog(mf.org.apache.xml.resolver.Catalog, java.io.InputStream):void");
    }
}
