package mf.org.apache.xerces.impl.dtd.models;

import mf.org.apache.xerces.xni.QName;

public class DFAContentModel implements ContentModelValidator {
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private static String fEOCString;
    private static String fEpsilonString;
    private int fEOCPos;
    private QName[] fElemMap;
    private int fElemMapSize;
    private int[] fElemMapType;
    private boolean fEmptyContentIsValid;
    private boolean[] fFinalStateFlags;
    private CMStateSet[] fFollowList;
    private CMNode fHeadNode;
    private int fLeafCount;
    private CMLeaf[] fLeafList;
    private int[] fLeafListType;
    private boolean fMixed;
    private final QName fQName;
    private int[][] fTransTable;
    private int fTransTableSize;

    static {
        fEpsilonString = "<<CMNODE_EPSILON>>";
        fEOCString = "<<CMNODE_EOC>>";
        fEpsilonString = fEpsilonString.intern();
        fEOCString = fEOCString.intern();
    }

    public DFAContentModel(CMNode syntaxTree, int leafCount, boolean mixed) {
        this.fElemMap = null;
        this.fElemMapType = null;
        this.fElemMapSize = 0;
        this.fEOCPos = 0;
        this.fFinalStateFlags = null;
        this.fFollowList = null;
        this.fHeadNode = null;
        this.fLeafCount = 0;
        this.fLeafList = null;
        this.fLeafListType = null;
        this.fTransTable = null;
        this.fTransTableSize = 0;
        this.fEmptyContentIsValid = false;
        this.fQName = new QName();
        this.fLeafCount = leafCount;
        this.fMixed = mixed;
        buildDFA(syntaxTree);
    }

