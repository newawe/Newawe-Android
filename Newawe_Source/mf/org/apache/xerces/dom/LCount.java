package mf.org.apache.xerces.dom;

import java.util.Hashtable;

class LCount {
    static Hashtable lCounts;
    public int bubbles;
    public int captures;
    public int defaults;
    public int total;

    LCount() {
        this.captures = 0;
        this.bubbles = 0;
        this.total = 0;
    }

    static {
        lCounts = new Hashtable();
    }

    static LCount lookup(String evtName) {
        LCount lc = (LCount) lCounts.get(evtName);
        if (lc != null) {
            return lc;
        }
        Hashtable hashtable = lCounts;
        lc = new LCount();
        hashtable.put(evtName, lc);
        return lc;
    }
}
