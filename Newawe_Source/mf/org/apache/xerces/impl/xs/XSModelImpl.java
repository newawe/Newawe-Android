package mf.org.apache.xerces.impl.xs;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSAttributeGroupDefinition;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSIDCDefinition;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSNamespaceItemList;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public final class XSModelImpl extends AbstractList implements XSModel, XSNamespaceItemList {
    private static final boolean[] GLOBAL_COMP;
    private static final short MAX_COMP_IDX = (short) 16;
    private XSObjectList fAnnotations;
    private final XSNamedMap[] fGlobalComponents;
    private final int fGrammarCount;
    private final SchemaGrammar[] fGrammarList;
    private final SymbolHash fGrammarMap;
    private final boolean fHasIDC;
    private final XSNamedMap[][] fNSComponents;
    private final String[] fNamespaces;
    private final StringList fNamespacesList;
    private final SymbolHash fSubGroupMap;

    private final class XSNamespaceItemListIterator implements ListIterator {
        private int index;

        public XSNamespaceItemListIterator(int index) {
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < XSModelImpl.this.fGrammarCount;
        }

        public Object next() {
            if (this.index < XSModelImpl.this.fGrammarCount) {
                SchemaGrammar[] access$1 = XSModelImpl.this.fGrammarList;
                int i = this.index;
                this.index = i + 1;
                return access$1[i];
            }
            throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return this.index > 0;
        }

        public Object previous() {
            if (this.index > 0) {
                SchemaGrammar[] access$1 = XSModelImpl.this.fGrammarList;
                int i = this.index - 1;
                this.index = i;
                return access$1[i];
            }
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return this.index;
        }

        public int previousIndex() {
            return this.index - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(Object o) {
            throw new UnsupportedOperationException();
        }

        public void add(Object o) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        boolean[] zArr = new boolean[17];
        zArr[1] = true;
        zArr[2] = true;
        zArr[3] = true;
        zArr[5] = true;
        zArr[6] = true;
        zArr[10] = true;
        zArr[11] = true;
        zArr[15] = true;
        zArr[16] = true;
        GLOBAL_COMP = zArr;
    }

    public XSModelImpl(SchemaGrammar[] grammars) {
        this(grammars, (short) 1);
    }

    public XSModelImpl(SchemaGrammar[] grammars, short s4sVersion) {
        int i;
        this.fAnnotations = null;
        int length = grammars.length;
        int initialSize = Math.max(length + 1, 5);
        String[] namespaces = new String[initialSize];
        SchemaGrammar[] grammarList = new SchemaGrammar[initialSize];
        boolean hasS4S = false;
        for (i = 0; i < length; i++) {
            SchemaGrammar sg = grammars[i];
            String tns = sg.getTargetNamespace();
            namespaces[i] = tns;
            grammarList[i] = sg;
            if (tns == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
                hasS4S = true;
            }
        }
        if (!hasS4S) {
            namespaces[length] = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            int len = length + 1;
            grammarList[length] = SchemaGrammar.getS4SGrammar(s4sVersion);
            length = len;
        }
        for (i = 0; i < length; i++) {
            Vector gs = grammarList[i].getImportedGrammars();
            int j = gs == null ? -1 : gs.size() - 1;
            while (j >= 0) {
                SchemaGrammar sg2 = (SchemaGrammar) gs.elementAt(j);
                int k = 0;
                while (k < length && sg2 != grammarList[k]) {
                    k++;
                }
                if (k == length) {
                    int length2 = grammarList.length;
                    if (length == r0) {
                        String[] newSA = new String[(length * 2)];
                        System.arraycopy(namespaces, 0, newSA, 0, length);
                        namespaces = newSA;
                        SchemaGrammar[] newGA = new SchemaGrammar[(length * 2)];
                        System.arraycopy(grammarList, 0, newGA, 0, length);
                        grammarList = newGA;
                    }
                    namespaces[length] = sg2.getTargetNamespace();
                    grammarList[length] = sg2;
                    length++;
                }
                j--;
            }
        }
        this.fNamespaces = namespaces;
        this.fGrammarList = grammarList;
        boolean hasIDC = false;
        this.fGrammarMap = new SymbolHash(length * 2);
        for (i = 0; i < length; i++) {
            this.fGrammarMap.put(null2EmptyString(this.fNamespaces[i]), this.fGrammarList[i]);
            if (this.fGrammarList[i].hasIDConstraints()) {
                hasIDC = true;
            }
        }
        this.fHasIDC = hasIDC;
        this.fGrammarCount = length;
        this.fGlobalComponents = new XSNamedMap[17];
        this.fNSComponents = (XSNamedMap[][]) Array.newInstance(XSNamedMap.class, new int[]{length, 17});
        this.fNamespacesList = new StringListImpl(this.fNamespaces, this.fGrammarCount);
        this.fSubGroupMap = buildSubGroups();
    }

    private SymbolHash buildSubGroups_Org() {
        int i;
        SubstitutionGroupHandler sgHandler = new SubstitutionGroupHandler(null);
        for (i = 0; i < this.fGrammarCount; i++) {
            sgHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSNamedMap elements = getComponents((short) 2);
        int len = elements.getLength();
        SymbolHash subGroupMap = new SymbolHash(len * 2);
        for (i = 0; i < len; i++) {
            XSElementDecl head = (XSElementDecl) elements.item(i);
            XSElementDeclaration[] subGroup = sgHandler.getSubstitutionGroup(head);
            subGroupMap.put(head, subGroup.length > 0 ? new XSObjectListImpl(subGroup, subGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return subGroupMap;
    }

    private SymbolHash buildSubGroups() {
        int i;
        SubstitutionGroupHandler sgHandler = new SubstitutionGroupHandler(null);
        for (i = 0; i < this.fGrammarCount; i++) {
            sgHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSObjectListImpl elements = getGlobalElements();
        int len = elements.getLength();
        SymbolHash subGroupMap = new SymbolHash(len * 2);
        for (i = 0; i < len; i++) {
            XSElementDecl head = (XSElementDecl) elements.item(i);
            XSElementDeclaration[] subGroup = sgHandler.getSubstitutionGroup(head);
            subGroupMap.put(head, subGroup.length > 0 ? new XSObjectListImpl(subGroup, subGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return subGroupMap;
    }

    private XSObjectListImpl getGlobalElements() {
        int i;
        SymbolHash[] tables = new SymbolHash[this.fGrammarCount];
        int length = 0;
        for (i = 0; i < this.fGrammarCount; i++) {
            tables[i] = this.fGrammarList[i].fAllGlobalElemDecls;
            length += tables[i].getLength();
        }
        if (length == 0) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        XSObject[] components = new XSObject[length];
        int start = 0;
        for (i = 0; i < this.fGrammarCount; i++) {
            tables[i].getValues(components, start);
            start += tables[i].getLength();
        }
        return new XSObjectListImpl(components, length);
    }

    public StringList getNamespaces() {
        return this.fNamespacesList;
    }

    public XSNamespaceItemList getNamespaceItems() {
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized mf.org.apache.xerces.xs.XSNamedMap getComponents(short r7) {
        /*
        r6 = this;
        r3 = 16;
        monitor-enter(r6);
        if (r7 <= 0) goto L_0x000d;
    L_0x0005:
        if (r7 > r3) goto L_0x000d;
    L_0x0007:
        r2 = GLOBAL_COMP;	 Catch:{ all -> 0x0047 }
        r2 = r2[r7];	 Catch:{ all -> 0x0047 }
        if (r2 != 0) goto L_0x0011;
    L_0x000d:
        r2 = mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl.EMPTY_MAP;	 Catch:{ all -> 0x0047 }
    L_0x000f:
        monitor-exit(r6);
        return r2;
    L_0x0011:
        r2 = r6.fGrammarCount;	 Catch:{ all -> 0x0047 }
        r1 = new mf.org.apache.xerces.util.SymbolHash[r2];	 Catch:{ all -> 0x0047 }
        r2 = r6.fGlobalComponents;	 Catch:{ all -> 0x0047 }
        r2 = r2[r7];	 Catch:{ all -> 0x0047 }
        if (r2 != 0) goto L_0x0033;
    L_0x001b:
        r0 = 0;
    L_0x001c:
        r2 = r6.fGrammarCount;	 Catch:{ all -> 0x0047 }
        if (r0 < r2) goto L_0x0038;
    L_0x0020:
        r2 = 15;
        if (r7 == r2) goto L_0x0026;
    L_0x0024:
        if (r7 != r3) goto L_0x0080;
    L_0x0026:
        r2 = r6.fGlobalComponents;	 Catch:{ all -> 0x0047 }
        r3 = new mf.org.apache.xerces.impl.xs.util.XSNamedMap4Types;	 Catch:{ all -> 0x0047 }
        r4 = r6.fNamespaces;	 Catch:{ all -> 0x0047 }
        r5 = r6.fGrammarCount;	 Catch:{ all -> 0x0047 }
        r3.<init>(r4, r1, r5, r7);	 Catch:{ all -> 0x0047 }
        r2[r7] = r3;	 Catch:{ all -> 0x0047 }
    L_0x0033:
        r2 = r6.fGlobalComponents;	 Catch:{ all -> 0x0047 }
        r2 = r2[r7];	 Catch:{ all -> 0x0047 }
        goto L_0x000f;
    L_0x0038:
        switch(r7) {
            case 1: goto L_0x004a;
            case 2: goto L_0x0053;
            case 3: goto L_0x003e;
            case 4: goto L_0x003b;
            case 5: goto L_0x005c;
            case 6: goto L_0x0065;
            case 7: goto L_0x003b;
            case 8: goto L_0x003b;
            case 9: goto L_0x003b;
            case 10: goto L_0x0077;
            case 11: goto L_0x006e;
            case 12: goto L_0x003b;
            case 13: goto L_0x003b;
            case 14: goto L_0x003b;
            case 15: goto L_0x003e;
            case 16: goto L_0x003e;
            default: goto L_0x003b;
        };	 Catch:{ all -> 0x0047 }
    L_0x003b:
        r0 = r0 + 1;
        goto L_0x001c;
    L_0x003e:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalTypeDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x0047:
        r2 = move-exception;
        monitor-exit(r6);
        throw r2;
    L_0x004a:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalAttrDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x0053:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalElemDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x005c:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalAttrGrpDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x0065:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalGroupDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x006e:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalNotationDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x0077:
        r2 = r6.fGrammarList;	 Catch:{ all -> 0x0047 }
        r2 = r2[r0];	 Catch:{ all -> 0x0047 }
        r2 = r2.fGlobalIDConstraintDecls;	 Catch:{ all -> 0x0047 }
        r1[r0] = r2;	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x0080:
        r2 = r6.fGlobalComponents;	 Catch:{ all -> 0x0047 }
        r3 = new mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl;	 Catch:{ all -> 0x0047 }
        r4 = r6.fNamespaces;	 Catch:{ all -> 0x0047 }
        r5 = r6.fGrammarCount;	 Catch:{ all -> 0x0047 }
        r3.<init>(r4, r1, r5);	 Catch:{ all -> 0x0047 }
        r2[r7] = r3;	 Catch:{ all -> 0x0047 }
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.XSModelImpl.getComponents(short):mf.org.apache.xerces.xs.XSNamedMap");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized mf.org.apache.xerces.xs.XSNamedMap getComponentsByNamespace(short r5, java.lang.String r6) {
        /*
        r4 = this;
        r3 = 16;
        monitor-enter(r4);
        if (r5 <= 0) goto L_0x000d;
    L_0x0005:
        if (r5 > r3) goto L_0x000d;
    L_0x0007:
        r2 = GLOBAL_COMP;	 Catch:{ all -> 0x009a }
        r2 = r2[r5];	 Catch:{ all -> 0x009a }
        if (r2 != 0) goto L_0x0011;
    L_0x000d:
        r2 = mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl.EMPTY_MAP;	 Catch:{ all -> 0x009a }
    L_0x000f:
        monitor-exit(r4);
        return r2;
    L_0x0011:
        r0 = 0;
        if (r6 == 0) goto L_0x0034;
    L_0x0014:
        r2 = r4.fGrammarCount;	 Catch:{ all -> 0x009a }
        if (r0 < r2) goto L_0x001f;
    L_0x0018:
        r2 = r4.fGrammarCount;	 Catch:{ all -> 0x009a }
        if (r0 != r2) goto L_0x0039;
    L_0x001c:
        r2 = mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl.EMPTY_MAP;	 Catch:{ all -> 0x009a }
        goto L_0x000f;
    L_0x001f:
        r2 = r4.fNamespaces;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r2 = r6.equals(r2);	 Catch:{ all -> 0x009a }
        if (r2 != 0) goto L_0x0018;
    L_0x0029:
        r0 = r0 + 1;
        goto L_0x0014;
    L_0x002c:
        r2 = r4.fNamespaces;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        if (r2 == 0) goto L_0x0018;
    L_0x0032:
        r0 = r0 + 1;
    L_0x0034:
        r2 = r4.fGrammarCount;	 Catch:{ all -> 0x009a }
        if (r0 < r2) goto L_0x002c;
    L_0x0038:
        goto L_0x0018;
    L_0x0039:
        r2 = r4.fNSComponents;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r2 = r2[r5];	 Catch:{ all -> 0x009a }
        if (r2 != 0) goto L_0x0056;
    L_0x0041:
        r1 = 0;
        switch(r5) {
            case 1: goto L_0x0064;
            case 2: goto L_0x006b;
            case 3: goto L_0x005d;
            case 4: goto L_0x0045;
            case 5: goto L_0x0072;
            case 6: goto L_0x0079;
            case 7: goto L_0x0045;
            case 8: goto L_0x0045;
            case 9: goto L_0x0045;
            case 10: goto L_0x0087;
            case 11: goto L_0x0080;
            case 12: goto L_0x0045;
            case 13: goto L_0x0045;
            case 14: goto L_0x0045;
            case 15: goto L_0x005d;
            case 16: goto L_0x005d;
            default: goto L_0x0045;
        };	 Catch:{ all -> 0x009a }
    L_0x0045:
        r2 = 15;
        if (r5 == r2) goto L_0x004b;
    L_0x0049:
        if (r5 != r3) goto L_0x008e;
    L_0x004b:
        r2 = r4.fNSComponents;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r3 = new mf.org.apache.xerces.impl.xs.util.XSNamedMap4Types;	 Catch:{ all -> 0x009a }
        r3.<init>(r6, r1, r5);	 Catch:{ all -> 0x009a }
        r2[r5] = r3;	 Catch:{ all -> 0x009a }
    L_0x0056:
        r2 = r4.fNSComponents;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r2 = r2[r5];	 Catch:{ all -> 0x009a }
        goto L_0x000f;
    L_0x005d:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalTypeDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x0064:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalAttrDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x006b:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalElemDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x0072:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalAttrGrpDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x0079:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalGroupDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x0080:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalNotationDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x0087:
        r2 = r4.fGrammarList;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r1 = r2.fGlobalIDConstraintDecls;	 Catch:{ all -> 0x009a }
        goto L_0x0045;
    L_0x008e:
        r2 = r4.fNSComponents;	 Catch:{ all -> 0x009a }
        r2 = r2[r0];	 Catch:{ all -> 0x009a }
        r3 = new mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl;	 Catch:{ all -> 0x009a }
        r3.<init>(r6, r1);	 Catch:{ all -> 0x009a }
        r2[r5] = r3;	 Catch:{ all -> 0x009a }
        goto L_0x0056;
    L_0x009a:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.XSModelImpl.getComponentsByNamespace(short, java.lang.String):mf.org.apache.xerces.xs.XSNamedMap");
    }

    public XSTypeDefinition getTypeDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSTypeDefinition) sg.fGlobalTypeDecls.get(name);
    }

    public XSTypeDefinition getTypeDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalTypeDecl(name, loc);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeDeclaration) sg.fGlobalAttrDecls.get(name);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalAttributeDecl(name, loc);
    }

    public XSElementDeclaration getElementDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSElementDeclaration) sg.fGlobalElemDecls.get(name);
    }

    public XSElementDeclaration getElementDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalElementDecl(name, loc);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeGroupDefinition) sg.fGlobalAttrGrpDecls.get(name);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalAttributeGroupDecl(name, loc);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSModelGroupDefinition) sg.fGlobalGroupDecls.get(name);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalGroupDecl(name, loc);
    }

    public XSIDCDefinition getIDCDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSIDCDefinition) sg.fGlobalIDConstraintDecls.get(name);
    }

    public XSIDCDefinition getIDCDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getIDConstraintDecl(name, loc);
    }

    public XSNotationDeclaration getNotationDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSNotationDeclaration) sg.fGlobalNotationDecls.get(name);
    }

    public XSNotationDeclaration getNotationDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalNotationDecl(name, loc);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized mf.org.apache.xerces.xs.XSObjectList getAnnotations() {
        /*
        r8 = this;
        monitor-enter(r8);
        r5 = r8.fAnnotations;	 Catch:{ all -> 0x004b }
        if (r5 == 0) goto L_0x0009;
    L_0x0005:
        r5 = r8.fAnnotations;	 Catch:{ all -> 0x004b }
    L_0x0007:
        monitor-exit(r8);
        return r5;
    L_0x0009:
        r4 = 0;
        r3 = 0;
    L_0x000b:
        r5 = r8.fGrammarCount;	 Catch:{ all -> 0x004b }
        if (r3 < r5) goto L_0x0018;
    L_0x000f:
        if (r4 != 0) goto L_0x0022;
    L_0x0011:
        r5 = mf.org.apache.xerces.impl.xs.util.XSObjectListImpl.EMPTY_LIST;	 Catch:{ all -> 0x004b }
        r8.fAnnotations = r5;	 Catch:{ all -> 0x004b }
        r5 = r8.fAnnotations;	 Catch:{ all -> 0x004b }
        goto L_0x0007;
    L_0x0018:
        r5 = r8.fGrammarList;	 Catch:{ all -> 0x004b }
        r5 = r5[r3];	 Catch:{ all -> 0x004b }
        r5 = r5.fNumAnnotations;	 Catch:{ all -> 0x004b }
        r4 = r4 + r5;
        r3 = r3 + 1;
        goto L_0x000b;
    L_0x0022:
        r0 = new mf.org.apache.xerces.impl.xs.XSAnnotationImpl[r4];	 Catch:{ all -> 0x004b }
        r2 = 0;
        r3 = 0;
    L_0x0026:
        r5 = r8.fGrammarCount;	 Catch:{ all -> 0x004b }
        if (r3 < r5) goto L_0x0035;
    L_0x002a:
        r5 = new mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;	 Catch:{ all -> 0x004b }
        r6 = r0.length;	 Catch:{ all -> 0x004b }
        r5.<init>(r0, r6);	 Catch:{ all -> 0x004b }
        r8.fAnnotations = r5;	 Catch:{ all -> 0x004b }
        r5 = r8.fAnnotations;	 Catch:{ all -> 0x004b }
        goto L_0x0007;
    L_0x0035:
        r5 = r8.fGrammarList;	 Catch:{ all -> 0x004b }
        r1 = r5[r3];	 Catch:{ all -> 0x004b }
        r5 = r1.fNumAnnotations;	 Catch:{ all -> 0x004b }
        if (r5 <= 0) goto L_0x0048;
    L_0x003d:
        r5 = r1.fAnnotations;	 Catch:{ all -> 0x004b }
        r6 = 0;
        r7 = r1.fNumAnnotations;	 Catch:{ all -> 0x004b }
        java.lang.System.arraycopy(r5, r6, r0, r2, r7);	 Catch:{ all -> 0x004b }
        r5 = r1.fNumAnnotations;	 Catch:{ all -> 0x004b }
        r2 = r2 + r5;
    L_0x0048:
        r3 = r3 + 1;
        goto L_0x0026;
    L_0x004b:
        r5 = move-exception;
        monitor-exit(r8);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.XSModelImpl.getAnnotations():mf.org.apache.xerces.xs.XSObjectList");
    }

    private static final String null2EmptyString(String str) {
        return str == null ? XMLSymbols.EMPTY_STRING : str;
    }

    public boolean hasIDConstraints() {
        return this.fHasIDC;
    }

    public XSObjectList getSubstitutionGroup(XSElementDeclaration head) {
        return (XSObjectList) this.fSubGroupMap.get(head);
    }

    public int getLength() {
        return this.fGrammarCount;
    }

    public XSNamespaceItem item(int index) {
        if (index < 0 || index >= this.fGrammarCount) {
            return null;
        }
        return this.fGrammarList[index];
    }

    public Object get(int index) {
        if (index >= 0 && index < this.fGrammarCount) {
            return this.fGrammarList[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Iterator iterator() {
        return listIterator0(0);
    }

    public ListIterator listIterator() {
        return listIterator0(0);
    }

    public ListIterator listIterator(int index) {
        if (index >= 0 && index < this.fGrammarCount) {
            return listIterator0(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    private ListIterator listIterator0(int index) {
        return new XSNamespaceItemListIterator(index);
    }

    public Object[] toArray() {
        Object[] a = new Object[this.fGrammarCount];
        toArray0(a);
        return a;
    }

    public Object[] toArray(Object[] a) {
        if (a.length < this.fGrammarCount) {
            a = (Object[]) Array.newInstance(a.getClass().getComponentType(), this.fGrammarCount);
        }
        toArray0(a);
        if (a.length > this.fGrammarCount) {
            a[this.fGrammarCount] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (this.fGrammarCount > 0) {
            System.arraycopy(this.fGrammarList, 0, a, 0, this.fGrammarCount);
        }
    }
}
