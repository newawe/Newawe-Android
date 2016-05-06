package mf.org.apache.xerces.impl.xs.traversers;

import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;
import mf.javax.xml.stream.XMLEventReader;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.XMLStreamReader;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaGrammar.Schema4Annotations;
import mf.org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.impl.xs.XMLSchemaLoader;
import mf.org.apache.xerces.impl.xs.XSAttributeDecl;
import mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSDDescription;
import mf.org.apache.xerces.impl.xs.XSDeclarationPool;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSGrammarBucket;
import mf.org.apache.xerces.impl.xs.XSGroupDecl;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.impl.xs.XSModelGroupImpl;
import mf.org.apache.xerces.impl.xs.XSNotationDecl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.opti.ElementImpl;
import mf.org.apache.xerces.impl.xs.opti.SchemaDOM;
import mf.org.apache.xerces.impl.xs.opti.SchemaDOMParser;
import mf.org.apache.xerces.impl.xs.opti.SchemaParsingConfig;
import mf.org.apache.xerces.impl.xs.util.SimpleLocator;
import mf.org.apache.xerces.impl.xs.util.XSInputSource;
import mf.org.apache.xerces.parsers.XML11Configuration;
import mf.org.apache.xerces.util.DOMInputSource;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.DefaultErrorHandler;
import mf.org.apache.xerces.util.ErrorHandlerWrapper;
import mf.org.apache.xerces.util.SAXInputSource;
import mf.org.apache.xerces.util.StAXInputSource;
import mf.org.apache.xerces.util.StAXLocationWrapper;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.grammars.XMLSchemaDescription;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSAttributeGroupDefinition;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSModelGroup;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSParticle;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTerm;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.datatypes.ObjectList;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XSDHandler {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    static final int ATTRIBUTEGROUP_TYPE = 2;
    static final int ATTRIBUTE_TYPE = 1;
    private static final String[] CIRCULAR_CODES;
    private static final String[] COMP_TYPE;
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final boolean DEBUG_NODE_POOL = false;
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    static final int ELEMENT_TYPE = 3;
    private static final String[] ELE_ERROR_CODES;
    private static final Hashtable EMPTY_TABLE;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    static final int GROUP_TYPE = 4;
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    static final int IDENTITYCONSTRAINT_TYPE = 5;
    private static final int INC_KEYREF_STACK_AMOUNT = 2;
    private static final int INC_STACK_SIZE = 10;
    private static final int INIT_KEYREF_STACK = 2;
    private static final int INIT_STACK_SIZE = 30;
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    static final int NOTATION_TYPE = 6;
    private static final String[][] NS_ERROR_CODES;
    public static final String REDEF_IDENTIFIER = "_fn3dktizrknc9pi";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    static final int TYPEDECL_TYPE = 7;
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private int[] fAllContext;
    private Vector fAllTNSs;
    XML11Configuration fAnnotationValidator;
    private XSAttributeChecker fAttributeChecker;
    XSDAttributeGroupTraverser fAttributeGroupTraverser;
    XSDAttributeTraverser fAttributeTraverser;
    XSDComplexTypeTraverser fComplexTypeTraverser;
    SchemaDVFactory fDVFactory;
    protected XSDeclarationPool fDeclPool;
    private Hashtable fDependencyMap;
    private Hashtable fDoc2SystemId;
    private Hashtable fDoc2XSDocumentMap;
    XSDElementTraverser fElementTraverser;
    private XMLEntityResolver fEntityResolver;
    private XMLErrorReporter fErrorReporter;
    SymbolHash fGlobalAttrDecls;
    SymbolHash fGlobalAttrGrpDecls;
    SymbolHash fGlobalElemDecls;
    SymbolHash fGlobalGroupDecls;
    SymbolHash fGlobalIDConstraintDecls;
    SymbolHash fGlobalNotationDecls;
    SymbolHash fGlobalTypeDecls;
    private XSGrammarBucket fGrammarBucket;
    XSAnnotationGrammarPool fGrammarBucketAdapter;
    private XMLGrammarPool fGrammarPool;
    XSDGroupTraverser fGroupTraverser;
    Hashtable fHiddenNodes;
    private boolean fHonourAllSchemaLocations;
    private Hashtable fImportMap;
    private XSElementDecl[] fKeyrefElems;
    private String[][] fKeyrefNamespaceContext;
    private int fKeyrefStackPos;
    XSDKeyrefTraverser fKeyrefTraverser;
    private Element[] fKeyrefs;
    private XSDocumentInfo[] fKeyrefsMapXSDocumentInfo;
    private boolean fLastSchemaWasDuplicate;
    private String[][] fLocalElemNamespaceContext;
    private int fLocalElemStackPos;
    private Element[] fLocalElementDecl;
    private XSDocumentInfo[] fLocalElementDecl_schema;
    private Hashtable fLocationPairs;
    boolean fNamespaceGrowth;
    protected Hashtable fNotationRegistry;
    XSDNotationTraverser fNotationTraverser;
    private XSObject[] fParent;
    private XSParticleDecl[] fParticle;
    private Hashtable fRedefine2NSSupport;
    private Hashtable fRedefine2XSDMap;
    private Hashtable fRedefinedRestrictedAttributeGroupRegistry;
    private Hashtable fRedefinedRestrictedGroupRegistry;
    private Vector fReportedTNS;
    private XSDocumentInfo fRoot;
    private XSDDescription fSchemaGrammarDescription;
    SchemaDOMParser fSchemaParser;
    XSDSimpleTypeTraverser fSimpleTypeTraverser;
    StAXSchemaParser fStAXSchemaParser;
    private SymbolTable fSymbolTable;
    boolean fTolerateDuplicates;
    private Hashtable fTraversed;
    XSDUniqueOrKeyTraverser fUniqueOrKeyTraverser;
    private Hashtable fUnparsedAttributeGroupRegistry;
    private Hashtable fUnparsedAttributeGroupRegistrySub;
    private Hashtable fUnparsedAttributeRegistry;
    private Hashtable fUnparsedAttributeRegistrySub;
    private Hashtable fUnparsedElementRegistry;
    private Hashtable fUnparsedElementRegistrySub;
    private Hashtable fUnparsedGroupRegistry;
    private Hashtable fUnparsedGroupRegistrySub;
    private Hashtable fUnparsedIdentityConstraintRegistry;
    private Hashtable fUnparsedIdentityConstraintRegistrySub;
    private Hashtable fUnparsedNotationRegistry;
    private Hashtable fUnparsedNotationRegistrySub;
    private Hashtable[] fUnparsedRegistriesExt;
    private Hashtable fUnparsedTypeRegistry;
    private Hashtable fUnparsedTypeRegistrySub;
    private boolean fValidateAnnotations;
    XSDWildcardTraverser fWildCardTraverser;
    SchemaContentHandler fXSContentHandler;
    private Hashtable fXSDocumentInfoRegistry;
    private SimpleLocator xl;

    private static class XSDKey {
        String referNS;
        short referType;
        String systemId;

        XSDKey(String systemId, short referType, String referNS) {
            this.systemId = systemId;
            this.referType = referType;
            this.referNS = referNS;
        }

        public int hashCode() {
            return this.referNS == null ? 0 : this.referNS.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof XSDKey)) {
                return XSDHandler.DEBUG_NODE_POOL;
            }
            XSDKey key = (XSDKey) obj;
            if (this.referNS == key.referNS && this.systemId != null && this.systemId.equals(key.systemId)) {
                return true;
            }
            return XSDHandler.DEBUG_NODE_POOL;
        }
    }

    private static class XSAnnotationGrammarPool implements XMLGrammarPool {
        private XSGrammarBucket fGrammarBucket;
        private Grammar[] fInitialGrammarSet;

        private XSAnnotationGrammarPool() {
        }

        public Grammar[] retrieveInitialGrammarSet(String grammarType) {
            if (grammarType != XMLGrammarDescription.XML_SCHEMA) {
                return new Grammar[0];
            }
            if (this.fInitialGrammarSet == null) {
                if (this.fGrammarBucket == null) {
                    Grammar[] grammarArr = new Grammar[XSDHandler.ATTRIBUTE_TYPE];
                    grammarArr[0] = Schema4Annotations.INSTANCE;
                    this.fInitialGrammarSet = grammarArr;
                } else {
                    SchemaGrammar[] schemaGrammars = this.fGrammarBucket.getGrammars();
                    for (int i = 0; i < schemaGrammars.length; i += XSDHandler.ATTRIBUTE_TYPE) {
                        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(schemaGrammars[i].getTargetNamespace())) {
                            this.fInitialGrammarSet = schemaGrammars;
                            return this.fInitialGrammarSet;
                        }
                    }
                    Grammar[] grammars = new Grammar[(schemaGrammars.length + XSDHandler.ATTRIBUTE_TYPE)];
                    System.arraycopy(schemaGrammars, 0, grammars, 0, schemaGrammars.length);
                    grammars[grammars.length - 1] = Schema4Annotations.INSTANCE;
                    this.fInitialGrammarSet = grammars;
                }
            }
            return this.fInitialGrammarSet;
        }

        public void cacheGrammars(String grammarType, Grammar[] grammars) {
        }

        public Grammar retrieveGrammar(XMLGrammarDescription desc) {
            if (desc.getGrammarType() == XMLGrammarDescription.XML_SCHEMA) {
                String tns = ((XMLSchemaDescription) desc).getTargetNamespace();
                if (this.fGrammarBucket != null) {
                    Grammar grammar = this.fGrammarBucket.getGrammar(tns);
                    if (grammar != null) {
                        return grammar;
                    }
                }
                if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(tns)) {
                    return Schema4Annotations.INSTANCE;
                }
            }
            return null;
        }

        public void refreshGrammars(XSGrammarBucket gBucket) {
            this.fGrammarBucket = gBucket;
            this.fInitialGrammarSet = null;
        }

        public void lockPool() {
        }

        public void unlockPool() {
        }

        public void clear() {
        }
    }

    private static final class SAX2XNIUtil extends ErrorHandlerWrapper {
        private SAX2XNIUtil() {
        }

        public static XMLParseException createXMLParseException0(SAXParseException exception) {
            return ErrorHandlerWrapper.createXMLParseException(exception);
        }

        public static XNIException createXNIException0(SAXException exception) {
            return ErrorHandlerWrapper.createXNIException(exception);
        }
    }

    static {
        EMPTY_TABLE = new Hashtable();
        r0 = new String[8][];
        String[] strArr = new String[INIT_KEYREF_STACK];
        strArr[0] = "src-include.2.1";
        strArr[ATTRIBUTE_TYPE] = "src-include.2.1";
        r0[0] = strArr;
        strArr = new String[INIT_KEYREF_STACK];
        strArr[0] = "src-redefine.3.1";
        strArr[ATTRIBUTE_TYPE] = "src-redefine.3.1";
        r0[ATTRIBUTE_TYPE] = strArr;
        strArr = new String[INIT_KEYREF_STACK];
        strArr[0] = "src-import.3.1";
        strArr[ATTRIBUTE_TYPE] = "src-import.3.2";
        r0[INIT_KEYREF_STACK] = strArr;
        strArr = new String[INIT_KEYREF_STACK];
        strArr[0] = "TargetNamespace.1";
        strArr[ATTRIBUTE_TYPE] = "TargetNamespace.2";
        r0[GROUP_TYPE] = strArr;
        strArr = new String[INIT_KEYREF_STACK];
        strArr[0] = "TargetNamespace.1";
        strArr[ATTRIBUTE_TYPE] = "TargetNamespace.2";
        r0[IDENTITYCONSTRAINT_TYPE] = strArr;
        String[] strArr2 = new String[INIT_KEYREF_STACK];
        strArr2[0] = "TargetNamespace.1";
        strArr2[ATTRIBUTE_TYPE] = "TargetNamespace.2";
        r0[NOTATION_TYPE] = strArr2;
        strArr2 = new String[INIT_KEYREF_STACK];
        strArr2[0] = "TargetNamespace.1";
        strArr2[ATTRIBUTE_TYPE] = "TargetNamespace.2";
        r0[TYPEDECL_TYPE] = strArr2;
        NS_ERROR_CODES = r0;
        ELE_ERROR_CODES = new String[]{"src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4"};
        String[] strArr3 = new String[8];
        strArr3[ATTRIBUTE_TYPE] = "attribute declaration";
        strArr3[INIT_KEYREF_STACK] = "attribute group";
        strArr3[ELEMENT_TYPE] = "element declaration";
        strArr3[GROUP_TYPE] = "group";
        strArr3[IDENTITYCONSTRAINT_TYPE] = "identity constraint";
        strArr3[NOTATION_TYPE] = "notation";
        strArr3[TYPEDECL_TYPE] = "type definition";
        COMP_TYPE = strArr3;
        CIRCULAR_CODES = new String[]{"Internal-Error", "Internal-Error", "src-attribute_group.3", "e-props-correct.6", "mg-props-correct.2", "Internal-Error", "Internal-Error", "st-props-correct.2"};
    }

    private String null2EmptyString(String ns) {
        return ns == null ? XMLSymbols.EMPTY_STRING : ns;
    }

    private String emptyString2Null(String ns) {
        return ns == XMLSymbols.EMPTY_STRING ? null : ns;
    }

    private String doc2SystemId(Element ele) {
        String documentURI = null;
        if (ele.getOwnerDocument() instanceof SchemaDOM) {
            documentURI = ((SchemaDOM) ele.getOwnerDocument()).getDocumentURI();
        }
        return documentURI != null ? documentURI : (String) this.fDoc2SystemId.get(ele);
    }

    public XSDHandler() {
        this.fNotationRegistry = new Hashtable();
        this.fDeclPool = null;
        this.fUnparsedAttributeRegistry = new Hashtable();
        this.fUnparsedAttributeGroupRegistry = new Hashtable();
        this.fUnparsedElementRegistry = new Hashtable();
        this.fUnparsedGroupRegistry = new Hashtable();
        this.fUnparsedIdentityConstraintRegistry = new Hashtable();
        this.fUnparsedNotationRegistry = new Hashtable();
        this.fUnparsedTypeRegistry = new Hashtable();
        this.fUnparsedAttributeRegistrySub = new Hashtable();
        this.fUnparsedAttributeGroupRegistrySub = new Hashtable();
        this.fUnparsedElementRegistrySub = new Hashtable();
        this.fUnparsedGroupRegistrySub = new Hashtable();
        this.fUnparsedIdentityConstraintRegistrySub = new Hashtable();
        this.fUnparsedNotationRegistrySub = new Hashtable();
        this.fUnparsedTypeRegistrySub = new Hashtable();
        Hashtable[] hashtableArr = new Hashtable[8];
        hashtableArr[ATTRIBUTE_TYPE] = new Hashtable();
        hashtableArr[INIT_KEYREF_STACK] = new Hashtable();
        hashtableArr[ELEMENT_TYPE] = new Hashtable();
        hashtableArr[GROUP_TYPE] = new Hashtable();
        hashtableArr[IDENTITYCONSTRAINT_TYPE] = new Hashtable();
        hashtableArr[NOTATION_TYPE] = new Hashtable();
        hashtableArr[TYPEDECL_TYPE] = new Hashtable();
        this.fUnparsedRegistriesExt = hashtableArr;
        this.fXSDocumentInfoRegistry = new Hashtable();
        this.fDependencyMap = new Hashtable();
        this.fImportMap = new Hashtable();
        this.fAllTNSs = new Vector();
        this.fLocationPairs = null;
        this.fHiddenNodes = null;
        this.fTraversed = new Hashtable();
        this.fDoc2SystemId = new Hashtable();
        this.fRoot = null;
        this.fDoc2XSDocumentMap = new Hashtable();
        this.fRedefine2XSDMap = new Hashtable();
        this.fRedefine2NSSupport = new Hashtable();
        this.fRedefinedRestrictedAttributeGroupRegistry = new Hashtable();
        this.fRedefinedRestrictedGroupRegistry = new Hashtable();
        this.fValidateAnnotations = DEBUG_NODE_POOL;
        this.fHonourAllSchemaLocations = DEBUG_NODE_POOL;
        this.fNamespaceGrowth = DEBUG_NODE_POOL;
        this.fTolerateDuplicates = DEBUG_NODE_POOL;
        this.fLocalElemStackPos = 0;
        this.fParticle = new XSParticleDecl[INIT_STACK_SIZE];
        this.fLocalElementDecl = new Element[INIT_STACK_SIZE];
        this.fLocalElementDecl_schema = new XSDocumentInfo[INIT_STACK_SIZE];
        this.fAllContext = new int[INIT_STACK_SIZE];
        this.fParent = new XSObject[INIT_STACK_SIZE];
        this.fLocalElemNamespaceContext = (String[][]) Array.newInstance(String.class, new int[]{INIT_STACK_SIZE, ATTRIBUTE_TYPE});
        this.fKeyrefStackPos = 0;
        this.fKeyrefs = new Element[INIT_KEYREF_STACK];
        this.fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[INIT_KEYREF_STACK];
        this.fKeyrefElems = new XSElementDecl[INIT_KEYREF_STACK];
        this.fKeyrefNamespaceContext = (String[][]) Array.newInstance(String.class, new int[]{INIT_KEYREF_STACK, ATTRIBUTE_TYPE});
        this.fGlobalAttrDecls = new SymbolHash();
        this.fGlobalAttrGrpDecls = new SymbolHash();
        this.fGlobalElemDecls = new SymbolHash();
        this.fGlobalGroupDecls = new SymbolHash();
        this.fGlobalNotationDecls = new SymbolHash();
        this.fGlobalIDConstraintDecls = new SymbolHash();
        this.fGlobalTypeDecls = new SymbolHash();
        this.fReportedTNS = null;
        this.xl = new SimpleLocator();
        this.fHiddenNodes = new Hashtable();
        this.fSchemaParser = new SchemaDOMParser(new SchemaParsingConfig());
    }

    public XSDHandler(XSGrammarBucket gBucket) {
        this();
        this.fGrammarBucket = gBucket;
        this.fSchemaGrammarDescription = new XSDDescription();
    }

    public SchemaGrammar parseSchema(XMLInputSource is, XSDDescription desc, Hashtable locationPairs) throws IOException {
        Element schemaRoot;
        this.fLocationPairs = locationPairs;
        this.fSchemaParser.resetNodePool();
        SchemaGrammar grammar = null;
        String schemaNamespace = null;
        short referType = desc.getContextType();
        if (referType != (short) 3) {
            if (this.fHonourAllSchemaLocations && referType == (short) 2) {
                if (isExistingGrammar(desc, this.fNamespaceGrowth)) {
                    grammar = this.fGrammarBucket.getGrammar(desc.getTargetNamespace());
                    if (grammar != null) {
                        if (!this.fNamespaceGrowth) {
                            return grammar;
                        }
                        try {
                            if (grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(is.getSystemId(), is.getBaseSystemId(), DEBUG_NODE_POOL))) {
                                return grammar;
                            }
                        } catch (MalformedURIException e) {
                        }
                    }
                    schemaNamespace = desc.getTargetNamespace();
                    if (schemaNamespace != null) {
                        schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
                    }
                }
            }
            grammar = findGrammar(desc, this.fNamespaceGrowth);
            if (grammar != null) {
                if (!this.fNamespaceGrowth) {
                    return grammar;
                }
                if (grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(is.getSystemId(), is.getBaseSystemId(), DEBUG_NODE_POOL))) {
                    return grammar;
                }
            }
            schemaNamespace = desc.getTargetNamespace();
            if (schemaNamespace != null) {
                schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
            }
        }
        prepareForParse();
        if (is instanceof DOMInputSource) {
            schemaRoot = getSchemaDocument(schemaNamespace, (DOMInputSource) is, referType == (short) 3 ? true : DEBUG_NODE_POOL, referType, null);
        } else if (is instanceof SAXInputSource) {
            schemaRoot = getSchemaDocument(schemaNamespace, (SAXInputSource) is, referType == (short) 3 ? true : DEBUG_NODE_POOL, referType, null);
        } else if (is instanceof StAXInputSource) {
            schemaRoot = getSchemaDocument(schemaNamespace, (StAXInputSource) is, referType == (short) 3 ? true : DEBUG_NODE_POOL, referType, null);
        } else if (is instanceof XSInputSource) {
            schemaRoot = getSchemaDocument((XSInputSource) is, desc);
        } else {
            schemaRoot = getSchemaDocument(schemaNamespace, is, referType == (short) 3 ? true : DEBUG_NODE_POOL, referType, null);
        }
        if (schemaRoot != null) {
            if (referType == (short) 3) {
                schemaNamespace = DOMUtil.getAttrValue(schemaRoot, SchemaSymbols.ATT_TARGETNAMESPACE);
                if (schemaNamespace == null || schemaNamespace.length() <= 0) {
                    schemaNamespace = null;
                } else {
                    schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
                    desc.setTargetNamespace(schemaNamespace);
                }
                grammar = findGrammar(desc, this.fNamespaceGrowth);
                String schemaId = XMLEntityManager.expandSystemId(is.getSystemId(), is.getBaseSystemId(), DEBUG_NODE_POOL);
                if (grammar != null && (!this.fNamespaceGrowth || (schemaId != null && grammar.getDocumentLocations().contains(schemaId)))) {
                    return grammar;
                }
                this.fTraversed.put(new XSDKey(schemaId, referType, schemaNamespace), schemaRoot);
                if (schemaId != null) {
                    this.fDoc2SystemId.put(schemaRoot, schemaId);
                }
            }
            prepareForTraverse();
            this.fRoot = constructTrees(schemaRoot, is.getSystemId(), desc, grammar != null ? true : DEBUG_NODE_POOL);
            if (this.fRoot == null) {
                return null;
            }
            buildGlobalNameRegistries();
            ArrayList annotationInfo = this.fValidateAnnotations ? new ArrayList() : null;
            traverseSchemas(annotationInfo);
            traverseLocalElements();
            resolveKeyRefs();
            for (int i = this.fAllTNSs.size() - 1; i >= 0; i--) {
                String tns = (String) this.fAllTNSs.elementAt(i);
                Vector ins = (Vector) this.fImportMap.get(tns);
                SchemaGrammar sg = this.fGrammarBucket.getGrammar(emptyString2Null(tns));
                if (sg != null) {
                    int count = 0;
                    for (int j = 0; j < ins.size(); j += ATTRIBUTE_TYPE) {
                        SchemaGrammar isg = this.fGrammarBucket.getGrammar((String) ins.elementAt(j));
                        if (isg != null) {
                            int count2 = count + ATTRIBUTE_TYPE;
                            ins.setElementAt(isg, count);
                            count = count2;
                        }
                    }
                    ins.setSize(count);
                    sg.setImportedGrammars(ins);
                }
            }
            if (this.fValidateAnnotations && annotationInfo.size() > 0) {
                validateAnnotations(annotationInfo);
            }
            return this.fGrammarBucket.getGrammar(this.fRoot.fTargetNamespace);
        } else if (is instanceof XSInputSource) {
            return this.fGrammarBucket.getGrammar(desc.getTargetNamespace());
        } else {
            return grammar;
        }
    }

    private void validateAnnotations(ArrayList annotationInfo) {
        if (this.fAnnotationValidator == null) {
            createAnnotationValidator();
        }
        int size = annotationInfo.size();
        XMLInputSource src = new XMLInputSource(null, null, null);
        this.fGrammarBucketAdapter.refreshGrammars(this.fGrammarBucket);
        for (int i = 0; i < size; i += INIT_KEYREF_STACK) {
            src.setSystemId((String) annotationInfo.get(i));
            for (XSAnnotationInfo annotation = (XSAnnotationInfo) annotationInfo.get(i + ATTRIBUTE_TYPE); annotation != null; annotation = annotation.next) {
                src.setCharacterStream(new StringReader(annotation.fAnnotation));
                try {
                    this.fAnnotationValidator.parse(src);
                } catch (IOException e) {
                }
            }
        }
    }

    private void createAnnotationValidator() {
        this.fAnnotationValidator = new XML11Configuration();
        this.fGrammarBucketAdapter = new XSAnnotationGrammarPool();
        this.fAnnotationValidator.setFeature(VALIDATION, true);
        this.fAnnotationValidator.setFeature(XMLSCHEMA_VALIDATION, true);
        this.fAnnotationValidator.setProperty(XMLGRAMMAR_POOL, this.fGrammarBucketAdapter);
        XMLErrorHandler errorHandler = this.fErrorReporter.getErrorHandler();
        XML11Configuration xML11Configuration = this.fAnnotationValidator;
        String str = ERROR_HANDLER;
        if (errorHandler == null) {
            errorHandler = new DefaultErrorHandler();
        }
        xML11Configuration.setProperty(str, errorHandler);
        this.fAnnotationValidator.setProperty(LOCALE, this.fErrorReporter.getLocale());
    }

    SchemaGrammar getGrammar(String tns) {
        return this.fGrammarBucket.getGrammar(tns);
    }

    protected SchemaGrammar findGrammar(XSDDescription desc, boolean ignoreConflict) {
        SchemaGrammar sg = this.fGrammarBucket.getGrammar(desc.getTargetNamespace());
        if (sg != null || this.fGrammarPool == null) {
            return sg;
        }
        sg = (SchemaGrammar) this.fGrammarPool.retrieveGrammar(desc);
        if (sg == null || this.fGrammarBucket.putGrammar(sg, true, ignoreConflict)) {
            return sg;
        }
        reportSchemaWarning("GrammarConflict", null, null);
        return null;
    }

    protected XSDocumentInfo constructTrees(Element schemaRoot, String locationHint, XSDDescription desc, boolean nsCollision) {
        if (schemaRoot == null) {
            return null;
        }
        String callerTNS = desc.getTargetNamespace();
        short referType = desc.getContextType();
        Object[] objArr;
        String str;
        try {
            SchemaGrammar sg;
            XSDocumentInfo currSchemaInfo = new XSDocumentInfo(schemaRoot, this.fAttributeChecker, this.fSymbolTable);
            if (currSchemaInfo.fTargetNamespace != null && currSchemaInfo.fTargetNamespace.length() == 0) {
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = locationHint;
                reportSchemaWarning("EmptyTargetNamespace", objArr, schemaRoot);
                currSchemaInfo.fTargetNamespace = null;
            }
            if (callerTNS != null) {
                if (referType == (short) 0 || referType == ATTRIBUTE_TYPE) {
                    if (currSchemaInfo.fTargetNamespace == null) {
                        currSchemaInfo.fTargetNamespace = callerTNS;
                        currSchemaInfo.fIsChameleonSchema = true;
                    } else if (callerTNS != currSchemaInfo.fTargetNamespace) {
                        str = NS_ERROR_CODES[referType][0];
                        objArr = new Object[INIT_KEYREF_STACK];
                        objArr[0] = callerTNS;
                        objArr[ATTRIBUTE_TYPE] = currSchemaInfo.fTargetNamespace;
                        reportSchemaError(str, objArr, schemaRoot);
                        return null;
                    }
                } else if (!(referType == ELEMENT_TYPE || callerTNS == currSchemaInfo.fTargetNamespace)) {
                    str = NS_ERROR_CODES[referType][0];
                    objArr = new Object[INIT_KEYREF_STACK];
                    objArr[0] = callerTNS;
                    objArr[ATTRIBUTE_TYPE] = currSchemaInfo.fTargetNamespace;
                    reportSchemaError(str, objArr, schemaRoot);
                    return null;
                }
            } else if (currSchemaInfo.fTargetNamespace != null) {
                if (referType == ELEMENT_TYPE) {
                    desc.setTargetNamespace(currSchemaInfo.fTargetNamespace);
                    callerTNS = currSchemaInfo.fTargetNamespace;
                } else {
                    str = NS_ERROR_CODES[referType][ATTRIBUTE_TYPE];
                    objArr = new Object[INIT_KEYREF_STACK];
                    objArr[0] = callerTNS;
                    objArr[ATTRIBUTE_TYPE] = currSchemaInfo.fTargetNamespace;
                    reportSchemaError(str, objArr, schemaRoot);
                    return null;
                }
            }
            currSchemaInfo.addAllowedNS(currSchemaInfo.fTargetNamespace);
            if (nsCollision) {
                SchemaGrammar sg2 = this.fGrammarBucket.getGrammar(currSchemaInfo.fTargetNamespace);
                if (sg2.isImmutable()) {
                    sg = new SchemaGrammar(sg2);
                    this.fGrammarBucket.putGrammar(sg);
                    updateImportListWith(sg);
                } else {
                    sg = sg2;
                }
                updateImportListFor(sg);
            } else if (referType == (short) 0 || referType == ATTRIBUTE_TYPE) {
                sg = this.fGrammarBucket.getGrammar(currSchemaInfo.fTargetNamespace);
            } else if (this.fHonourAllSchemaLocations && referType == INIT_KEYREF_STACK) {
                sg = findGrammar(desc, DEBUG_NODE_POOL);
                if (sg == null) {
                    r0 = new SchemaGrammar(currSchemaInfo.fTargetNamespace, desc.makeClone(), this.fSymbolTable);
                    this.fGrammarBucket.putGrammar(r0);
                }
            } else {
                r0 = new SchemaGrammar(currSchemaInfo.fTargetNamespace, desc.makeClone(), this.fSymbolTable);
                this.fGrammarBucket.putGrammar(r0);
            }
            sg.addDocument(null, (String) this.fDoc2SystemId.get(currSchemaInfo.fSchemaElement));
            this.fDoc2XSDocumentMap.put(schemaRoot, currSchemaInfo);
            Vector dependencies = new Vector();
            Element newSchemaRoot = null;
            for (Element child = DOMUtil.getFirstChildElement(schemaRoot); child != null; child = DOMUtil.getNextSiblingElement(child)) {
                String localName = DOMUtil.getLocalName(child);
                boolean importCollision = DEBUG_NODE_POOL;
                if (!localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                    String schemaHint;
                    String schemaNamespace;
                    String text;
                    XSDDescription xSDDescription;
                    String[] strArr;
                    if (localName.equals(SchemaSymbols.ELT_IMPORT)) {
                        Object[] importAttrs = this.fAttributeChecker.checkAttributes(child, true, currSchemaInfo);
                        schemaHint = importAttrs[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                        schemaNamespace = importAttrs[XSAttributeChecker.ATTIDX_NAMESPACE];
                        if (schemaNamespace != null) {
                            schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
                        }
                        Element importChild = DOMUtil.getFirstChildElement(child);
                        if (importChild != null) {
                            String importComponentType = DOMUtil.getLocalName(importChild);
                            if (importComponentType.equals(SchemaSymbols.ELT_ANNOTATION)) {
                                sg.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(importChild, importAttrs, true, currSchemaInfo));
                            } else {
                                objArr = new Object[ELEMENT_TYPE];
                                objArr[0] = localName;
                                objArr[ATTRIBUTE_TYPE] = "annotation?";
                                objArr[INIT_KEYREF_STACK] = importComponentType;
                                reportSchemaError("s4s-elt-must-match.1", objArr, child);
                            }
                            if (DOMUtil.getNextSiblingElement(importChild) != null) {
                                objArr = new Object[ELEMENT_TYPE];
                                objArr[0] = localName;
                                objArr[ATTRIBUTE_TYPE] = "annotation?";
                                objArr[INIT_KEYREF_STACK] = DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(importChild));
                                reportSchemaError("s4s-elt-must-match.1", objArr, child);
                            }
                        } else {
                            text = DOMUtil.getSyntheticAnnotation(child);
                            if (text != null) {
                                sg.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(child, text, importAttrs, true, currSchemaInfo));
                            }
                        }
                        this.fAttributeChecker.returnAttrArray(importAttrs, currSchemaInfo);
                        if (schemaNamespace == currSchemaInfo.fTargetNamespace) {
                            str = schemaNamespace != null ? "src-import.1.1" : "src-import.1.2";
                            objArr = new Object[ATTRIBUTE_TYPE];
                            objArr[0] = schemaNamespace;
                            reportSchemaError(str, objArr, child);
                        } else {
                            boolean z;
                            if (!currSchemaInfo.isAllowedNS(schemaNamespace)) {
                                currSchemaInfo.addAllowedNS(schemaNamespace);
                            } else if (!(this.fHonourAllSchemaLocations || this.fNamespaceGrowth)) {
                            }
                            String tns = null2EmptyString(currSchemaInfo.fTargetNamespace);
                            Vector ins = (Vector) this.fImportMap.get(tns);
                            if (ins == null) {
                                this.fAllTNSs.addElement(tns);
                                ins = new Vector();
                                this.fImportMap.put(tns, ins);
                                ins.addElement(schemaNamespace);
                            } else if (!ins.contains(schemaNamespace)) {
                                ins.addElement(schemaNamespace);
                            }
                            this.fSchemaGrammarDescription.reset();
                            this.fSchemaGrammarDescription.setContextType((short) 2);
                            this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(schemaRoot));
                            this.fSchemaGrammarDescription.setLiteralSystemId(schemaHint);
                            xSDDescription = this.fSchemaGrammarDescription;
                            strArr = new String[ATTRIBUTE_TYPE];
                            strArr[0] = schemaHint;
                            xSDDescription.setLocationHints(strArr);
                            this.fSchemaGrammarDescription.setTargetNamespace(schemaNamespace);
                            SchemaGrammar isg = findGrammar(this.fSchemaGrammarDescription, this.fNamespaceGrowth);
                            if (isg != null) {
                                if (this.fNamespaceGrowth) {
                                    try {
                                        if (!isg.getDocumentLocations().contains(XMLEntityManager.expandSystemId(schemaHint, this.fSchemaGrammarDescription.getBaseSystemId(), DEBUG_NODE_POOL))) {
                                            importCollision = true;
                                        }
                                    } catch (MalformedURIException e) {
                                    }
                                } else if (this.fHonourAllSchemaLocations) {
                                    if (isExistingGrammar(this.fSchemaGrammarDescription, DEBUG_NODE_POOL)) {
                                    }
                                }
                            }
                            XSDDescription xSDDescription2 = this.fSchemaGrammarDescription;
                            if (isg == null) {
                                z = true;
                            } else {
                                z = DEBUG_NODE_POOL;
                            }
                            newSchemaRoot = resolveSchema(xSDDescription2, (boolean) DEBUG_NODE_POOL, child, z);
                        }
                    } else {
                        if (!localName.equals(SchemaSymbols.ELT_INCLUDE)) {
                            if (!localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                                break;
                            }
                        }
                        Object[] includeAttrs = this.fAttributeChecker.checkAttributes(child, true, currSchemaInfo);
                        schemaHint = includeAttrs[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                        if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                            this.fRedefine2NSSupport.put(child, new SchemaNamespaceSupport(currSchemaInfo.fNamespaceSupport));
                        }
                        if (localName.equals(SchemaSymbols.ELT_INCLUDE)) {
                            Element includeChild = DOMUtil.getFirstChildElement(child);
                            if (includeChild != null) {
                                String includeComponentType = DOMUtil.getLocalName(includeChild);
                                if (includeComponentType.equals(SchemaSymbols.ELT_ANNOTATION)) {
                                    sg.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(includeChild, includeAttrs, true, currSchemaInfo));
                                } else {
                                    objArr = new Object[ELEMENT_TYPE];
                                    objArr[0] = localName;
                                    objArr[ATTRIBUTE_TYPE] = "annotation?";
                                    objArr[INIT_KEYREF_STACK] = includeComponentType;
                                    reportSchemaError("s4s-elt-must-match.1", objArr, child);
                                }
                                if (DOMUtil.getNextSiblingElement(includeChild) != null) {
                                    objArr = new Object[ELEMENT_TYPE];
                                    objArr[0] = localName;
                                    objArr[ATTRIBUTE_TYPE] = "annotation?";
                                    objArr[INIT_KEYREF_STACK] = DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(includeChild));
                                    reportSchemaError("s4s-elt-must-match.1", objArr, child);
                                }
                            } else {
                                text = DOMUtil.getSyntheticAnnotation(child);
                                if (text != null) {
                                    sg.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(child, text, includeAttrs, true, currSchemaInfo));
                                }
                            }
                        } else {
                            for (Element redefinedChild = DOMUtil.getFirstChildElement(child); redefinedChild != null; redefinedChild = DOMUtil.getNextSiblingElement(redefinedChild)) {
                                if (DOMUtil.getLocalName(redefinedChild).equals(SchemaSymbols.ELT_ANNOTATION)) {
                                    sg.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(redefinedChild, includeAttrs, true, currSchemaInfo));
                                    DOMUtil.setHidden(redefinedChild, this.fHiddenNodes);
                                } else {
                                    text = DOMUtil.getSyntheticAnnotation(child);
                                    if (text != null) {
                                        sg.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(child, text, includeAttrs, true, currSchemaInfo));
                                    }
                                }
                            }
                        }
                        this.fAttributeChecker.returnAttrArray(includeAttrs, currSchemaInfo);
                        if (schemaHint == null) {
                            objArr = new Object[INIT_KEYREF_STACK];
                            objArr[0] = "<include> or <redefine>";
                            objArr[ATTRIBUTE_TYPE] = "schemaLocation";
                            reportSchemaError("s4s-att-must-appear", objArr, child);
                        }
                        boolean mustResolve = DEBUG_NODE_POOL;
                        short refType = (short) 0;
                        if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                            mustResolve = nonAnnotationContent(child);
                            refType = (short) 1;
                        }
                        this.fSchemaGrammarDescription.reset();
                        this.fSchemaGrammarDescription.setContextType(refType);
                        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(schemaRoot));
                        xSDDescription = this.fSchemaGrammarDescription;
                        strArr = new String[ATTRIBUTE_TYPE];
                        strArr[0] = schemaHint;
                        xSDDescription.setLocationHints(strArr);
                        this.fSchemaGrammarDescription.setTargetNamespace(callerTNS);
                        boolean alreadyTraversed = DEBUG_NODE_POOL;
                        XMLInputSource schemaSource = resolveSchemaSource(this.fSchemaGrammarDescription, mustResolve, child, true);
                        if (this.fNamespaceGrowth && refType == (short) 0) {
                            try {
                                alreadyTraversed = sg.getDocumentLocations().contains(XMLEntityManager.expandSystemId(schemaSource.getSystemId(), schemaSource.getBaseSystemId(), DEBUG_NODE_POOL));
                            } catch (MalformedURIException e2) {
                            }
                        }
                        if (alreadyTraversed) {
                            this.fLastSchemaWasDuplicate = true;
                        } else {
                            newSchemaRoot = resolveSchema(schemaSource, this.fSchemaGrammarDescription, mustResolve, child);
                            schemaNamespace = currSchemaInfo.fTargetNamespace;
                        }
                    }
                    XSDocumentInfo newSchemaInfo = this.fLastSchemaWasDuplicate ? newSchemaRoot == null ? null : (XSDocumentInfo) this.fDoc2XSDocumentMap.get(newSchemaRoot) : constructTrees(newSchemaRoot, schemaHint, this.fSchemaGrammarDescription, importCollision);
                    if (localName.equals(SchemaSymbols.ELT_REDEFINE) && newSchemaInfo != null) {
                        this.fRedefine2XSDMap.put(child, newSchemaInfo);
                    }
                    if (newSchemaRoot != null) {
                        if (newSchemaInfo != null) {
                            dependencies.addElement(newSchemaInfo);
                        }
                        newSchemaRoot = null;
                    }
                }
            }
            this.fDependencyMap.put(currSchemaInfo, dependencies);
            return currSchemaInfo;
        } catch (XMLSchemaException e3) {
            str = ELE_ERROR_CODES[referType];
            objArr = new Object[ATTRIBUTE_TYPE];
            objArr[0] = locationHint;
            reportSchemaError(str, objArr, schemaRoot);
            return null;
        }
    }

    private boolean isExistingGrammar(XSDDescription desc, boolean ignoreConflict) {
        SchemaGrammar sg = this.fGrammarBucket.getGrammar(desc.getTargetNamespace());
        if (sg == null) {
            if (findGrammar(desc, ignoreConflict) != null) {
                return true;
            }
            return DEBUG_NODE_POOL;
        } else if (sg.isImmutable()) {
            return true;
        } else {
            try {
                return sg.getDocumentLocations().contains(XMLEntityManager.expandSystemId(desc.getLiteralSystemId(), desc.getBaseSystemId(), DEBUG_NODE_POOL));
            } catch (MalformedURIException e) {
                return DEBUG_NODE_POOL;
            }
        }
    }

    private void updateImportListFor(SchemaGrammar grammar) {
        Vector importedGrammars = grammar.getImportedGrammars();
        if (importedGrammars != null) {
            for (int i = 0; i < importedGrammars.size(); i += ATTRIBUTE_TYPE) {
                SchemaGrammar isg1 = (SchemaGrammar) importedGrammars.elementAt(i);
                SchemaGrammar isg2 = this.fGrammarBucket.getGrammar(isg1.getTargetNamespace());
                if (!(isg2 == null || isg1 == isg2)) {
                    importedGrammars.set(i, isg2);
                }
            }
        }
    }

    private void updateImportListWith(SchemaGrammar newGrammar) {
        SchemaGrammar[] schemaGrammars = this.fGrammarBucket.getGrammars();
        for (int i = 0; i < schemaGrammars.length; i += ATTRIBUTE_TYPE) {
            SchemaGrammar sg = schemaGrammars[i];
            if (sg != newGrammar) {
                Vector importedGrammars = sg.getImportedGrammars();
                if (importedGrammars != null) {
                    int j = 0;
                    while (j < importedGrammars.size()) {
                        SchemaGrammar isg = (SchemaGrammar) importedGrammars.elementAt(j);
                        if (!null2EmptyString(isg.getTargetNamespace()).equals(null2EmptyString(newGrammar.getTargetNamespace()))) {
                            j += ATTRIBUTE_TYPE;
                        } else if (isg != newGrammar) {
                            importedGrammars.set(j, newGrammar);
                        }
                    }
                }
            }
        }
    }

    protected void buildGlobalNameRegistries() {
        Stack schemasToProcess = new Stack();
        schemasToProcess.push(this.fRoot);
        while (!schemasToProcess.empty()) {
            XSDocumentInfo currSchemaDoc = (XSDocumentInfo) schemasToProcess.pop();
            Element currDoc = currSchemaDoc.fSchemaElement;
            if (!DOMUtil.isHidden(currDoc, this.fHiddenNodes)) {
                boolean dependenciesCanOccur = true;
                Element globalComp = DOMUtil.getFirstChildElement(currDoc);
                while (globalComp != null) {
                    if (!DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        Object[] objArr;
                        if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_IMPORT)) {
                            if (!dependenciesCanOccur) {
                                objArr = new Object[ATTRIBUTE_TYPE];
                                objArr[0] = DOMUtil.getLocalName(globalComp);
                                reportSchemaError("s4s-elt-invalid-content.3", objArr, globalComp);
                            }
                            DOMUtil.setHidden(globalComp, this.fHiddenNodes);
                        } else if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_REDEFINE)) {
                            if (!dependenciesCanOccur) {
                                objArr = new Object[ATTRIBUTE_TYPE];
                                objArr[0] = DOMUtil.getLocalName(globalComp);
                                reportSchemaError("s4s-elt-invalid-content.3", objArr, globalComp);
                            }
                            for (Element redefineComp = DOMUtil.getFirstChildElement(globalComp); redefineComp != null; redefineComp = DOMUtil.getNextSiblingElement(redefineComp)) {
                                lName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME);
                                if (lName.length() != 0) {
                                    if (currSchemaDoc.fTargetNamespace == null) {
                                        qName = "," + lName;
                                    } else {
                                        qName = currSchemaDoc.fTargetNamespace + "," + lName;
                                    }
                                    componentType = DOMUtil.getLocalName(redefineComp);
                                    XSDocumentInfo xSDocumentInfo;
                                    Element element;
                                    if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                        checkForDuplicateNames(qName, INIT_KEYREF_STACK, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, redefineComp, currSchemaDoc);
                                        xSDocumentInfo = currSchemaDoc;
                                        element = redefineComp;
                                        renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_ATTRIBUTEGROUP, lName, DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + REDEF_IDENTIFIER);
                                    } else {
                                        if (!componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                            if (!componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                                if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
                                                    checkForDuplicateNames(qName, GROUP_TYPE, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, redefineComp, currSchemaDoc);
                                                    xSDocumentInfo = currSchemaDoc;
                                                    element = redefineComp;
                                                    renameRedefiningComponents(xSDocumentInfo, element, SchemaSymbols.ELT_GROUP, lName, DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + REDEF_IDENTIFIER);
                                                }
                                            }
                                        }
                                        checkForDuplicateNames(qName, TYPEDECL_TYPE, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, redefineComp, currSchemaDoc);
                                        String targetLName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + REDEF_IDENTIFIER;
                                        if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                            renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_COMPLEXTYPE, lName, targetLName);
                                        } else {
                                            renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_SIMPLETYPE, lName, targetLName);
                                        }
                                    }
                                }
                            }
                        } else {
                            dependenciesCanOccur = DEBUG_NODE_POOL;
                            lName = DOMUtil.getAttrValue(globalComp, SchemaSymbols.ATT_NAME);
                            if (lName.length() != 0) {
                                if (currSchemaDoc.fTargetNamespace == null) {
                                    qName = "," + lName;
                                } else {
                                    qName = currSchemaDoc.fTargetNamespace + "," + lName;
                                }
                                componentType = DOMUtil.getLocalName(globalComp);
                                String str;
                                if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                                    str = qName;
                                    checkForDuplicateNames(str, ATTRIBUTE_TYPE, this.fUnparsedAttributeRegistry, this.fUnparsedAttributeRegistrySub, globalComp, currSchemaDoc);
                                } else {
                                    if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                        str = qName;
                                        checkForDuplicateNames(str, INIT_KEYREF_STACK, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistrySub, globalComp, currSchemaDoc);
                                    } else {
                                        if (!componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                            if (!componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                                if (componentType.equals(SchemaSymbols.ELT_ELEMENT)) {
                                                    str = qName;
                                                    checkForDuplicateNames(str, ELEMENT_TYPE, this.fUnparsedElementRegistry, this.fUnparsedElementRegistrySub, globalComp, currSchemaDoc);
                                                } else {
                                                    if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
                                                        str = qName;
                                                        checkForDuplicateNames(str, GROUP_TYPE, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistrySub, globalComp, currSchemaDoc);
                                                    } else {
                                                        if (componentType.equals(SchemaSymbols.ELT_NOTATION)) {
                                                            str = qName;
                                                            checkForDuplicateNames(str, NOTATION_TYPE, this.fUnparsedNotationRegistry, this.fUnparsedNotationRegistrySub, globalComp, currSchemaDoc);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        str = qName;
                                        checkForDuplicateNames(str, TYPEDECL_TYPE, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistrySub, globalComp, currSchemaDoc);
                                    }
                                }
                            }
                        }
                    }
                    globalComp = DOMUtil.getNextSiblingElement(globalComp);
                }
                DOMUtil.setHidden(currDoc, this.fHiddenNodes);
                Vector currSchemaDepends = (Vector) this.fDependencyMap.get(currSchemaDoc);
                for (int i = 0; i < currSchemaDepends.size(); i += ATTRIBUTE_TYPE) {
                    schemasToProcess.push(currSchemaDepends.elementAt(i));
                }
            }
        }
    }

    protected void traverseSchemas(ArrayList annotationInfo) {
        setSchemasVisible(this.fRoot);
        Stack schemasToProcess = new Stack();
        schemasToProcess.push(this.fRoot);
        while (!schemasToProcess.empty()) {
            XSDocumentInfo currSchemaDoc = (XSDocumentInfo) schemasToProcess.pop();
            Element currDoc = currSchemaDoc.fSchemaElement;
            SchemaGrammar currSG = this.fGrammarBucket.getGrammar(currSchemaDoc.fTargetNamespace);
            if (!DOMUtil.isHidden(currDoc, this.fHiddenNodes)) {
                Element currRoot = currDoc;
                boolean sawAnnotation = DEBUG_NODE_POOL;
                Element globalComp = DOMUtil.getFirstVisibleChildElement(currRoot, this.fHiddenNodes);
                while (globalComp != null) {
                    DOMUtil.setHidden(globalComp, this.fHiddenNodes);
                    String componentType = DOMUtil.getLocalName(globalComp);
                    Object[] objArr;
                    if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_REDEFINE)) {
                        currSchemaDoc.backupNSSupport((SchemaNamespaceSupport) this.fRedefine2NSSupport.get(globalComp));
                        Element redefinedComp = DOMUtil.getFirstVisibleChildElement(globalComp, this.fHiddenNodes);
                        while (redefinedComp != null) {
                            String redefinedComponentType = DOMUtil.getLocalName(redefinedComp);
                            DOMUtil.setHidden(redefinedComp, this.fHiddenNodes);
                            if (redefinedComponentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                this.fAttributeGroupTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                            } else if (redefinedComponentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                this.fComplexTypeTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                            } else if (redefinedComponentType.equals(SchemaSymbols.ELT_GROUP)) {
                                this.fGroupTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                            } else if (redefinedComponentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                this.fSimpleTypeTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                            } else {
                                objArr = new Object[ELEMENT_TYPE];
                                objArr[0] = DOMUtil.getLocalName(globalComp);
                                objArr[ATTRIBUTE_TYPE] = "(annotation | (simpleType | complexType | group | attributeGroup))*";
                                objArr[INIT_KEYREF_STACK] = redefinedComponentType;
                                reportSchemaError("s4s-elt-must-match.1", objArr, redefinedComp);
                            }
                            redefinedComp = DOMUtil.getNextVisibleSiblingElement(redefinedComp, this.fHiddenNodes);
                        }
                        currSchemaDoc.restoreNSSupport();
                    } else if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                        this.fAttributeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                        this.fAttributeGroupTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                        this.fComplexTypeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_ELEMENT)) {
                        this.fElementTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
                        this.fGroupTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_NOTATION)) {
                        this.fNotationTraverser.traverse(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                        this.fSimpleTypeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                    } else if (componentType.equals(SchemaSymbols.ELT_ANNOTATION)) {
                        currSG.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(globalComp, currSchemaDoc.getSchemaAttrs(), true, currSchemaDoc));
                        sawAnnotation = true;
                    } else {
                        objArr = new Object[INIT_KEYREF_STACK];
                        objArr[0] = SchemaSymbols.ELT_SCHEMA;
                        objArr[ATTRIBUTE_TYPE] = DOMUtil.getLocalName(globalComp);
                        reportSchemaError("s4s-elt-invalid-content.1", objArr, globalComp);
                    }
                    globalComp = DOMUtil.getNextVisibleSiblingElement(globalComp, this.fHiddenNodes);
                }
                if (!sawAnnotation) {
                    String text = DOMUtil.getSyntheticAnnotation(currRoot);
                    if (text != null) {
                        currSG.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(currRoot, text, currSchemaDoc.getSchemaAttrs(), true, currSchemaDoc));
                    }
                }
                if (annotationInfo != null) {
                    XSAnnotationInfo info = currSchemaDoc.getAnnotations();
                    if (info != null) {
                        annotationInfo.add(doc2SystemId(currDoc));
                        annotationInfo.add(info);
                    }
                }
                currSchemaDoc.returnSchemaAttrs();
                DOMUtil.setHidden(currDoc, this.fHiddenNodes);
                Vector currSchemaDepends = (Vector) this.fDependencyMap.get(currSchemaDoc);
                for (int i = 0; i < currSchemaDepends.size(); i += ATTRIBUTE_TYPE) {
                    schemasToProcess.push(currSchemaDepends.elementAt(i));
                }
            }
        }
    }

    private final boolean needReportTNSError(String uri) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        } else if (this.fReportedTNS.contains(uri)) {
            return DEBUG_NODE_POOL;
        }
        this.fReportedTNS.addElement(uri);
        return true;
    }

    void addGlobalAttributeDecl(XSAttributeDecl decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalAttrDecls.get(declKey) == null) {
            this.fGlobalAttrDecls.put(declKey, decl);
        }
    }

    void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalAttrGrpDecls.get(declKey) == null) {
            this.fGlobalAttrGrpDecls.put(declKey, decl);
        }
    }

    void addGlobalElementDecl(XSElementDecl decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalElemDecls.get(declKey) == null) {
            this.fGlobalElemDecls.put(declKey, decl);
        }
    }

    void addGlobalGroupDecl(XSGroupDecl decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalGroupDecls.get(declKey) == null) {
            this.fGlobalGroupDecls.put(declKey, decl);
        }
    }

    void addGlobalNotationDecl(XSNotationDecl decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalNotationDecls.get(declKey) == null) {
            this.fGlobalNotationDecls.put(declKey, decl);
        }
    }

    void addGlobalTypeDecl(XSTypeDefinition decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getName()).toString();
        if (this.fGlobalTypeDecls.get(declKey) == null) {
            this.fGlobalTypeDecls.put(declKey, decl);
        }
    }

    void addIDConstraintDecl(IdentityConstraint decl) {
        String namespace = decl.getNamespace();
        String declKey = (namespace == null || namespace.length() == 0) ? "," + decl.getIdentityConstraintName() : new StringBuilder(String.valueOf(namespace)).append(",").append(decl.getIdentityConstraintName()).toString();
        if (this.fGlobalIDConstraintDecls.get(declKey) == null) {
            this.fGlobalIDConstraintDecls.put(declKey, decl);
        }
    }

    private XSAttributeDecl getGlobalAttributeDecl(String declKey) {
        return (XSAttributeDecl) this.fGlobalAttrDecls.get(declKey);
    }

    private XSAttributeGroupDecl getGlobalAttributeGroupDecl(String declKey) {
        return (XSAttributeGroupDecl) this.fGlobalAttrGrpDecls.get(declKey);
    }

    private XSElementDecl getGlobalElementDecl(String declKey) {
        return (XSElementDecl) this.fGlobalElemDecls.get(declKey);
    }

    private XSGroupDecl getGlobalGroupDecl(String declKey) {
        return (XSGroupDecl) this.fGlobalGroupDecls.get(declKey);
    }

    private XSNotationDecl getGlobalNotationDecl(String declKey) {
        return (XSNotationDecl) this.fGlobalNotationDecls.get(declKey);
    }

    private XSTypeDefinition getGlobalTypeDecl(String declKey) {
        return (XSTypeDefinition) this.fGlobalTypeDecls.get(declKey);
    }

    private IdentityConstraint getIDConstraintDecl(String declKey) {
        return (IdentityConstraint) this.fGlobalIDConstraintDecls.get(declKey);
    }

    protected Object getGlobalDecl(XSDocumentInfo currSchema, int declType, QName declToTraverse, Element elmNode) {
        XSTypeDefinition retObj;
        if (declToTraverse.uri != null && declToTraverse.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && declType == TYPEDECL_TYPE) {
            retObj = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(declToTraverse.localpart);
            if (retObj != null) {
                return retObj;
            }
        }
        if (!currSchema.isAllowedNS(declToTraverse.uri) && currSchema.needReportTNSError(declToTraverse.uri)) {
            String code = declToTraverse.uri == null ? "src-resolve.4.1" : "src-resolve.4.2";
            Object[] objArr = new Object[ELEMENT_TYPE];
            objArr[0] = this.fDoc2SystemId.get(currSchema.fSchemaElement);
            objArr[ATTRIBUTE_TYPE] = declToTraverse.uri;
            objArr[INIT_KEYREF_STACK] = declToTraverse.rawname;
            reportSchemaError(code, objArr, elmNode);
        }
        SchemaGrammar sGrammar = this.fGrammarBucket.getGrammar(declToTraverse.uri);
        if (sGrammar == null) {
            if (needReportTNSError(declToTraverse.uri)) {
                Object[] objArr2 = new Object[INIT_KEYREF_STACK];
                objArr2[0] = declToTraverse.rawname;
                objArr2[ATTRIBUTE_TYPE] = COMP_TYPE[declType];
                reportSchemaError("src-resolve", objArr2, elmNode);
            }
            return null;
        }
        String declKey;
        retObj = getGlobalDeclFromGrammar(sGrammar, declType, declToTraverse.localpart);
        if (declToTraverse.uri == null) {
            declKey = "," + declToTraverse.localpart;
        } else {
            declKey = declToTraverse.uri + "," + declToTraverse.localpart;
        }
        if (this.fTolerateDuplicates) {
            XSTypeDefinition retObj2 = getGlobalDecl(declKey, declType);
            if (retObj2 != null) {
                return retObj2;
            }
        } else if (retObj != null) {
            return retObj;
        }
        Element decl = null;
        XSDocumentInfo declDoc = null;
        switch (declType) {
            case ATTRIBUTE_TYPE /*1*/:
                decl = (Element) this.fUnparsedAttributeRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedAttributeRegistrySub.get(declKey);
                break;
            case INIT_KEYREF_STACK /*2*/:
                decl = (Element) this.fUnparsedAttributeGroupRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedAttributeGroupRegistrySub.get(declKey);
                break;
            case ELEMENT_TYPE /*3*/:
                decl = (Element) this.fUnparsedElementRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedElementRegistrySub.get(declKey);
                break;
            case GROUP_TYPE /*4*/:
                decl = (Element) this.fUnparsedGroupRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedGroupRegistrySub.get(declKey);
                break;
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                decl = (Element) this.fUnparsedIdentityConstraintRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedIdentityConstraintRegistrySub.get(declKey);
                break;
            case NOTATION_TYPE /*6*/:
                decl = (Element) this.fUnparsedNotationRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedNotationRegistrySub.get(declKey);
                break;
            case TYPEDECL_TYPE /*7*/:
                decl = (Element) this.fUnparsedTypeRegistry.get(declKey);
                declDoc = (XSDocumentInfo) this.fUnparsedTypeRegistrySub.get(declKey);
                break;
            default:
                objArr2 = new Object[ATTRIBUTE_TYPE];
                objArr2[0] = "XSDHandler asked to locate component of type " + declType + "; it does not recognize this type!";
                reportSchemaError("Internal-Error", objArr2, elmNode);
                break;
        }
        if (decl != null) {
            XSDocumentInfo schemaWithDecl = findXSDocumentForDecl(currSchema, decl, declDoc);
            if (schemaWithDecl == null) {
                if (retObj != null) {
                    return retObj;
                }
                code = declToTraverse.uri == null ? "src-resolve.4.1" : "src-resolve.4.2";
                objArr = new Object[ELEMENT_TYPE];
                objArr[0] = this.fDoc2SystemId.get(currSchema.fSchemaElement);
                objArr[ATTRIBUTE_TYPE] = declToTraverse.uri;
                objArr[INIT_KEYREF_STACK] = declToTraverse.rawname;
                reportSchemaError(code, objArr, elmNode);
                return retObj;
            } else if (!DOMUtil.isHidden(decl, this.fHiddenNodes)) {
                return traverseGlobalDecl(declType, decl, schemaWithDecl, sGrammar);
            } else {
                if (retObj != null) {
                    return retObj;
                }
                code = CIRCULAR_CODES[declType];
                if (declType == TYPEDECL_TYPE && SchemaSymbols.ELT_COMPLEXTYPE.equals(DOMUtil.getLocalName(decl))) {
                    code = "ct-props-correct.3";
                }
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = declToTraverse.prefix + ":" + declToTraverse.localpart;
                reportSchemaError(code, objArr, elmNode);
                return retObj;
            }
        } else if (retObj != null) {
            return retObj;
        } else {
            objArr2 = new Object[INIT_KEYREF_STACK];
            objArr2[0] = declToTraverse.rawname;
            objArr2[ATTRIBUTE_TYPE] = COMP_TYPE[declType];
            reportSchemaError("src-resolve", objArr2, elmNode);
            return retObj;
        }
    }

    protected Object getGlobalDecl(String declKey, int declType) {
        switch (declType) {
            case ATTRIBUTE_TYPE /*1*/:
                return getGlobalAttributeDecl(declKey);
            case INIT_KEYREF_STACK /*2*/:
                return getGlobalAttributeGroupDecl(declKey);
            case ELEMENT_TYPE /*3*/:
                return getGlobalElementDecl(declKey);
            case GROUP_TYPE /*4*/:
                return getGlobalGroupDecl(declKey);
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                return getIDConstraintDecl(declKey);
            case NOTATION_TYPE /*6*/:
                return getGlobalNotationDecl(declKey);
            case TYPEDECL_TYPE /*7*/:
                return getGlobalTypeDecl(declKey);
            default:
                return null;
        }
    }

    protected Object getGlobalDeclFromGrammar(SchemaGrammar sGrammar, int declType, String localpart) {
        switch (declType) {
            case ATTRIBUTE_TYPE /*1*/:
                return sGrammar.getGlobalAttributeDecl(localpart);
            case INIT_KEYREF_STACK /*2*/:
                return sGrammar.getGlobalAttributeGroupDecl(localpart);
            case ELEMENT_TYPE /*3*/:
                return sGrammar.getGlobalElementDecl(localpart);
            case GROUP_TYPE /*4*/:
                return sGrammar.getGlobalGroupDecl(localpart);
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                return sGrammar.getIDConstraintDecl(localpart);
            case NOTATION_TYPE /*6*/:
                return sGrammar.getGlobalNotationDecl(localpart);
            case TYPEDECL_TYPE /*7*/:
                return sGrammar.getGlobalTypeDecl(localpart);
            default:
                return null;
        }
    }

    protected Object getGlobalDeclFromGrammar(SchemaGrammar sGrammar, int declType, String localpart, String schemaLoc) {
        switch (declType) {
            case ATTRIBUTE_TYPE /*1*/:
                return sGrammar.getGlobalAttributeDecl(localpart, schemaLoc);
            case INIT_KEYREF_STACK /*2*/:
                return sGrammar.getGlobalAttributeGroupDecl(localpart, schemaLoc);
            case ELEMENT_TYPE /*3*/:
                return sGrammar.getGlobalElementDecl(localpart, schemaLoc);
            case GROUP_TYPE /*4*/:
                return sGrammar.getGlobalGroupDecl(localpart, schemaLoc);
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                return sGrammar.getIDConstraintDecl(localpart, schemaLoc);
            case NOTATION_TYPE /*6*/:
                return sGrammar.getGlobalNotationDecl(localpart, schemaLoc);
            case TYPEDECL_TYPE /*7*/:
                return sGrammar.getGlobalTypeDecl(localpart, schemaLoc);
            default:
                return null;
        }
    }

    protected Object traverseGlobalDecl(int declType, Element decl, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object retObj = null;
        DOMUtil.setHidden(decl, this.fHiddenNodes);
        SchemaNamespaceSupport nsSupport = null;
        Element parent = DOMUtil.getParent(decl);
        if (DOMUtil.getLocalName(parent).equals(SchemaSymbols.ELT_REDEFINE)) {
            nsSupport = (SchemaNamespaceSupport) this.fRedefine2NSSupport.get(parent);
        }
        schemaDoc.backupNSSupport(nsSupport);
        switch (declType) {
            case ATTRIBUTE_TYPE /*1*/:
                retObj = this.fAttributeTraverser.traverseGlobal(decl, schemaDoc, grammar);
                break;
            case INIT_KEYREF_STACK /*2*/:
                retObj = this.fAttributeGroupTraverser.traverseGlobal(decl, schemaDoc, grammar);
                break;
            case ELEMENT_TYPE /*3*/:
                retObj = this.fElementTraverser.traverseGlobal(decl, schemaDoc, grammar);
                break;
            case GROUP_TYPE /*4*/:
                retObj = this.fGroupTraverser.traverseGlobal(decl, schemaDoc, grammar);
                break;
            case NOTATION_TYPE /*6*/:
                retObj = this.fNotationTraverser.traverse(decl, schemaDoc, grammar);
                break;
            case TYPEDECL_TYPE /*7*/:
                if (!DOMUtil.getLocalName(decl).equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    retObj = this.fSimpleTypeTraverser.traverseGlobal(decl, schemaDoc, grammar);
                    break;
                }
                retObj = this.fComplexTypeTraverser.traverseGlobal(decl, schemaDoc, grammar);
                break;
        }
        schemaDoc.restoreNSSupport();
        return retObj;
    }

    public String schemaDocument2SystemId(XSDocumentInfo schemaDoc) {
        return (String) this.fDoc2SystemId.get(schemaDoc.fSchemaElement);
    }

    Object getGrpOrAttrGrpRedefinedByRestriction(int type, QName name, XSDocumentInfo currSchema, Element elmNode) {
        String realName;
        String nameToFind;
        if (name.uri != null) {
            realName = name.uri + "," + name.localpart;
        } else {
            realName = "," + name.localpart;
        }
        switch (type) {
            case INIT_KEYREF_STACK /*2*/:
                nameToFind = (String) this.fRedefinedRestrictedAttributeGroupRegistry.get(realName);
                break;
            case GROUP_TYPE /*4*/:
                nameToFind = (String) this.fRedefinedRestrictedGroupRegistry.get(realName);
                break;
            default:
                return null;
        }
        if (nameToFind == null) {
            return null;
        }
        int commaPos = nameToFind.indexOf(",");
        Object retObj = getGlobalDecl(currSchema, type, new QName(XMLSymbols.EMPTY_STRING, nameToFind.substring(commaPos + ATTRIBUTE_TYPE), nameToFind.substring(commaPos), commaPos == 0 ? null : nameToFind.substring(0, commaPos)), elmNode);
        if (retObj != null) {
            return retObj;
        }
        Object[] objArr;
        switch (type) {
            case INIT_KEYREF_STACK /*2*/:
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = name.localpart;
                reportSchemaError("src-redefine.7.2.1", objArr, elmNode);
                break;
            case GROUP_TYPE /*4*/:
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = name.localpart;
                reportSchemaError("src-redefine.6.2.1", objArr, elmNode);
                break;
        }
        return null;
    }

    protected void resolveKeyRefs() {
        for (int i = 0; i < this.fKeyrefStackPos; i += ATTRIBUTE_TYPE) {
            XSDocumentInfo keyrefSchemaDoc = this.fKeyrefsMapXSDocumentInfo[i];
            keyrefSchemaDoc.fNamespaceSupport.makeGlobal();
            keyrefSchemaDoc.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[i]);
            SchemaGrammar keyrefGrammar = this.fGrammarBucket.getGrammar(keyrefSchemaDoc.fTargetNamespace);
            DOMUtil.setHidden(this.fKeyrefs[i], this.fHiddenNodes);
            this.fKeyrefTraverser.traverse(this.fKeyrefs[i], this.fKeyrefElems[i], keyrefSchemaDoc, keyrefGrammar);
        }
    }

    protected Hashtable getIDRegistry() {
        return this.fUnparsedIdentityConstraintRegistry;
    }

    protected Hashtable getIDRegistry_sub() {
        return this.fUnparsedIdentityConstraintRegistrySub;
    }

    protected void storeKeyRef(Element keyrefToStore, XSDocumentInfo schemaDoc, XSElementDecl currElemDecl) {
        String keyrefName = DOMUtil.getAttrValue(keyrefToStore, SchemaSymbols.ATT_NAME);
        if (keyrefName.length() != 0) {
            checkForDuplicateNames(schemaDoc.fTargetNamespace == null ? "," + keyrefName : schemaDoc.fTargetNamespace + "," + keyrefName, IDENTITYCONSTRAINT_TYPE, this.fUnparsedIdentityConstraintRegistry, this.fUnparsedIdentityConstraintRegistrySub, keyrefToStore, schemaDoc);
        }
        if (this.fKeyrefStackPos == this.fKeyrefs.length) {
            Element[] elemArray = new Element[(this.fKeyrefStackPos + INIT_KEYREF_STACK)];
            System.arraycopy(this.fKeyrefs, 0, elemArray, 0, this.fKeyrefStackPos);
            this.fKeyrefs = elemArray;
            XSElementDecl[] declArray = new XSElementDecl[(this.fKeyrefStackPos + INIT_KEYREF_STACK)];
            System.arraycopy(this.fKeyrefElems, 0, declArray, 0, this.fKeyrefStackPos);
            this.fKeyrefElems = declArray;
            String[][] stringArray = new String[(this.fKeyrefStackPos + INIT_KEYREF_STACK)][];
            System.arraycopy(this.fKeyrefNamespaceContext, 0, stringArray, 0, this.fKeyrefStackPos);
            this.fKeyrefNamespaceContext = stringArray;
            XSDocumentInfo[] xsDocumentInfo = new XSDocumentInfo[(this.fKeyrefStackPos + INIT_KEYREF_STACK)];
            System.arraycopy(this.fKeyrefsMapXSDocumentInfo, 0, xsDocumentInfo, 0, this.fKeyrefStackPos);
            this.fKeyrefsMapXSDocumentInfo = xsDocumentInfo;
        }
        this.fKeyrefs[this.fKeyrefStackPos] = keyrefToStore;
        this.fKeyrefElems[this.fKeyrefStackPos] = currElemDecl;
        this.fKeyrefNamespaceContext[this.fKeyrefStackPos] = schemaDoc.fNamespaceSupport.getEffectiveLocalContext();
        XSDocumentInfo[] xSDocumentInfoArr = this.fKeyrefsMapXSDocumentInfo;
        int i = this.fKeyrefStackPos;
        this.fKeyrefStackPos = i + ATTRIBUTE_TYPE;
        xSDocumentInfoArr[i] = schemaDoc;
    }

    private Element resolveSchema(XSDDescription desc, boolean mustResolve, Element referElement, boolean usePairs) {
        Hashtable pairs;
        XMLInputSource schemaSource = null;
        if (usePairs) {
            try {
                pairs = this.fLocationPairs;
            } catch (IOException e) {
                Object[] objArr;
                if (mustResolve) {
                    objArr = new Object[ATTRIBUTE_TYPE];
                    objArr[0] = desc.getLocationHints()[0];
                    reportSchemaError("schema_reference.4", objArr, referElement);
                } else {
                    objArr = new Object[ATTRIBUTE_TYPE];
                    objArr[0] = desc.getLocationHints()[0];
                    reportSchemaWarning("schema_reference.4", objArr, referElement);
                }
            }
        } else {
            pairs = EMPTY_TABLE;
        }
        schemaSource = XMLSchemaLoader.resolveDocument(desc, pairs, this.fEntityResolver);
        if (schemaSource instanceof DOMInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (DOMInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof SAXInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (SAXInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof StAXInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (StAXInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof XSInputSource) {
            return getSchemaDocument((XSInputSource) schemaSource, desc);
        } else {
            return getSchemaDocument(desc.getTargetNamespace(), schemaSource, mustResolve, desc.getContextType(), referElement);
        }
    }

    private Element resolveSchema(XMLInputSource schemaSource, XSDDescription desc, boolean mustResolve, Element referElement) {
        if (schemaSource instanceof DOMInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (DOMInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof SAXInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (SAXInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof StAXInputSource) {
            return getSchemaDocument(desc.getTargetNamespace(), (StAXInputSource) schemaSource, mustResolve, desc.getContextType(), referElement);
        } else if (schemaSource instanceof XSInputSource) {
            return getSchemaDocument((XSInputSource) schemaSource, desc);
        } else {
            return getSchemaDocument(desc.getTargetNamespace(), schemaSource, mustResolve, desc.getContextType(), referElement);
        }
    }

    private XMLInputSource resolveSchemaSource(XSDDescription desc, boolean mustResolve, Element referElement, boolean usePairs) {
        Hashtable pairs;
        if (usePairs) {
            try {
                pairs = this.fLocationPairs;
            } catch (IOException e) {
                Object[] objArr;
                if (mustResolve) {
                    objArr = new Object[ATTRIBUTE_TYPE];
                    objArr[0] = desc.getLocationHints()[0];
                    reportSchemaError("schema_reference.4", objArr, referElement);
                    return null;
                }
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = desc.getLocationHints()[0];
                reportSchemaWarning("schema_reference.4", objArr, referElement);
                return null;
            }
        }
        pairs = EMPTY_TABLE;
        return XMLSchemaLoader.resolveDocument(desc, pairs, this.fEntityResolver);
    }

    private Element getSchemaDocument(String schemaNamespace, XMLInputSource schemaSource, boolean mustResolve, short referType, Element referElement) {
        boolean hasInput = true;
        IOException exception = null;
        if (schemaSource != null) {
            try {
                if (!(schemaSource.getSystemId() == null && schemaSource.getByteStream() == null && schemaSource.getCharacterStream() == null)) {
                    XSDKey key = null;
                    String schemaId = null;
                    if (referType != ELEMENT_TYPE) {
                        schemaId = XMLEntityManager.expandSystemId(schemaSource.getSystemId(), schemaSource.getBaseSystemId(), DEBUG_NODE_POOL);
                        key = new XSDKey(schemaId, referType, schemaNamespace);
                        Element schemaElement = (Element) this.fTraversed.get(key);
                        if (schemaElement != null) {
                            this.fLastSchemaWasDuplicate = true;
                            return schemaElement;
                        }
                    }
                    this.fSchemaParser.parse(schemaSource);
                    Document schemaDocument = this.fSchemaParser.getDocument();
                    return getSchemaDocument0(key, schemaId, schemaDocument != null ? DOMUtil.getRoot(schemaDocument) : null);
                }
            } catch (IOException ex) {
                exception = ex;
            }
        }
        hasInput = DEBUG_NODE_POOL;
        return getSchemaDocument1(mustResolve, hasInput, schemaSource, referElement, exception);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private mf.org.w3c.dom.Element getSchemaDocument(java.lang.String r23, mf.org.apache.xerces.util.SAXInputSource r24, boolean r25, short r26, mf.org.w3c.dom.Element r27) {
        /*
        r22 = this;
        r13 = r24.getXMLReader();
        r9 = r24.getInputSource();
        r5 = 1;
        r8 = 0;
        r16 = 0;
        if (r9 == 0) goto L_0x00ef;
    L_0x000e:
        r3 = r9.getSystemId();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        if (r3 != 0) goto L_0x0020;
    L_0x0014:
        r3 = r9.getByteStream();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        if (r3 != 0) goto L_0x0020;
    L_0x001a:
        r3 = r9.getCharacterStream();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        if (r3 == 0) goto L_0x00ef;
    L_0x0020:
        r11 = 0;
        r17 = 0;
        r3 = 3;
        r0 = r26;
        if (r0 == r3) goto L_0x0057;
    L_0x0028:
        r3 = r9.getSystemId();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r4 = r24.getBaseSystemId();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r6 = 0;
        r17 = mf.org.apache.xerces.impl.XMLEntityManager.expandSystemId(r3, r4, r6);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r11 = new mf.org.apache.xerces.impl.xs.traversers.XSDHandler$XSDKey;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r17;
        r1 = r26;
        r2 = r23;
        r11.<init>(r0, r1, r2);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r3 = r0.fTraversed;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r3 = r3.get(r11);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r3;
        r0 = (mf.org.w3c.dom.Element) r0;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r16 = r0;
        if (r16 == 0) goto L_0x0057;
    L_0x004f:
        r3 = 1;
        r0 = r22;
        r0.fLastSchemaWasDuplicate = r3;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r3 = r16;
    L_0x0056:
        return r3;
    L_0x0057:
        r12 = 0;
        if (r13 == 0) goto L_0x00be;
    L_0x005a:
        r3 = "http://xml.org/sax/features/namespace-prefixes";
        r12 = r13.getFeature(r3);	 Catch:{ SAXException -> 0x0112, SAXParseException -> 0x00fe, IOException -> 0x010a }
    L_0x0060:
        r21 = 0;
        r3 = "http://xml.org/sax/features/string-interning";
        r21 = r13.getFeature(r3);	 Catch:{ SAXException -> 0x010f, SAXParseException -> 0x00fe, IOException -> 0x010a }
    L_0x0068:
        r0 = r22;
        r3 = r0.fXSContentHandler;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        if (r3 != 0) goto L_0x0077;
    L_0x006e:
        r3 = new mf.org.apache.xerces.impl.xs.traversers.SchemaContentHandler;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r3.<init>();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r0.fXSContentHandler = r3;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
    L_0x0077:
        r0 = r22;
        r3 = r0.fXSContentHandler;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r4 = r0.fSchemaParser;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r6 = r0.fSymbolTable;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r21;
        r3.reset(r4, r6, r12, r0);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r3 = r0.fXSContentHandler;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r13.setContentHandler(r3);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r0 = r22;
        r3 = r0.fErrorReporter;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r3 = r3.getSAXErrorHandler();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r13.setErrorHandler(r3);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r13.parse(r9);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r3 = 0;
        r13.setContentHandler(r3);	 Catch:{ Exception -> 0x010d }
        r3 = 0;
        r13.setErrorHandler(r3);	 Catch:{ Exception -> 0x010d }
    L_0x00a5:
        r0 = r22;
        r3 = r0.fXSContentHandler;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r15 = r3.getDocument();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        if (r15 == 0) goto L_0x00ec;
    L_0x00af:
        r16 = mf.org.apache.xerces.util.DOMUtil.getRoot(r15);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
    L_0x00b3:
        r0 = r22;
        r1 = r17;
        r2 = r16;
        r3 = r0.getSchemaDocument0(r11, r1, r2);	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        goto L_0x0056;
    L_0x00be:
        r13 = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();	 Catch:{ SAXException -> 0x00e4, SAXParseException -> 0x00fe, IOException -> 0x010a }
    L_0x00c2:
        r3 = "http://xml.org/sax/features/namespace-prefixes";
        r4 = 1;
        r13.setFeature(r3, r4);	 Catch:{ SAXException -> 0x00e1, SAXParseException -> 0x00fe, IOException -> 0x010a }
        r12 = 1;
        r3 = r13 instanceof mf.org.apache.xerces.parsers.SAXParser;	 Catch:{ SAXException -> 0x00e1, SAXParseException -> 0x00fe, IOException -> 0x010a }
        if (r3 == 0) goto L_0x0060;
    L_0x00cd:
        r0 = r22;
        r3 = r0.fSchemaParser;	 Catch:{ SAXException -> 0x00e1, SAXParseException -> 0x00fe, IOException -> 0x010a }
        r4 = "http://apache.org/xml/properties/security-manager";
        r19 = r3.getProperty(r4);	 Catch:{ SAXException -> 0x00e1, SAXParseException -> 0x00fe, IOException -> 0x010a }
        if (r19 == 0) goto L_0x0060;
    L_0x00d9:
        r3 = "http://apache.org/xml/properties/security-manager";
        r0 = r19;
        r13.setProperty(r3, r0);	 Catch:{ SAXException -> 0x00e1, SAXParseException -> 0x00fe, IOException -> 0x010a }
        goto L_0x0060;
    L_0x00e1:
        r3 = move-exception;
        goto L_0x0060;
    L_0x00e4:
        r18 = move-exception;
        r14 = new mf.org.apache.xerces.parsers.SAXParser;	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r14.<init>();	 Catch:{ SAXParseException -> 0x00fe, SAXException -> 0x0104, IOException -> 0x010a }
        r13 = r14;
        goto L_0x00c2;
    L_0x00ec:
        r16 = 0;
        goto L_0x00b3;
    L_0x00ef:
        r5 = 0;
    L_0x00f0:
        r3 = r22;
        r4 = r25;
        r6 = r24;
        r7 = r27;
        r3 = r3.getSchemaDocument1(r4, r5, r6, r7, r8);
        goto L_0x0056;
    L_0x00fe:
        r20 = move-exception;
        r3 = mf.org.apache.xerces.impl.xs.traversers.XSDHandler.SAX2XNIUtil.createXMLParseException0(r20);
        throw r3;
    L_0x0104:
        r18 = move-exception;
        r3 = mf.org.apache.xerces.impl.xs.traversers.XSDHandler.SAX2XNIUtil.createXNIException0(r18);
        throw r3;
    L_0x010a:
        r10 = move-exception;
        r8 = r10;
        goto L_0x00f0;
    L_0x010d:
        r3 = move-exception;
        goto L_0x00a5;
    L_0x010f:
        r3 = move-exception;
        goto L_0x0068;
    L_0x0112:
        r3 = move-exception;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.traversers.XSDHandler.getSchemaDocument(java.lang.String, mf.org.apache.xerces.util.SAXInputSource, boolean, short, mf.org.w3c.dom.Element):mf.org.w3c.dom.Element");
    }

    private Element getSchemaDocument(String schemaNamespace, DOMInputSource schemaSource, boolean mustResolve, short referType, Element referElement) {
        IOException ioe;
        boolean hasInput = true;
        IOException exception = null;
        Element schemaRootElement = null;
        Node node = schemaSource.getNode();
        short nodeType = (short) -1;
        if (node != null) {
            nodeType = node.getNodeType();
            if (nodeType == (short) 9) {
                schemaRootElement = DOMUtil.getRoot((Document) node);
            } else if (nodeType == (short) 1) {
                schemaRootElement = (Element) node;
            }
        }
        if (schemaRootElement != null) {
            XSDKey key = null;
            String schemaId = null;
            if (referType != ELEMENT_TYPE) {
                try {
                    schemaId = XMLEntityManager.expandSystemId(schemaSource.getSystemId(), schemaSource.getBaseSystemId(), DEBUG_NODE_POOL);
                    boolean isDocument = nodeType == (short) 9 ? true : DEBUG_NODE_POOL;
                    if (!isDocument) {
                        Node parent = schemaRootElement.getParentNode();
                        if (parent != null) {
                            isDocument = parent.getNodeType() == (short) 9 ? true : DEBUG_NODE_POOL;
                        }
                    }
                    if (isDocument) {
                        XSDKey key2 = new XSDKey(schemaId, referType, schemaNamespace);
                        try {
                            Element schemaElement = (Element) this.fTraversed.get(key2);
                            if (schemaElement != null) {
                                this.fLastSchemaWasDuplicate = true;
                                return schemaElement;
                            }
                            key = key2;
                        } catch (IOException e) {
                            ioe = e;
                            key = key2;
                            exception = ioe;
                            return getSchemaDocument1(mustResolve, hasInput, schemaSource, referElement, exception);
                        }
                    }
                } catch (IOException e2) {
                    ioe = e2;
                    exception = ioe;
                    return getSchemaDocument1(mustResolve, hasInput, schemaSource, referElement, exception);
                }
            }
            return getSchemaDocument0(key, schemaId, schemaRootElement);
        }
        hasInput = DEBUG_NODE_POOL;
        return getSchemaDocument1(mustResolve, hasInput, schemaSource, referElement, exception);
    }

    private Element getSchemaDocument(String schemaNamespace, StAXInputSource schemaSource, boolean mustResolve, short referType, Element referElement) {
        IOException exception;
        try {
            boolean consumeRemainingContent = schemaSource.shouldConsumeRemainingContent();
            XMLStreamReader streamReader = schemaSource.getXMLStreamReader();
            XMLEventReader eventReader = schemaSource.getXMLEventReader();
            XSDKey key = null;
            String schemaId = null;
            if (referType != ELEMENT_TYPE) {
                schemaId = XMLEntityManager.expandSystemId(schemaSource.getSystemId(), schemaSource.getBaseSystemId(), DEBUG_NODE_POOL);
                boolean isDocument = consumeRemainingContent;
                if (!isDocument) {
                    isDocument = streamReader != null ? streamReader.getEventType() == TYPEDECL_TYPE ? true : DEBUG_NODE_POOL : eventReader.peek().isStartDocument();
                }
                if (isDocument) {
                    key = new XSDKey(schemaId, referType, schemaNamespace);
                    Element schemaElement = (Element) this.fTraversed.get(key);
                    if (schemaElement != null) {
                        this.fLastSchemaWasDuplicate = true;
                        return schemaElement;
                    }
                }
            }
            if (this.fStAXSchemaParser == null) {
                this.fStAXSchemaParser = new StAXSchemaParser();
            }
            this.fStAXSchemaParser.reset(this.fSchemaParser, this.fSymbolTable);
            if (streamReader != null) {
                this.fStAXSchemaParser.parse(streamReader);
                if (consumeRemainingContent) {
                    while (streamReader.hasNext()) {
                        streamReader.next();
                    }
                }
            } else {
                this.fStAXSchemaParser.parse(eventReader);
                if (consumeRemainingContent) {
                    while (eventReader.hasNext()) {
                        eventReader.nextEvent();
                    }
                }
            }
            Document schemaDocument = this.fStAXSchemaParser.getDocument();
            return getSchemaDocument0(key, schemaId, schemaDocument != null ? DOMUtil.getRoot(schemaDocument) : null);
        } catch (XMLStreamException e) {
            Throwable t = e.getNestedException();
            if (t instanceof IOException) {
                exception = (IOException) t;
                return getSchemaDocument1(mustResolve, true, schemaSource, referElement, exception);
            }
            StAXLocationWrapper slw = new StAXLocationWrapper();
            slw.setLocation(e.getLocation());
            throw new XMLParseException(slw, e.getMessage(), e);
        } catch (Exception e2) {
            Exception exception2 = e2;
            return getSchemaDocument1(mustResolve, true, schemaSource, referElement, exception);
        }
    }

    private Element getSchemaDocument0(XSDKey key, String schemaId, Element schemaElement) {
        if (key != null) {
            this.fTraversed.put(key, schemaElement);
        }
        if (schemaId != null) {
            this.fDoc2SystemId.put(schemaElement, schemaId);
        }
        this.fLastSchemaWasDuplicate = DEBUG_NODE_POOL;
        return schemaElement;
    }

    private Element getSchemaDocument1(boolean mustResolve, boolean hasInput, XMLInputSource schemaSource, Element referElement, IOException ioe) {
        Object[] objArr;
        if (mustResolve) {
            if (hasInput) {
                objArr = new Object[ATTRIBUTE_TYPE];
                objArr[0] = schemaSource.getSystemId();
                reportSchemaError("schema_reference.4", objArr, referElement, ioe);
            } else {
                String str = "schema_reference.4";
                Object[] objArr2 = new Object[ATTRIBUTE_TYPE];
                objArr2[0] = schemaSource == null ? StringUtils.EMPTY : schemaSource.getSystemId();
                reportSchemaError(str, objArr2, referElement, ioe);
            }
        } else if (hasInput) {
            objArr = new Object[ATTRIBUTE_TYPE];
            objArr[0] = schemaSource.getSystemId();
            reportSchemaWarning("schema_reference.4", objArr, referElement, ioe);
        }
        this.fLastSchemaWasDuplicate = DEBUG_NODE_POOL;
        return null;
    }

    private Element getSchemaDocument(XSInputSource schemaSource, XSDDescription desc) {
        SchemaGrammar[] grammars = schemaSource.getGrammars();
        short referType = desc.getContextType();
        if (grammars == null || grammars.length <= 0) {
            XSObject[] components = schemaSource.getComponents();
            if (components != null && components.length > 0) {
                Hashtable importDependencies = new Hashtable();
                Vector expandedComponents = expandComponents(components, importDependencies);
                if (this.fNamespaceGrowth || canAddComponents(expandedComponents)) {
                    addGlobalComponents(expandedComponents, importDependencies);
                    if (referType == (short) 3) {
                        desc.setTargetNamespace(components[0].getNamespace());
                    }
                }
            }
        } else {
            Vector expandedGrammars = expandGrammars(grammars);
            if (this.fNamespaceGrowth || !existingGrammars(expandedGrammars)) {
                addGrammars(expandedGrammars);
                if (referType == (short) 3) {
                    desc.setTargetNamespace(grammars[0].getTargetNamespace());
                }
            }
        }
        return null;
    }

    private Vector expandGrammars(SchemaGrammar[] grammars) {
        int i;
        Vector currGrammars = new Vector();
        for (i = 0; i < grammars.length; i += ATTRIBUTE_TYPE) {
            if (!currGrammars.contains(grammars[i])) {
                currGrammars.add(grammars[i]);
            }
        }
        for (i = 0; i < currGrammars.size(); i += ATTRIBUTE_TYPE) {
            Vector gs = ((SchemaGrammar) currGrammars.elementAt(i)).getImportedGrammars();
            if (gs != null) {
                for (int j = gs.size() - 1; j >= 0; j--) {
                    SchemaGrammar sg2 = (SchemaGrammar) gs.elementAt(j);
                    if (!currGrammars.contains(sg2)) {
                        currGrammars.addElement(sg2);
                    }
                }
            }
        }
        return currGrammars;
    }

    private boolean existingGrammars(Vector grammars) {
        int length = grammars.size();
        XSDDescription desc = new XSDDescription();
        for (int i = 0; i < length; i += ATTRIBUTE_TYPE) {
            desc.setNamespace(((SchemaGrammar) grammars.elementAt(i)).getTargetNamespace());
            if (findGrammar(desc, DEBUG_NODE_POOL) != null) {
                return true;
            }
        }
        return DEBUG_NODE_POOL;
    }

    private boolean canAddComponents(Vector components) {
        int size = components.size();
        XSDDescription desc = new XSDDescription();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            if (!canAddComponent((XSObject) components.elementAt(i), desc)) {
                return DEBUG_NODE_POOL;
            }
        }
        return true;
    }

    private boolean canAddComponent(XSObject component, XSDDescription desc) {
        desc.setNamespace(component.getNamespace());
        SchemaGrammar sg = findGrammar(desc, DEBUG_NODE_POOL);
        if (sg == null) {
            return true;
        }
        if (sg.isImmutable()) {
            return DEBUG_NODE_POOL;
        }
        short componentType = component.getType();
        String name = component.getName();
        switch (componentType) {
            case ATTRIBUTE_TYPE /*1*/:
                if (sg.getGlobalAttributeDecl(name) == component) {
                    return true;
                }
                break;
            case INIT_KEYREF_STACK /*2*/:
                if (sg.getGlobalElementDecl(name) == component) {
                    return true;
                }
                break;
            case ELEMENT_TYPE /*3*/:
                if (sg.getGlobalTypeDecl(name) == component) {
                    return true;
                }
                break;
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                if (sg.getGlobalAttributeDecl(name) == component) {
                    return true;
                }
                break;
            case NOTATION_TYPE /*6*/:
                if (sg.getGlobalGroupDecl(name) == component) {
                    return true;
                }
                break;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                if (sg.getGlobalNotationDecl(name) == component) {
                    return true;
                }
                break;
            default:
                return true;
        }
        return DEBUG_NODE_POOL;
    }

    private void addGrammars(Vector grammars) {
        int length = grammars.size();
        XSDDescription desc = new XSDDescription();
        for (int i = 0; i < length; i += ATTRIBUTE_TYPE) {
            SchemaGrammar sg1 = (SchemaGrammar) grammars.elementAt(i);
            desc.setNamespace(sg1.getTargetNamespace());
            SchemaGrammar sg2 = findGrammar(desc, this.fNamespaceGrowth);
            if (sg1 != sg2) {
                addGrammarComponents(sg1, sg2);
            }
        }
    }

    private void addGrammarComponents(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        if (dstGrammar == null) {
            createGrammarFrom(srcGrammar);
            return;
        }
        SchemaGrammar tmpGrammar = dstGrammar;
        if (tmpGrammar.isImmutable()) {
            tmpGrammar = createGrammarFrom(dstGrammar);
        }
        addNewGrammarLocations(srcGrammar, tmpGrammar);
        addNewImportedGrammars(srcGrammar, tmpGrammar);
        addNewGrammarComponents(srcGrammar, tmpGrammar);
    }

    private SchemaGrammar createGrammarFrom(SchemaGrammar grammar) {
        SchemaGrammar newGrammar = new SchemaGrammar(grammar);
        this.fGrammarBucket.putGrammar(newGrammar);
        updateImportListWith(newGrammar);
        updateImportListFor(newGrammar);
        return newGrammar;
    }

    private void addNewGrammarLocations(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        StringList locations = srcGrammar.getDocumentLocations();
        int locSize = locations.size();
        StringList locations2 = dstGrammar.getDocumentLocations();
        for (int i = 0; i < locSize; i += ATTRIBUTE_TYPE) {
            String loc = locations.item(i);
            if (!locations2.contains(loc)) {
                dstGrammar.addDocument(null, loc);
            }
        }
    }

    private void addNewImportedGrammars(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        Vector igs1 = srcGrammar.getImportedGrammars();
        if (igs1 != null) {
            Vector igs2 = dstGrammar.getImportedGrammars();
            if (igs2 == null) {
                dstGrammar.setImportedGrammars((Vector) igs1.clone());
            } else {
                updateImportList(igs1, igs2);
            }
        }
    }

    private void updateImportList(Vector importedSrc, Vector importedDst) {
        int size = importedSrc.size();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            SchemaGrammar sg = (SchemaGrammar) importedSrc.elementAt(i);
            if (!containedImportedGrammar(importedDst, sg)) {
                importedDst.add(sg);
            }
        }
    }

    private void addNewGrammarComponents(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        dstGrammar.resetComponents();
        addGlobalElementDecls(srcGrammar, dstGrammar);
        addGlobalAttributeDecls(srcGrammar, dstGrammar);
        addGlobalAttributeGroupDecls(srcGrammar, dstGrammar);
        addGlobalGroupDecls(srcGrammar, dstGrammar);
        addGlobalTypeDecls(srcGrammar, dstGrammar);
        addGlobalNotationDecls(srcGrammar, dstGrammar);
    }

    private void addGlobalElementDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 2);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSElementDecl srcDecl = (XSElementDecl) components.item(i);
            if (dstGrammar.getGlobalElementDecl(srcDecl.getName()) == null) {
                dstGrammar.addGlobalElementDecl(srcDecl);
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 2);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSElementDecl) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalElementDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalElementDecl(srcDecl, location);
            }
        }
    }

    private void addGlobalAttributeDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 1);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSAttributeDecl srcDecl = (XSAttributeDecl) components.item(i);
            XSAttributeDecl dstDecl = dstGrammar.getGlobalAttributeDecl(srcDecl.getName());
            if (dstDecl == null) {
                dstGrammar.addGlobalAttributeDecl(srcDecl);
            } else if (!(dstDecl == srcDecl || this.fTolerateDuplicates)) {
                reportSharingError(srcDecl.getNamespace(), srcDecl.getName());
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 1);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSAttributeDecl) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalAttributeDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalAttributeDecl(srcDecl, location);
            }
        }
    }

    private void addGlobalAttributeGroupDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 5);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSAttributeGroupDecl srcDecl = (XSAttributeGroupDecl) components.item(i);
            XSAttributeGroupDecl dstDecl = dstGrammar.getGlobalAttributeGroupDecl(srcDecl.getName());
            if (dstDecl == null) {
                dstGrammar.addGlobalAttributeGroupDecl(srcDecl);
            } else if (!(dstDecl == srcDecl || this.fTolerateDuplicates)) {
                reportSharingError(srcDecl.getNamespace(), srcDecl.getName());
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 5);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSAttributeGroupDecl) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalAttributeGroupDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalAttributeGroupDecl(srcDecl, location);
            }
        }
    }

    private void addGlobalNotationDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 11);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSNotationDecl srcDecl = (XSNotationDecl) components.item(i);
            XSNotationDecl dstDecl = dstGrammar.getGlobalNotationDecl(srcDecl.getName());
            if (dstDecl == null) {
                dstGrammar.addGlobalNotationDecl(srcDecl);
            } else if (!(dstDecl == srcDecl || this.fTolerateDuplicates)) {
                reportSharingError(srcDecl.getNamespace(), srcDecl.getName());
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 11);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSNotationDecl) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalNotationDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalNotationDecl(srcDecl, location);
            }
        }
    }

    private void addGlobalGroupDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 6);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSGroupDecl srcDecl = (XSGroupDecl) components.item(i);
            XSGroupDecl dstDecl = dstGrammar.getGlobalGroupDecl(srcDecl.getName());
            if (dstDecl == null) {
                dstGrammar.addGlobalGroupDecl(srcDecl);
            } else if (!(srcDecl == dstDecl || this.fTolerateDuplicates)) {
                reportSharingError(srcDecl.getNamespace(), srcDecl.getName());
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 6);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSGroupDecl) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalGroupDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalGroupDecl(srcDecl, location);
            }
        }
    }

    private void addGlobalTypeDecls(SchemaGrammar srcGrammar, SchemaGrammar dstGrammar) {
        int i;
        XSNamedMap components = srcGrammar.getComponents((short) 3);
        int len = components.getLength();
        for (i = 0; i < len; i += ATTRIBUTE_TYPE) {
            XSTypeDefinition srcDecl = (XSTypeDefinition) components.item(i);
            XSTypeDefinition dstDecl = dstGrammar.getGlobalTypeDecl(srcDecl.getName());
            if (dstDecl == null) {
                dstGrammar.addGlobalTypeDecl(srcDecl);
            } else if (!(dstDecl == srcDecl || this.fTolerateDuplicates)) {
                reportSharingError(srcDecl.getNamespace(), srcDecl.getName());
            }
        }
        ObjectList componentsExt = srcGrammar.getComponentsExt((short) 3);
        len = componentsExt.getLength();
        for (i = 0; i < len; i += INIT_KEYREF_STACK) {
            String key = (String) componentsExt.item(i);
            int index = key.indexOf(44);
            String location = key.substring(0, index);
            srcDecl = (XSTypeDefinition) componentsExt.item(i + ATTRIBUTE_TYPE);
            if (dstGrammar.getGlobalTypeDecl(key.substring(index + ATTRIBUTE_TYPE, key.length()), location) == null) {
                dstGrammar.addGlobalTypeDecl(srcDecl, location);
            }
        }
    }

    private Vector expandComponents(XSObject[] components, Hashtable dependencies) {
        int i;
        Vector newComponents = new Vector();
        for (i = 0; i < components.length; i += ATTRIBUTE_TYPE) {
            if (!newComponents.contains(components[i])) {
                newComponents.add(components[i]);
            }
        }
        for (i = 0; i < newComponents.size(); i += ATTRIBUTE_TYPE) {
            expandRelatedComponents((XSObject) newComponents.elementAt(i), newComponents, dependencies);
        }
        return newComponents;
    }

    private void expandRelatedComponents(XSObject component, Vector componentList, Hashtable dependencies) {
        switch (component.getType()) {
            case ATTRIBUTE_TYPE /*1*/:
                expandRelatedAttributeComponents((XSAttributeDeclaration) component, componentList, component.getNamespace(), dependencies);
                return;
            case INIT_KEYREF_STACK /*2*/:
                break;
            case ELEMENT_TYPE /*3*/:
                expandRelatedTypeComponents((XSTypeDefinition) component, componentList, component.getNamespace(), dependencies);
                return;
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                expandRelatedAttributeGroupComponents((XSAttributeGroupDefinition) component, componentList, component.getNamespace(), dependencies);
                break;
            case NOTATION_TYPE /*6*/:
                expandRelatedModelGroupDefinitionComponents((XSModelGroupDefinition) component, componentList, component.getNamespace(), dependencies);
                return;
            default:
                return;
        }
        expandRelatedElementComponents((XSElementDeclaration) component, componentList, component.getNamespace(), dependencies);
    }

    private void expandRelatedAttributeComponents(XSAttributeDeclaration decl, Vector componentList, String namespace, Hashtable dependencies) {
        addRelatedType(decl.getTypeDefinition(), componentList, namespace, dependencies);
    }

    private void expandRelatedElementComponents(XSElementDeclaration decl, Vector componentList, String namespace, Hashtable dependencies) {
        addRelatedType(decl.getTypeDefinition(), componentList, namespace, dependencies);
        XSElementDeclaration subElemDecl = decl.getSubstitutionGroupAffiliation();
        if (subElemDecl != null) {
            addRelatedElement(subElemDecl, componentList, namespace, dependencies);
        }
    }

    private void expandRelatedTypeComponents(XSTypeDefinition type, Vector componentList, String namespace, Hashtable dependencies) {
        if (type instanceof XSComplexTypeDecl) {
            expandRelatedComplexTypeComponents((XSComplexTypeDecl) type, componentList, namespace, dependencies);
        } else if (type instanceof XSSimpleTypeDecl) {
            expandRelatedSimpleTypeComponents((XSSimpleTypeDefinition) type, componentList, namespace, dependencies);
        }
    }

    private void expandRelatedModelGroupDefinitionComponents(XSModelGroupDefinition modelGroupDef, Vector componentList, String namespace, Hashtable dependencies) {
        expandRelatedModelGroupComponents(modelGroupDef.getModelGroup(), componentList, namespace, dependencies);
    }

    private void expandRelatedAttributeGroupComponents(XSAttributeGroupDefinition attrGroup, Vector componentList, String namespace, Hashtable dependencies) {
        expandRelatedAttributeUsesComponents(attrGroup.getAttributeUses(), componentList, namespace, dependencies);
    }

    private void expandRelatedComplexTypeComponents(XSComplexTypeDecl type, Vector componentList, String namespace, Hashtable dependencies) {
        addRelatedType(type.getBaseType(), componentList, namespace, dependencies);
        expandRelatedAttributeUsesComponents(type.getAttributeUses(), componentList, namespace, dependencies);
        XSParticle particle = type.getParticle();
        if (particle != null) {
            expandRelatedParticleComponents(particle, componentList, namespace, dependencies);
        }
    }

    private void expandRelatedSimpleTypeComponents(XSSimpleTypeDefinition type, Vector componentList, String namespace, Hashtable dependencies) {
        XSTypeDefinition baseType = type.getBaseType();
        if (baseType != null) {
            addRelatedType(baseType, componentList, namespace, dependencies);
        }
        XSTypeDefinition itemType = type.getItemType();
        if (itemType != null) {
            addRelatedType(itemType, componentList, namespace, dependencies);
        }
        XSTypeDefinition primitiveType = type.getPrimitiveType();
        if (primitiveType != null) {
            addRelatedType(primitiveType, componentList, namespace, dependencies);
        }
        XSObjectList memberTypes = type.getMemberTypes();
        if (memberTypes.size() > 0) {
            for (int i = 0; i < memberTypes.size(); i += ATTRIBUTE_TYPE) {
                addRelatedType((XSTypeDefinition) memberTypes.item(i), componentList, namespace, dependencies);
            }
        }
    }

    private void expandRelatedAttributeUsesComponents(XSObjectList attrUses, Vector componentList, String namespace, Hashtable dependencies) {
        int attrUseSize = attrUses == null ? 0 : attrUses.size();
        for (int i = 0; i < attrUseSize; i += ATTRIBUTE_TYPE) {
            expandRelatedAttributeUseComponents((XSAttributeUse) attrUses.item(i), componentList, namespace, dependencies);
        }
    }

    private void expandRelatedAttributeUseComponents(XSAttributeUse component, Vector componentList, String namespace, Hashtable dependencies) {
        addRelatedAttribute(component.getAttrDeclaration(), componentList, namespace, dependencies);
    }

    private void expandRelatedParticleComponents(XSParticle component, Vector componentList, String namespace, Hashtable dependencies) {
        XSTerm term = component.getTerm();
        switch (term.getType()) {
            case INIT_KEYREF_STACK /*2*/:
                addRelatedElement((XSElementDeclaration) term, componentList, namespace, dependencies);
            case TYPEDECL_TYPE /*7*/:
                expandRelatedModelGroupComponents((XSModelGroup) term, componentList, namespace, dependencies);
            default:
        }
    }

    private void expandRelatedModelGroupComponents(XSModelGroup modelGroup, Vector componentList, String namespace, Hashtable dependencies) {
        XSObjectList particles = modelGroup.getParticles();
        int length = particles == null ? 0 : particles.getLength();
        for (int i = 0; i < length; i += ATTRIBUTE_TYPE) {
            expandRelatedParticleComponents((XSParticle) particles.item(i), componentList, namespace, dependencies);
        }
    }

    private void addRelatedType(XSTypeDefinition type, Vector componentList, String namespace, Hashtable dependencies) {
        if (type.getAnonymous()) {
            expandRelatedTypeComponents(type, componentList, namespace, dependencies);
        } else if (!type.getNamespace().equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && !componentList.contains(type)) {
            addNamespaceDependency(namespace, type.getNamespace(), findDependentNamespaces(namespace, dependencies));
            componentList.add(type);
        }
    }

    private void addRelatedElement(XSElementDeclaration decl, Vector componentList, String namespace, Hashtable dependencies) {
        if (decl.getScope() != (short) 1) {
            expandRelatedElementComponents(decl, componentList, namespace, dependencies);
        } else if (!componentList.contains(decl)) {
            addNamespaceDependency(namespace, decl.getNamespace(), findDependentNamespaces(namespace, dependencies));
            componentList.add(decl);
        }
    }

    private void addRelatedAttribute(XSAttributeDeclaration decl, Vector componentList, String namespace, Hashtable dependencies) {
        if (decl.getScope() != (short) 1) {
            expandRelatedAttributeComponents(decl, componentList, namespace, dependencies);
        } else if (!componentList.contains(decl)) {
            addNamespaceDependency(namespace, decl.getNamespace(), findDependentNamespaces(namespace, dependencies));
            componentList.add(decl);
        }
    }

    private void addGlobalComponents(Vector components, Hashtable importDependencies) {
        XSDDescription desc = new XSDDescription();
        int size = components.size();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            addGlobalComponent((XSObject) components.elementAt(i), desc);
        }
        updateImportDependencies(importDependencies);
    }

    private void addGlobalComponent(XSObject component, XSDDescription desc) {
        desc.setNamespace(component.getNamespace());
        SchemaGrammar sg = getSchemaGrammar(desc);
        short componentType = component.getType();
        String name = component.getName();
        switch (componentType) {
            case ATTRIBUTE_TYPE /*1*/:
                if (((XSAttributeDecl) component).getScope() == (short) 1) {
                    if (sg.getGlobalAttributeDecl(name) == null) {
                        sg.addGlobalAttributeDecl((XSAttributeDecl) component);
                    }
                    if (sg.getGlobalAttributeDecl(name, StringUtils.EMPTY) == null) {
                        sg.addGlobalAttributeDecl((XSAttributeDecl) component, StringUtils.EMPTY);
                    }
                }
            case INIT_KEYREF_STACK /*2*/:
                if (((XSElementDecl) component).getScope() == (short) 1) {
                    sg.addGlobalElementDeclAll((XSElementDecl) component);
                    if (sg.getGlobalElementDecl(name) == null) {
                        sg.addGlobalElementDecl((XSElementDecl) component);
                    }
                    if (sg.getGlobalElementDecl(name, StringUtils.EMPTY) == null) {
                        sg.addGlobalElementDecl((XSElementDecl) component, StringUtils.EMPTY);
                    }
                }
            case ELEMENT_TYPE /*3*/:
                if (!((XSTypeDefinition) component).getAnonymous()) {
                    if (sg.getGlobalTypeDecl(name) == null) {
                        sg.addGlobalTypeDecl((XSTypeDefinition) component);
                    }
                    if (sg.getGlobalTypeDecl(name, StringUtils.EMPTY) == null) {
                        sg.addGlobalTypeDecl((XSTypeDefinition) component, StringUtils.EMPTY);
                    }
                }
            case IDENTITYCONSTRAINT_TYPE /*5*/:
                if (sg.getGlobalAttributeDecl(name) == null) {
                    sg.addGlobalAttributeGroupDecl((XSAttributeGroupDecl) component);
                }
                if (sg.getGlobalAttributeDecl(name, StringUtils.EMPTY) == null) {
                    sg.addGlobalAttributeGroupDecl((XSAttributeGroupDecl) component, StringUtils.EMPTY);
                }
            case NOTATION_TYPE /*6*/:
                if (sg.getGlobalGroupDecl(name) == null) {
                    sg.addGlobalGroupDecl((XSGroupDecl) component);
                }
                if (sg.getGlobalGroupDecl(name, StringUtils.EMPTY) == null) {
                    sg.addGlobalGroupDecl((XSGroupDecl) component, StringUtils.EMPTY);
                }
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                if (sg.getGlobalNotationDecl(name) == null) {
                    sg.addGlobalNotationDecl((XSNotationDecl) component);
                }
                if (sg.getGlobalNotationDecl(name, StringUtils.EMPTY) == null) {
                    sg.addGlobalNotationDecl((XSNotationDecl) component, StringUtils.EMPTY);
                }
            default:
        }
    }

    private void updateImportDependencies(Hashtable table) {
        Enumeration keys = table.keys();
        while (keys.hasMoreElements()) {
            String namespace = (String) keys.nextElement();
            Vector importList = (Vector) table.get(null2EmptyString(namespace));
            if (importList.size() > 0) {
                expandImportList(namespace, importList);
            }
        }
    }

    private void expandImportList(String namespace, Vector namespaceList) {
        SchemaGrammar sg = this.fGrammarBucket.getGrammar(namespace);
        if (sg != null) {
            Vector isgs = sg.getImportedGrammars();
            if (isgs == null) {
                isgs = new Vector();
                addImportList(sg, isgs, namespaceList);
                sg.setImportedGrammars(isgs);
                return;
            }
            updateImportList(sg, isgs, namespaceList);
        }
    }

    private void addImportList(SchemaGrammar sg, Vector importedGrammars, Vector namespaceList) {
        int size = namespaceList.size();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            SchemaGrammar isg = this.fGrammarBucket.getGrammar((String) namespaceList.elementAt(i));
            if (isg != null) {
                importedGrammars.add(isg);
            }
        }
    }

    private void updateImportList(SchemaGrammar sg, Vector importedGrammars, Vector namespaceList) {
        int size = namespaceList.size();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            SchemaGrammar isg = this.fGrammarBucket.getGrammar((String) namespaceList.elementAt(i));
            if (!(isg == null || containedImportedGrammar(importedGrammars, isg))) {
                importedGrammars.add(isg);
            }
        }
    }

    private boolean containedImportedGrammar(Vector importedGrammar, SchemaGrammar grammar) {
        int size = importedGrammar.size();
        for (int i = 0; i < size; i += ATTRIBUTE_TYPE) {
            if (null2EmptyString(((SchemaGrammar) importedGrammar.elementAt(i)).getTargetNamespace()).equals(null2EmptyString(grammar.getTargetNamespace()))) {
                return true;
            }
        }
        return DEBUG_NODE_POOL;
    }

    private SchemaGrammar getSchemaGrammar(XSDDescription desc) {
        SchemaGrammar sg = findGrammar(desc, this.fNamespaceGrowth);
        if (sg == null) {
            sg = new SchemaGrammar(desc.getNamespace(), desc.makeClone(), this.fSymbolTable);
            this.fGrammarBucket.putGrammar(sg);
            return sg;
        } else if (sg.isImmutable()) {
            return createGrammarFrom(sg);
        } else {
            return sg;
        }
    }

    private Vector findDependentNamespaces(String namespace, Hashtable table) {
        String ns = null2EmptyString(namespace);
        Vector namespaceList = (Vector) table.get(ns);
        if (namespaceList != null) {
            return namespaceList;
        }
        namespaceList = new Vector();
        table.put(ns, namespaceList);
        return namespaceList;
    }

    private void addNamespaceDependency(String namespace1, String namespace2, Vector list) {
        String ns1 = null2EmptyString(namespace1);
        String ns2 = null2EmptyString(namespace2);
        if (!ns1.equals(ns2) && !list.contains(ns2)) {
            list.add(ns2);
        }
    }

    private void reportSharingError(String namespace, String name) {
        Object[] objArr = new Object[ATTRIBUTE_TYPE];
        objArr[0] = namespace == null ? "," + name : new StringBuilder(String.valueOf(namespace)).append(",").append(name).toString();
        reportSchemaError("sch-props-correct.2", objArr, null);
    }

    private void createTraversers() {
        this.fAttributeChecker = new XSAttributeChecker(this);
        this.fAttributeGroupTraverser = new XSDAttributeGroupTraverser(this, this.fAttributeChecker);
        this.fAttributeTraverser = new XSDAttributeTraverser(this, this.fAttributeChecker);
        this.fComplexTypeTraverser = new XSDComplexTypeTraverser(this, this.fAttributeChecker);
        this.fElementTraverser = new XSDElementTraverser(this, this.fAttributeChecker);
        this.fGroupTraverser = new XSDGroupTraverser(this, this.fAttributeChecker);
        this.fKeyrefTraverser = new XSDKeyrefTraverser(this, this.fAttributeChecker);
        this.fNotationTraverser = new XSDNotationTraverser(this, this.fAttributeChecker);
        this.fSimpleTypeTraverser = new XSDSimpleTypeTraverser(this, this.fAttributeChecker);
        this.fUniqueOrKeyTraverser = new XSDUniqueOrKeyTraverser(this, this.fAttributeChecker);
        this.fWildCardTraverser = new XSDWildcardTraverser(this, this.fAttributeChecker);
    }

    void prepareForParse() {
        this.fTraversed.clear();
        this.fDoc2SystemId.clear();
        this.fHiddenNodes.clear();
        this.fLastSchemaWasDuplicate = DEBUG_NODE_POOL;
    }

    void prepareForTraverse() {
        int i;
        this.fUnparsedAttributeRegistry.clear();
        this.fUnparsedAttributeGroupRegistry.clear();
        this.fUnparsedElementRegistry.clear();
        this.fUnparsedGroupRegistry.clear();
        this.fUnparsedIdentityConstraintRegistry.clear();
        this.fUnparsedNotationRegistry.clear();
        this.fUnparsedTypeRegistry.clear();
        this.fUnparsedAttributeRegistrySub.clear();
        this.fUnparsedAttributeGroupRegistrySub.clear();
        this.fUnparsedElementRegistrySub.clear();
        this.fUnparsedGroupRegistrySub.clear();
        this.fUnparsedIdentityConstraintRegistrySub.clear();
        this.fUnparsedNotationRegistrySub.clear();
        this.fUnparsedTypeRegistrySub.clear();
        for (i = ATTRIBUTE_TYPE; i <= TYPEDECL_TYPE; i += ATTRIBUTE_TYPE) {
            this.fUnparsedRegistriesExt[i].clear();
        }
        this.fXSDocumentInfoRegistry.clear();
        this.fDependencyMap.clear();
        this.fDoc2XSDocumentMap.clear();
        this.fRedefine2XSDMap.clear();
        this.fRedefine2NSSupport.clear();
        this.fAllTNSs.removeAllElements();
        this.fImportMap.clear();
        this.fRoot = null;
        for (i = 0; i < this.fLocalElemStackPos; i += ATTRIBUTE_TYPE) {
            this.fParticle[i] = null;
            this.fLocalElementDecl[i] = null;
            this.fLocalElementDecl_schema[i] = null;
            this.fLocalElemNamespaceContext[i] = null;
        }
        this.fLocalElemStackPos = 0;
        for (i = 0; i < this.fKeyrefStackPos; i += ATTRIBUTE_TYPE) {
            this.fKeyrefs[i] = null;
            this.fKeyrefElems[i] = null;
            this.fKeyrefNamespaceContext[i] = null;
            this.fKeyrefsMapXSDocumentInfo[i] = null;
        }
        this.fKeyrefStackPos = 0;
        if (this.fAttributeChecker == null) {
            createTraversers();
        }
        Locale locale = this.fErrorReporter.getLocale();
        this.fAttributeChecker.reset(this.fSymbolTable);
        this.fAttributeGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fAttributeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fComplexTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fElementTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fKeyrefTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fNotationTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fSimpleTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fUniqueOrKeyTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fWildCardTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fRedefinedRestrictedAttributeGroupRegistry.clear();
        this.fRedefinedRestrictedGroupRegistry.clear();
        this.fGlobalAttrDecls.clear();
        this.fGlobalAttrGrpDecls.clear();
        this.fGlobalElemDecls.clear();
        this.fGlobalGroupDecls.clear();
        this.fGlobalNotationDecls.clear();
        this.fGlobalIDConstraintDecls.clear();
        this.fGlobalTypeDecls.clear();
    }

    public void setDeclPool(XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }

    public void setDVFactory(SchemaDVFactory dvFactory) {
        this.fDVFactory = dvFactory;
    }

    public void reset(XMLComponentManager componentManager) {
        this.fSymbolTable = (SymbolTable) componentManager.getProperty(SYMBOL_TABLE);
        this.fEntityResolver = (XMLEntityResolver) componentManager.getProperty(ENTITY_MANAGER);
        XMLEntityResolver er = (XMLEntityResolver) componentManager.getProperty(ENTITY_RESOLVER);
        if (er != null) {
            this.fSchemaParser.setEntityResolver(er);
        }
        this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty(ERROR_REPORTER);
        try {
            XMLErrorHandler currErrorHandler = this.fErrorReporter.getErrorHandler();
            if (currErrorHandler != this.fSchemaParser.getProperty(ERROR_HANDLER)) {
                Object obj;
                SchemaDOMParser schemaDOMParser = this.fSchemaParser;
                String str = ERROR_HANDLER;
                if (currErrorHandler != null) {
                    obj = currErrorHandler;
                } else {
                    obj = new DefaultErrorHandler();
                }
                schemaDOMParser.setProperty(str, obj);
                if (this.fAnnotationValidator != null) {
                    XML11Configuration xML11Configuration = this.fAnnotationValidator;
                    String str2 = ERROR_HANDLER;
                    if (currErrorHandler == null) {
                        currErrorHandler = new DefaultErrorHandler();
                    }
                    xML11Configuration.setProperty(str2, currErrorHandler);
                }
            }
            Locale currentLocale = this.fErrorReporter.getLocale();
            if (currentLocale != this.fSchemaParser.getProperty(LOCALE)) {
                this.fSchemaParser.setProperty(LOCALE, currentLocale);
                if (this.fAnnotationValidator != null) {
                    this.fAnnotationValidator.setProperty(LOCALE, currentLocale);
                }
            }
        } catch (XMLConfigurationException e) {
        }
        try {
            this.fValidateAnnotations = componentManager.getFeature(VALIDATE_ANNOTATIONS);
        } catch (XMLConfigurationException e2) {
            this.fValidateAnnotations = DEBUG_NODE_POOL;
        }
        try {
            this.fHonourAllSchemaLocations = componentManager.getFeature(HONOUR_ALL_SCHEMALOCATIONS);
        } catch (XMLConfigurationException e3) {
            this.fHonourAllSchemaLocations = DEBUG_NODE_POOL;
        }
        try {
            this.fNamespaceGrowth = componentManager.getFeature(NAMESPACE_GROWTH);
        } catch (XMLConfigurationException e4) {
            this.fNamespaceGrowth = DEBUG_NODE_POOL;
        }
        try {
            this.fTolerateDuplicates = componentManager.getFeature(TOLERATE_DUPLICATES);
        } catch (XMLConfigurationException e5) {
            this.fTolerateDuplicates = DEBUG_NODE_POOL;
        }
        try {
            this.fSchemaParser.setFeature(CONTINUE_AFTER_FATAL_ERROR, this.fErrorReporter.getFeature(CONTINUE_AFTER_FATAL_ERROR));
        } catch (XMLConfigurationException e6) {
        }
        try {
            this.fSchemaParser.setFeature(ALLOW_JAVA_ENCODINGS, componentManager.getFeature(ALLOW_JAVA_ENCODINGS));
        } catch (XMLConfigurationException e7) {
        }
        try {
            this.fSchemaParser.setFeature(STANDARD_URI_CONFORMANT_FEATURE, componentManager.getFeature(STANDARD_URI_CONFORMANT_FEATURE));
        } catch (XMLConfigurationException e8) {
        }
        try {
            this.fGrammarPool = (XMLGrammarPool) componentManager.getProperty(XMLGRAMMAR_POOL);
        } catch (XMLConfigurationException e9) {
            this.fGrammarPool = null;
        }
        try {
            this.fSchemaParser.setFeature(DISALLOW_DOCTYPE, componentManager.getFeature(DISALLOW_DOCTYPE));
        } catch (XMLConfigurationException e10) {
        }
        try {
            Object security = componentManager.getProperty(SECURITY_MANAGER);
            if (security != null) {
                this.fSchemaParser.setProperty(SECURITY_MANAGER, security);
            }
        } catch (XMLConfigurationException e11) {
        }
    }

    void traverseLocalElements() {
        this.fElementTraverser.fDeferTraversingLocalElements = DEBUG_NODE_POOL;
        for (int i = 0; i < this.fLocalElemStackPos; i += ATTRIBUTE_TYPE) {
            Element currElem = this.fLocalElementDecl[i];
            XSDocumentInfo currSchema = this.fLocalElementDecl_schema[i];
            this.fElementTraverser.traverseLocal(this.fParticle[i], currElem, currSchema, this.fGrammarBucket.getGrammar(currSchema.fTargetNamespace), this.fAllContext[i], this.fParent[i], this.fLocalElemNamespaceContext[i]);
            if (this.fParticle[i].fType == (short) 0) {
                XSModelGroupImpl group = null;
                if (this.fParent[i] instanceof XSComplexTypeDecl) {
                    XSParticle p = ((XSComplexTypeDecl) this.fParent[i]).getParticle();
                    if (p != null) {
                        group = (XSModelGroupImpl) p.getTerm();
                    }
                } else {
                    group = ((XSGroupDecl) this.fParent[i]).fModelGroup;
                }
                if (group != null) {
                    removeParticle(group, this.fParticle[i]);
                }
            }
        }
    }

    private boolean removeParticle(XSModelGroupImpl group, XSParticleDecl particle) {
        int i = 0;
        while (i < group.fParticleCount) {
            XSParticleDecl member = group.fParticles[i];
            if (member == particle) {
                for (int j = i; j < group.fParticleCount - 1; j += ATTRIBUTE_TYPE) {
                    group.fParticles[j] = group.fParticles[j + ATTRIBUTE_TYPE];
                }
                group.fParticleCount--;
                return true;
            } else if (member.fType == (short) 3 && removeParticle((XSModelGroupImpl) member.fValue, particle)) {
                return true;
            } else {
                i += ATTRIBUTE_TYPE;
            }
        }
        return DEBUG_NODE_POOL;
    }

    void fillInLocalElemInfo(Element elmDecl, XSDocumentInfo schemaDoc, int allContextFlags, XSObject parent, XSParticleDecl particle) {
        if (this.fParticle.length == this.fLocalElemStackPos) {
            XSParticleDecl[] newStackP = new XSParticleDecl[(this.fLocalElemStackPos + INC_STACK_SIZE)];
            System.arraycopy(this.fParticle, 0, newStackP, 0, this.fLocalElemStackPos);
            this.fParticle = newStackP;
            Element[] newStackE = new Element[(this.fLocalElemStackPos + INC_STACK_SIZE)];
            System.arraycopy(this.fLocalElementDecl, 0, newStackE, 0, this.fLocalElemStackPos);
            this.fLocalElementDecl = newStackE;
            XSDocumentInfo[] newStackE_schema = new XSDocumentInfo[(this.fLocalElemStackPos + INC_STACK_SIZE)];
            System.arraycopy(this.fLocalElementDecl_schema, 0, newStackE_schema, 0, this.fLocalElemStackPos);
            this.fLocalElementDecl_schema = newStackE_schema;
            int[] newStackI = new int[(this.fLocalElemStackPos + INC_STACK_SIZE)];
            System.arraycopy(this.fAllContext, 0, newStackI, 0, this.fLocalElemStackPos);
            this.fAllContext = newStackI;
            XSObject[] newStackC = new XSObject[(this.fLocalElemStackPos + INC_STACK_SIZE)];
            System.arraycopy(this.fParent, 0, newStackC, 0, this.fLocalElemStackPos);
            this.fParent = newStackC;
            String[][] newStackN = new String[(this.fLocalElemStackPos + INC_STACK_SIZE)][];
            System.arraycopy(this.fLocalElemNamespaceContext, 0, newStackN, 0, this.fLocalElemStackPos);
            this.fLocalElemNamespaceContext = newStackN;
        }
        this.fParticle[this.fLocalElemStackPos] = particle;
        this.fLocalElementDecl[this.fLocalElemStackPos] = elmDecl;
        this.fLocalElementDecl_schema[this.fLocalElemStackPos] = schemaDoc;
        this.fAllContext[this.fLocalElemStackPos] = allContextFlags;
        this.fParent[this.fLocalElemStackPos] = parent;
        String[][] strArr = this.fLocalElemNamespaceContext;
        int i = this.fLocalElemStackPos;
        this.fLocalElemStackPos = i + ATTRIBUTE_TYPE;
        strArr[i] = schemaDoc.fNamespaceSupport.getEffectiveLocalContext();
    }

    void checkForDuplicateNames(String qName, int declType, Hashtable registry, Hashtable registry_sub, Element currComp, XSDocumentInfo currSchema) {
        Element objElem = registry.get(qName);
        if (objElem == null) {
            if (this.fNamespaceGrowth && !this.fTolerateDuplicates) {
                checkForDuplicateNames(qName, declType, currComp);
            }
            registry.put(qName, currComp);
            registry_sub.put(qName, currSchema);
        } else {
            Element collidingElem = objElem;
            XSDocumentInfo collidingElemSchema = (XSDocumentInfo) registry_sub.get(qName);
            if (collidingElem != currComp) {
                XSDocumentInfo redefinedSchema = null;
                boolean collidedWithRedefine = true;
                Element elemParent = DOMUtil.getParent(collidingElem);
                if (DOMUtil.getLocalName(elemParent).equals(SchemaSymbols.ELT_REDEFINE)) {
                    redefinedSchema = (XSDocumentInfo) this.fRedefine2XSDMap.get(elemParent);
                } else if (DOMUtil.getLocalName(DOMUtil.getParent(currComp)).equals(SchemaSymbols.ELT_REDEFINE)) {
                    redefinedSchema = collidingElemSchema;
                    collidedWithRedefine = DEBUG_NODE_POOL;
                }
                Object[] objArr;
                if (redefinedSchema != null) {
                    if (collidingElemSchema == currSchema) {
                        objArr = new Object[ATTRIBUTE_TYPE];
                        objArr[0] = qName;
                        reportSchemaError("sch-props-correct.2", objArr, currComp);
                        return;
                    }
                    String newName = qName.substring(qName.lastIndexOf(44) + ATTRIBUTE_TYPE) + REDEF_IDENTIFIER;
                    if (redefinedSchema == currSchema) {
                        currComp.setAttribute(SchemaSymbols.ATT_NAME, newName);
                        if (currSchema.fTargetNamespace == null) {
                            registry.put("," + newName, currComp);
                            registry_sub.put("," + newName, currSchema);
                        } else {
                            registry.put(currSchema.fTargetNamespace + "," + newName, currComp);
                            registry_sub.put(currSchema.fTargetNamespace + "," + newName, currSchema);
                        }
                        if (currSchema.fTargetNamespace == null) {
                            checkForDuplicateNames("," + newName, declType, registry, registry_sub, currComp, currSchema);
                        } else {
                            checkForDuplicateNames(currSchema.fTargetNamespace + "," + newName, declType, registry, registry_sub, currComp, currSchema);
                        }
                    } else if (!collidedWithRedefine) {
                        objArr = new Object[ATTRIBUTE_TYPE];
                        objArr[0] = qName;
                        reportSchemaError("sch-props-correct.2", objArr, currComp);
                    } else if (currSchema.fTargetNamespace == null) {
                        checkForDuplicateNames("," + newName, declType, registry, registry_sub, currComp, currSchema);
                    } else {
                        checkForDuplicateNames(currSchema.fTargetNamespace + "," + newName, declType, registry, registry_sub, currComp, currSchema);
                    }
                } else if (!this.fTolerateDuplicates || this.fUnparsedRegistriesExt[declType].get(qName) == currSchema) {
                    objArr = new Object[ATTRIBUTE_TYPE];
                    objArr[0] = qName;
                    reportSchemaError("sch-props-correct.2", objArr, currComp);
                }
            } else {
                return;
            }
        }
        if (this.fTolerateDuplicates) {
            this.fUnparsedRegistriesExt[declType].put(qName, currSchema);
        }
    }

    void checkForDuplicateNames(String qName, int declType, Element currComp) {
        int namespaceEnd = qName.indexOf(44);
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(emptyString2Null(qName.substring(0, namespaceEnd)));
        if (grammar != null && getGlobalDeclFromGrammar(grammar, declType, qName.substring(namespaceEnd + ATTRIBUTE_TYPE)) != null) {
            Object[] objArr = new Object[ATTRIBUTE_TYPE];
            objArr[0] = qName;
            reportSchemaError("sch-props-correct.2", objArr, currComp);
        }
    }

    private void renameRedefiningComponents(XSDocumentInfo currSchema, Element child, String componentType, String oldName, String newName) {
        Element grandKid;
        QName derivedBase;
        if (componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            grandKid = DOMUtil.getFirstChildElement(child);
            if (grandKid == null) {
                reportSchemaError("src-redefine.5.a.a", null, child);
                return;
            }
            if (DOMUtil.getLocalName(grandKid).equals(SchemaSymbols.ELT_ANNOTATION)) {
                grandKid = DOMUtil.getNextSiblingElement(grandKid);
            }
            if (grandKid == null) {
                reportSchemaError("src-redefine.5.a.a", null, child);
                return;
            }
            String grandKidName = DOMUtil.getLocalName(grandKid);
            if (grandKidName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                Object[] attrs = this.fAttributeChecker.checkAttributes(grandKid, DEBUG_NODE_POOL, currSchema);
                derivedBase = attrs[XSAttributeChecker.ATTIDX_BASE];
                if (derivedBase == null || derivedBase.uri != currSchema.fTargetNamespace || !derivedBase.localpart.equals(oldName)) {
                    String str = "src-redefine.5.a.c";
                    Object[] objArr = new Object[INIT_KEYREF_STACK];
                    objArr[0] = grandKidName;
                    objArr[ATTRIBUTE_TYPE] = new StringBuilder(String.valueOf(currSchema.fTargetNamespace == null ? StringUtils.EMPTY : currSchema.fTargetNamespace)).append(",").append(oldName).toString();
                    reportSchemaError(str, objArr, child);
                } else if (derivedBase.prefix == null || derivedBase.prefix.length() <= 0) {
                    grandKid.setAttribute(SchemaSymbols.ATT_BASE, newName);
                } else {
                    grandKid.setAttribute(SchemaSymbols.ATT_BASE, derivedBase.prefix + ":" + newName);
                }
                this.fAttributeChecker.returnAttrArray(attrs, currSchema);
                return;
            }
            Object[] objArr2 = new Object[ATTRIBUTE_TYPE];
            objArr2[0] = grandKidName;
            reportSchemaError("src-redefine.5.a.b", objArr2, child);
            return;
        }
        if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
            grandKid = DOMUtil.getFirstChildElement(child);
            if (grandKid == null) {
                reportSchemaError("src-redefine.5.b.a", null, child);
                return;
            }
            if (DOMUtil.getLocalName(grandKid).equals(SchemaSymbols.ELT_ANNOTATION)) {
                grandKid = DOMUtil.getNextSiblingElement(grandKid);
            }
            if (grandKid == null) {
                reportSchemaError("src-redefine.5.b.a", null, child);
                return;
            }
            Element greatGrandKid = DOMUtil.getFirstChildElement(grandKid);
            if (greatGrandKid == null) {
                reportSchemaError("src-redefine.5.b.b", null, grandKid);
                return;
            }
            if (DOMUtil.getLocalName(greatGrandKid).equals(SchemaSymbols.ELT_ANNOTATION)) {
                greatGrandKid = DOMUtil.getNextSiblingElement(greatGrandKid);
            }
            if (greatGrandKid == null) {
                reportSchemaError("src-redefine.5.b.b", null, grandKid);
                return;
            }
            String greatGrandKidName = DOMUtil.getLocalName(greatGrandKid);
            if (greatGrandKidName.equals(SchemaSymbols.ELT_RESTRICTION) || greatGrandKidName.equals(SchemaSymbols.ELT_EXTENSION)) {
                derivedBase = (QName) this.fAttributeChecker.checkAttributes(greatGrandKid, DEBUG_NODE_POOL, currSchema)[XSAttributeChecker.ATTIDX_BASE];
                if (derivedBase == null || derivedBase.uri != currSchema.fTargetNamespace || !derivedBase.localpart.equals(oldName)) {
                    str = "src-redefine.5.b.d";
                    objArr = new Object[INIT_KEYREF_STACK];
                    objArr[0] = greatGrandKidName;
                    objArr[ATTRIBUTE_TYPE] = new StringBuilder(String.valueOf(currSchema.fTargetNamespace == null ? StringUtils.EMPTY : currSchema.fTargetNamespace)).append(",").append(oldName).toString();
                    reportSchemaError(str, objArr, greatGrandKid);
                    return;
                } else if (derivedBase.prefix == null || derivedBase.prefix.length() <= 0) {
                    greatGrandKid.setAttribute(SchemaSymbols.ATT_BASE, newName);
                    return;
                } else {
                    greatGrandKid.setAttribute(SchemaSymbols.ATT_BASE, derivedBase.prefix + ":" + newName);
                    return;
                }
            }
            objArr2 = new Object[ATTRIBUTE_TYPE];
            objArr2[0] = greatGrandKidName;
            reportSchemaError("src-redefine.5.b.c", objArr2, greatGrandKid);
            return;
        }
        String processedBaseName;
        if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
            processedBaseName = currSchema.fTargetNamespace == null ? "," + oldName : currSchema.fTargetNamespace + "," + oldName;
            int attGroupRefsCount = changeRedefineGroup(processedBaseName, componentType, newName, child, currSchema);
            if (attGroupRefsCount > ATTRIBUTE_TYPE) {
                objArr2 = new Object[ATTRIBUTE_TYPE];
                objArr2[0] = new Integer(attGroupRefsCount);
                reportSchemaError("src-redefine.7.1", objArr2, child);
                return;
            } else if (attGroupRefsCount == ATTRIBUTE_TYPE) {
                return;
            } else {
                if (currSchema.fTargetNamespace == null) {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(processedBaseName, "," + newName);
                    return;
                } else {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(processedBaseName, currSchema.fTargetNamespace + "," + newName);
                    return;
                }
            }
        }
        if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
            processedBaseName = currSchema.fTargetNamespace == null ? "," + oldName : currSchema.fTargetNamespace + "," + oldName;
            int groupRefsCount = changeRedefineGroup(processedBaseName, componentType, newName, child, currSchema);
            if (groupRefsCount > ATTRIBUTE_TYPE) {
                objArr2 = new Object[ATTRIBUTE_TYPE];
                objArr2[0] = new Integer(groupRefsCount);
                reportSchemaError("src-redefine.6.1.1", objArr2, child);
                return;
            } else if (groupRefsCount == ATTRIBUTE_TYPE) {
                return;
            } else {
                if (currSchema.fTargetNamespace == null) {
                    this.fRedefinedRestrictedGroupRegistry.put(processedBaseName, "," + newName);
                    return;
                } else {
                    this.fRedefinedRestrictedGroupRegistry.put(processedBaseName, currSchema.fTargetNamespace + "," + newName);
                    return;
                }
            }
        }
        objArr2 = new Object[ATTRIBUTE_TYPE];
        objArr2[0] = "could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!";
        reportSchemaError("Internal-Error", objArr2, child);
    }

    private String findQName(String name, XSDocumentInfo schemaDoc) {
        SchemaNamespaceSupport currNSMap = schemaDoc.fNamespaceSupport;
        int colonPtr = name.indexOf(58);
        String prefix = XMLSymbols.EMPTY_STRING;
        if (colonPtr > 0) {
            prefix = name.substring(0, colonPtr);
        }
        String uri = currNSMap.getURI(this.fSymbolTable.addSymbol(prefix));
        String localpart = colonPtr == 0 ? name : name.substring(colonPtr + ATTRIBUTE_TYPE);
        if (prefix == XMLSymbols.EMPTY_STRING && uri == null && schemaDoc.fIsChameleonSchema) {
            uri = schemaDoc.fTargetNamespace;
        }
        if (uri == null) {
            return "," + localpart;
        }
        return new StringBuilder(String.valueOf(uri)).append(",").append(localpart).toString();
    }

    private int changeRedefineGroup(String originalQName, String elementSought, String newName, Element curr, XSDocumentInfo schemaDoc) {
        int result = 0;
        for (Element child = DOMUtil.getFirstChildElement(curr); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            if (DOMUtil.getLocalName(child).equals(elementSought)) {
                String ref = child.getAttribute(SchemaSymbols.ATT_REF);
                if (ref.length() != 0) {
                    if (originalQName.equals(findQName(ref, schemaDoc))) {
                        String prefix = XMLSymbols.EMPTY_STRING;
                        int colonptr = ref.indexOf(":");
                        if (colonptr > 0) {
                            child.setAttribute(SchemaSymbols.ATT_REF, ref.substring(0, colonptr) + ":" + newName);
                        } else {
                            child.setAttribute(SchemaSymbols.ATT_REF, newName);
                        }
                        result += ATTRIBUTE_TYPE;
                        if (elementSought.equals(SchemaSymbols.ELT_GROUP)) {
                            String minOccurs = child.getAttribute(SchemaSymbols.ATT_MINOCCURS);
                            String maxOccurs = child.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
                            if (!((maxOccurs.length() == 0 || maxOccurs.equals(SchemaSymbols.ATTVAL_TRUE_1)) && (minOccurs.length() == 0 || minOccurs.equals(SchemaSymbols.ATTVAL_TRUE_1)))) {
                                Object[] objArr = new Object[ATTRIBUTE_TYPE];
                                objArr[0] = ref;
                                reportSchemaError("src-redefine.6.1.2", objArr, child);
                            }
                        }
                    }
                }
            } else {
                result += changeRedefineGroup(originalQName, elementSought, newName, child, schemaDoc);
            }
        }
        return result;
    }

    private XSDocumentInfo findXSDocumentForDecl(XSDocumentInfo currSchema, Element decl, XSDocumentInfo decl_Doc) {
        XSDocumentInfo temp = decl_Doc;
        if (temp == null) {
            return null;
        }
        return temp;
    }

    private boolean nonAnnotationContent(Element elem) {
        for (Element child = DOMUtil.getFirstChildElement(elem); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            if (!DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                return true;
            }
        }
        return DEBUG_NODE_POOL;
    }

    private void setSchemasVisible(XSDocumentInfo startSchema) {
        if (DOMUtil.isHidden(startSchema.fSchemaElement, this.fHiddenNodes)) {
            DOMUtil.setVisible(startSchema.fSchemaElement, this.fHiddenNodes);
            Vector dependingSchemas = (Vector) this.fDependencyMap.get(startSchema);
            for (int i = 0; i < dependingSchemas.size(); i += ATTRIBUTE_TYPE) {
                setSchemasVisible((XSDocumentInfo) dependingSchemas.elementAt(i));
            }
        }
    }

    public SimpleLocator element2Locator(Element e) {
        if (!(e instanceof ElementImpl)) {
            return null;
        }
        SimpleLocator l = new SimpleLocator();
        if (!element2Locator(e, l)) {
            l = null;
        }
        return l;
    }

    public boolean element2Locator(Element e, SimpleLocator l) {
        if (l == null || !(e instanceof ElementImpl)) {
            return DEBUG_NODE_POOL;
        }
        ElementImpl ele = (ElementImpl) e;
        String sid = (String) this.fDoc2SystemId.get(DOMUtil.getRoot(ele.getOwnerDocument()));
        l.setValues(sid, sid, ele.getLineNumber(), ele.getColumnNumber(), ele.getCharacterOffset());
        return true;
    }

    void reportSchemaError(String key, Object[] args, Element ele) {
        reportSchemaError(key, args, ele, null);
    }

    void reportSchemaError(String key, Object[] args, Element ele, Exception exception) {
        if (element2Locator(ele, this.xl)) {
            this.fErrorReporter.reportError(this.xl, XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 1, exception);
        } else {
            this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 1, exception);
        }
    }

    void reportSchemaWarning(String key, Object[] args, Element ele) {
        reportSchemaWarning(key, args, ele, null);
    }

    void reportSchemaWarning(String key, Object[] args, Element ele, Exception exception) {
        if (element2Locator(ele, this.xl)) {
            this.fErrorReporter.reportError(this.xl, XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 0, exception);
        } else {
            this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 0, exception);
        }
    }

    public void setGenerateSyntheticAnnotations(boolean state) {
        this.fSchemaParser.setFeature(GENERATE_SYNTHETIC_ANNOTATIONS, state);
    }
}
