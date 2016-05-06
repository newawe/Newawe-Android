package mf.org.apache.xerces.impl.xs.traversers;

/* compiled from: XSAttributeChecker */
abstract class Container {
    static final int THRESHOLD = 5;
    int pos;
    OneAttr[] values;

    abstract OneAttr get(String str);

    abstract void put(String str, OneAttr oneAttr);

    Container() {
        this.pos = 0;
    }

    static Container getContainer(int size) {
        if (size > THRESHOLD) {
            return new LargeContainer(size);
        }
        return new SmallContainer(size);
    }
}
