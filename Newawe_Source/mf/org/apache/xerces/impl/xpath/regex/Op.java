package mf.org.apache.xerces.impl.xpath.regex;

import java.util.Vector;

class Op {
    static final int ANCHOR = 5;
    static final int BACKREFERENCE = 16;
    static final int CAPTURE = 15;
    static final int CHAR = 1;
    static final int CLOSURE = 7;
    static final int CONDITION = 26;
    static final boolean COUNT = false;
    static final int DOT = 0;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIER = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    static final int NONGREEDYCLOSURE = 8;
    static final int NONGREEDYQUESTION = 10;
    static final int NRANGE = 4;
    static final int QUESTION = 9;
    static final int RANGE = 3;
    static final int STRING = 6;
    static final int UNION = 11;
    static int nofinstances;
    Op next;
    final int type;

    static class CharOp extends Op {
        final int charData;

        CharOp(int type, int data) {
            super(type);
            this.charData = data;
        }

        int getData() {
            return this.charData;
        }
    }

    static class ChildOp extends Op {
        Op child;

        ChildOp(int type) {
            super(type);
        }

        void setChild(Op child) {
            this.child = child;
        }

        Op getChild() {
            return this.child;
        }
    }

    static class ConditionOp extends Op {
        final Op condition;
        final Op no;
        final int refNumber;
        final Op yes;

        ConditionOp(int type, int refno, Op conditionflow, Op yesflow, Op noflow) {
            super(type);
            this.refNumber = refno;
            this.condition = conditionflow;
            this.yes = yesflow;
            this.no = noflow;
        }
    }

    static class RangeOp extends Op {
        final Token tok;

        RangeOp(int type, Token tok) {
            super(type);
            this.tok = tok;
        }

        RangeToken getToken() {
            return (RangeToken) this.tok;
        }
    }

    static class StringOp extends Op {
        final String string;

        StringOp(int type, String literal) {
            super(type);
            this.string = literal;
        }

        String getString() {
            return this.string;
        }
    }

    static class UnionOp extends Op {
        final Vector branches;

        UnionOp(int type, int size) {
            super(type);
            this.branches = new Vector(size);
        }

        void addElement(Op op) {
            this.branches.addElement(op);
        }

        int size() {
            return this.branches.size();
        }

        Op elementAt(int index) {
            return (Op) this.branches.elementAt(index);
        }
    }

    static class ModifierOp extends ChildOp {
        final int v1;
        final int v2;

        ModifierOp(int type, int v1, int v2) {
            super(type);
            this.v1 = v1;
            this.v2 = v2;
        }

        int getData() {
            return this.v1;
        }

        int getData2() {
            return this.v2;
        }
    }

    static {
        nofinstances = DOT;
    }

    static Op createDot() {
        return new Op(DOT);
    }

    static CharOp createChar(int data) {
        return new CharOp(CHAR, data);
    }

    static CharOp createAnchor(int data) {
        return new CharOp(ANCHOR, data);
    }

    static CharOp createCapture(int number, Op next) {
        CharOp op = new CharOp(CAPTURE, number);
        op.next = next;
        return op;
    }

    static UnionOp createUnion(int size) {
        return new UnionOp(UNION, size);
    }

    static ChildOp createClosure(int id) {
        return new ModifierOp(CLOSURE, id, -1);
    }

    static ChildOp createNonGreedyClosure() {
        return new ChildOp(NONGREEDYCLOSURE);
    }

    static ChildOp createQuestion(boolean nongreedy) {
        return new ChildOp(nongreedy ? NONGREEDYQUESTION : QUESTION);
    }

    static RangeOp createRange(Token tok) {
        return new RangeOp(RANGE, tok);
    }

    static ChildOp createLook(int type, Op next, Op branch) {
        ChildOp op = new ChildOp(type);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static CharOp createBackReference(int refno) {
        return new CharOp(BACKREFERENCE, refno);
    }

    static StringOp createString(String literal) {
        return new StringOp(STRING, literal);
    }

    static ChildOp createIndependent(Op next, Op branch) {
        ChildOp op = new ChildOp(INDEPENDENT);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static ModifierOp createModifier(Op next, Op branch, int add, int mask) {
        ModifierOp op = new ModifierOp(MODIFIER, add, mask);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static ConditionOp createCondition(Op next, int ref, Op conditionflow, Op yesflow, Op noflow) {
        ConditionOp op = new ConditionOp(CONDITION, ref, conditionflow, yesflow, noflow);
        op.next = next;
        return op;
    }

    protected Op(int type) {
        this.next = null;
        this.type = type;
    }

    int size() {
        return DOT;
    }

    Op elementAt(int index) {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    Op getChild() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    int getData() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    int getData2() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    RangeToken getToken() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    String getString() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }
}
