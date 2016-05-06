package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;

public final class XSDeclarationPool {
    private static final int CHUNK_MASK = 255;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private SchemaDVFactoryImpl dvFactory;
    private XSAttributeDecl[][] fAttrDecl;
    private int fAttrDeclIndex;
    private XSAttributeUseImpl[][] fAttributeUse;
    private int fAttributeUseIndex;
    private XSComplexTypeDecl[][] fCTDecl;
    private int fCTDeclIndex;
    private XSElementDecl[][] fElementDecl;
    private int fElementDeclIndex;
    private XSModelGroupImpl[][] fModelGroup;
    private int fModelGroupIndex;
    private XSParticleDecl[][] fParticleDecl;
    private int fParticleDeclIndex;
    private XSSimpleTypeDecl[][] fSTDecl;
    private int fSTDeclIndex;

    public XSDeclarationPool() {
        this.fElementDecl = new XSElementDecl[INITIAL_CHUNK_COUNT][];
        this.fElementDeclIndex = 0;
        this.fParticleDecl = new XSParticleDecl[INITIAL_CHUNK_COUNT][];
        this.fParticleDeclIndex = 0;
        this.fModelGroup = new XSModelGroupImpl[INITIAL_CHUNK_COUNT][];
        this.fModelGroupIndex = 0;
        this.fAttrDecl = new XSAttributeDecl[INITIAL_CHUNK_COUNT][];
        this.fAttrDeclIndex = 0;
        this.fCTDecl = new XSComplexTypeDecl[INITIAL_CHUNK_COUNT][];
        this.fCTDeclIndex = 0;
        this.fSTDecl = new XSSimpleTypeDecl[INITIAL_CHUNK_COUNT][];
        this.fSTDeclIndex = 0;
        this.fAttributeUse = new XSAttributeUseImpl[INITIAL_CHUNK_COUNT][];
        this.fAttributeUseIndex = 0;
    }

    public void setDVFactory(SchemaDVFactoryImpl dvFactory) {
        this.dvFactory = dvFactory;
    }

    public final XSElementDecl getElementDecl() {
        int chunk = this.fElementDeclIndex >> CHUNK_SHIFT;
        int index = this.fElementDeclIndex & CHUNK_MASK;
        ensureElementDeclCapacity(chunk);
        if (this.fElementDecl[chunk][index] == null) {
            this.fElementDecl[chunk][index] = new XSElementDecl();
        } else {
            this.fElementDecl[chunk][index].reset();
        }
        this.fElementDeclIndex++;
        return this.fElementDecl[chunk][index];
    }

    public final XSAttributeDecl getAttributeDecl() {
        int chunk = this.fAttrDeclIndex >> CHUNK_SHIFT;
        int index = this.fAttrDeclIndex & CHUNK_MASK;
        ensureAttrDeclCapacity(chunk);
        if (this.fAttrDecl[chunk][index] == null) {
            this.fAttrDecl[chunk][index] = new XSAttributeDecl();
        } else {
            this.fAttrDecl[chunk][index].reset();
        }
        this.fAttrDeclIndex++;
        return this.fAttrDecl[chunk][index];
    }

    public final XSAttributeUseImpl getAttributeUse() {
        int chunk = this.fAttributeUseIndex >> CHUNK_SHIFT;
        int index = this.fAttributeUseIndex & CHUNK_MASK;
        ensureAttributeUseCapacity(chunk);
        if (this.fAttributeUse[chunk][index] == null) {
            this.fAttributeUse[chunk][index] = new XSAttributeUseImpl();
        } else {
            this.fAttributeUse[chunk][index].reset();
        }
        this.fAttributeUseIndex++;
        return this.fAttributeUse[chunk][index];
    }

    public final XSComplexTypeDecl getComplexTypeDecl() {
        int chunk = this.fCTDeclIndex >> CHUNK_SHIFT;
        int index = this.fCTDeclIndex & CHUNK_MASK;
        ensureCTDeclCapacity(chunk);
        if (this.fCTDecl[chunk][index] == null) {
            this.fCTDecl[chunk][index] = new XSComplexTypeDecl();
        } else {
            this.fCTDecl[chunk][index].reset();
        }
        this.fCTDeclIndex++;
        return this.fCTDecl[chunk][index];
    }

    public final XSSimpleTypeDecl getSimpleTypeDecl() {
        int chunk = this.fSTDeclIndex >> CHUNK_SHIFT;
        int index = this.fSTDeclIndex & CHUNK_MASK;
        ensureSTDeclCapacity(chunk);
        if (this.fSTDecl[chunk][index] == null) {
            this.fSTDecl[chunk][index] = this.dvFactory.newXSSimpleTypeDecl();
        } else {
            this.fSTDecl[chunk][index].reset();
        }
        this.fSTDeclIndex++;
        return this.fSTDecl[chunk][index];
    }

    public final XSParticleDecl getParticleDecl() {
        int chunk = this.fParticleDeclIndex >> CHUNK_SHIFT;
        int index = this.fParticleDeclIndex & CHUNK_MASK;
        ensureParticleDeclCapacity(chunk);
        if (this.fParticleDecl[chunk][index] == null) {
            this.fParticleDecl[chunk][index] = new XSParticleDecl();
        } else {
            this.fParticleDecl[chunk][index].reset();
        }
        this.fParticleDeclIndex++;
        return this.fParticleDecl[chunk][index];
    }

