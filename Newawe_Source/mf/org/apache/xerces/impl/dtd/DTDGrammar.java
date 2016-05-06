package mf.org.apache.xerces.impl.dtd;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.Hashtable;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.impl.dtd.models.CMAny;
import mf.org.apache.xerces.impl.dtd.models.CMBinOp;
import mf.org.apache.xerces.impl.dtd.models.CMLeaf;
import mf.org.apache.xerces.impl.dtd.models.CMNode;
import mf.org.apache.xerces.impl.dtd.models.CMUniOp;
import mf.org.apache.xerces.impl.dtd.models.ContentModelValidator;
import mf.org.apache.xerces.impl.dtd.models.DFAContentModel;
import mf.org.apache.xerces.impl.dtd.models.MixedContentModel;
import mf.org.apache.xerces.impl.dtd.models.SimpleContentModel;
import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.validation.EntityState;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import mf.org.apache.xerces.xni.parser.XMLDTDSource;

public class DTDGrammar implements XMLDTDHandler, XMLDTDContentModelHandler, EntityState, Grammar {
    private static final int CHUNK_MASK = 255;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final boolean DEBUG = false;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private static final short LIST_FLAG = (short) 128;
    private static final short LIST_MASK = (short) -129;
    public static final int TOP_LEVEL_SCOPE = -1;
    protected final XMLAttributeDecl fAttributeDecl;
    private int fAttributeDeclCount;
    private DatatypeValidator[][] fAttributeDeclDatatypeValidator;
    private short[][] fAttributeDeclDefaultType;
    private String[][] fAttributeDeclDefaultValue;
    private String[][][] fAttributeDeclEnumeration;
    private int[][] fAttributeDeclIsExternal;
    private QName[][] fAttributeDeclName;
    private int[][] fAttributeDeclNextAttributeDeclIndex;
    private String[][] fAttributeDeclNonNormalizedDefaultValue;
    private short[][] fAttributeDeclType;
    private XMLContentSpec fContentSpec;
    private int fContentSpecCount;
    private Object[][] fContentSpecOtherValue;
    private short[][] fContentSpecType;
    private Object[][] fContentSpecValue;
    protected int fCurrentAttributeIndex;
    protected int fCurrentElementIndex;
    protected XMLDTDContentModelSource fDTDContentModelSource;
    protected XMLDTDSource fDTDSource;
    private int fDepth;
    private XMLElementDecl fElementDecl;
    private ContentModelValidator[][] fElementDeclContentModelValidator;
    private int[][] fElementDeclContentSpecIndex;
    private int fElementDeclCount;
    private int[][] fElementDeclFirstAttributeDeclIndex;
    private int[][] fElementDeclIsExternal;
    private int[][] fElementDeclLastAttributeDeclIndex;
    private QName[][] fElementDeclName;
    Hashtable fElementDeclTab;
    private short[][] fElementDeclType;
    private QNameHashtable fElementIndexMap;
    private String[][] fEntityBaseSystemId;
    private int fEntityCount;
    private XMLEntityDecl fEntityDecl;
    private byte[][] fEntityInExternal;
    private QNameHashtable fEntityIndexMap;
    private byte[][] fEntityIsPE;
    private String[][] fEntityName;
    private String[][] fEntityNotation;
    private String[][] fEntityPublicId;
    private String[][] fEntitySystemId;
    private String[][] fEntityValue;
    private int fEpsilonIndex;
    protected XMLDTDDescription fGrammarDescription;
    private boolean fIsImmutable;
    private int fLeafCount;
    private boolean fMixed;
    private int[] fNodeIndexStack;
    private String[][] fNotationBaseSystemId;
    private int fNotationCount;
    private QNameHashtable fNotationIndexMap;
    private String[][] fNotationName;
    private String[][] fNotationPublicId;
    private String[][] fNotationSystemId;
    private short[] fOpStack;
    private int fPEDepth;
    private boolean[] fPEntityStack;
    private int[] fPrevNodeIndexStack;
    private final QName fQName;
    private final QName fQName2;
    protected boolean fReadingExternalDTD;
    private XMLSimpleType fSimpleType;
    private SymbolTable fSymbolTable;
    int nodeIndex;
    int prevNodeIndex;
    int valueIndex;

    private static class ChildrenList {
        public int length;
        public QName[] qname;
        public int[] type;

        public ChildrenList() {
            this.length = 0;
            this.qname = new QName[2];
            this.type = new int[2];
        }
    }

    protected static final class QNameHashtable {
        private static final int HASHTABLE_SIZE = 101;
        private static final int INITIAL_BUCKET_SIZE = 4;
        private Object[][] fHashTable;

        protected QNameHashtable() {
            this.fHashTable = new Object[HASHTABLE_SIZE][];
        }

        public void put(String key, int value) {
            int hash = (key.hashCode() & ASContentModel.AS_UNBOUNDED) % HASHTABLE_SIZE;
            Object[] bucket = this.fHashTable[hash];
            if (bucket == null) {
                bucket = new Object[9];
                bucket[0] = new int[]{1};
                bucket[1] = key;
                bucket[2] = new int[]{value};
                this.fHashTable[hash] = bucket;
                return;
            }
            int count = ((int[]) bucket[0])[0];
            int offset = (count * 2) + 1;
            if (offset == bucket.length) {
                Object[] newBucket = new Object[(((count + INITIAL_BUCKET_SIZE) * 2) + 1)];
                System.arraycopy(bucket, 0, newBucket, 0, offset);
                bucket = newBucket;
                this.fHashTable[hash] = bucket;
            }
            boolean found = DTDGrammar.DEBUG;
            int j = 1;
            for (int i = 0; i < count; i++) {
                if (((String) bucket[j]) == key) {
                    ((int[]) bucket[j + 1])[0] = value;
                    found = true;
                    break;
                }
                j += 2;
            }
            if (!found) {
                int offset2 = offset + 1;
                bucket[offset] = key;
                bucket[offset2] = new int[]{value};
                ((int[]) bucket[0])[0] = count + 1;
            }
        }

        public int get(String key) {
            Object[] bucket = this.fHashTable[(key.hashCode() & ASContentModel.AS_UNBOUNDED) % HASHTABLE_SIZE];
            if (bucket == null) {
                return DTDGrammar.TOP_LEVEL_SCOPE;
            }
            int count = ((int[]) bucket[0])[0];
            int j = 1;
            for (int i = 0; i < count; i++) {
                if (((String) bucket[j]) == key) {
                    return ((int[]) bucket[j + 1])[0];
                }
                j += 2;
            }
            return DTDGrammar.TOP_LEVEL_SCOPE;
        }
    }

