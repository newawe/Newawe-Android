package mf.org.apache.xerces.impl.xs.traversers;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import java.util.Locale;
import java.util.Vector;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.validation.ValidationState;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import mf.org.apache.xerces.impl.xs.XSAttributeUseImpl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.XSWildcardDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

abstract class XSDAbstractTraverser {
    protected static final int CHILD_OF_GROUP = 4;
    protected static final int GROUP_REF_WITH_ALL = 2;
    protected static final int NOT_ALL_CONTEXT = 0;
    protected static final String NO_NAME = "(no name)";
    protected static final int PROCESSING_ALL_EL = 1;
    protected static final int PROCESSING_ALL_GP = 8;
    private static final XSSimpleType fQNameDV;
    protected XSAttributeChecker fAttrChecker;
    private StringBuffer fPattern;
    protected XSDHandler fSchemaHandler;
    protected SymbolTable fSymbolTable;
    protected boolean fValidateAnnotations;
    ValidationState fValidationState;
    private final XSFacets xsFacets;

    static final class FacetInfo {
        final short fFixedFacets;
        final short fPresentFacets;
        final XSFacets facetdata;
        final Element nodeAfterFacets;

        FacetInfo(XSFacets facets, Element nodeAfterFacets, short presentFacets, short fixedFacets) {
            this.facetdata = facets;
            this.nodeAfterFacets = nodeAfterFacets;
            this.fPresentFacets = presentFacets;
            this.fFixedFacets = fixedFacets;
        }
    }