    public final XSModelGroupImpl getModelGroup() {
        int chunk = this.fModelGroupIndex >> CHUNK_SHIFT;
        int index = this.fModelGroupIndex & CHUNK_MASK;
        ensureModelGroupCapacity(chunk);
        if (this.fModelGroup[chunk][index] == null) {
            this.fModelGroup[chunk][index] = new XSModelGroupImpl();
        } else {
            this.fModelGroup[chunk][index].reset();
        }
        this.fModelGroupIndex++;
        return this.fModelGroup[chunk][index];
    }

    private boolean ensureElementDeclCapacity(int chunk) {
        if (chunk >= this.fElementDecl.length) {
            this.fElementDecl = resize(this.fElementDecl, this.fElementDecl.length * 2);
        } else if (this.fElementDecl[chunk] != null) {
            return false;
        }
        this.fElementDecl[chunk] = new XSElementDecl[CHUNK_SIZE];
        return true;
    }

    private static XSElementDecl[][] resize(XSElementDecl[][] array, int newsize) {
        XSElementDecl[][] newarray = new XSElementDecl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private boolean ensureParticleDeclCapacity(int chunk) {
        if (chunk >= this.fParticleDecl.length) {
            this.fParticleDecl = resize(this.fParticleDecl, this.fParticleDecl.length * 2);
        } else if (this.fParticleDecl[chunk] != null) {
            return false;
        }
        this.fParticleDecl[chunk] = new XSParticleDecl[CHUNK_SIZE];
        return true;
    }

    private boolean ensureModelGroupCapacity(int chunk) {
        if (chunk >= this.fModelGroup.length) {
            this.fModelGroup = resize(this.fModelGroup, this.fModelGroup.length * 2);
        } else if (this.fModelGroup[chunk] != null) {
            return false;
        }
        this.fModelGroup[chunk] = new XSModelGroupImpl[CHUNK_SIZE];
        return true;
    }

    private static XSParticleDecl[][] resize(XSParticleDecl[][] array, int newsize) {
        XSParticleDecl[][] newarray = new XSParticleDecl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private static XSModelGroupImpl[][] resize(XSModelGroupImpl[][] array, int newsize) {
        XSModelGroupImpl[][] newarray = new XSModelGroupImpl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private boolean ensureAttrDeclCapacity(int chunk) {
        if (chunk >= this.fAttrDecl.length) {
            this.fAttrDecl = resize(this.fAttrDecl, this.fAttrDecl.length * 2);
        } else if (this.fAttrDecl[chunk] != null) {
            return false;
        }
        this.fAttrDecl[chunk] = new XSAttributeDecl[CHUNK_SIZE];
        return true;
    }

    private static XSAttributeDecl[][] resize(XSAttributeDecl[][] array, int newsize) {
        XSAttributeDecl[][] newarray = new XSAttributeDecl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private boolean ensureAttributeUseCapacity(int chunk) {
        if (chunk >= this.fAttributeUse.length) {
            this.fAttributeUse = resize(this.fAttributeUse, this.fAttributeUse.length * 2);
        } else if (this.fAttributeUse[chunk] != null) {
            return false;
        }
        this.fAttributeUse[chunk] = new XSAttributeUseImpl[CHUNK_SIZE];
        return true;
    }

    private static XSAttributeUseImpl[][] resize(XSAttributeUseImpl[][] array, int newsize) {
        XSAttributeUseImpl[][] newarray = new XSAttributeUseImpl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private boolean ensureSTDeclCapacity(int chunk) {
        if (chunk >= this.fSTDecl.length) {
            this.fSTDecl = resize(this.fSTDecl, this.fSTDecl.length * 2);
        } else if (this.fSTDecl[chunk] != null) {
            return false;
        }
        this.fSTDecl[chunk] = new XSSimpleTypeDecl[CHUNK_SIZE];
        return true;
    }

    private static XSSimpleTypeDecl[][] resize(XSSimpleTypeDecl[][] array, int newsize) {
        XSSimpleTypeDecl[][] newarray = new XSSimpleTypeDecl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    private boolean ensureCTDeclCapacity(int chunk) {
        if (chunk >= this.fCTDecl.length) {
            this.fCTDecl = resize(this.fCTDecl, this.fCTDecl.length * 2);
        } else if (this.fCTDecl[chunk] != null) {
            return false;
        }
        this.fCTDecl[chunk] = new XSComplexTypeDecl[CHUNK_SIZE];
        return true;
    }

    private static XSComplexTypeDecl[][] resize(XSComplexTypeDecl[][] array, int newsize) {
        XSComplexTypeDecl[][] newarray = new XSComplexTypeDecl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }

    public void reset() {
        this.fElementDeclIndex = 0;
        this.fParticleDeclIndex = 0;
        this.fModelGroupIndex = 0;
        this.fSTDeclIndex = 0;
        this.fCTDeclIndex = 0;
        this.fAttrDeclIndex = 0;
        this.fAttributeUseIndex = 0;
    }
}
