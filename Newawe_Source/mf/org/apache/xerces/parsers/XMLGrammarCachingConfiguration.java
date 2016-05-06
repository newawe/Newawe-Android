package mf.org.apache.xerces.parsers;

import java.io.IOException;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.impl.dtd.XMLDTDLoader;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.XMLSchemaLoader;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.jaxp.JAXPConstants;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.SynchronizedSymbolTable;
import mf.org.apache.xerces.util.XMLGrammarPoolImpl;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLGrammarCachingConfiguration extends XIncludeAwareParserConfiguration {
    public static final int BIG_PRIME = 2039;
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final XMLGrammarPoolImpl fStaticGrammarPool;
    protected static final SynchronizedSymbolTable fStaticSymbolTable;
    protected XMLDTDLoader fDTDLoader;
    protected XMLSchemaLoader fSchemaLoader;

    static {
        fStaticSymbolTable = new SynchronizedSymbolTable((int) BIG_PRIME);
        fStaticGrammarPool = new XMLGrammarPoolImpl();
    }

    public XMLGrammarCachingConfiguration() {
        this(fStaticSymbolTable, fStaticGrammarPool, null);
    }

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable) {
        this(symbolTable, fStaticGrammarPool, null);
    }

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public XMLGrammarCachingConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        this.fSchemaLoader = new XMLSchemaLoader(this.fSymbolTable);
        this.fSchemaLoader.setProperty(XMLSchemaValidator.XMLGRAMMAR_POOL, this.fGrammarPool);
        this.fDTDLoader = new XMLDTDLoader(this.fSymbolTable, this.fGrammarPool);
    }

    public void lockGrammarPool() {
        this.fGrammarPool.lockPool();
    }

    public void clearGrammarPool() {
        this.fGrammarPool.clear();
    }

    public void unlockGrammarPool() {
        this.fGrammarPool.unlockPool();
    }

    public Grammar parseGrammar(String type, String uri) throws XNIException, IOException {
        return parseGrammar(type, new XMLInputSource(null, uri, null));
    }

    public Grammar parseGrammar(String type, XMLInputSource is) throws XNIException, IOException {
        if (type.equals(XMLGrammarDescription.XML_SCHEMA)) {
            return parseXMLSchema(is);
        }
        if (type.equals(XMLGrammarDescription.XML_DTD)) {
            return parseDTD(is);
        }
        return null;
    }

    protected void checkFeature(String featureId) throws XMLConfigurationException {
        super.checkFeature(featureId);
    }

    protected void checkProperty(String propertyId) throws XMLConfigurationException {
        super.checkProperty(propertyId);
    }

    SchemaGrammar parseXMLSchema(XMLInputSource is) throws IOException {
        XMLEntityResolver resolver = getEntityResolver();
        if (resolver != null) {
            this.fSchemaLoader.setEntityResolver(resolver);
        }
        if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        }
        this.fSchemaLoader.setProperty(XMLSchemaValidator.ERROR_REPORTER, this.fErrorReporter);
        String propPrefix = Constants.XERCES_PROPERTY_PREFIX;
        String propName = new StringBuilder(String.valueOf(propPrefix)).append(Constants.SCHEMA_LOCATION).toString();
        this.fSchemaLoader.setProperty(propName, getProperty(propName));
        propName = new StringBuilder(String.valueOf(propPrefix)).append(Constants.SCHEMA_NONS_LOCATION).toString();
        this.fSchemaLoader.setProperty(propName, getProperty(propName));
        propName = JAXPConstants.JAXP_SCHEMA_SOURCE;
        this.fSchemaLoader.setProperty(propName, getProperty(propName));
        this.fSchemaLoader.setFeature(SCHEMA_FULL_CHECKING, getFeature(SCHEMA_FULL_CHECKING));
        SchemaGrammar grammar = (SchemaGrammar) this.fSchemaLoader.loadGrammar(is);
        if (grammar != null) {
            this.fGrammarPool.cacheGrammars(XMLGrammarDescription.XML_SCHEMA, new Grammar[]{grammar});
        }
        return grammar;
    }

    DTDGrammar parseDTD(XMLInputSource is) throws IOException {
        XMLEntityResolver resolver = getEntityResolver();
        if (resolver != null) {
            this.fDTDLoader.setEntityResolver(resolver);
        }
        this.fDTDLoader.setProperty(XMLSchemaValidator.ERROR_REPORTER, this.fErrorReporter);
        DTDGrammar grammar = (DTDGrammar) this.fDTDLoader.loadGrammar(is);
        if (grammar != null) {
            this.fGrammarPool.cacheGrammars(XMLGrammarDescription.XML_DTD, new Grammar[]{grammar});
        }
        return grammar;
    }
}
