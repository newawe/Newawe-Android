package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.models.CMBuilder;
import mf.org.apache.xerces.impl.xs.models.XSCMValidator;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSParticle;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSWildcard;
import mf.org.w3c.dom.TypeInfo;

public class XSComplexTypeDecl implements XSComplexTypeDefinition, TypeInfo {
    private static final short CT_HAS_TYPE_ID = (short) 2;
    private static final short CT_IS_ABSTRACT = (short) 1;
    private static final short CT_IS_ANONYMOUS = (short) 4;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_LIST = 8;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_UNION = 4;
    XSObjectListImpl fAnnotations;
    XSAttributeGroupDecl fAttrGrp;
    XSTypeDefinition fBaseType;
    short fBlock;
    XSCMValidator fCMValidator;
    short fContentType;
    short fDerivedBy;
    short fFinal;
    short fMiscFlags;
    String fName;
    private XSNamespaceItem fNamespaceItem;
    XSParticleDecl fParticle;
    String fTargetNamespace;
    XSCMValidator fUPACMValidator;
    XSSimpleType fXSSimpleType;

    public XSComplexTypeDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fBaseType = null;
        this.fDerivedBy = CT_HAS_TYPE_ID;
        this.fFinal = (short) 0;
        this.fBlock = (short) 0;
        this.fMiscFlags = (short) 0;
        this.fAttrGrp = null;
        this.fContentType = (short) 0;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fCMValidator = null;
        this.fUPACMValidator = null;
        this.fAnnotations = null;
        this.fNamespaceItem = null;
    }

    public void setValues(String name, String targetNamespace, XSTypeDefinition baseType, short derivedBy, short schemaFinal, short block, short contentType, boolean isAbstract, XSAttributeGroupDecl attrGrp, XSSimpleType simpleType, XSParticleDecl particle, XSObjectListImpl annotations) {
        this.fTargetNamespace = targetNamespace;
        this.fBaseType = baseType;
        this.fDerivedBy = derivedBy;
        this.fFinal = schemaFinal;
        this.fBlock = block;
        this.fContentType = contentType;
        if (isAbstract) {
            this.fMiscFlags = (short) (this.fMiscFlags | DERIVATION_RESTRICTION);
        }
        this.fAttrGrp = attrGrp;
        this.fXSSimpleType = simpleType;
        this.fParticle = particle;
        this.fAnnotations = annotations;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public short getTypeCategory() {
        return (short) 15;
    }

    public String getTypeName() {
        return this.fName;
    }

    public short getFinalSet() {
        return this.fFinal;
    }

    public String getTargetNamespace() {
        return this.fTargetNamespace;
    }

    public boolean containsTypeID() {
        return (this.fMiscFlags & DERIVATION_EXTENSION) != 0;
    }

    public void setIsAbstractType() {
        this.fMiscFlags = (short) (this.fMiscFlags | DERIVATION_RESTRICTION);
    }

    public void setContainsTypeID() {
        this.fMiscFlags = (short) (this.fMiscFlags | DERIVATION_EXTENSION);
    }

    public void setIsAnonymous() {
        this.fMiscFlags = (short) (this.fMiscFlags | DERIVATION_UNION);
    }

    public XSCMValidator getContentModel(CMBuilder cmBuilder) {
        return getContentModel(cmBuilder, false);
    }

    public synchronized XSCMValidator getContentModel(CMBuilder cmBuilder, boolean forUPA) {
        XSCMValidator xSCMValidator;
        if (this.fCMValidator == null) {
            if (forUPA) {
                if (this.fUPACMValidator == null) {
                    this.fUPACMValidator = cmBuilder.getContentModel(this, true);
                    if (!(this.fUPACMValidator == null || this.fUPACMValidator.isCompactedForUPA())) {
                        this.fCMValidator = this.fUPACMValidator;
                    }
                }
                xSCMValidator = this.fUPACMValidator;
            } else {
                this.fCMValidator = cmBuilder.getContentModel(this, false);
            }
        }
        xSCMValidator = this.fCMValidator;
        return xSCMValidator;
    }

    public XSAttributeGroupDecl getAttrGrp() {
        return this.fAttrGrp;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        appendTypeInfo(str);
        return str.toString();
    }

    void appendTypeInfo(StringBuffer str) {
        String[] contentType = new String[DERIVATION_UNION];
        contentType[DERIVATION_ANY] = "EMPTY";
        contentType[DERIVATION_RESTRICTION] = "SIMPLE";
        contentType[DERIVATION_EXTENSION] = "ELEMENT";
        contentType[3] = "MIXED";
        String[] derivedBy = new String[]{"EMPTY", "EXTENSION", "RESTRICTION"};
        str.append("Complex type name='").append(this.fTargetNamespace).append(',').append(getTypeName()).append("', ");
        if (this.fBaseType != null) {
            str.append(" base type name='").append(this.fBaseType.getName()).append("', ");
        }
        str.append(" content type='").append(contentType[this.fContentType]).append("', ");
        str.append(" isAbstract='").append(getAbstract()).append("', ");
        str.append(" hasTypeId='").append(containsTypeID()).append("', ");
        str.append(" final='").append(this.fFinal).append("', ");
        str.append(" block='").append(this.fBlock).append("', ");
        if (this.fParticle != null) {
            str.append(" particle='").append(this.fParticle.toString()).append("', ");
        }
        str.append(" derivedBy='").append(derivedBy[this.fDerivedBy]).append("'. ");
    }

    public boolean derivedFromType(XSTypeDefinition ancestor, short derivationMethod) {
        if (ancestor == null) {
            return false;
        }
        if (ancestor == SchemaGrammar.fAnyType) {
            return true;
        }
        XSTypeDefinition type = this;
        while (type != ancestor && type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType) {
            type = type.getBaseType();
        }
        if (type == ancestor) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean derivedFrom(java.lang.String r5, java.lang.String r6, short r7) {
        /*
        r4 = this;
        r2 = 1;
        r1 = 0;
        if (r6 != 0) goto L_0x0005;
    L_0x0004:
        return r1;
    L_0x0005:
        if (r5 == 0) goto L_0x0019;
    L_0x0007:
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.URI_SCHEMAFORSCHEMA;
        r3 = r5.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x000f:
        r3 = "anyType";
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x0017:
        r1 = r2;
        goto L_0x0004;
    L_0x0019:
        r0 = r4;
    L_0x001a:
        r3 = r0.getName();
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x0038;
    L_0x0024:
        if (r5 != 0) goto L_0x002c;
    L_0x0026:
        r3 = r0.getNamespace();
        if (r3 == 0) goto L_0x0040;
    L_0x002c:
        if (r5 == 0) goto L_0x0038;
    L_0x002e:
        r3 = r0.getNamespace();
        r3 = r5.equals(r3);
        if (r3 != 0) goto L_0x0040;
    L_0x0038:
        r3 = mf.org.apache.xerces.impl.xs.SchemaGrammar.fAnySimpleType;
        if (r0 == r3) goto L_0x0040;
    L_0x003c:
        r3 = mf.org.apache.xerces.impl.xs.SchemaGrammar.fAnyType;
        if (r0 != r3) goto L_0x004a;
    L_0x0040:
        r3 = mf.org.apache.xerces.impl.xs.SchemaGrammar.fAnySimpleType;
        if (r0 == r3) goto L_0x0004;
    L_0x0044:
        r3 = mf.org.apache.xerces.impl.xs.SchemaGrammar.fAnyType;
        if (r0 == r3) goto L_0x0004;
    L_0x0048:
        r1 = r2;
        goto L_0x0004;
    L_0x004a:
        r0 = r0.getBaseType();
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.XSComplexTypeDecl.derivedFrom(java.lang.String, java.lang.String, short):boolean");
    }

    public boolean isDOMDerivedFrom(String ancestorNS, String ancestorName, int derivationMethod) {
        if (ancestorName == null) {
            return false;
        }
        if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE) && derivationMethod == DERIVATION_RESTRICTION && derivationMethod == DERIVATION_EXTENSION) {
            return true;
        }
        if ((derivationMethod & DERIVATION_RESTRICTION) != 0 && isDerivedByRestriction(ancestorNS, ancestorName, derivationMethod, this)) {
            return true;
        }
        if ((derivationMethod & DERIVATION_EXTENSION) != 0 && isDerivedByExtension(ancestorNS, ancestorName, derivationMethod, this)) {
            return true;
        }
        if (!((derivationMethod & DERIVATION_LIST) == 0 && (derivationMethod & DERIVATION_UNION) == 0) && (derivationMethod & DERIVATION_RESTRICTION) == 0 && (derivationMethod & DERIVATION_EXTENSION) == 0) {
            if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
            }
            if (!(this.fName.equals(SchemaSymbols.ATTVAL_ANYTYPE) && this.fTargetNamespace.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA))) {
                if (this.fBaseType != null && (this.fBaseType instanceof XSSimpleTypeDecl)) {
                    return ((XSSimpleTypeDecl) this.fBaseType).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
                }
                if (this.fBaseType != null && (this.fBaseType instanceof XSComplexTypeDecl)) {
                    return ((XSComplexTypeDecl) this.fBaseType).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
                }
            }
        }
        if ((derivationMethod & DERIVATION_EXTENSION) == 0 && (derivationMethod & DERIVATION_RESTRICTION) == 0 && (derivationMethod & DERIVATION_LIST) == 0 && (derivationMethod & DERIVATION_UNION) == 0) {
            return isDerivedByAny(ancestorNS, ancestorName, derivationMethod, this);
        }
        return false;
    }

    private boolean isDerivedByAny(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        boolean derivedFrom = false;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) {
                derivedFrom = true;
                break;
            } else if (isDerivedByRestriction(ancestorNS, ancestorName, derivationMethod, type) || !isDerivedByExtension(ancestorNS, ancestorName, derivationMethod, type)) {
                return true;
            } else {
                oldType = type;
                type = type.getBaseType();
            }
        }
        return derivedFrom;
    }

    private boolean isDerivedByRestriction(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE)) {
                return false;
            }
            if ((ancestorName.equals(type.getName()) && ancestorNS != null && ancestorNS.equals(type.getNamespace())) || (type.getNamespace() == null && ancestorNS == null)) {
                return true;
            }
            if (type instanceof XSSimpleTypeDecl) {
                if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                    ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
                }
                return ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
            } else if (((XSComplexTypeDecl) type).getDerivationMethod() != CT_HAS_TYPE_ID) {
                return false;
            } else {
                oldType = type;
                type = type.getBaseType();
            }
        }
        return false;
    }

    private boolean isDerivedByExtension(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        boolean extension = false;
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType && (ancestorNS == null || !ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE) || !SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(type.getNamespace()) || !SchemaSymbols.ATTVAL_ANYTYPE.equals(type.getName()))) {
            if (ancestorName.equals(type.getName())) {
                if (ancestorNS == null && type.getNamespace() == null) {
                    return extension;
                }
                if (ancestorNS != null && ancestorNS.equals(type.getNamespace())) {
                    return extension;
                }
            }
            if (type instanceof XSSimpleTypeDecl) {
                if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                    ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
                }
                if ((derivationMethod & DERIVATION_EXTENSION) != 0) {
                    return extension & ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod & DERIVATION_RESTRICTION);
                }
                return extension & ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
            }
            if (((XSComplexTypeDecl) type).getDerivationMethod() == CT_IS_ABSTRACT) {
                extension |= DERIVATION_RESTRICTION;
            }
            oldType = type;
            type = type.getBaseType();
        }
        return false;
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fBaseType = null;
        this.fDerivedBy = CT_HAS_TYPE_ID;
        this.fFinal = (short) 0;
        this.fBlock = (short) 0;
        this.fMiscFlags = (short) 0;
        this.fAttrGrp.reset();
        this.fContentType = (short) 0;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fCMValidator = null;
        this.fUPACMValidator = null;
        if (this.fAnnotations != null) {
            this.fAnnotations.clearXSObjectList();
        }
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 3;
    }

    public String getName() {
        return getAnonymous() ? null : this.fName;
    }

    public boolean getAnonymous() {
        return (this.fMiscFlags & DERIVATION_UNION) != 0;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSTypeDefinition getBaseType() {
        return this.fBaseType;
    }

    public short getDerivationMethod() {
        return this.fDerivedBy;
    }

    public boolean isFinal(short derivation) {
        return (this.fFinal & derivation) != 0;
    }

    public short getFinal() {
        return this.fFinal;
    }

    public boolean getAbstract() {
        return (this.fMiscFlags & DERIVATION_RESTRICTION) != 0;
    }

    public XSObjectList getAttributeUses() {
        return this.fAttrGrp.getAttributeUses();
    }

    public XSWildcard getAttributeWildcard() {
        return this.fAttrGrp.getAttributeWildcard();
    }

    public short getContentType() {
        return this.fContentType;
    }

    public XSSimpleTypeDefinition getSimpleType() {
        return this.fXSSimpleType;
    }

    public XSParticle getParticle() {
        return this.fParticle;
    }

    public boolean isProhibitedSubstitution(short prohibited) {
        return (this.fBlock & prohibited) != 0;
    }

    public short getProhibitedSubstitutions() {
        return this.fBlock;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }

    public XSAttributeUse getAttributeUse(String namespace, String name) {
        return this.fAttrGrp.getAttributeUse(namespace, name);
    }

    public String getTypeNamespace() {
        return getNamespace();
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }
}