    public DTDGrammar(SymbolTable symbolTable, XMLDTDDescription desc) {
        this.fDTDSource = null;
        this.fDTDContentModelSource = null;
        this.fReadingExternalDTD = DEBUG;
        this.fGrammarDescription = null;
        this.fElementDeclCount = 0;
        this.fElementDeclName = new QName[INITIAL_CHUNK_COUNT][];
        this.fElementDeclType = new short[INITIAL_CHUNK_COUNT][];
        this.fElementDeclContentSpecIndex = new int[INITIAL_CHUNK_COUNT][];
        this.fElementDeclContentModelValidator = new ContentModelValidator[INITIAL_CHUNK_COUNT][];
        this.fElementDeclFirstAttributeDeclIndex = new int[INITIAL_CHUNK_COUNT][];
        this.fElementDeclLastAttributeDeclIndex = new int[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclCount = 0;
        this.fAttributeDeclName = new QName[INITIAL_CHUNK_COUNT][];
        this.fIsImmutable = DEBUG;
        this.fAttributeDeclType = new short[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclEnumeration = new String[INITIAL_CHUNK_COUNT][][];
        this.fAttributeDeclDefaultType = new short[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclDatatypeValidator = new DatatypeValidator[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclDefaultValue = new String[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclNonNormalizedDefaultValue = new String[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclNextAttributeDeclIndex = new int[INITIAL_CHUNK_COUNT][];
        this.fContentSpecCount = 0;
        this.fContentSpecType = new short[INITIAL_CHUNK_COUNT][];
        this.fContentSpecValue = new Object[INITIAL_CHUNK_COUNT][];
        this.fContentSpecOtherValue = new Object[INITIAL_CHUNK_COUNT][];
        this.fEntityCount = 0;
        this.fEntityName = new String[INITIAL_CHUNK_COUNT][];
        this.fEntityValue = new String[INITIAL_CHUNK_COUNT][];
        this.fEntityPublicId = new String[INITIAL_CHUNK_COUNT][];
        this.fEntitySystemId = new String[INITIAL_CHUNK_COUNT][];
        this.fEntityBaseSystemId = new String[INITIAL_CHUNK_COUNT][];
        this.fEntityNotation = new String[INITIAL_CHUNK_COUNT][];
        this.fEntityIsPE = new byte[INITIAL_CHUNK_COUNT][];
        this.fEntityInExternal = new byte[INITIAL_CHUNK_COUNT][];
        this.fNotationCount = 0;
        this.fNotationName = new String[INITIAL_CHUNK_COUNT][];
        this.fNotationPublicId = new String[INITIAL_CHUNK_COUNT][];
        this.fNotationSystemId = new String[INITIAL_CHUNK_COUNT][];
        this.fNotationBaseSystemId = new String[INITIAL_CHUNK_COUNT][];
        this.fElementIndexMap = new QNameHashtable();
        this.fEntityIndexMap = new QNameHashtable();
        this.fNotationIndexMap = new QNameHashtable();
        this.fQName = new QName();
        this.fQName2 = new QName();
        this.fAttributeDecl = new XMLAttributeDecl();
        this.fLeafCount = 0;
        this.fEpsilonIndex = TOP_LEVEL_SCOPE;
        this.fElementDecl = new XMLElementDecl();
        this.fEntityDecl = new XMLEntityDecl();
        this.fSimpleType = new XMLSimpleType();
        this.fContentSpec = new XMLContentSpec();
        this.fElementDeclTab = new Hashtable();
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
        this.fDepth = 0;
        this.fPEntityStack = new boolean[INITIAL_CHUNK_COUNT];
        this.fPEDepth = 0;
        this.fElementDeclIsExternal = new int[INITIAL_CHUNK_COUNT][];
        this.fAttributeDeclIsExternal = new int[INITIAL_CHUNK_COUNT][];
        this.valueIndex = TOP_LEVEL_SCOPE;
        this.prevNodeIndex = TOP_LEVEL_SCOPE;
        this.nodeIndex = TOP_LEVEL_SCOPE;
        this.fSymbolTable = symbolTable;
        this.fGrammarDescription = desc;
    }

    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }

    public boolean getElementDeclIsExternal(int elementDeclIndex) {
        if (elementDeclIndex < 0) {
            return DEBUG;
        }
        if (this.fElementDeclIsExternal[elementDeclIndex >> CHUNK_SHIFT][elementDeclIndex & CHUNK_MASK] != 0) {
            return true;
        }
        return DEBUG;
    }

    public boolean getAttributeDeclIsExternal(int attributeDeclIndex) {
        if (attributeDeclIndex < 0) {
            return DEBUG;
        }
        if (this.fAttributeDeclIsExternal[attributeDeclIndex >> CHUNK_SHIFT][attributeDeclIndex & CHUNK_MASK] != 0) {
            return true;
        }
        return DEBUG;
    }

    public int getAttributeDeclIndex(int elementDeclIndex, String attributeDeclName) {
        if (elementDeclIndex == TOP_LEVEL_SCOPE) {
            return TOP_LEVEL_SCOPE;
        }
        int attDefIndex = getFirstAttributeDeclIndex(elementDeclIndex);
        while (attDefIndex != TOP_LEVEL_SCOPE) {
            getAttributeDecl(attDefIndex, this.fAttributeDecl);
            if (this.fAttributeDecl.name.rawname == attributeDeclName || attributeDeclName.equals(this.fAttributeDecl.name.rawname)) {
                return attDefIndex;
            }
            attDefIndex = getNextAttributeDeclIndex(attDefIndex);
        }
        return TOP_LEVEL_SCOPE;
    }

    public void startDTD(XMLLocator locator, Augmentations augs) throws XNIException {
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
    }

    public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (this.fPEDepth == this.fPEntityStack.length) {
            boolean[] entityarray = new boolean[(this.fPEntityStack.length * 2)];
            System.arraycopy(this.fPEntityStack, 0, entityarray, 0, this.fPEntityStack.length);
            this.fPEntityStack = entityarray;
        }
        this.fPEntityStack[this.fPEDepth] = this.fReadingExternalDTD;
        this.fPEDepth++;
    }

    public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        this.fReadingExternalDTD = true;
    }

    public void endParameterEntity(String name, Augmentations augs) throws XNIException {
        this.fPEDepth += TOP_LEVEL_SCOPE;
        this.fReadingExternalDTD = this.fPEntityStack[this.fPEDepth];
    }

    public void endExternalSubset(Augmentations augs) throws XNIException {
        this.fReadingExternalDTD = DEBUG;
    }

    public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
        short s = (short) 0;
        XMLElementDecl tmpElementDecl = (XMLElementDecl) this.fElementDeclTab.get(name);
        if (tmpElementDecl == null) {
            this.fCurrentElementIndex = createElementDecl();
        } else if (tmpElementDecl.type == (short) -1) {
            this.fCurrentElementIndex = getElementDeclIndex(name);
        } else {
            return;
        }
        XMLElementDecl elementDecl = new XMLElementDecl();
        this.fQName.setValues(null, name, name, null);
        elementDecl.name.setValues(this.fQName);
        elementDecl.contentModelValidator = null;
        elementDecl.scope = TOP_LEVEL_SCOPE;
        if (contentModel.equals("EMPTY")) {
            elementDecl.type = (short) 1;
        } else if (contentModel.equals("ANY")) {
            elementDecl.type = (short) 0;
        } else if (contentModel.startsWith("(")) {
            if (contentModel.indexOf("#PCDATA") > 0) {
                elementDecl.type = (short) 2;
            } else {
                elementDecl.type = (short) 3;
            }
        }
        this.fElementDeclTab.put(name, elementDecl);
        this.fElementDecl = elementDecl;
        addContentSpecToElement(elementDecl);
        setElementDecl(this.fCurrentElementIndex, this.fElementDecl);
        int chunk = this.fCurrentElementIndex >> CHUNK_SHIFT;
        int index = this.fCurrentElementIndex & CHUNK_MASK;
        ensureElementDeclCapacity(chunk);
        int[] iArr = this.fElementDeclIsExternal[chunk];
        if (this.fReadingExternalDTD || this.fPEDepth > 0) {
            s = (short) 1;
        }
        iArr[index] = s;
    }

    public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augs) throws XNIException {
        if (!this.fElementDeclTab.containsKey(elementName)) {
            this.fCurrentElementIndex = createElementDecl();
            XMLElementDecl elementDecl = new XMLElementDecl();
            elementDecl.name.setValues(null, elementName, elementName, null);
            elementDecl.scope = TOP_LEVEL_SCOPE;
            this.fElementDeclTab.put(elementName, elementDecl);
            setElementDecl(this.fCurrentElementIndex, elementDecl);
        }
        int elementIndex = getElementDeclIndex(elementName);
        if (getAttributeDeclIndex(elementIndex, attributeName) == TOP_LEVEL_SCOPE) {
            int i;
            this.fCurrentAttributeIndex = createAttributeDecl();
            this.fSimpleType.clear();
            if (defaultType != null) {
                if (defaultType.equals("#FIXED")) {
                    this.fSimpleType.defaultType = (short) 1;
                } else if (defaultType.equals("#IMPLIED")) {
                    this.fSimpleType.defaultType = (short) 0;
                } else if (defaultType.equals("#REQUIRED")) {
                    this.fSimpleType.defaultType = (short) 2;
                }
            }
            this.fSimpleType.defaultValue = defaultValue != null ? defaultValue.toString() : null;
            this.fSimpleType.nonNormalizedDefaultValue = nonNormalizedDefaultValue != null ? nonNormalizedDefaultValue.toString() : null;
            this.fSimpleType.enumeration = enumeration;
            if (type.equals("CDATA")) {
                this.fSimpleType.type = (short) 0;
            } else if (type.equals(SchemaSymbols.ATTVAL_ID)) {
                this.fSimpleType.type = (short) 3;
            } else if (type.startsWith(SchemaSymbols.ATTVAL_IDREF)) {
                this.fSimpleType.type = (short) 4;
                if (type.indexOf("S") > 0) {
                    this.fSimpleType.list = true;
                }
            } else if (type.equals(SchemaSymbols.ATTVAL_ENTITIES)) {
                this.fSimpleType.type = (short) 1;
                this.fSimpleType.list = true;
            } else if (type.equals(SchemaSymbols.ATTVAL_ENTITY)) {
                this.fSimpleType.type = (short) 1;
            } else if (type.equals(SchemaSymbols.ATTVAL_NMTOKENS)) {
                this.fSimpleType.type = (short) 5;
                this.fSimpleType.list = true;
            } else if (type.equals(SchemaSymbols.ATTVAL_NMTOKEN)) {
                this.fSimpleType.type = (short) 5;
            } else if (type.startsWith(SchemaSymbols.ATTVAL_NOTATION)) {
                this.fSimpleType.type = (short) 6;
            } else if (type.startsWith("ENUMERATION")) {
                this.fSimpleType.type = (short) 2;
            } else {
                System.err.println("!!! unknown attribute type " + type);
            }
            this.fQName.setValues(null, attributeName, attributeName, null);
            this.fAttributeDecl.setValues(this.fQName, this.fSimpleType, DEBUG);
            setAttributeDecl(elementIndex, this.fCurrentAttributeIndex, this.fAttributeDecl);
            int chunk = this.fCurrentAttributeIndex >> CHUNK_SHIFT;
            int index = this.fCurrentAttributeIndex & CHUNK_MASK;
            ensureAttributeDeclCapacity(chunk);
            int[] iArr = this.fAttributeDeclIsExternal[chunk];
            if (this.fReadingExternalDTD || this.fPEDepth > 0) {
                i = 1;
            } else {
                i = 0;
            }
            iArr[index] = i;
        }
    }

    public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augs) throws XNIException {
        if (getEntityDeclIndex(name) == TOP_LEVEL_SCOPE) {
            int entityIndex = createEntityDecl();
            boolean isPE = name.startsWith("%");
            boolean inExternal = (this.fReadingExternalDTD || this.fPEDepth > 0) ? true : DEBUG;
            XMLEntityDecl entityDecl = new XMLEntityDecl();
            entityDecl.setValues(name, null, null, null, null, text.toString(), isPE, inExternal);
            setEntityDecl(entityIndex, entityDecl);
        }
    }

