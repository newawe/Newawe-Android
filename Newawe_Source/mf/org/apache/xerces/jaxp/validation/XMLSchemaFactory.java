package mf.org.apache.xerces.jaxp.validation;

import mf.javax.xml.XMLConstants;
import mf.javax.xml.transform.dom.DOMSource;
import mf.javax.xml.transform.sax.SAXSource;
import mf.javax.xml.transform.stax.StAXSource;
import mf.javax.xml.transform.stream.StreamSource;
import mf.javax.xml.validation.Schema;
import mf.javax.xml.validation.SchemaFactory;
import mf.org.apache.xerces.impl.xs.XMLSchemaLoader;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.ErrorHandlerWrapper;
import mf.org.apache.xerces.util.SAXMessageFormatter;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.XMLGrammarPoolImpl;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public final class XMLSchemaFactory extends SchemaFactory {
    private static final String JAXP_SOURCE_FEATURE_PREFIX = "http://javax.xml.transform";
    private static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private final DOMEntityResolverWrapper fDOMEntityResolverWrapper;
    private ErrorHandler fErrorHandler;
    private final ErrorHandlerWrapper fErrorHandlerWrapper;
    private LSResourceResolver fLSResourceResolver;
    private SecurityManager fSecurityManager;
    private boolean fUseGrammarPoolOnly;
    private final XMLGrammarPoolWrapper fXMLGrammarPoolWrapper;
    private final XMLSchemaLoader fXMLSchemaLoader;

    static class XMLGrammarPoolWrapper implements XMLGrammarPool {
        private XMLGrammarPool fGrammarPool;

        XMLGrammarPoolWrapper() {
        }

        public Grammar[] retrieveInitialGrammarSet(String grammarType) {
            return this.fGrammarPool.retrieveInitialGrammarSet(grammarType);
        }

        public void cacheGrammars(String grammarType, Grammar[] grammars) {
            this.fGrammarPool.cacheGrammars(grammarType, grammars);
        }

        public Grammar retrieveGrammar(XMLGrammarDescription desc) {
            return this.fGrammarPool.retrieveGrammar(desc);
        }

        public void lockPool() {
            this.fGrammarPool.lockPool();
        }

        public void unlockPool() {
            this.fGrammarPool.unlockPool();
        }

        public void clear() {
            this.fGrammarPool.clear();
        }

        void setGrammarPool(XMLGrammarPool grammarPool) {
            this.fGrammarPool = grammarPool;
        }

        XMLGrammarPool getGrammarPool() {
            return this.fGrammarPool;
        }
    }

    static class XMLGrammarPoolImplExtension extends XMLGrammarPoolImpl {
        public XMLGrammarPoolImplExtension(int initialCapacity) {
            super(initialCapacity);
        }

        int getGrammarCount() {
            return this.fGrammarCount;
        }
    }

    public XMLSchemaFactory() {
        this.fXMLSchemaLoader = new XMLSchemaLoader();
        this.fErrorHandlerWrapper = new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
        this.fDOMEntityResolverWrapper = new DOMEntityResolverWrapper();
        this.fXMLGrammarPoolWrapper = new XMLGrammarPoolWrapper();
        this.fXMLSchemaLoader.setFeature(SCHEMA_FULL_CHECKING, true);
        this.fXMLSchemaLoader.setProperty(XMLGRAMMAR_POOL, this.fXMLGrammarPoolWrapper);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
        this.fUseGrammarPoolOnly = true;
    }

    public boolean isSchemaLanguageSupported(String schemaLanguage) {
        if (schemaLanguage == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageNull", null));
        } else if (schemaLanguage.length() != 0) {
            return schemaLanguage.equals(XMLGrammarDescription.XML_SCHEMA);
        } else {
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageLengthZero", null));
        }
    }

    public LSResourceResolver getResourceResolver() {
        return this.fLSResourceResolver;
    }

    public void setResourceResolver(LSResourceResolver resourceResolver) {
        this.fLSResourceResolver = resourceResolver;
        this.fDOMEntityResolverWrapper.setEntityResolver(resourceResolver);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
    }

    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
        ErrorHandlerWrapper errorHandlerWrapper = this.fErrorHandlerWrapper;
        if (errorHandler == null) {
            errorHandler = DraconianErrorHandler.getInstance();
        }
        errorHandlerWrapper.setErrorHandler(errorHandler);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public mf.javax.xml.validation.Schema newSchema(mf.javax.xml.transform.Source[] r32) throws org.xml.sax.SAXException {
        /*
        r31 = this;
        r12 = new mf.org.apache.xerces.jaxp.validation.XMLSchemaFactory$XMLGrammarPoolImplExtension;
        r12.<init>();
        r0 = r31;
        r0 = r0.fXMLGrammarPoolWrapper;
        r25 = r0;
        r0 = r25;
        r0.setGrammarPool(r12);
        r0 = r32;
        r0 = r0.length;
        r25 = r0;
        r0 = r25;
        r0 = new mf.org.apache.xerces.xni.parser.XMLInputSource[r0];
        r24 = r0;
        r8 = 0;
    L_0x001c:
        r0 = r32;
        r0 = r0.length;
        r25 = r0;
        r0 = r25;
        if (r8 < r0) goto L_0x0069;
    L_0x0025:
        r0 = r31;
        r0 = r0.fXMLSchemaLoader;	 Catch:{ XNIException -> 0x016b, IOException -> 0x0171 }
        r25 = r0;
        r0 = r25;
        r1 = r24;
        r0.loadGrammar(r1);	 Catch:{ XNIException -> 0x016b, IOException -> 0x0171 }
        r0 = r31;
        r0 = r0.fXMLGrammarPoolWrapper;
        r25 = r0;
        r26 = 0;
        r25.setGrammarPool(r26);
        r6 = r12.getGrammarCount();
        r16 = 0;
        r0 = r31;
        r0 = r0.fUseGrammarPoolOnly;
        r25 = r0;
        if (r25 == 0) goto L_0x01bd;
    L_0x004b:
        r25 = 1;
        r0 = r25;
        if (r6 <= r0) goto L_0x0199;
    L_0x0051:
        r16 = new mf.org.apache.xerces.jaxp.validation.XMLSchema;
        r25 = new mf.org.apache.xerces.jaxp.validation.ReadOnlyGrammarPool;
        r0 = r25;
        r0.<init>(r12);
        r0 = r16;
        r1 = r25;
        r0.<init>(r1);
    L_0x0061:
        r0 = r31;
        r1 = r16;
        r0.propagateFeatures(r1);
        return r16;
    L_0x0069:
        r18 = r32[r8];
        r0 = r18;
        r0 = r0 instanceof mf.javax.xml.transform.stream.StreamSource;
        r25 = r0;
        if (r25 == 0) goto L_0x00a4;
    L_0x0073:
        r20 = r18;
        r20 = (mf.javax.xml.transform.stream.StreamSource) r20;
        r13 = r20.getPublicId();
        r22 = r20.getSystemId();
        r10 = r20.getInputStream();
        r14 = r20.getReader();
        r23 = new mf.org.apache.xerces.xni.parser.XMLInputSource;
        r25 = 0;
        r0 = r23;
        r1 = r22;
        r2 = r25;
        r0.<init>(r13, r1, r2);
        r0 = r23;
        r0.setByteStream(r10);
        r0 = r23;
        r0.setCharacterStream(r14);
        r24[r8] = r23;
    L_0x00a0:
        r8 = r8 + 1;
        goto L_0x001c;
    L_0x00a4:
        r0 = r18;
        r0 = r0 instanceof mf.javax.xml.transform.sax.SAXSource;
        r25 = r0;
        if (r25 == 0) goto L_0x00de;
    L_0x00ac:
        r15 = r18;
        r15 = (mf.javax.xml.transform.sax.SAXSource) r15;
        r9 = r15.getInputSource();
        if (r9 != 0) goto L_0x00ce;
    L_0x00b6:
        r25 = new org.xml.sax.SAXException;
        r0 = r31;
        r0 = r0.fXMLSchemaLoader;
        r26 = r0;
        r26 = r26.getLocale();
        r27 = "SAXSourceNullInputSource";
        r28 = 0;
        r26 = mf.org.apache.xerces.jaxp.validation.JAXPValidationMessageFormatter.formatMessage(r26, r27, r28);
        r25.<init>(r26);
        throw r25;
    L_0x00ce:
        r25 = new mf.org.apache.xerces.util.SAXInputSource;
        r26 = r15.getXMLReader();
        r0 = r25;
        r1 = r26;
        r0.<init>(r1, r9);
        r24[r8] = r25;
        goto L_0x00a0;
    L_0x00de:
        r0 = r18;
        r0 = r0 instanceof mf.javax.xml.transform.dom.DOMSource;
        r25 = r0;
        if (r25 == 0) goto L_0x00fe;
    L_0x00e6:
        r3 = r18;
        r3 = (mf.javax.xml.transform.dom.DOMSource) r3;
        r11 = r3.getNode();
        r21 = r3.getSystemId();
        r25 = new mf.org.apache.xerces.util.DOMInputSource;
        r0 = r25;
        r1 = r21;
        r0.<init>(r11, r1);
        r24[r8] = r25;
        goto L_0x00a0;
    L_0x00fe:
        r0 = r18;
        r0 = r0 instanceof mf.javax.xml.transform.stax.StAXSource;
        r25 = r0;
        if (r25 == 0) goto L_0x0127;
    L_0x0106:
        r19 = r18;
        r19 = (mf.javax.xml.transform.stax.StAXSource) r19;
        r5 = r19.getXMLEventReader();
        if (r5 == 0) goto L_0x011a;
    L_0x0110:
        r25 = new mf.org.apache.xerces.util.StAXInputSource;
        r0 = r25;
        r0.<init>(r5);
        r24[r8] = r25;
        goto L_0x00a0;
    L_0x011a:
        r25 = new mf.org.apache.xerces.util.StAXInputSource;
        r26 = r19.getXMLStreamReader();
        r25.<init>(r26);
        r24[r8] = r25;
        goto L_0x00a0;
    L_0x0127:
        if (r18 != 0) goto L_0x0141;
    L_0x0129:
        r25 = new java.lang.NullPointerException;
        r0 = r31;
        r0 = r0.fXMLSchemaLoader;
        r26 = r0;
        r26 = r26.getLocale();
        r27 = "SchemaSourceArrayMemberNull";
        r28 = 0;
        r26 = mf.org.apache.xerces.jaxp.validation.JAXPValidationMessageFormatter.formatMessage(r26, r27, r28);
        r25.<init>(r26);
        throw r25;
    L_0x0141:
        r25 = new java.lang.IllegalArgumentException;
        r0 = r31;
        r0 = r0.fXMLSchemaLoader;
        r26 = r0;
        r26 = r26.getLocale();
        r27 = "SchemaFactorySourceUnrecognized";
        r28 = 1;
        r0 = r28;
        r0 = new java.lang.Object[r0];
        r28 = r0;
        r29 = 0;
        r30 = r18.getClass();
        r30 = r30.getName();
        r28[r29] = r30;
        r26 = mf.org.apache.xerces.jaxp.validation.JAXPValidationMessageFormatter.formatMessage(r26, r27, r28);
        r25.<init>(r26);
        throw r25;
    L_0x016b:
        r4 = move-exception;
        r25 = mf.org.apache.xerces.jaxp.validation.Util.toSAXException(r4);
        throw r25;
    L_0x0171:
        r4 = move-exception;
        r17 = new org.xml.sax.SAXParseException;
        r25 = r4.getMessage();
        r26 = 0;
        r0 = r17;
        r1 = r25;
        r2 = r26;
        r0.<init>(r1, r2, r4);
        r0 = r31;
        r0 = r0.fErrorHandler;
        r25 = r0;
        if (r25 == 0) goto L_0x0198;
    L_0x018b:
        r0 = r31;
        r0 = r0.fErrorHandler;
        r25 = r0;
        r0 = r25;
        r1 = r17;
        r0.error(r1);
    L_0x0198:
        throw r17;
    L_0x0199:
        r25 = 1;
        r0 = r25;
        if (r6 != r0) goto L_0x01b6;
    L_0x019f:
        r25 = "http://www.w3.org/2001/XMLSchema";
        r0 = r25;
        r7 = r12.retrieveInitialGrammarSet(r0);
        r16 = new mf.org.apache.xerces.jaxp.validation.SimpleXMLSchema;
        r25 = 0;
        r25 = r7[r25];
        r0 = r16;
        r1 = r25;
        r0.<init>(r1);
        goto L_0x0061;
    L_0x01b6:
        r16 = new mf.org.apache.xerces.jaxp.validation.EmptyXMLSchema;
        r16.<init>();
        goto L_0x0061;
    L_0x01bd:
        r16 = new mf.org.apache.xerces.jaxp.validation.XMLSchema;
        r25 = new mf.org.apache.xerces.jaxp.validation.ReadOnlyGrammarPool;
        r0 = r25;
        r0.<init>(r12);
        r26 = 0;
        r0 = r16;
        r1 = r25;
        r2 = r26;
        r0.<init>(r1, r2);
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.jaxp.validation.XMLSchemaFactory.newSchema(mf.javax.xml.transform.Source[]):mf.javax.xml.validation.Schema");
    }

    public Schema newSchema() throws SAXException {
        AbstractXMLSchema schema = new WeakReferenceXMLSchema();
        propagateFeatures(schema);
        return schema;
    }

    public Schema newSchema(XMLGrammarPool pool) throws SAXException {
        AbstractXMLSchema schema;
        if (this.fUseGrammarPoolOnly) {
            schema = new XMLSchema(new ReadOnlyGrammarPool(pool));
        } else {
            schema = new XMLSchema(pool, false);
        }
        propagateFeatures(schema);
        return schema;
    }

    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        int i = 1;
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
        } else if (name.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (name.equals(StreamSource.FEATURE) || name.equals(SAXSource.FEATURE) || name.equals(DOMSource.FEATURE) || name.equals(StAXSource.FEATURE))) {
            return i;
        } else {
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                if (this.fSecurityManager == null) {
                    return 0;
                }
                return i;
            } else if (name.equals(USE_GRAMMAR_POOL_ONLY)) {
                return this.fUseGrammarPoolOnly;
            } else {
                try {
                    return this.fXMLSchemaLoader.getFeature(name);
                } catch (XMLConfigurationException e) {
                    String identifier = e.getIdentifier();
                    Object[] objArr;
                    if (e.getType() == (short) 0) {
                        objArr = new Object[i];
                        objArr[0] = identifier;
                        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", objArr));
                    }
                    objArr = new Object[i];
                    objArr[0] = identifier;
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", objArr));
                }
            }
        }
    }

    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
        } else if (name.equals(SECURITY_MANAGER)) {
            return this.fSecurityManager;
        } else {
            if (name.equals(XMLGRAMMAR_POOL)) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{name}));
            }
            try {
                return this.fXMLSchemaLoader.getProperty(name);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
                }
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
        }
    }

    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        SecurityManager securityManager = null;
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
        } else if (name.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (name.equals(StreamSource.FEATURE) || name.equals(SAXSource.FEATURE) || name.equals(DOMSource.FEATURE) || name.equals(StAXSource.FEATURE))) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-read-only", new Object[]{name}));
        } else if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            if (value) {
                securityManager = new SecurityManager();
            }
            this.fSecurityManager = securityManager;
            this.fXMLSchemaLoader.setProperty(SECURITY_MANAGER, this.fSecurityManager);
        } else if (name.equals(USE_GRAMMAR_POOL_ONLY)) {
            this.fUseGrammarPoolOnly = value;
        } else {
            try {
                this.fXMLSchemaLoader.setFeature(name, value);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", new Object[]{identifier}));
                } else {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", new Object[]{identifier}));
                }
            }
        }
    }

    public void setProperty(String name, Object object) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
        } else if (name.equals(SECURITY_MANAGER)) {
            this.fSecurityManager = (SecurityManager) object;
            this.fXMLSchemaLoader.setProperty(SECURITY_MANAGER, this.fSecurityManager);
        } else if (name.equals(XMLGRAMMAR_POOL)) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{name}));
        } else {
            try {
                this.fXMLSchemaLoader.setProperty(name, object);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
                } else {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
                }
            }
        }
    }

    private void propagateFeatures(AbstractXMLSchema schema) {
        schema.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, this.fSecurityManager != null);
        String[] features = this.fXMLSchemaLoader.getRecognizedFeatures();
        for (int i = 0; i < features.length; i++) {
            schema.setFeature(features[i], this.fXMLSchemaLoader.getFeature(features[i]));
        }
    }
}