    public int validate(QName[] children, int offset, int length) {
        if (length != 0) {
            int curState = 0;
            for (int childIndex = 0; childIndex < length; childIndex++) {
                QName curElem = children[offset + childIndex];
                if (!this.fMixed || curElem.localpart != null) {
                    int elemIndex = 0;
                    while (elemIndex < this.fElemMapSize) {
                        int type = this.fElemMapType[elemIndex] & 15;
                        if (type != 0) {
                            if (type != 6) {
                                if (type != 8) {
                                    if (type == 7 && this.fElemMap[elemIndex].uri != curElem.uri) {
                                        break;
                                    }
                                } else if (curElem.uri == null) {
                                    break;
                                }
                            } else {
                                String uri = this.fElemMap[elemIndex].uri;
                                if (uri == null) {
                                    break;
                                } else if (uri == curElem.uri) {
                                    break;
                                }
                            }
                        } else if (this.fElemMap[elemIndex].rawname == curElem.rawname) {
                            break;
                        }
                        elemIndex++;
                    }
                    if (elemIndex == this.fElemMapSize) {
                        return childIndex;
                    }
                    curState = this.fTransTable[curState][elemIndex];
                    if (curState == -1) {
                        return childIndex;
                    }
                }
            }
            if (this.fFinalStateFlags[curState]) {
                return -1;
            }
            return length;
        } else if (this.fEmptyContentIsValid) {
            return -1;
        } else {
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildDFA(mf.org.apache.xerces.impl.dtd.models.CMNode r37) {
        /*
        r36 = this;
        r0 = r36;
        r0 = r0.fQName;
        r31 = r0;
        r32 = 0;
        r33 = fEOCString;
        r34 = fEOCString;
        r35 = 0;
        r31.setValues(r32, r33, r34, r35);
        r20 = new mf.org.apache.xerces.impl.dtd.models.CMLeaf;
        r0 = r36;
        r0 = r0.fQName;
        r31 = r0;
        r0 = r20;
        r1 = r31;
        r0.<init>(r1);
        r31 = new mf.org.apache.xerces.impl.dtd.models.CMBinOp;
        r32 = 5;
        r0 = r31;
        r1 = r32;
        r2 = r37;
        r3 = r20;
        r0.<init>(r1, r2, r3);
        r0 = r31;
        r1 = r36;
        r1.fHeadNode = r0;
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fEOCPos = r0;
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r32 = r31 + 1;
        r0 = r32;
        r1 = r36;
        r1.fLeafCount = r0;
        r0 = r20;
        r1 = r31;
        r0.setPosition(r1);
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r0 = new mf.org.apache.xerces.impl.dtd.models.CMLeaf[r0];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fLeafList = r0;
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r0 = new int[r0];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fLeafListType = r0;
        r0 = r36;
        r0 = r0.fHeadNode;
        r31 = r0;
        r32 = 0;
        r0 = r36;
        r1 = r31;
        r2 = r32;
        r0.postTreeBuildInit(r1, r2);
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r0 = new mf.org.apache.xerces.impl.dtd.models.CMStateSet[r0];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fFollowList = r0;
        r12 = 0;
    L_0x009e:
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        if (r12 < r0) goto L_0x0182;
    L_0x00a8:
        r0 = r36;
        r0 = r0.fHeadNode;
        r31 = r0;
        r0 = r36;
        r1 = r31;
        r0.calcFollowList(r1);
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r0 = new mf.org.apache.xerces.xni.QName[r0];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fElemMap = r0;
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        r0 = new int[r0];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fElemMapType = r0;
        r31 = 0;
        r0 = r31;
        r1 = r36;
        r1.fElemMapSize = r0;
        r21 = 0;
    L_0x00e3:
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r21;
        r1 = r31;
        if (r0 < r1) goto L_0x0199;
    L_0x00ef:
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r36;
        r0 = r0.fElemMapSize;
        r32 = r0;
        r31 = r31 + r32;
        r0 = r31;
        r8 = new int[r0];
        r9 = 0;
        r6 = 0;
    L_0x0103:
        r0 = r36;
        r0 = r0.fElemMapSize;
        r31 = r0;
        r0 = r31;
        if (r6 < r0) goto L_0x021d;
    L_0x010d:
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r4 = r31 * 4;
        r0 = new mf.org.apache.xerces.impl.dtd.models.CMStateSet[r4];
        r28 = r0;
        r0 = new boolean[r4];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fFinalStateFlags = r0;
        r0 = new int[r4][];
        r31 = r0;
        r0 = r31;
        r1 = r36;
        r1.fTransTable = r0;
        r0 = r36;
        r0 = r0.fHeadNode;
        r31 = r0;
        r22 = r31.firstPos();
        r30 = 0;
        r5 = 0;
        r0 = r36;
        r0 = r0.fTransTable;
        r31 = r0;
        r32 = r36.makeDefStateList();
        r31[r5] = r32;
        r28[r5] = r22;
        r5 = r5 + 1;
        r27 = new java.util.HashMap;
        r27.<init>();
    L_0x014f:
        r0 = r30;
        if (r0 < r5) goto L_0x025d;
    L_0x0153:
        r0 = r36;
        r0 = r0.fHeadNode;
        r31 = r0;
        r31 = (mf.org.apache.xerces.impl.dtd.models.CMBinOp) r31;
        r31 = r31.getLeft();
        r31 = r31.isNullable();
        r0 = r31;
        r1 = r36;
        r1.fEmptyContentIsValid = r0;
        r31 = 0;
        r0 = r31;
        r1 = r36;
        r1.fHeadNode = r0;
        r31 = 0;
        r0 = r31;
        r1 = r36;
        r1.fLeafList = r0;
        r31 = 0;
        r0 = r31;
        r1 = r36;
        r1.fFollowList = r0;
        return;
    L_0x0182:
        r0 = r36;
        r0 = r0.fFollowList;
        r31 = r0;
        r32 = new mf.org.apache.xerces.impl.dtd.models.CMStateSet;
        r0 = r36;
        r0 = r0.fLeafCount;
        r33 = r0;
        r32.<init>(r33);
        r31[r12] = r32;
        r12 = r12 + 1;
        goto L_0x009e;
    L_0x0199:
        r0 = r36;
        r0 = r0.fElemMap;
        r31 = r0;
        r32 = new mf.org.apache.xerces.xni.QName;
        r32.<init>();
        r31[r21] = r32;
        r0 = r36;
        r0 = r0.fLeafList;
        r31 = r0;
        r31 = r31[r21];
        r7 = r31.getElement();
        r11 = 0;
    L_0x01b3:
        r0 = r36;
        r0 = r0.fElemMapSize;
        r31 = r0;
        r0 = r31;
        if (r11 < r0) goto L_0x0202;
    L_0x01bd:
        r0 = r36;
        r0 = r0.fElemMapSize;
        r31 = r0;
        r0 = r31;
        if (r11 != r0) goto L_0x01fe;
    L_0x01c7:
        r0 = r36;
        r0 = r0.fElemMap;
        r31 = r0;
        r0 = r36;
        r0 = r0.fElemMapSize;
        r32 = r0;
        r31 = r31[r32];
        r0 = r31;
        r0.setValues(r7);
        r0 = r36;
        r0 = r0.fElemMapType;
        r31 = r0;
        r0 = r36;
        r0 = r0.fElemMapSize;
        r32 = r0;
        r0 = r36;
        r0 = r0.fLeafListType;
        r33 = r0;
        r33 = r33[r21];
        r31[r32] = r33;
        r0 = r36;
        r0 = r0.fElemMapSize;
        r31 = r0;
        r31 = r31 + 1;
        r0 = r31;
        r1 = r36;
        r1.fElemMapSize = r0;
    L_0x01fe:
        r21 = r21 + 1;
        goto L_0x00e3;
    L_0x0202:
        r0 = r36;
        r0 = r0.fElemMap;
        r31 = r0;
        r31 = r31[r11];
        r0 = r31;
        r0 = r0.rawname;
        r31 = r0;
        r0 = r7.rawname;
        r32 = r0;
        r0 = r31;
        r1 = r32;
        if (r0 == r1) goto L_0x01bd;
    L_0x021a:
        r11 = r11 + 1;
        goto L_0x01b3;
    L_0x021d:
        r14 = 0;
    L_0x021e:
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r31;
        if (r14 < r0) goto L_0x0233;
    L_0x0228:
        r10 = r9 + 1;
        r31 = -1;
        r8[r9] = r31;
        r6 = r6 + 1;
        r9 = r10;
        goto L_0x0103;
    L_0x0233:
        r0 = r36;
        r0 = r0.fLeafList;
        r31 = r0;
        r31 = r31[r14];
        r13 = r31.getElement();
        r0 = r36;
        r0 = r0.fElemMap;
        r31 = r0;
        r7 = r31[r6];
        r0 = r13.rawname;
        r31 = r0;
        r0 = r7.rawname;
        r32 = r0;
        r0 = r31;
        r1 = r32;
        if (r0 != r1) goto L_0x025a;
    L_0x0255:
        r10 = r9 + 1;
        r8[r9] = r14;
        r9 = r10;
    L_0x025a:
        r14 = r14 + 1;
        goto L_0x021e;
    L_0x025d:
        r22 = r28[r30];
        r0 = r36;
        r0 = r0.fTransTable;
        r31 = r0;
        r29 = r31[r30];
        r0 = r36;
        r0 = r0.fFinalStateFlags;
        r31 = r0;
        r0 = r36;
        r0 = r0.fEOCPos;
        r32 = r0;
        r0 = r22;
        r1 = r32;
        r32 = r0.getBit(r1);
        r31[r30] = r32;
        r30 = r30 + 1;
        r16 = 0;
        r23 = 0;
        r6 = 0;
    L_0x0284:
        r0 = r36;
        r0 = r0.fElemMapSize;
        r31 = r0;
        r0 = r31;
        if (r6 >= r0) goto L_0x014f;
    L_0x028e:
        if (r16 != 0) goto L_0x034e;
    L_0x0290:
        r16 = new mf.org.apache.xerces.impl.dtd.models.CMStateSet;
        r0 = r36;
        r0 = r0.fLeafCount;
        r31 = r0;
        r0 = r16;
        r1 = r31;
        r0.<init>(r1);
    L_0x029f:
        r24 = r23 + 1;
        r14 = r8[r23];
    L_0x02a3:
        r31 = -1;
        r0 = r31;
        if (r14 != r0) goto L_0x0353;
    L_0x02a9:
        r31 = r16.isEmpty();
        if (r31 != 0) goto L_0x0348;
    L_0x02af:
        r0 = r27;
        r1 = r16;
        r26 = r0.get(r1);
        r26 = (java.lang.Integer) r26;
        if (r26 != 0) goto L_0x0372;
    L_0x02bb:
        r25 = r5;
    L_0x02bd:
        r0 = r25;
        if (r0 != r5) goto L_0x02e3;
    L_0x02c1:
        r28[r5] = r16;
        r0 = r36;
        r0 = r0.fTransTable;
        r31 = r0;
        r32 = r36.makeDefStateList();
        r31[r5] = r32;
        r31 = new java.lang.Integer;
        r0 = r31;
        r0.<init>(r5);
        r0 = r27;
        r1 = r16;
        r2 = r31;
        r0.put(r1, r2);
        r5 = r5 + 1;
        r16 = 0;
    L_0x02e3:
        r29[r6] = r25;
        if (r5 != r4) goto L_0x0348;
    L_0x02e7:
        r0 = (double) r4;
        r32 = r0;
        r34 = 4609434218613702656; // 0x3ff8000000000000 float:0.0 double:1.5;
        r32 = r32 * r34;
        r0 = r32;
        r0 = (int) r0;
        r17 = r0;
        r0 = r17;
        r0 = new mf.org.apache.xerces.impl.dtd.models.CMStateSet[r0];
        r18 = r0;
        r0 = r17;
        r15 = new boolean[r0];
        r0 = r17;
        r0 = new int[r0][];
        r19 = r0;
        r31 = 0;
        r32 = 0;
        r0 = r28;
        r1 = r31;
        r2 = r18;
        r3 = r32;
        java.lang.System.arraycopy(r0, r1, r2, r3, r4);
        r0 = r36;
        r0 = r0.fFinalStateFlags;
        r31 = r0;
        r32 = 0;
        r33 = 0;
        r0 = r31;
        r1 = r32;
        r2 = r33;
        java.lang.System.arraycopy(r0, r1, r15, r2, r4);
        r0 = r36;
        r0 = r0.fTransTable;
        r31 = r0;
        r32 = 0;
        r33 = 0;
        r0 = r31;
        r1 = r32;
        r2 = r19;
        r3 = r33;
        java.lang.System.arraycopy(r0, r1, r2, r3, r4);
        r4 = r17;
        r28 = r18;
        r0 = r36;
        r0.fFinalStateFlags = r15;
        r0 = r19;
        r1 = r36;
        r1.fTransTable = r0;
    L_0x0348:
        r6 = r6 + 1;
        r23 = r24;
        goto L_0x0284;
    L_0x034e:
        r16.zeroBits();
        goto L_0x029f;
    L_0x0353:
        r0 = r22;
        r31 = r0.getBit(r14);
        if (r31 == 0) goto L_0x036a;
    L_0x035b:
        r0 = r36;
        r0 = r0.fFollowList;
        r31 = r0;
        r31 = r31[r14];
        r0 = r16;
        r1 = r31;
        r0.union(r1);
    L_0x036a:
        r23 = r24 + 1;
        r14 = r8[r24];
        r24 = r23;
        goto L_0x02a3;
    L_0x0372:
        r25 = r26.intValue();
        goto L_0x02bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dtd.models.DFAContentModel.buildDFA(mf.org.apache.xerces.impl.dtd.models.CMNode):void");
    }

    private void calcFollowList(CMNode nodeCur) {
        if (nodeCur.type() == 4) {
            calcFollowList(((CMBinOp) nodeCur).getLeft());
            calcFollowList(((CMBinOp) nodeCur).getRight());
        } else if (nodeCur.type() == 5) {
            calcFollowList(((CMBinOp) nodeCur).getLeft());
            calcFollowList(((CMBinOp) nodeCur).getRight());
            last = ((CMBinOp) nodeCur).getLeft().lastPos();
            first = ((CMBinOp) nodeCur).getRight().firstPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 2 || nodeCur.type() == 3) {
            calcFollowList(((CMUniOp) nodeCur).getChild());
            first = nodeCur.firstPos();
            last = nodeCur.lastPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 1) {
            calcFollowList(((CMUniOp) nodeCur).getChild());
        }
    }

    private void dumpTree(CMNode nodeCur, int level) {
        for (int index = 0; index < level; index++) {
            System.out.print("   ");
        }
        int type = nodeCur.type();
        if (type == 4 || type == 5) {
            if (type == 4) {
                System.out.print("Choice Node ");
            } else {
                System.out.print("Seq Node ");
            }
            if (nodeCur.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
            dumpTree(((CMBinOp) nodeCur).getLeft(), level + 1);
            dumpTree(((CMBinOp) nodeCur).getRight(), level + 1);
        } else if (nodeCur.type() == 2) {
            System.out.print("Rep Node ");
            if (nodeCur.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
            dumpTree(((CMUniOp) nodeCur).getChild(), level + 1);
        } else if (nodeCur.type() == 0) {
            System.out.print("Leaf: (pos=" + ((CMLeaf) nodeCur).getPosition() + "), " + ((CMLeaf) nodeCur).getElement() + "(elemIndex=" + ((CMLeaf) nodeCur).getElement() + ") ");
            if (nodeCur.isNullable()) {
                System.out.print(" Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
        } else {
            throw new RuntimeException("ImplementationMessages.VAL_NIICM");
        }
    }

    private int[] makeDefStateList() {
        int[] retArray = new int[this.fElemMapSize];
        for (int index = 0; index < this.fElemMapSize; index++) {
            retArray[index] = -1;
        }
        return retArray;
    }

    private int postTreeBuildInit(CMNode nodeCur, int curIndex) {
        nodeCur.setMaxStates(this.fLeafCount);
        if ((nodeCur.type() & 15) == 6 || (nodeCur.type() & 15) == 8 || (nodeCur.type() & 15) == 7) {
            this.fLeafList[curIndex] = new CMLeaf(new QName(null, null, null, ((CMAny) nodeCur).getURI()), ((CMAny) nodeCur).getPosition());
            this.fLeafListType[curIndex] = nodeCur.type();
            return curIndex + 1;
        } else if (nodeCur.type() == 4 || nodeCur.type() == 5) {
            return postTreeBuildInit(((CMBinOp) nodeCur).getRight(), postTreeBuildInit(((CMBinOp) nodeCur).getLeft(), curIndex));
        } else if (nodeCur.type() == 2 || nodeCur.type() == 3 || nodeCur.type() == 1) {
            return postTreeBuildInit(((CMUniOp) nodeCur).getChild(), curIndex);
        } else {
            if (nodeCur.type() != 0) {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM: type=" + nodeCur.type());
            } else if (((CMLeaf) nodeCur).getElement().localpart == fEpsilonString) {
                return curIndex;
            } else {
                this.fLeafList[curIndex] = (CMLeaf) nodeCur;
                this.fLeafListType[curIndex] = 0;
                return curIndex + 1;
            }
        }
    }
}