    XSDAbstractTraverser(XSDHandler handler, XSAttributeChecker attrChecker) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fAttrChecker = null;
        this.fValidateAnnotations = false;
        this.fValidationState = new ValidationState();
        this.fPattern = new StringBuffer();
        this.xsFacets = new XSFacets();
        this.fSchemaHandler = handler;
        this.fAttrChecker = attrChecker;
    }

    void reset(SymbolTable symbolTable, boolean validateAnnotations, Locale locale) {
        this.fSymbolTable = symbolTable;
        this.fValidateAnnotations = validateAnnotations;
        this.fValidationState.setExtraChecking(false);
        this.fValidationState.setSymbolTable(symbolTable);
        this.fValidationState.setLocale(locale);
    }

    XSAnnotationImpl traverseAnnotationDecl(Element annotationDecl, Object[] parentAttrs, boolean isGlobal, XSDocumentInfo schemaDoc) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(annotationDecl, isGlobal, schemaDoc);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        String contents = DOMUtil.getAnnotation(annotationDecl);
        Element child = DOMUtil.getFirstChildElement(annotationDecl);
        if (child != null) {
            do {
                String name = DOMUtil.getLocalName(child);
                if (!name.equals(SchemaSymbols.ELT_APPINFO)) {
                    if (!name.equals(SchemaSymbols.ELT_DOCUMENTATION)) {
                        String[] strArr = new Object[PROCESSING_ALL_EL];
                        strArr[NOT_ALL_CONTEXT] = name;
                        reportSchemaError("src-annotation", strArr, child);
                        child = DOMUtil.getNextSiblingElement(child);
                    }
                }
                attrValues = this.fAttrChecker.checkAttributes(child, true, schemaDoc);
                this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            } while (child != null);
        }
        if (contents == null) {
            return null;
        }
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(schemaDoc.fTargetNamespace);
        Vector annotationLocalAttrs = parentAttrs[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (annotationLocalAttrs == null || annotationLocalAttrs.isEmpty()) {
            if (this.fValidateAnnotations) {
                schemaDoc.addAnnotation(new XSAnnotationInfo(contents, annotationDecl));
            }
            return new XSAnnotationImpl(contents, grammar);
        }
        StringBuffer localStrBuffer = new StringBuffer(64);
        localStrBuffer.append(" ");
        int i = NOT_ALL_CONTEXT;
        while (i < annotationLocalAttrs.size()) {
            String prefix;
            String localpart;
            int i2 = i + PROCESSING_ALL_EL;
            String rawname = (String) annotationLocalAttrs.elementAt(i);
            int colonIndex = rawname.indexOf(58);
            if (colonIndex == -1) {
                prefix = StringUtils.EMPTY;
                localpart = rawname;
            } else {
                prefix = rawname.substring(NOT_ALL_CONTEXT, colonIndex);
                localpart = rawname.substring(colonIndex + PROCESSING_ALL_EL);
            }
            if (annotationDecl.getAttributeNS(schemaDoc.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(prefix)), localpart).length() != 0) {
                i = i2 + PROCESSING_ALL_EL;
            } else {
                localStrBuffer.append(rawname).append("=\"");
                i = i2 + PROCESSING_ALL_EL;
                localStrBuffer.append(processAttValue((String) annotationLocalAttrs.elementAt(i2))).append("\" ");
            }
        }
        StringBuffer contentBuffer = new StringBuffer(contents.length() + localStrBuffer.length());
        int annotationTokenEnd = contents.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (annotationTokenEnd == -1) {
            return null;
        }
        annotationTokenEnd += SchemaSymbols.ELT_ANNOTATION.length();
        contentBuffer.append(contents.substring(NOT_ALL_CONTEXT, annotationTokenEnd));
        contentBuffer.append(localStrBuffer.toString());
        contentBuffer.append(contents.substring(annotationTokenEnd, contents.length()));
        String annotation = contentBuffer.toString();
        if (this.fValidateAnnotations) {
            schemaDoc.addAnnotation(new XSAnnotationInfo(annotation, annotationDecl));
        }
        return new XSAnnotationImpl(annotation, grammar);
    }

    XSAnnotationImpl traverseSyntheticAnnotation(Element annotationParent, String initialContent, Object[] parentAttrs, boolean isGlobal, XSDocumentInfo schemaDoc) {
        String contents = initialContent;
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(schemaDoc.fTargetNamespace);
        Vector annotationLocalAttrs = parentAttrs[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (annotationLocalAttrs == null || annotationLocalAttrs.isEmpty()) {
            if (this.fValidateAnnotations) {
                schemaDoc.addAnnotation(new XSAnnotationInfo(contents, annotationParent));
            }
            return new XSAnnotationImpl(contents, grammar);
        }
        StringBuffer localStrBuffer = new StringBuffer(64);
        localStrBuffer.append(" ");
        int i = NOT_ALL_CONTEXT;
        while (i < annotationLocalAttrs.size()) {
            String prefix;
            int i2 = i + PROCESSING_ALL_EL;
            String rawname = (String) annotationLocalAttrs.elementAt(i);
            int colonIndex = rawname.indexOf(58);
            String localpart;
            if (colonIndex == -1) {
                prefix = StringUtils.EMPTY;
                localpart = rawname;
            } else {
                prefix = rawname.substring(NOT_ALL_CONTEXT, colonIndex);
                localpart = rawname.substring(colonIndex + PROCESSING_ALL_EL);
            }
            String uri = schemaDoc.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(prefix));
            localStrBuffer.append(rawname).append("=\"");
            i = i2 + PROCESSING_ALL_EL;
            localStrBuffer.append(processAttValue((String) annotationLocalAttrs.elementAt(i2))).append("\" ");
        }
        StringBuffer contentBuffer = new StringBuffer(contents.length() + localStrBuffer.length());
        int annotationTokenEnd = contents.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (annotationTokenEnd == -1) {
            return null;
        }
        annotationTokenEnd += SchemaSymbols.ELT_ANNOTATION.length();
        contentBuffer.append(contents.substring(NOT_ALL_CONTEXT, annotationTokenEnd));
        contentBuffer.append(localStrBuffer.toString());
        contentBuffer.append(contents.substring(annotationTokenEnd, contents.length()));
        String annotation = contentBuffer.toString();
        if (this.fValidateAnnotations) {
            schemaDoc.addAnnotation(new XSAnnotationInfo(annotation, annotationParent));
        }
        return new XSAnnotationImpl(annotation, grammar);
    }

    static {
        fQNameDV = (XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
    }

    FacetInfo traverseFacets(Element content, XSSimpleType baseValidator, XSDocumentInfo schemaDoc) {
        short facetsPresent = (short) 0;
        short facetsFixed = (short) 0;
        boolean hasQName = containsQName(baseValidator);
        Vector enumData = null;
        XSObjectList enumAnnotations = null;
        XSObjectListImpl patternAnnotations = null;
        Vector enumNSDecls = hasQName ? new Vector() : null;
        this.xsFacets.reset();
        while (content != null) {
            Object[] attrs;
            String facet = DOMUtil.getLocalName(content);
            Object[] objArr;
            Element child;
            String text;
            if (facet.equals(SchemaSymbols.ELT_ENUMERATION)) {
                attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc, hasQName);
                String enumVal = attrs[XSAttributeChecker.ATTIDX_VALUE];
                if (enumVal == null) {
                    objArr = new Object[GROUP_REF_WITH_ALL];
                    objArr[NOT_ALL_CONTEXT] = SchemaSymbols.ELT_ENUMERATION;
                    objArr[PROCESSING_ALL_EL] = SchemaSymbols.ATT_VALUE;
                    reportSchemaError("s4s-att-must-appear", objArr, content);
                    this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                    content = DOMUtil.getNextSiblingElement(content);
                } else {
                    NamespaceSupport nsDecls = attrs[XSAttributeChecker.ATTIDX_ENUMNSDECLS];
                    if (baseValidator.getVariety() == (short) 1 && baseValidator.getPrimitiveKind() == (short) 20) {
                        schemaDoc.fValidationContext.setNamespaceSupport(nsDecls);
                        Object notation = null;
                        try {
                            notation = this.fSchemaHandler.getGlobalDecl(schemaDoc, 6, (QName) fQNameDV.validate(enumVal, schemaDoc.fValidationContext, null), content);
                        } catch (InvalidDatatypeValueException ex) {
                            reportSchemaError(ex.getKey(), ex.getArgs(), content);
                        }
                        if (notation == null) {
                            this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                            content = DOMUtil.getNextSiblingElement(content);
                        } else {
                            schemaDoc.fValidationContext.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                        }
                    }
                    if (enumData == null) {
                        enumData = new Vector();
                        enumAnnotations = new XSObjectListImpl();
                    }
                    enumData.addElement(enumVal);
                    enumAnnotations.addXSObject(null);
                    if (hasQName) {
                        enumNSDecls.addElement(nsDecls);
                    }
                    child = DOMUtil.getFirstChildElement(content);
                    if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        text = DOMUtil.getSyntheticAnnotation(content);
                        if (text != null) {
                            enumAnnotations.addXSObject(enumAnnotations.getLength() - 1, traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc));
                        }
                    } else {
                        enumAnnotations.addXSObject(enumAnnotations.getLength() - 1, traverseAnnotationDecl(child, attrs, false, schemaDoc));
                        child = DOMUtil.getNextSiblingElement(child);
                    }
                    if (child != null) {
                        reportSchemaError("s4s-elt-must-match.1", new Object[]{"enumeration", "(annotation?)", DOMUtil.getLocalName(child)}, child);
                    }
                }
            } else {
                if (facet.equals(SchemaSymbols.ELT_PATTERN)) {
                    attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                    String patternVal = attrs[XSAttributeChecker.ATTIDX_VALUE];
                    if (patternVal == null) {
                        objArr = new Object[GROUP_REF_WITH_ALL];
                        objArr[NOT_ALL_CONTEXT] = SchemaSymbols.ELT_PATTERN;
                        objArr[PROCESSING_ALL_EL] = SchemaSymbols.ATT_VALUE;
                        reportSchemaError("s4s-att-must-appear", objArr, content);
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else {
                        if (this.fPattern.length() == 0) {
                            this.fPattern.append(patternVal);
                        } else {
                            this.fPattern.append("|");
                            this.fPattern.append(patternVal);
                        }
                        child = DOMUtil.getFirstChildElement(content);
                        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            text = DOMUtil.getSyntheticAnnotation(content);
                            if (text != null) {
                                if (patternAnnotations == null) {
                                    patternAnnotations = new XSObjectListImpl();
                                }
                                patternAnnotations.addXSObject(traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc));
                            }
                        } else {
                            if (patternAnnotations == null) {
                                patternAnnotations = new XSObjectListImpl();
                            }
                            patternAnnotations.addXSObject(traverseAnnotationDecl(child, attrs, false, schemaDoc));
                            child = DOMUtil.getNextSiblingElement(child);
                        }
                        if (child != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{"pattern", "(annotation?)", DOMUtil.getLocalName(child)}, child);
                        }
                    }
                } else {
                    int currentFacet;
                    if (facet.equals(SchemaSymbols.ELT_MINLENGTH)) {
                        currentFacet = GROUP_REF_WITH_ALL;
                    } else {
                        if (facet.equals(SchemaSymbols.ELT_MAXLENGTH)) {
                            currentFacet = CHILD_OF_GROUP;
                        } else {
                            if (facet.equals(SchemaSymbols.ELT_MAXEXCLUSIVE)) {
                                currentFacet = 64;
                            } else {
                                if (facet.equals(SchemaSymbols.ELT_MAXINCLUSIVE)) {
                                    currentFacet = 32;
                                } else {
                                    if (facet.equals(SchemaSymbols.ELT_MINEXCLUSIVE)) {
                                        currentFacet = TransportMediator.FLAG_KEY_MEDIA_NEXT;
                                    } else {
                                        if (facet.equals(SchemaSymbols.ELT_MININCLUSIVE)) {
                                            currentFacet = NodeFilter.SHOW_DOCUMENT;
                                        } else {
                                            if (facet.equals(SchemaSymbols.ELT_TOTALDIGITS)) {
                                                currentFacet = XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE;
                                            } else {
                                                if (facet.equals(SchemaSymbols.ELT_FRACTIONDIGITS)) {
                                                    currentFacet = NodeFilter.SHOW_DOCUMENT_FRAGMENT;
                                                } else {
                                                    if (facet.equals(SchemaSymbols.ELT_WHITESPACE)) {
                                                        currentFacet = 16;
                                                    } else {
                                                        if (facet.equals(SchemaSymbols.ELT_LENGTH)) {
                                                            currentFacet = PROCESSING_ALL_EL;
                                                        } else {
                                                            if (enumData != null) {
                                                                facetsPresent = (short) (facetsPresent | XMLEntityManager.DEFAULT_BUFFER_SIZE);
                                                                this.xsFacets.enumeration = enumData;
                                                                this.xsFacets.enumNSDecls = enumNSDecls;
                                                                this.xsFacets.enumAnnotations = enumAnnotations;
                                                            }
                                                            if (this.fPattern.length() != 0) {
                                                                facetsPresent = (short) (facetsPresent | PROCESSING_ALL_GP);
                                                                this.xsFacets.pattern = this.fPattern.toString();
                                                                this.xsFacets.patternAnnotations = patternAnnotations;
                                                            }
                                                            this.fPattern.setLength(NOT_ALL_CONTEXT);
                                                            return new FacetInfo(this.xsFacets, content, facetsPresent, facetsFixed);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                    if ((facetsPresent & currentFacet) != 0) {
                        objArr = new Object[PROCESSING_ALL_EL];
                        objArr[NOT_ALL_CONTEXT] = facet;
                        reportSchemaError("src-single-facet-value", objArr, content);
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else if (attrs[XSAttributeChecker.ATTIDX_VALUE] == null) {
                        if (content.getAttributeNodeNS(null, "value") == null) {
                            objArr = new Object[GROUP_REF_WITH_ALL];
                            objArr[NOT_ALL_CONTEXT] = content.getLocalName();
                            objArr[PROCESSING_ALL_EL] = SchemaSymbols.ATT_VALUE;
                            reportSchemaError("s4s-att-must-appear", objArr, content);
                        }
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else {
                        facetsPresent = (short) (facetsPresent | currentFacet);
                        if (((Boolean) attrs[XSAttributeChecker.ATTIDX_FIXED]).booleanValue()) {
                            facetsFixed = (short) (facetsFixed | currentFacet);
                        }
                        switch (currentFacet) {
                            case PROCESSING_ALL_EL /*1*/:
                                this.xsFacets.length = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case GROUP_REF_WITH_ALL /*2*/:
                                this.xsFacets.minLength = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case CHILD_OF_GROUP /*4*/:
                                this.xsFacets.maxLength = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case ConnectionResult.API_UNAVAILABLE /*16*/:
                                this.xsFacets.whiteSpace = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).shortValue();
                                break;
                            case XMLStringBuffer.DEFAULT_SIZE /*32*/:
                                this.xsFacets.maxInclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case XMLEntityManager.DEFAULT_XMLDECL_BUFFER_SIZE /*64*/:
                                this.xsFacets.maxExclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case TransportMediator.FLAG_KEY_MEDIA_NEXT /*128*/:
                                this.xsFacets.minExclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case NodeFilter.SHOW_DOCUMENT /*256*/:
                                this.xsFacets.minInclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE /*512*/:
                                this.xsFacets.totalDigits = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case NodeFilter.SHOW_DOCUMENT_FRAGMENT /*1024*/:
                                this.xsFacets.fractionDigits = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                        }
                        child = DOMUtil.getFirstChildElement(content);
                        XSAnnotationImpl annotation = null;
                        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            text = DOMUtil.getSyntheticAnnotation(content);
                            if (text != null) {
                                annotation = traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc);
                            }
                        } else {
                            annotation = traverseAnnotationDecl(child, attrs, false, schemaDoc);
                            child = DOMUtil.getNextSiblingElement(child);
                        }
                        switch (currentFacet) {
                            case PROCESSING_ALL_EL /*1*/:
                                this.xsFacets.lengthAnnotation = annotation;
                                break;
                            case GROUP_REF_WITH_ALL /*2*/:
                                this.xsFacets.minLengthAnnotation = annotation;
                                break;
                            case CHILD_OF_GROUP /*4*/:
                                this.xsFacets.maxLengthAnnotation = annotation;
                                break;
                            case ConnectionResult.API_UNAVAILABLE /*16*/:
                                this.xsFacets.whiteSpaceAnnotation = annotation;
                                break;
                            case XMLStringBuffer.DEFAULT_SIZE /*32*/:
                                this.xsFacets.maxInclusiveAnnotation = annotation;
                                break;
                            case XMLEntityManager.DEFAULT_XMLDECL_BUFFER_SIZE /*64*/:
                                this.xsFacets.maxExclusiveAnnotation = annotation;
                                break;
                            case TransportMediator.FLAG_KEY_MEDIA_NEXT /*128*/:
                                this.xsFacets.minExclusiveAnnotation = annotation;
                                break;
                            case NodeFilter.SHOW_DOCUMENT /*256*/:
                                this.xsFacets.minInclusiveAnnotation = annotation;
                                break;
                            case XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE /*512*/:
                                this.xsFacets.totalDigitsAnnotation = annotation;
                                break;
                            case NodeFilter.SHOW_DOCUMENT_FRAGMENT /*1024*/:
                                this.xsFacets.fractionDigitsAnnotation = annotation;
                                break;
                        }
                        if (child != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{facet, "(annotation?)", DOMUtil.getLocalName(child)}, child);
                        }
                    }
                }
            }
            this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        if (enumData != null) {
            facetsPresent = (short) (facetsPresent | XMLEntityManager.DEFAULT_BUFFER_SIZE);
            this.xsFacets.enumeration = enumData;
            this.xsFacets.enumNSDecls = enumNSDecls;
            this.xsFacets.enumAnnotations = enumAnnotations;
        }
        if (this.fPattern.length() != 0) {
            facetsPresent = (short) (facetsPresent | PROCESSING_ALL_GP);
            this.xsFacets.pattern = this.fPattern.toString();
            this.xsFacets.patternAnnotations = patternAnnotations;
        }
        this.fPattern.setLength(NOT_ALL_CONTEXT);
        return new FacetInfo(this.xsFacets, content, facetsPresent, facetsFixed);
    }

    private boolean containsQName(XSSimpleType type) {
        if (type.getVariety() == (short) 1) {
            short primitive = type.getPrimitiveKind();
            return primitive == (short) 18 || primitive == (short) 20;
        } else if (type.getVariety() == (short) 2) {
            return containsQName((XSSimpleType) type.getItemType());
        } else {
            if (type.getVariety() == (short) 3) {
                XSObjectList members = type.getMemberTypes();
                for (int i = NOT_ALL_CONTEXT; i < members.getLength(); i += PROCESSING_ALL_EL) {
                    if (containsQName((XSSimpleType) members.item(i))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    Element traverseAttrsAndAttrGrps(Element firstAttr, XSAttributeGroupDecl attrGrp, XSDocumentInfo schemaDoc, SchemaGrammar grammar, XSComplexTypeDecl enclosingCT) {
        Element child = firstAttr;
        while (child != null) {
            String code;
            String[] strArr;
            String childName = DOMUtil.getLocalName(child);
            short s;
            String idName;
            String name;
            if (!childName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                if (!childName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    break;
                }
                XSAttributeGroupDecl tempAttrGrp = this.fSchemaHandler.fAttributeGroupTraverser.traverseLocal(child, schemaDoc, grammar);
                if (tempAttrGrp != null) {
                    XSObjectList attrUseS = tempAttrGrp.getAttributeUses();
                    int attrCount = attrUseS.getLength();
                    for (int i = NOT_ALL_CONTEXT; i < attrCount; i += PROCESSING_ALL_EL) {
                        XSAttributeUseImpl oneAttrUse = (XSAttributeUseImpl) attrUseS.item(i);
                        s = oneAttrUse.fUse;
                        if (r0 == GROUP_REF_WITH_ALL) {
                            attrGrp.addAttributeUse(oneAttrUse);
                        } else {
                            Object otherUse = attrGrp.getAttributeUseNoProhibited(oneAttrUse.fAttrDecl.getNamespace(), oneAttrUse.fAttrDecl.getName());
                            if (otherUse == null) {
                                idName = attrGrp.addAttributeUse(oneAttrUse);
                                if (idName != null) {
                                    code = enclosingCT == null ? "ag-props-correct.3" : "ct-props-correct.5";
                                    name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                    strArr = new Object[3];
                                    strArr[PROCESSING_ALL_EL] = oneAttrUse.fAttrDecl.getName();
                                    strArr[GROUP_REF_WITH_ALL] = idName;
                                    reportSchemaError(code, strArr, child);
                                }
                            } else if (oneAttrUse != otherUse) {
                                code = enclosingCT == null ? "ag-props-correct.2" : "ct-props-correct.4";
                                strArr = new Object[GROUP_REF_WITH_ALL];
                                strArr[NOT_ALL_CONTEXT] = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                strArr[PROCESSING_ALL_EL] = oneAttrUse.fAttrDecl.getName();
                                reportSchemaError(code, strArr, child);
                            }
                        }
                    }
                    if (tempAttrGrp.fAttributeWC != null) {
                        if (attrGrp.fAttributeWC == null) {
                            attrGrp.fAttributeWC = tempAttrGrp.fAttributeWC;
                        } else {
                            attrGrp.fAttributeWC = attrGrp.fAttributeWC.performIntersectionWith(tempAttrGrp.fAttributeWC, attrGrp.fAttributeWC.fProcessContents);
                            if (attrGrp.fAttributeWC == null) {
                                code = enclosingCT == null ? "src-attribute_group.2" : "src-ct.4";
                                strArr = new Object[PROCESSING_ALL_EL];
                                strArr[NOT_ALL_CONTEXT] = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                reportSchemaError(code, strArr, child);
                            }
                        }
                    }
                }
            } else {
                XSAttributeUse tempAttrUse = this.fSchemaHandler.fAttributeTraverser.traverseLocal(child, schemaDoc, grammar, enclosingCT);
                if (tempAttrUse != null) {
                    s = tempAttrUse.fUse;
                    if (r0 == GROUP_REF_WITH_ALL) {
                        attrGrp.addAttributeUse(tempAttrUse);
                    } else {
                        XSAttributeUse otherUse2 = attrGrp.getAttributeUseNoProhibited(tempAttrUse.fAttrDecl.getNamespace(), tempAttrUse.fAttrDecl.getName());
                        if (otherUse2 == null) {
                            idName = attrGrp.addAttributeUse(tempAttrUse);
                            if (idName != null) {
                                code = enclosingCT == null ? "ag-props-correct.3" : "ct-props-correct.5";
                                name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                strArr = new Object[3];
                                strArr[PROCESSING_ALL_EL] = tempAttrUse.fAttrDecl.getName();
                                strArr[GROUP_REF_WITH_ALL] = idName;
                                reportSchemaError(code, strArr, child);
                            }
                        } else if (otherUse2 != tempAttrUse) {
                            code = enclosingCT == null ? "ag-props-correct.2" : "ct-props-correct.4";
                            strArr = new Object[GROUP_REF_WITH_ALL];
                            strArr[NOT_ALL_CONTEXT] = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                            strArr[PROCESSING_ALL_EL] = tempAttrUse.fAttrDecl.getName();
                            reportSchemaError(code, strArr, child);
                        }
                    }
                }
            }
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (child == null) {
            return child;
        }
        if (!DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
            return child;
        }
        XSWildcardDecl tempAttrWC = this.fSchemaHandler.fWildCardTraverser.traverseAnyAttribute(child, schemaDoc, grammar);
        if (attrGrp.fAttributeWC == null) {
            attrGrp.fAttributeWC = tempAttrWC;
        } else {
            attrGrp.fAttributeWC = tempAttrWC.performIntersectionWith(attrGrp.fAttributeWC, tempAttrWC.fProcessContents);
            if (attrGrp.fAttributeWC == null) {
                code = enclosingCT == null ? "src-attribute_group.2" : "src-ct.4";
                strArr = new Object[PROCESSING_ALL_EL];
                strArr[NOT_ALL_CONTEXT] = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                reportSchemaError(code, strArr, child);
            }
        }
        return DOMUtil.getNextSiblingElement(child);
    }

    void reportSchemaError(String key, Object[] args, Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }

    void checkNotationType(String refName, XSTypeDefinition typeDecl, Element elem) {
        if (typeDecl.getTypeCategory() == (short) 16 && ((XSSimpleType) typeDecl).getVariety() == (short) 1 && ((XSSimpleType) typeDecl).getPrimitiveKind() == (short) 20 && (((XSSimpleType) typeDecl).getDefinedFacets() & XMLEntityManager.DEFAULT_BUFFER_SIZE) == 0) {
            reportSchemaError("enumeration-required-notation", new Object[]{typeDecl.getName(), refName, DOMUtil.getLocalName(elem)}, elem);
        }
    }

    protected XSParticleDecl checkOccurrences(XSParticleDecl particle, String particleName, Element parent, int allContextFlags, long defaultVals) {
        int min = particle.fMinOccurs;
        int max = particle.fMaxOccurs;
        boolean defaultMin = (((long) (PROCESSING_ALL_EL << XSAttributeChecker.ATTIDX_MINOCCURS)) & defaultVals) != 0;
        boolean defaultMax = (((long) (PROCESSING_ALL_EL << XSAttributeChecker.ATTIDX_MAXOCCURS)) & defaultVals) != 0;
        boolean processingAllEl = (allContextFlags & PROCESSING_ALL_EL) != 0;
        boolean processingAllGP = (allContextFlags & PROCESSING_ALL_GP) != 0;
        boolean groupRefWithAll = (allContextFlags & GROUP_REF_WITH_ALL) != 0;
        if ((allContextFlags & CHILD_OF_GROUP) != 0) {
            Object[] args;
            if (!defaultMin) {
                args = new Object[GROUP_REF_WITH_ALL];
                args[NOT_ALL_CONTEXT] = particleName;
                args[PROCESSING_ALL_EL] = "minOccurs";
                reportSchemaError("s4s-att-not-allowed", args, parent);
                min = PROCESSING_ALL_EL;
            }
            if (!defaultMax) {
                args = new Object[GROUP_REF_WITH_ALL];
                args[NOT_ALL_CONTEXT] = particleName;
                args[PROCESSING_ALL_EL] = "maxOccurs";
                reportSchemaError("s4s-att-not-allowed", args, parent);
                max = PROCESSING_ALL_EL;
            }
        }
        if (min == 0 && max == 0) {
            particle.fType = (short) 0;
            return null;
        }
        if (processingAllEl) {
            if (max != PROCESSING_ALL_EL) {
                String str = "cos-all-limited.2";
                Object[] objArr = new Object[GROUP_REF_WITH_ALL];
                objArr[NOT_ALL_CONTEXT] = max == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max);
                objArr[PROCESSING_ALL_EL] = ((XSElementDecl) particle.fValue).getName();
                reportSchemaError(str, objArr, parent);
                max = PROCESSING_ALL_EL;
                if (min > PROCESSING_ALL_EL) {
                    min = PROCESSING_ALL_EL;
                }
            }
        } else if ((processingAllGP || groupRefWithAll) && max != PROCESSING_ALL_EL) {
            reportSchemaError("cos-all-limited.1.2", null, parent);
            if (min > PROCESSING_ALL_EL) {
                min = PROCESSING_ALL_EL;
            }
            max = PROCESSING_ALL_EL;
        }
        particle.fMinOccurs = min;
        particle.fMaxOccurs = max;
        return particle;
    }

    private static String processAttValue(String original) {
        int length = original.length();
        for (int i = NOT_ALL_CONTEXT; i < length; i += PROCESSING_ALL_EL) {
            char currChar = original.charAt(i);
            if (currChar == '\"' || currChar == '<' || currChar == '&' || currChar == '\t' || currChar == '\n' || currChar == CharUtils.CR) {
                return escapeAttValue(original, i);
            }
        }
        return original;
    }

    private static String escapeAttValue(String original, int from) {
        int length = original.length();
        StringBuffer newVal = new StringBuffer(length);
        newVal.append(original.substring(NOT_ALL_CONTEXT, from));
        for (int i = from; i < length; i += PROCESSING_ALL_EL) {
            char currChar = original.charAt(i);
            if (currChar == '\"') {
                newVal.append("&quot;");
            } else if (currChar == '<') {
                newVal.append("&lt;");
            } else if (currChar == '&') {
                newVal.append("&amp;");
            } else if (currChar == '\t') {
                newVal.append("&#x9;");
            } else if (currChar == '\n') {
                newVal.append("&#xA;");
            } else if (currChar == CharUtils.CR) {
                newVal.append("&#xD;");
            } else {
                newVal.append(currChar);
            }
        }
        return newVal.toString();
    }
}
