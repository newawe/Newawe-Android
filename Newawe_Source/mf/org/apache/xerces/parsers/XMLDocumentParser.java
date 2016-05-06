package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public class XMLDocumentParser extends AbstractXMLDocumentParser {
    public XMLDocumentParser() {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    }

    public XMLDocumentParser(XMLParserConfiguration config) {
        super(config);
    }

    public XMLDocumentParser(SymbolTable symbolTable) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty(XMLSchemaValidator.SYMBOL_TABLE, symbolTable);
    }

    public XMLDocumentParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty(XMLSchemaValidator.SYMBOL_TABLE, symbolTable);
        this.fConfiguration.setProperty(XMLSchemaValidator.XMLGRAMMAR_POOL, grammarPool);
    }
}
