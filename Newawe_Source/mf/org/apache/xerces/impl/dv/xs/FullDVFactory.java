package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.SymbolHash;

public class FullDVFactory extends BaseDVFactory {
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static SymbolHash fFullTypes;

    static {
        fFullTypes = new SymbolHash(89);
        createBuiltInTypes(fFullTypes);
    }

    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType) fFullTypes.get(name);
    }

    public SymbolHash getBuiltInTypes() {
        return fFullTypes.makeClone();
    }

    static void createBuiltInTypes(SymbolHash types) {
        BaseDVFactory.createBuiltInTypes(types);
        String DOUBLE = SchemaSymbols.ATTVAL_DOUBLE;
        String DURATION = SchemaSymbols.ATTVAL_DURATION;
        String ENTITY = SchemaSymbols.ATTVAL_ENTITY;
        String ENTITIES = SchemaSymbols.ATTVAL_ENTITIES;
        String FLOAT = SchemaSymbols.ATTVAL_FLOAT;
        String HEXBINARY = SchemaSymbols.ATTVAL_HEXBINARY;
        String ID = SchemaSymbols.ATTVAL_ID;
        String IDREF = SchemaSymbols.ATTVAL_IDREF;
        String IDREFS = SchemaSymbols.ATTVAL_IDREFS;
        String NAME = SchemaSymbols.ATTVAL_NAME;
        String NCNAME = SchemaSymbols.ATTVAL_NCNAME;
        String NMTOKEN = SchemaSymbols.ATTVAL_NMTOKEN;
        String NMTOKENS = SchemaSymbols.ATTVAL_NMTOKENS;
        String LANGUAGE = SchemaSymbols.ATTVAL_LANGUAGE;
        String NORMALIZEDSTRING = SchemaSymbols.ATTVAL_NORMALIZEDSTRING;
        String NOTATION = SchemaSymbols.ATTVAL_NOTATION;
        String QNAME = SchemaSymbols.ATTVAL_QNAME;
        String STRING = SchemaSymbols.ATTVAL_STRING;
        String TOKEN = SchemaSymbols.ATTVAL_TOKEN;
        XSFacets facets = new XSFacets();
        XSSimpleTypeDecl anySimpleType = XSSimpleTypeDecl.fAnySimpleType;
        XSSimpleTypeDecl stringDV = (XSSimpleTypeDecl) types.get(SchemaSymbols.ATTVAL_STRING);
        String str = SchemaSymbols.ATTVAL_FLOAT;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_FLOAT, (short) 4, (short) 1, true, true, true, true, (short) 5));
        str = SchemaSymbols.ATTVAL_DOUBLE;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_DOUBLE, (short) 5, (short) 1, true, true, true, true, (short) 6));
        str = SchemaSymbols.ATTVAL_DURATION;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_DURATION, (short) 6, (short) 1, false, false, false, true, (short) 7));
        str = SchemaSymbols.ATTVAL_HEXBINARY;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_HEXBINARY, (short) 15, (short) 0, false, false, false, true, (short) 16));
        str = SchemaSymbols.ATTVAL_QNAME;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_QNAME, (short) 18, (short) 0, false, false, false, true, (short) 19));
        str = SchemaSymbols.ATTVAL_NOTATION;
        types.put(r17, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_NOTATION, (short) 20, (short) 0, false, false, false, true, (short) 20));
        facets.whiteSpace = (short) 1;
        XSSimpleTypeDecl normalizedDV = new XSSimpleTypeDecl(stringDV, SchemaSymbols.ATTVAL_NORMALIZEDSTRING, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 21);
        normalizedDV.applyFacets1(facets, (short) 16, (short) 0);
        types.put(SchemaSymbols.ATTVAL_NORMALIZEDSTRING, normalizedDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl tokenDV = new XSSimpleTypeDecl(normalizedDV, SchemaSymbols.ATTVAL_TOKEN, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 22);
        tokenDV.applyFacets1(facets, (short) 16, (short) 0);
        types.put(SchemaSymbols.ATTVAL_TOKEN, tokenDV);
        facets.whiteSpace = (short) 2;
        facets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        XSSimpleTypeDecl languageDV = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_LANGUAGE, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 23);
        languageDV.applyFacets1(facets, (short) 24, (short) 0);
        types.put(SchemaSymbols.ATTVAL_LANGUAGE, languageDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl nameDV = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_NAME, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 25);
        nameDV.applyFacets1(facets, (short) 16, (short) 0, (short) 2);
        types.put(SchemaSymbols.ATTVAL_NAME, nameDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl ncnameDV = new XSSimpleTypeDecl(nameDV, SchemaSymbols.ATTVAL_NCNAME, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 26);
        ncnameDV.applyFacets1(facets, (short) 16, (short) 0, (short) 3);
        types.put(SchemaSymbols.ATTVAL_NCNAME, ncnameDV);
        SymbolHash symbolHash = types;
        symbolHash.put(SchemaSymbols.ATTVAL_ID, new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_ID, (short) 21, (short) 0, false, false, false, true, (short) 27));
        XSSimpleTypeDecl idrefDV = new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_IDREF, (short) 22, (short) 0, false, false, false, true, (short) 28);
        types.put(SchemaSymbols.ATTVAL_IDREF, idrefDV);
        facets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short) 0, idrefDV, true, null);
        XSSimpleTypeDecl idrefsDV = new XSSimpleTypeDecl(tempDV, SchemaSymbols.ATTVAL_IDREFS, URI_SCHEMAFORSCHEMA, (short) 0, false, null);
        idrefsDV.applyFacets1(facets, (short) 2, (short) 0);
        types.put(SchemaSymbols.ATTVAL_IDREFS, idrefsDV);
        xSSimpleTypeDecl = new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_ENTITY, (short) 23, (short) 0, false, false, false, true, (short) 29);
        types.put(SchemaSymbols.ATTVAL_ENTITY, xSSimpleTypeDecl);
        facets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl2 = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short) 0, xSSimpleTypeDecl, true, null);
        XSSimpleTypeDecl entitiesDV = new XSSimpleTypeDecl(entitiesDV, SchemaSymbols.ATTVAL_ENTITIES, URI_SCHEMAFORSCHEMA, (short) 0, false, null);
        entitiesDV.applyFacets1(facets, (short) 2, (short) 0);
        types.put(SchemaSymbols.ATTVAL_ENTITIES, entitiesDV);
        facets.whiteSpace = (short) 2;
        xSSimpleTypeDecl2 = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_NMTOKEN, URI_SCHEMAFORSCHEMA, (short) 0, false, null, (short) 24);
        xSSimpleTypeDecl2.applyFacets1(facets, (short) 16, (short) 0, (short) 1);
        types.put(SchemaSymbols.ATTVAL_NMTOKEN, xSSimpleTypeDecl2);
        facets.minLength = 1;
        XSSimpleTypeDecl xSSimpleTypeDecl3 = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short) 0, xSSimpleTypeDecl2, true, null);
        XSSimpleTypeDecl nmtokensDV = new XSSimpleTypeDecl(nmtokensDV, SchemaSymbols.ATTVAL_NMTOKENS, URI_SCHEMAFORSCHEMA, (short) 0, false, null);
        nmtokensDV.applyFacets1(facets, (short) 2, (short) 0);
        types.put(SchemaSymbols.ATTVAL_NMTOKENS, nmtokensDV);
    }
}