    public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        if (getEntityDeclIndex(name) == TOP_LEVEL_SCOPE) {
            int entityIndex = createEntityDecl();
            boolean isPE = name.startsWith("%");
            boolean inExternal = (this.fReadingExternalDTD || this.fPEDepth > 0) ? true : DEBUG;
            XMLEntityDecl entityDecl = new XMLEntityDecl();
            entityDecl.setValues(name, identifier.getPublicId(), identifier.getLiteralSystemId(), identifier.getBaseSystemId(), null, null, isPE, inExternal);
            setEntityDecl(entityIndex, entityDecl);
        }
    }

    public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augs) throws XNIException {
        XMLEntityDecl entityDecl = new XMLEntityDecl();
        boolean isPE = name.startsWith("%");
        boolean inExternal = (this.fReadingExternalDTD || this.fPEDepth > 0) ? true : DEBUG;
        entityDecl.setValues(name, identifier.getPublicId(), identifier.getLiteralSystemId(), identifier.getBaseSystemId(), notation, null, isPE, inExternal);
        if (getEntityDeclIndex(name) == TOP_LEVEL_SCOPE) {
            setEntityDecl(createEntityDecl(), entityDecl);
        }
    }

    public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        XMLNotationDecl notationDecl = new XMLNotationDecl();
        notationDecl.setValues(name, identifier.getPublicId(), identifier.getLiteralSystemId(), identifier.getBaseSystemId());
        if (getNotationDeclIndex(name) == TOP_LEVEL_SCOPE) {
            setNotationDecl(createNotationDecl(), notationDecl);
        }
    }

    public void endDTD(Augmentations augs) throws XNIException {
        this.fIsImmutable = true;
        if (this.fGrammarDescription.getRootName() == null) {
            int size = this.fElementDeclCount;
            ArrayList elements = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int index = i & CHUNK_MASK;
                elements.add(this.fElementDeclName[i >> CHUNK_SHIFT][index].rawname);
            }
            this.fGrammarDescription.setPossibleRoots(elements);
        }
    }

    public void setDTDSource(XMLDTDSource source) {
        this.fDTDSource = source;
    }

    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    }

    public void startAttlist(String elementName, Augmentations augs) throws XNIException {
    }

    public void endAttlist(Augmentations augs) throws XNIException {
    }

    public void startConditional(short type, Augmentations augs) throws XNIException {
    }

    public void ignoredCharacters(XMLString text, Augmentations augs) throws XNIException {
    }

    public void endConditional(Augmentations augs) throws XNIException {
    }

    public void setDTDContentModelSource(XMLDTDContentModelSource source) {
        this.fDTDContentModelSource = source;
    }

    public XMLDTDContentModelSource getDTDContentModelSource() {
        return this.fDTDContentModelSource;
    }

    public void startContentModel(String elementName, Augmentations augs) throws XNIException {
        XMLElementDecl elementDecl = (XMLElementDecl) this.fElementDeclTab.get(elementName);
        if (elementDecl != null) {
            this.fElementDecl = elementDecl;
        }
        this.fDepth = 0;
        initializeContentModelStack();
    }

    public void startGroup(Augmentations augs) throws XNIException {
        this.fDepth++;
        initializeContentModelStack();
        this.fMixed = DEBUG;
    }

    public void pcdata(Augmentations augs) throws XNIException {
        this.fMixed = true;
    }

    public void element(String elementName, Augmentations augs) throws XNIException {
        if (!this.fMixed) {
            this.fNodeIndexStack[this.fDepth] = addContentSpecNode((short) 0, elementName);
        } else if (this.fNodeIndexStack[this.fDepth] == TOP_LEVEL_SCOPE) {
            this.fNodeIndexStack[this.fDepth] = addUniqueLeafNode(elementName);
        } else {
            this.fNodeIndexStack[this.fDepth] = addContentSpecNode((short) 4, this.fNodeIndexStack[this.fDepth], addUniqueLeafNode(elementName));
        }
    }

    public void separator(short separator, Augmentations augs) throws XNIException {
        if (!this.fMixed) {
            if (this.fOpStack[this.fDepth] != (short) 5 && separator == (short) 0) {
                if (this.fPrevNodeIndexStack[this.fDepth] != TOP_LEVEL_SCOPE) {
                    this.fNodeIndexStack[this.fDepth] = addContentSpecNode(this.fOpStack[this.fDepth], this.fPrevNodeIndexStack[this.fDepth], this.fNodeIndexStack[this.fDepth]);
                }
                this.fPrevNodeIndexStack[this.fDepth] = this.fNodeIndexStack[this.fDepth];
                this.fOpStack[this.fDepth] = (short) 4;
            } else if (this.fOpStack[this.fDepth] != (short) 4 && separator == (short) 1) {
                if (this.fPrevNodeIndexStack[this.fDepth] != TOP_LEVEL_SCOPE) {
                    this.fNodeIndexStack[this.fDepth] = addContentSpecNode(this.fOpStack[this.fDepth], this.fPrevNodeIndexStack[this.fDepth], this.fNodeIndexStack[this.fDepth]);
                }
                this.fPrevNodeIndexStack[this.fDepth] = this.fNodeIndexStack[this.fDepth];
                this.fOpStack[this.fDepth] = (short) 5;
            }
        }
    }

    public void occurrence(short occurrence, Augmentations augs) throws XNIException {
        if (!this.fMixed) {
            if (occurrence == (short) 2) {
                this.fNodeIndexStack[this.fDepth] = addContentSpecNode((short) 1, this.fNodeIndexStack[this.fDepth], TOP_LEVEL_SCOPE);
            } else if (occurrence == (short) 3) {
                this.fNodeIndexStack[this.fDepth] = addContentSpecNode((short) 2, this.fNodeIndexStack[this.fDepth], TOP_LEVEL_SCOPE);
            } else if (occurrence == (short) 4) {
                this.fNodeIndexStack[this.fDepth] = addContentSpecNode((short) 3, this.fNodeIndexStack[this.fDepth], TOP_LEVEL_SCOPE);
            }
        }
    }

    public void endGroup(Augmentations augs) throws XNIException {
        if (!this.fMixed) {
            if (this.fPrevNodeIndexStack[this.fDepth] != TOP_LEVEL_SCOPE) {
                this.fNodeIndexStack[this.fDepth] = addContentSpecNode(this.fOpStack[this.fDepth], this.fPrevNodeIndexStack[this.fDepth], this.fNodeIndexStack[this.fDepth]);
            }
            int[] iArr = this.fNodeIndexStack;
            int i = this.fDepth;
            this.fDepth = i + TOP_LEVEL_SCOPE;
            this.fNodeIndexStack[this.fDepth] = iArr[i];
        }
    }

    public void any(Augmentations augs) throws XNIException {
    }

    public void empty(Augmentations augs) throws XNIException {
    }

    public void endContentModel(Augmentations augs) throws XNIException {
    }

    public boolean isNamespaceAware() {
        return DEBUG;
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    public int getFirstElementDeclIndex() {
        return this.fElementDeclCount >= 0 ? 0 : TOP_LEVEL_SCOPE;
    }

    public int getNextElementDeclIndex(int elementDeclIndex) {
        return elementDeclIndex < this.fElementDeclCount + TOP_LEVEL_SCOPE ? elementDeclIndex + 1 : TOP_LEVEL_SCOPE;
    }

    public int getElementDeclIndex(String elementDeclName) {
        return this.fElementIndexMap.get(elementDeclName);
    }

    public int getElementDeclIndex(QName elementDeclQName) {
        return getElementDeclIndex(elementDeclQName.rawname);
    }

    public short getContentSpecType(int elementIndex) {
        if (elementIndex < 0 || elementIndex >= this.fElementDeclCount) {
            return (short) -1;
        }
        int chunk = elementIndex >> CHUNK_SHIFT;
        int index = elementIndex & CHUNK_MASK;
        if (this.fElementDeclType[chunk][index] != (short) -1) {
            return (short) (this.fElementDeclType[chunk][index] & -129);
        }
        return (short) -1;
    }

    public boolean getElementDecl(int elementDeclIndex, XMLElementDecl elementDecl) {
        boolean z = DEBUG;
        if (elementDeclIndex < 0 || elementDeclIndex >= this.fElementDeclCount) {
            return DEBUG;
        }
        int chunk = elementDeclIndex >> CHUNK_SHIFT;
        int index = elementDeclIndex & CHUNK_MASK;
        elementDecl.name.setValues(this.fElementDeclName[chunk][index]);
        if (this.fElementDeclType[chunk][index] == (short) -1) {
            elementDecl.type = (short) -1;
            elementDecl.simpleType.list = DEBUG;
        } else {
            elementDecl.type = (short) (this.fElementDeclType[chunk][index] & -129);
            XMLSimpleType xMLSimpleType = elementDecl.simpleType;
            if ((this.fElementDeclType[chunk][index] & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0) {
                z = true;
            }
            xMLSimpleType.list = z;
        }
        if (elementDecl.type == (short) 3 || elementDecl.type == (short) 2) {
            elementDecl.contentModelValidator = getElementContentModelValidator(elementDeclIndex);
        }
        elementDecl.simpleType.datatypeValidator = null;
        elementDecl.simpleType.defaultType = (short) -1;
        elementDecl.simpleType.defaultValue = null;
        return true;
    }

    QName getElementDeclName(int elementDeclIndex) {
        if (elementDeclIndex < 0 || elementDeclIndex >= this.fElementDeclCount) {
            return null;
        }
        return this.fElementDeclName[elementDeclIndex >> CHUNK_SHIFT][elementDeclIndex & CHUNK_MASK];
    }

    public int getFirstAttributeDeclIndex(int elementDeclIndex) {
        return this.fElementDeclFirstAttributeDeclIndex[elementDeclIndex >> CHUNK_SHIFT][elementDeclIndex & CHUNK_MASK];
    }

    public int getNextAttributeDeclIndex(int attributeDeclIndex) {
        return this.fAttributeDeclNextAttributeDeclIndex[attributeDeclIndex >> CHUNK_SHIFT][attributeDeclIndex & CHUNK_MASK];
    }

    public boolean getAttributeDecl(int attributeDeclIndex, XMLAttributeDecl attributeDecl) {
        boolean isList = DEBUG;
        if (attributeDeclIndex < 0 || attributeDeclIndex >= this.fAttributeDeclCount) {
            return DEBUG;
        }
        short attributeType;
        int chunk = attributeDeclIndex >> CHUNK_SHIFT;
        int index = attributeDeclIndex & CHUNK_MASK;
        attributeDecl.name.setValues(this.fAttributeDeclName[chunk][index]);
        if (this.fAttributeDeclType[chunk][index] == (short) -1) {
            attributeType = (short) -1;
            isList = DEBUG;
        } else {
            attributeType = (short) (this.fAttributeDeclType[chunk][index] & -129);
            if ((this.fAttributeDeclType[chunk][index] & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0) {
                isList = true;
            }
        }
        attributeDecl.simpleType.setValues(attributeType, this.fAttributeDeclName[chunk][index].localpart, this.fAttributeDeclEnumeration[chunk][index], isList, this.fAttributeDeclDefaultType[chunk][index], this.fAttributeDeclDefaultValue[chunk][index], this.fAttributeDeclNonNormalizedDefaultValue[chunk][index], this.fAttributeDeclDatatypeValidator[chunk][index]);
        return true;
    }

    public boolean isCDATAAttribute(QName elName, QName atName) {
        if (!getAttributeDecl(getElementDeclIndex(elName), this.fAttributeDecl) || this.fAttributeDecl.simpleType.type == (short) 0) {
            return true;
        }
        return DEBUG;
    }

    public int getEntityDeclIndex(String entityDeclName) {
        if (entityDeclName == null) {
            return TOP_LEVEL_SCOPE;
        }
        return this.fEntityIndexMap.get(entityDeclName);
    }

    public boolean getEntityDecl(int entityDeclIndex, XMLEntityDecl entityDecl) {
        if (entityDeclIndex < 0 || entityDeclIndex >= this.fEntityCount) {
            return DEBUG;
        }
        boolean z;
        boolean z2;
        int chunk = entityDeclIndex >> CHUNK_SHIFT;
        int index = entityDeclIndex & CHUNK_MASK;
        String str = this.fEntityName[chunk][index];
        String str2 = this.fEntityPublicId[chunk][index];
        String str3 = this.fEntitySystemId[chunk][index];
        String str4 = this.fEntityBaseSystemId[chunk][index];
        String str5 = this.fEntityNotation[chunk][index];
        String str6 = this.fEntityValue[chunk][index];
        if (this.fEntityIsPE[chunk][index] == null) {
            z = DEBUG;
        } else {
            z = true;
        }
        if (this.fEntityInExternal[chunk][index] == null) {
            z2 = DEBUG;
        } else {
            z2 = true;
        }
        entityDecl.setValues(str, str2, str3, str4, str5, str6, z, z2);
        return true;
    }

    public int getNotationDeclIndex(String notationDeclName) {
        if (notationDeclName == null) {
            return TOP_LEVEL_SCOPE;
        }
        return this.fNotationIndexMap.get(notationDeclName);
    }

    public boolean getNotationDecl(int notationDeclIndex, XMLNotationDecl notationDecl) {
        if (notationDeclIndex < 0 || notationDeclIndex >= this.fNotationCount) {
            return DEBUG;
        }
        int chunk = notationDeclIndex >> CHUNK_SHIFT;
        int index = notationDeclIndex & CHUNK_MASK;
        notationDecl.setValues(this.fNotationName[chunk][index], this.fNotationPublicId[chunk][index], this.fNotationSystemId[chunk][index], this.fNotationBaseSystemId[chunk][index]);
        return true;
    }

    public boolean getContentSpec(int contentSpecIndex, XMLContentSpec contentSpec) {
        if (contentSpecIndex < 0 || contentSpecIndex >= this.fContentSpecCount) {
            return DEBUG;
        }
        int chunk = contentSpecIndex >> CHUNK_SHIFT;
        int index = contentSpecIndex & CHUNK_MASK;
        contentSpec.type = this.fContentSpecType[chunk][index];
        contentSpec.value = this.fContentSpecValue[chunk][index];
        contentSpec.otherValue = this.fContentSpecOtherValue[chunk][index];
        return true;
    }

    public int getContentSpecIndex(int elementDeclIndex) {
        if (elementDeclIndex < 0 || elementDeclIndex >= this.fElementDeclCount) {
            return TOP_LEVEL_SCOPE;
        }
        return this.fElementDeclContentSpecIndex[elementDeclIndex >> CHUNK_SHIFT][elementDeclIndex & CHUNK_MASK];
    }

    public String getContentSpecAsString(int elementDeclIndex) {
        if (elementDeclIndex < 0 || elementDeclIndex >= this.fElementDeclCount) {
            return null;
        }
        int index = elementDeclIndex & CHUNK_MASK;
        int contentSpecIndex = this.fElementDeclContentSpecIndex[elementDeclIndex >> CHUNK_SHIFT][index];
        XMLContentSpec contentSpec = new XMLContentSpec();
        if (!getContentSpec(contentSpecIndex, contentSpec)) {
            return null;
        }
        StringBuffer str = new StringBuffer();
        int parentContentSpecType = contentSpec.type & 15;
        int nextContentSpec;
        switch (parentContentSpecType) {
            case DurationDV.DURATION_TYPE /*0*/:
                str.append('(');
                if (contentSpec.value == null && contentSpec.otherValue == null) {
                    str.append("#PCDATA");
                } else {
                    str.append(contentSpec.value);
                }
                str.append(')');
                break;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                nextContentSpec = contentSpec.type;
                if (nextContentSpec == 0) {
                    str.append('(');
                    str.append(contentSpec.value);
                    str.append(')');
                } else if (nextContentSpec == 3 || nextContentSpec == 2 || nextContentSpec == 1) {
                    str.append('(');
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                    str.append(')');
                } else {
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                }
                str.append('?');
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                nextContentSpec = contentSpec.type;
                if (nextContentSpec == 0) {
                    str.append('(');
                    if (contentSpec.value == null && contentSpec.otherValue == null) {
                        str.append("#PCDATA");
                    } else if (contentSpec.otherValue != null) {
                        str.append("##any:uri=").append(contentSpec.otherValue);
                    } else if (contentSpec.value == null) {
                        str.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                    } else {
                        appendContentSpec(contentSpec, str, true, parentContentSpecType);
                    }
                    str.append(')');
                } else if (nextContentSpec == 3 || nextContentSpec == 2 || nextContentSpec == 1) {
                    str.append('(');
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                    str.append(')');
                } else {
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                }
                str.append('*');
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                nextContentSpec = contentSpec.type;
                if (nextContentSpec == 0) {
                    str.append('(');
                    if (contentSpec.value == null && contentSpec.otherValue == null) {
                        str.append("#PCDATA");
                    } else if (contentSpec.otherValue != null) {
                        str.append("##any:uri=").append(contentSpec.otherValue);
                    } else if (contentSpec.value == null) {
                        str.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                    } else {
                        str.append(contentSpec.value);
                    }
                    str.append(')');
                } else if (nextContentSpec == 3 || nextContentSpec == 2 || nextContentSpec == 1) {
                    str.append('(');
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                    str.append(')');
                } else {
                    appendContentSpec(contentSpec, str, true, parentContentSpecType);
                }
                str.append('+');
                break;
            case INITIAL_CHUNK_COUNT /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                appendContentSpec(contentSpec, str, true, parentContentSpecType);
                break;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                str.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                if (contentSpec.otherValue != null) {
                    str.append(":uri=");
                    str.append(contentSpec.otherValue);
                    break;
                }
                break;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                str.append("##other:uri=");
                str.append(contentSpec.otherValue);
                break;
            case CHUNK_SHIFT /*8*/:
                str.append(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL);
                break;
            default:
                str.append("???");
                break;
        }
        return str.toString();
    }

    public void printElements() {
        int elementDeclIndex = 0;
        XMLElementDecl elementDecl = new XMLElementDecl();
        while (true) {
            int elementDeclIndex2 = elementDeclIndex + 1;
            if (getElementDecl(elementDeclIndex, elementDecl)) {
                System.out.println("element decl: " + elementDecl.name + ", " + elementDecl.name.rawname);
                elementDeclIndex = elementDeclIndex2;
            } else {
                return;
            }
        }
    }

    public void printAttributes(int elementDeclIndex) {
        int attributeDeclIndex = getFirstAttributeDeclIndex(elementDeclIndex);
        System.out.print(elementDeclIndex);
        System.out.print(" [");
        while (attributeDeclIndex != TOP_LEVEL_SCOPE) {
            System.out.print(' ');
            System.out.print(attributeDeclIndex);
            printAttribute(attributeDeclIndex);
            attributeDeclIndex = getNextAttributeDeclIndex(attributeDeclIndex);
            if (attributeDeclIndex != TOP_LEVEL_SCOPE) {
                System.out.print(",");
            }
        }
        System.out.println(" ]");
    }

    protected void addContentSpecToElement(XMLElementDecl elementDecl) {
        if ((this.fDepth == 0 || (this.fDepth == 1 && elementDecl.type == (short) 2)) && this.fNodeIndexStack != null) {
            if (elementDecl.type == (short) 2) {
                int pcdata = addUniqueLeafNode(null);
                if (this.fNodeIndexStack[0] == TOP_LEVEL_SCOPE) {
                    this.fNodeIndexStack[0] = pcdata;
                } else {
                    this.fNodeIndexStack[0] = addContentSpecNode((short) 4, pcdata, this.fNodeIndexStack[0]);
                }
            }
            setContentSpecIndex(this.fCurrentElementIndex, this.fNodeIndexStack[this.fDepth]);
        }
    }

    protected ContentModelValidator getElementContentModelValidator(int elementDeclIndex) {
        int chunk = elementDeclIndex >> CHUNK_SHIFT;
        int index = elementDeclIndex & CHUNK_MASK;
        ContentModelValidator contentModel = this.fElementDeclContentModelValidator[chunk][index];
        if (contentModel != null) {
            return contentModel;
        }
        int contentType = this.fElementDeclType[chunk][index];
        if (contentType == INITIAL_CHUNK_COUNT) {
            return null;
        }
        int contentSpecIndex = this.fElementDeclContentSpecIndex[chunk][index];
        XMLContentSpec contentSpec = new XMLContentSpec();
        getContentSpec(contentSpecIndex, contentSpec);
        if (contentType == 2) {
            ChildrenList children = new ChildrenList();
            contentSpecTree(contentSpecIndex, contentSpec, children);
            contentModel = new MixedContentModel(children.qname, children.type, 0, children.length, DEBUG);
        } else if (contentType == 3) {
            contentModel = createChildModel(contentSpecIndex);
        } else {
            throw new RuntimeException("Unknown content type for a element decl in getElementContentModelValidator() in AbstractDTDGrammar class");
        }
        this.fElementDeclContentModelValidator[chunk][index] = contentModel;
        return contentModel;
    }

    protected int createElementDecl() {
        int chunk = this.fElementDeclCount >> CHUNK_SHIFT;
        int index = this.fElementDeclCount & CHUNK_MASK;
        ensureElementDeclCapacity(chunk);
        this.fElementDeclName[chunk][index] = new QName();
        this.fElementDeclType[chunk][index] = TOP_LEVEL_SCOPE;
        this.fElementDeclContentModelValidator[chunk][index] = null;
        this.fElementDeclFirstAttributeDeclIndex[chunk][index] = TOP_LEVEL_SCOPE;
        this.fElementDeclLastAttributeDeclIndex[chunk][index] = TOP_LEVEL_SCOPE;
        int i = this.fElementDeclCount;
        this.fElementDeclCount = i + 1;
        return i;
    }

    protected void setElementDecl(int elementDeclIndex, XMLElementDecl elementDecl) {
        if (elementDeclIndex >= 0 && elementDeclIndex < this.fElementDeclCount) {
            int chunk = elementDeclIndex >> CHUNK_SHIFT;
            int index = elementDeclIndex & CHUNK_MASK;
            this.fElementDeclName[chunk][index].setValues(elementDecl.name);
            this.fElementDeclType[chunk][index] = elementDecl.type;
            this.fElementDeclContentModelValidator[chunk][index] = elementDecl.contentModelValidator;
            if (elementDecl.simpleType.list) {
                short[] sArr = this.fElementDeclType[chunk];
                sArr[index] = (short) (sArr[index] | TransportMediator.FLAG_KEY_MEDIA_NEXT);
            }
            this.fElementIndexMap.put(elementDecl.name.rawname, elementDeclIndex);
        }
    }

    protected void putElementNameMapping(QName name, int scope, int elementDeclIndex) {
    }

    protected void setFirstAttributeDeclIndex(int elementDeclIndex, int newFirstAttrIndex) {
        if (elementDeclIndex >= 0 && elementDeclIndex < this.fElementDeclCount) {
            int index = elementDeclIndex & CHUNK_MASK;
            this.fElementDeclFirstAttributeDeclIndex[elementDeclIndex >> CHUNK_SHIFT][index] = newFirstAttrIndex;
        }
    }

    protected void setContentSpecIndex(int elementDeclIndex, int contentSpecIndex) {
        if (elementDeclIndex >= 0 && elementDeclIndex < this.fElementDeclCount) {
            int index = elementDeclIndex & CHUNK_MASK;
            this.fElementDeclContentSpecIndex[elementDeclIndex >> CHUNK_SHIFT][index] = contentSpecIndex;
        }
    }

    protected int createAttributeDecl() {
        int chunk = this.fAttributeDeclCount >> CHUNK_SHIFT;
        int index = this.fAttributeDeclCount & CHUNK_MASK;
        ensureAttributeDeclCapacity(chunk);
        this.fAttributeDeclName[chunk][index] = new QName();
        this.fAttributeDeclType[chunk][index] = TOP_LEVEL_SCOPE;
        this.fAttributeDeclDatatypeValidator[chunk][index] = null;
        this.fAttributeDeclEnumeration[chunk][index] = null;
        this.fAttributeDeclDefaultType[chunk][index] = (short) 0;
        this.fAttributeDeclDefaultValue[chunk][index] = null;
        this.fAttributeDeclNonNormalizedDefaultValue[chunk][index] = null;
        this.fAttributeDeclNextAttributeDeclIndex[chunk][index] = TOP_LEVEL_SCOPE;
        int i = this.fAttributeDeclCount;
        this.fAttributeDeclCount = i + 1;
        return i;
    }

    protected void setAttributeDecl(int elementDeclIndex, int attributeDeclIndex, XMLAttributeDecl attributeDecl) {
        int attrChunk = attributeDeclIndex >> CHUNK_SHIFT;
        int attrIndex = attributeDeclIndex & CHUNK_MASK;
        this.fAttributeDeclName[attrChunk][attrIndex].setValues(attributeDecl.name);
        this.fAttributeDeclType[attrChunk][attrIndex] = attributeDecl.simpleType.type;
        if (attributeDecl.simpleType.list) {
            short[] sArr = this.fAttributeDeclType[attrChunk];
            sArr[attrIndex] = (short) (sArr[attrIndex] | TransportMediator.FLAG_KEY_MEDIA_NEXT);
        }
        this.fAttributeDeclEnumeration[attrChunk][attrIndex] = attributeDecl.simpleType.enumeration;
        this.fAttributeDeclDefaultType[attrChunk][attrIndex] = attributeDecl.simpleType.defaultType;
        this.fAttributeDeclDatatypeValidator[attrChunk][attrIndex] = attributeDecl.simpleType.datatypeValidator;
        this.fAttributeDeclDefaultValue[attrChunk][attrIndex] = attributeDecl.simpleType.defaultValue;
        this.fAttributeDeclNonNormalizedDefaultValue[attrChunk][attrIndex] = attributeDecl.simpleType.nonNormalizedDefaultValue;
        int elemChunk = elementDeclIndex >> CHUNK_SHIFT;
        int elemIndex = elementDeclIndex & CHUNK_MASK;
        int index = this.fElementDeclFirstAttributeDeclIndex[elemChunk][elemIndex];
        while (index != TOP_LEVEL_SCOPE && index != attributeDeclIndex) {
            attrIndex = index & CHUNK_MASK;
            index = this.fAttributeDeclNextAttributeDeclIndex[index >> CHUNK_SHIFT][attrIndex];
        }
        if (index == TOP_LEVEL_SCOPE) {
            if (this.fElementDeclFirstAttributeDeclIndex[elemChunk][elemIndex] == TOP_LEVEL_SCOPE) {
                this.fElementDeclFirstAttributeDeclIndex[elemChunk][elemIndex] = attributeDeclIndex;
            } else {
                index = this.fElementDeclLastAttributeDeclIndex[elemChunk][elemIndex];
                attrIndex = index & CHUNK_MASK;
                this.fAttributeDeclNextAttributeDeclIndex[index >> CHUNK_SHIFT][attrIndex] = attributeDeclIndex;
            }
            this.fElementDeclLastAttributeDeclIndex[elemChunk][elemIndex] = attributeDeclIndex;
        }
    }

    protected int createContentSpec() {
        int chunk = this.fContentSpecCount >> CHUNK_SHIFT;
        int index = this.fContentSpecCount & CHUNK_MASK;
        ensureContentSpecCapacity(chunk);
        this.fContentSpecType[chunk][index] = (short) -1;
        this.fContentSpecValue[chunk][index] = null;
        this.fContentSpecOtherValue[chunk][index] = null;
        int i = this.fContentSpecCount;
        this.fContentSpecCount = i + 1;
        return i;
    }

    protected void setContentSpec(int contentSpecIndex, XMLContentSpec contentSpec) {
        int chunk = contentSpecIndex >> CHUNK_SHIFT;
        int index = contentSpecIndex & CHUNK_MASK;
        this.fContentSpecType[chunk][index] = contentSpec.type;
        this.fContentSpecValue[chunk][index] = contentSpec.value;
        this.fContentSpecOtherValue[chunk][index] = contentSpec.otherValue;
    }

    protected int createEntityDecl() {
        int chunk = this.fEntityCount >> CHUNK_SHIFT;
        int index = this.fEntityCount & CHUNK_MASK;
        ensureEntityDeclCapacity(chunk);
        this.fEntityIsPE[chunk][index] = (byte) 0;
        this.fEntityInExternal[chunk][index] = (byte) 0;
        int i = this.fEntityCount;
        this.fEntityCount = i + 1;
        return i;
    }

    protected void setEntityDecl(int entityDeclIndex, XMLEntityDecl entityDecl) {
        byte b;
        byte b2 = (byte) 1;
        int chunk = entityDeclIndex >> CHUNK_SHIFT;
        int index = entityDeclIndex & CHUNK_MASK;
        this.fEntityName[chunk][index] = entityDecl.name;
        this.fEntityValue[chunk][index] = entityDecl.value;
        this.fEntityPublicId[chunk][index] = entityDecl.publicId;
        this.fEntitySystemId[chunk][index] = entityDecl.systemId;
        this.fEntityBaseSystemId[chunk][index] = entityDecl.baseSystemId;
        this.fEntityNotation[chunk][index] = entityDecl.notation;
        byte[] bArr = this.fEntityIsPE[chunk];
        if (entityDecl.isPE) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        bArr[index] = b;
        byte[] bArr2 = this.fEntityInExternal[chunk];
        if (!entityDecl.inExternal) {
            b2 = (byte) 0;
        }
        bArr2[index] = b2;
        this.fEntityIndexMap.put(entityDecl.name, entityDeclIndex);
    }

    protected int createNotationDecl() {
        ensureNotationDeclCapacity(this.fNotationCount >> CHUNK_SHIFT);
        int i = this.fNotationCount;
        this.fNotationCount = i + 1;
        return i;
    }

    protected void setNotationDecl(int notationDeclIndex, XMLNotationDecl notationDecl) {
        int chunk = notationDeclIndex >> CHUNK_SHIFT;
        int index = notationDeclIndex & CHUNK_MASK;
        this.fNotationName[chunk][index] = notationDecl.name;
        this.fNotationPublicId[chunk][index] = notationDecl.publicId;
        this.fNotationSystemId[chunk][index] = notationDecl.systemId;
        this.fNotationBaseSystemId[chunk][index] = notationDecl.baseSystemId;
        this.fNotationIndexMap.put(notationDecl.name, notationDeclIndex);
    }

    protected int addContentSpecNode(short nodeType, String nodeValue) {
        int contentSpecIndex = createContentSpec();
        this.fContentSpec.setValues(nodeType, nodeValue, null);
        setContentSpec(contentSpecIndex, this.fContentSpec);
        return contentSpecIndex;
    }

    protected int addUniqueLeafNode(String elementName) {
        int contentSpecIndex = createContentSpec();
        this.fContentSpec.setValues((short) 0, elementName, null);
        setContentSpec(contentSpecIndex, this.fContentSpec);
        return contentSpecIndex;
    }

    protected int addContentSpecNode(short nodeType, int leftNodeIndex, int rightNodeIndex) {
        int contentSpecIndex = createContentSpec();
        int[] leftIntArray = new int[1];
        int[] rightIntArray = new int[]{leftNodeIndex};
        rightIntArray[0] = rightNodeIndex;
        this.fContentSpec.setValues(nodeType, leftIntArray, rightIntArray);
        setContentSpec(contentSpecIndex, this.fContentSpec);
        return contentSpecIndex;
    }

    protected void initializeContentModelStack() {
        if (this.fOpStack == null) {
            this.fOpStack = new short[CHUNK_SHIFT];
            this.fNodeIndexStack = new int[CHUNK_SHIFT];
            this.fPrevNodeIndexStack = new int[CHUNK_SHIFT];
        } else if (this.fDepth == this.fOpStack.length) {
            short[] newStack = new short[(this.fDepth * 2)];
            System.arraycopy(this.fOpStack, 0, newStack, 0, this.fDepth);
            this.fOpStack = newStack;
            int[] newIntStack = new int[(this.fDepth * 2)];
            System.arraycopy(this.fNodeIndexStack, 0, newIntStack, 0, this.fDepth);
            this.fNodeIndexStack = newIntStack;
            newIntStack = new int[(this.fDepth * 2)];
            System.arraycopy(this.fPrevNodeIndexStack, 0, newIntStack, 0, this.fDepth);
            this.fPrevNodeIndexStack = newIntStack;
        }
        this.fOpStack[this.fDepth] = (short) -1;
        this.fNodeIndexStack[this.fDepth] = TOP_LEVEL_SCOPE;
        this.fPrevNodeIndexStack[this.fDepth] = TOP_LEVEL_SCOPE;
    }

    boolean isImmutable() {
        return this.fIsImmutable;
    }

    private void appendContentSpec(XMLContentSpec contentSpec, StringBuffer str, boolean parens, int parentContentSpecType) {
        int thisContentSpec = contentSpec.type & 15;
        switch (thisContentSpec) {
            case DurationDV.DURATION_TYPE /*0*/:
                if (contentSpec.value == null && contentSpec.otherValue == null) {
                    str.append("#PCDATA");
                } else if (contentSpec.value == null && contentSpec.otherValue != null) {
                    str.append("##any:uri=").append(contentSpec.otherValue);
                } else if (contentSpec.value == null) {
                    str.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                } else {
                    str.append(contentSpec.value);
                }
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (parentContentSpecType == 3 || parentContentSpecType == 2 || parentContentSpecType == 1) {
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    str.append('(');
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                    str.append(')');
                } else {
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                }
                str.append('?');
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (parentContentSpecType == 3 || parentContentSpecType == 2 || parentContentSpecType == 1) {
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    str.append('(');
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                    str.append(')');
                } else {
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                }
                str.append('*');
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                if (parentContentSpecType == 3 || parentContentSpecType == 2 || parentContentSpecType == 1) {
                    str.append('(');
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                    str.append(')');
                } else {
                    getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                    appendContentSpec(contentSpec, str, true, thisContentSpec);
                }
                str.append('+');
            case INITIAL_CHUNK_COUNT /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                if (parens) {
                    str.append('(');
                }
                short type = contentSpec.type;
                int otherValue = ((int[]) contentSpec.otherValue)[0];
                getContentSpec(((int[]) contentSpec.value)[0], contentSpec);
                appendContentSpec(contentSpec, str, contentSpec.type != type ? true : DEBUG, thisContentSpec);
                if (type == INITIAL_CHUNK_COUNT) {
                    str.append('|');
                } else {
                    str.append(',');
                }
                getContentSpec(otherValue, contentSpec);
                appendContentSpec(contentSpec, str, true, thisContentSpec);
                if (parens) {
                    str.append(')');
                }
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                str.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                if (contentSpec.otherValue != null) {
                    str.append(":uri=");
                    str.append(contentSpec.otherValue);
                }
            case ConnectionResult.NETWORK_ERROR /*7*/:
                str.append("##other:uri=");
                str.append(contentSpec.otherValue);
            case CHUNK_SHIFT /*8*/:
                str.append(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL);
            default:
                str.append("???");
        }
    }

    private void printAttribute(int attributeDeclIndex) {
        XMLAttributeDecl attributeDecl = new XMLAttributeDecl();
        if (getAttributeDecl(attributeDeclIndex, attributeDecl)) {
            System.out.print(" { ");
            System.out.print(attributeDecl.name.localpart);
            System.out.print(" }");
        }
    }

    private synchronized ContentModelValidator createChildModel(int contentSpecIndex) {
        ContentModelValidator simpleContentModel;
        XMLContentSpec contentSpec = new XMLContentSpec();
        getContentSpec(contentSpecIndex, contentSpec);
        if (!((contentSpec.type & 15) == 6 || (contentSpec.type & 15) == 7 || (contentSpec.type & 15) == CHUNK_SHIFT)) {
            if (contentSpec.type == (short) 0) {
                if (contentSpec.value == null && contentSpec.otherValue == null) {
                    throw new RuntimeException("ImplementationMessages.VAL_NPCD");
                }
                this.fQName.setValues(null, (String) contentSpec.value, (String) contentSpec.value, (String) contentSpec.otherValue);
                simpleContentModel = new SimpleContentModel(contentSpec.type, this.fQName, null);
            } else if (contentSpec.type == (short) 4 || contentSpec.type == (short) 5) {
                contentSpecLeft = new XMLContentSpec();
                XMLContentSpec contentSpecRight = new XMLContentSpec();
                getContentSpec(((int[]) contentSpec.value)[0], contentSpecLeft);
                getContentSpec(((int[]) contentSpec.otherValue)[0], contentSpecRight);
                if (contentSpecLeft.type == (short) 0 && contentSpecRight.type == (short) 0) {
                    this.fQName.setValues(null, (String) contentSpecLeft.value, (String) contentSpecLeft.value, (String) contentSpecLeft.otherValue);
                    this.fQName2.setValues(null, (String) contentSpecRight.value, (String) contentSpecRight.value, (String) contentSpecRight.otherValue);
                    simpleContentModel = new SimpleContentModel(contentSpec.type, this.fQName, this.fQName2);
                }
            } else if (contentSpec.type == (short) 1 || contentSpec.type == (short) 2 || contentSpec.type == (short) 3) {
                contentSpecLeft = new XMLContentSpec();
                getContentSpec(((int[]) contentSpec.value)[0], contentSpecLeft);
                if (contentSpecLeft.type == (short) 0) {
                    this.fQName.setValues(null, (String) contentSpecLeft.value, (String) contentSpecLeft.value, (String) contentSpecLeft.otherValue);
                    simpleContentModel = new SimpleContentModel(contentSpec.type, this.fQName, null);
                }
            } else {
                throw new RuntimeException("ImplementationMessages.VAL_CST");
            }
        }
        this.fLeafCount = 0;
        this.fLeafCount = 0;
        simpleContentModel = new DFAContentModel(buildSyntaxTree(contentSpecIndex, contentSpec), this.fLeafCount, DEBUG);
        return simpleContentModel;
    }

    private final CMNode buildSyntaxTree(int startNode, XMLContentSpec contentSpec) {
        getContentSpec(startNode, contentSpec);
        short s;
        String str;
        int i;
        if ((contentSpec.type & 15) == 6) {
            s = contentSpec.type;
            str = (String) contentSpec.otherValue;
            i = this.fLeafCount;
            this.fLeafCount = i + 1;
            return new CMAny(s, str, i);
        } else if ((contentSpec.type & 15) == 7) {
            s = contentSpec.type;
            str = (String) contentSpec.otherValue;
            i = this.fLeafCount;
            this.fLeafCount = i + 1;
            return new CMAny(s, str, i);
        } else if ((contentSpec.type & 15) == CHUNK_SHIFT) {
            short s2 = contentSpec.type;
            r4 = this.fLeafCount;
            this.fLeafCount = r4 + 1;
            return new CMAny(s2, null, r4);
        } else if (contentSpec.type == (short) 0) {
            this.fQName.setValues(null, (String) contentSpec.value, (String) contentSpec.value, (String) contentSpec.otherValue);
            QName qName = this.fQName;
            r4 = this.fLeafCount;
            this.fLeafCount = r4 + 1;
            return new CMLeaf(qName, r4);
        } else {
            int leftNode = ((int[]) contentSpec.value)[0];
            int rightNode = ((int[]) contentSpec.otherValue)[0];
            if (contentSpec.type == (short) 4 || contentSpec.type == (short) 5) {
                return new CMBinOp(contentSpec.type, buildSyntaxTree(leftNode, contentSpec), buildSyntaxTree(rightNode, contentSpec));
            }
            if (contentSpec.type == (short) 2) {
                return new CMUniOp(contentSpec.type, buildSyntaxTree(leftNode, contentSpec));
            }
            if (contentSpec.type == (short) 2 || contentSpec.type == (short) 1 || contentSpec.type == (short) 3) {
                return new CMUniOp(contentSpec.type, buildSyntaxTree(leftNode, contentSpec));
            }
            throw new RuntimeException("ImplementationMessages.VAL_CST");
        }
    }

    private void contentSpecTree(int contentSpecIndex, XMLContentSpec contentSpec, ChildrenList children) {
        getContentSpec(contentSpecIndex, contentSpec);
        if (contentSpec.type == (short) 0 || (contentSpec.type & 15) == 6 || (contentSpec.type & 15) == CHUNK_SHIFT || (contentSpec.type & 15) == 7) {
            if (children.length == children.qname.length) {
                QName[] newQName = new QName[(children.length * 2)];
                System.arraycopy(children.qname, 0, newQName, 0, children.length);
                children.qname = newQName;
                int[] newType = new int[(children.length * 2)];
                System.arraycopy(children.type, 0, newType, 0, children.length);
                children.type = newType;
            }
            children.qname[children.length] = new QName(null, (String) contentSpec.value, (String) contentSpec.value, (String) contentSpec.otherValue);
            children.type[children.length] = contentSpec.type;
            children.length++;
            return;
        }
        int leftNode = contentSpec.value != null ? ((int[]) contentSpec.value)[0] : TOP_LEVEL_SCOPE;
        if (contentSpec.otherValue != null) {
            int rightNode = ((int[]) contentSpec.otherValue)[0];
            if (contentSpec.type == (short) 4 || contentSpec.type == (short) 5) {
                contentSpecTree(leftNode, contentSpec, children);
                contentSpecTree(rightNode, contentSpec, children);
            } else if (contentSpec.type == (short) 1 || contentSpec.type == (short) 2 || contentSpec.type == (short) 3) {
                contentSpecTree(leftNode, contentSpec, children);
            } else {
                throw new RuntimeException("Invalid content spec type seen in contentSpecTree() method of AbstractDTDGrammar class : " + contentSpec.type);
            }
        }
    }

    private void ensureElementDeclCapacity(int chunk) {
        if (chunk >= this.fElementDeclName.length) {
            this.fElementDeclIsExternal = resize(this.fElementDeclIsExternal, this.fElementDeclIsExternal.length * 2);
            this.fElementDeclName = resize(this.fElementDeclName, this.fElementDeclName.length * 2);
            this.fElementDeclType = resize(this.fElementDeclType, this.fElementDeclType.length * 2);
            this.fElementDeclContentModelValidator = resize(this.fElementDeclContentModelValidator, this.fElementDeclContentModelValidator.length * 2);
            this.fElementDeclContentSpecIndex = resize(this.fElementDeclContentSpecIndex, this.fElementDeclContentSpecIndex.length * 2);
            this.fElementDeclFirstAttributeDeclIndex = resize(this.fElementDeclFirstAttributeDeclIndex, this.fElementDeclFirstAttributeDeclIndex.length * 2);
            this.fElementDeclLastAttributeDeclIndex = resize(this.fElementDeclLastAttributeDeclIndex, this.fElementDeclLastAttributeDeclIndex.length * 2);
        } else if (this.fElementDeclName[chunk] != null) {
            return;
        }
        this.fElementDeclIsExternal[chunk] = new int[CHUNK_SIZE];
        this.fElementDeclName[chunk] = new QName[CHUNK_SIZE];
        this.fElementDeclType[chunk] = new short[CHUNK_SIZE];
        this.fElementDeclContentModelValidator[chunk] = new ContentModelValidator[CHUNK_SIZE];
        this.fElementDeclContentSpecIndex[chunk] = new int[CHUNK_SIZE];
        this.fElementDeclFirstAttributeDeclIndex[chunk] = new int[CHUNK_SIZE];
        this.fElementDeclLastAttributeDeclIndex[chunk] = new int[CHUNK_SIZE];
    }

    private void ensureAttributeDeclCapacity(int chunk) {
        if (chunk >= this.fAttributeDeclName.length) {
            this.fAttributeDeclIsExternal = resize(this.fAttributeDeclIsExternal, this.fAttributeDeclIsExternal.length * 2);
            this.fAttributeDeclName = resize(this.fAttributeDeclName, this.fAttributeDeclName.length * 2);
            this.fAttributeDeclType = resize(this.fAttributeDeclType, this.fAttributeDeclType.length * 2);
            this.fAttributeDeclEnumeration = resize(this.fAttributeDeclEnumeration, this.fAttributeDeclEnumeration.length * 2);
            this.fAttributeDeclDefaultType = resize(this.fAttributeDeclDefaultType, this.fAttributeDeclDefaultType.length * 2);
            this.fAttributeDeclDatatypeValidator = resize(this.fAttributeDeclDatatypeValidator, this.fAttributeDeclDatatypeValidator.length * 2);
            this.fAttributeDeclDefaultValue = resize(this.fAttributeDeclDefaultValue, this.fAttributeDeclDefaultValue.length * 2);
            this.fAttributeDeclNonNormalizedDefaultValue = resize(this.fAttributeDeclNonNormalizedDefaultValue, this.fAttributeDeclNonNormalizedDefaultValue.length * 2);
            this.fAttributeDeclNextAttributeDeclIndex = resize(this.fAttributeDeclNextAttributeDeclIndex, this.fAttributeDeclNextAttributeDeclIndex.length * 2);
        } else if (this.fAttributeDeclName[chunk] != null) {
            return;
        }
        this.fAttributeDeclIsExternal[chunk] = new int[CHUNK_SIZE];
        this.fAttributeDeclName[chunk] = new QName[CHUNK_SIZE];
        this.fAttributeDeclType[chunk] = new short[CHUNK_SIZE];
        this.fAttributeDeclEnumeration[chunk] = new String[CHUNK_SIZE][];
        this.fAttributeDeclDefaultType[chunk] = new short[CHUNK_SIZE];
        this.fAttributeDeclDatatypeValidator[chunk] = new DatatypeValidator[CHUNK_SIZE];
        this.fAttributeDeclDefaultValue[chunk] = new String[CHUNK_SIZE];
        this.fAttributeDeclNonNormalizedDefaultValue[chunk] = new String[CHUNK_SIZE];
        this.fAttributeDeclNextAttributeDeclIndex[chunk] = new int[CHUNK_SIZE];
    }

    private void ensureEntityDeclCapacity(int chunk) {
        if (chunk >= this.fEntityName.length) {
            this.fEntityName = resize(this.fEntityName, this.fEntityName.length * 2);
            this.fEntityValue = resize(this.fEntityValue, this.fEntityValue.length * 2);
            this.fEntityPublicId = resize(this.fEntityPublicId, this.fEntityPublicId.length * 2);
            this.fEntitySystemId = resize(this.fEntitySystemId, this.fEntitySystemId.length * 2);
            this.fEntityBaseSystemId = resize(this.fEntityBaseSystemId, this.fEntityBaseSystemId.length * 2);
            this.fEntityNotation = resize(this.fEntityNotation, this.fEntityNotation.length * 2);
            this.fEntityIsPE = resize(this.fEntityIsPE, this.fEntityIsPE.length * 2);
            this.fEntityInExternal = resize(this.fEntityInExternal, this.fEntityInExternal.length * 2);
        } else if (this.fEntityName[chunk] != null) {
            return;
        }
        this.fEntityName[chunk] = new String[CHUNK_SIZE];
        this.fEntityValue[chunk] = new String[CHUNK_SIZE];
        this.fEntityPublicId[chunk] = new String[CHUNK_SIZE];
        this.fEntitySystemId[chunk] = new String[CHUNK_SIZE];
        this.fEntityBaseSystemId[chunk] = new String[CHUNK_SIZE];
        this.fEntityNotation[chunk] = new String[CHUNK_SIZE];
        this.fEntityIsPE[chunk] = new byte[CHUNK_SIZE];
        this.fEntityInExternal[chunk] = new byte[CHUNK_SIZE];
    }

    private void ensureNotationDeclCapacity(int chunk) {
        if (chunk >= this.fNotationName.length) {
            this.fNotationName = resize(this.fNotationName, this.fNotationName.length * 2);
            this.fNotationPublicId = resize(this.fNotationPublicId, this.fNotationPublicId.length * 2);
            this.fNotationSystemId = resize(this.fNotationSystemId, this.fNotationSystemId.length * 2);
            this.fNotationBaseSystemId = resize(this.fNotationBaseSystemId, this.fNotationBaseSystemId.length * 2);
        } else if (this.fNotationName[chunk] != null) {
            return;
        }
        this.fNotationName[chunk] = new String[CHUNK_SIZE];
        this.fNotationPublicId[chunk] = new String[CHUNK_SIZE];
        this.fNotationSystemId[chunk] = new String[CHUNK_SIZE];
        this.fNotationBaseSystemId[chunk] = new String[CHUNK_SIZE];
    }

    private void ensureContentSpecCapacity(int chunk) {
        if (chunk >= this.fContentSpecType.length) {
            this.fContentSpecType = resize(this.fContentSpecType, this.fContentSpecType.length * 2);
            this.fContentSpecValue = resize(this.fContentSpecValue, this.fContentSpecValue.length * 2);
            this.fContentSpecOtherValue = resize(this.fContentSpecOtherValue, this.fContentSpecOtherValue.length * 2);
        } else if (this.fContentSpecType[chunk] != null) {
            return;
        }
        this.fContentSpecType[chunk] = new short[CHUNK_SIZE];
        this.fContentSpecValue[chunk] = new Object[CHUNK_SIZE];
        this.fContentSpecOtherValue[chunk] = new Object[CHUNK_SIZE];
    }

    private static byte[][] resize(byte[][] array, int newsize) {
        byte[][] newarray = new byte[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static short[][] resize(short[][] array, int newsize) {
        short[][] newarray = new short[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static int[][] resize(int[][] array, int newsize) {
        int[][] newarray = new int[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static DatatypeValidator[][] resize(DatatypeValidator[][] array, int newsize) {
        DatatypeValidator[][] newarray = new DatatypeValidator[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static ContentModelValidator[][] resize(ContentModelValidator[][] array, int newsize) {
        ContentModelValidator[][] newarray = new ContentModelValidator[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static Object[][] resize(Object[][] array, int newsize) {
        Object[][] newarray = new Object[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static QName[][] resize(QName[][] array, int newsize) {
        QName[][] newarray = new QName[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static String[][] resize(String[][] array, int newsize) {
        String[][] newarray = new String[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static String[][][] resize(String[][][] array, int newsize) {
        String[][][] newarray = new String[newsize][][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    public boolean isEntityDeclared(String name) {
        return getEntityDeclIndex(name) != TOP_LEVEL_SCOPE ? true : DEBUG;
    }

    public boolean isEntityUnparsed(String name) {
        int entityIndex = getEntityDeclIndex(name);
        if (entityIndex <= TOP_LEVEL_SCOPE) {
            return DEBUG;
        }
        if (this.fEntityNotation[entityIndex >> CHUNK_SHIFT][entityIndex & CHUNK_MASK] != null) {
            return true;
        }
        return DEBUG;
    }
}
