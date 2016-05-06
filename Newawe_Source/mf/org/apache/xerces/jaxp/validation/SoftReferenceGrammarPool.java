package mf.org.apache.xerces.jaxp.validation;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.grammars.XMLSchemaDescription;

final class SoftReferenceGrammarPool implements XMLGrammarPool {
    protected static final int TABLE_SIZE = 11;
    protected static final Grammar[] ZERO_LENGTH_GRAMMAR_ARRAY;
    protected int fGrammarCount;
    protected Entry[] fGrammars;
    protected boolean fPoolIsLocked;
    protected final ReferenceQueue fReferenceQueue;

    static final class Entry {
        public int bucket;
        public XMLGrammarDescription desc;
        public SoftGrammarReference grammar;
        public int hash;
        public Entry next;
        public Entry prev;

        protected Entry(int hash, int bucket, XMLGrammarDescription desc, Grammar grammar, Entry next, ReferenceQueue queue) {
            this.hash = hash;
            this.bucket = bucket;
            this.prev = null;
            this.next = next;
            if (next != null) {
                next.prev = this;
            }
            this.desc = desc;
            this.grammar = new SoftGrammarReference(this, grammar, queue);
        }

        protected void clear() {
            this.desc = null;
            this.grammar = null;
            if (this.next != null) {
                this.next.clear();
                this.next = null;
            }
        }
    }

    static final class SoftGrammarReference extends SoftReference {
        public Entry entry;

        protected SoftGrammarReference(Entry entry, Grammar grammar, ReferenceQueue queue) {
            super(grammar, queue);
            this.entry = entry;
        }
    }

    static {
        ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar[0];
    }

    public SoftReferenceGrammarPool() {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fReferenceQueue = new ReferenceQueue();
        this.fGrammars = new Entry[TABLE_SIZE];
        this.fPoolIsLocked = false;
    }

    public SoftReferenceGrammarPool(int initialCapacity) {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fReferenceQueue = new ReferenceQueue();
        this.fGrammars = new Entry[initialCapacity];
        this.fPoolIsLocked = false;
    }

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        Grammar[] grammarArr;
        synchronized (this.fGrammars) {
            clean();
            grammarArr = ZERO_LENGTH_GRAMMAR_ARRAY;
        }
        return grammarArr;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {
        if (!this.fPoolIsLocked) {
            for (Grammar putGrammar : grammars) {
                putGrammar(putGrammar);
            }
        }
    }

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return getGrammar(desc);
    }

    public void putGrammar(Grammar grammar) {
        if (!this.fPoolIsLocked) {
            synchronized (this.fGrammars) {
                clean();
                XMLGrammarDescription desc = grammar.getGrammarDescription();
                int hash = hashCode(desc);
                int index = (ASContentModel.AS_UNBOUNDED & hash) % this.fGrammars.length;
                Entry entry = this.fGrammars[index];
                while (entry != null) {
                    if (entry.hash == hash && equals(entry.desc, desc)) {
                        if (entry.grammar.get() != grammar) {
                            entry.grammar = new SoftGrammarReference(entry, grammar, this.fReferenceQueue);
                        }
                        return;
                    }
                    entry = entry.next;
                }
                this.fGrammars[index] = new Entry(hash, index, desc, grammar, this.fGrammars[index], this.fReferenceQueue);
                this.fGrammarCount++;
            }
        }
    }

    public Grammar getGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(ASContentModel.AS_UNBOUNDED & hash) % this.fGrammars.length];
            while (entry != null) {
                Grammar tempGrammar = (Grammar) entry.grammar.get();
                if (tempGrammar == null) {
                    removeEntry(entry);
                } else if (entry.hash == hash && equals(entry.desc, desc)) {
                    return tempGrammar;
                }
                entry = entry.next;
            }
            return null;
        }
    }

    public Grammar removeGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(ASContentModel.AS_UNBOUNDED & hash) % this.fGrammars.length];
            while (entry != null) {
                if (entry.hash == hash && equals(entry.desc, desc)) {
                    Grammar removeEntry = removeEntry(entry);
                    return removeEntry;
                }
                entry = entry.next;
            }
            return null;
        }
    }

    public boolean containsGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(ASContentModel.AS_UNBOUNDED & hash) % this.fGrammars.length];
            while (entry != null) {
                if (((Grammar) entry.grammar.get()) == null) {
                    removeEntry(entry);
                } else if (entry.hash == hash && equals(entry.desc, desc)) {
                    return true;
                }
                entry = entry.next;
            }
            return false;
        }
    }

    public void lockPool() {
        this.fPoolIsLocked = true;
    }

    public void unlockPool() {
        this.fPoolIsLocked = false;
    }

    public void clear() {
        for (int i = 0; i < this.fGrammars.length; i++) {
            if (this.fGrammars[i] != null) {
                this.fGrammars[i].clear();
                this.fGrammars[i] = null;
            }
        }
        this.fGrammarCount = 0;
    }

    public boolean equals(XMLGrammarDescription desc1, XMLGrammarDescription desc2) {
        if (!(desc1 instanceof XMLSchemaDescription)) {
            return desc1.equals(desc2);
        }
        if (!(desc2 instanceof XMLSchemaDescription)) {
            return false;
        }
        XMLSchemaDescription sd1 = (XMLSchemaDescription) desc1;
        XMLSchemaDescription sd2 = (XMLSchemaDescription) desc2;
        String targetNamespace = sd1.getTargetNamespace();
        if (targetNamespace != null) {
            if (!targetNamespace.equals(sd2.getTargetNamespace())) {
                return false;
            }
        } else if (sd2.getTargetNamespace() != null) {
            return false;
        }
        String expandedSystemId = sd1.getExpandedSystemId();
        if (expandedSystemId != null) {
            if (!expandedSystemId.equals(sd2.getExpandedSystemId())) {
                return false;
            }
        } else if (sd2.getExpandedSystemId() != null) {
            return false;
        }
        return true;
    }

    public int hashCode(XMLGrammarDescription desc) {
        int i = 0;
        if (!(desc instanceof XMLSchemaDescription)) {
            return desc.hashCode();
        }
        int hash;
        XMLSchemaDescription sd = (XMLSchemaDescription) desc;
        String targetNamespace = sd.getTargetNamespace();
        String expandedSystemId = sd.getExpandedSystemId();
        if (targetNamespace != null) {
            hash = targetNamespace.hashCode();
        } else {
            hash = 0;
        }
        if (expandedSystemId != null) {
            i = expandedSystemId.hashCode();
        }
        return hash ^ i;
    }

    private Grammar removeEntry(Entry entry) {
        if (entry.prev != null) {
            entry.prev.next = entry.next;
        } else {
            this.fGrammars[entry.bucket] = entry.next;
        }
        if (entry.next != null) {
            entry.next.prev = entry.prev;
        }
        this.fGrammarCount--;
        entry.grammar.entry = null;
        return (Grammar) entry.grammar.get();
    }

    private void clean() {
        Reference ref = this.fReferenceQueue.poll();
        while (ref != null) {
            Entry entry = ((SoftGrammarReference) ref).entry;
            if (entry != null) {
                removeEntry(entry);
            }
            ref = this.fReferenceQueue.poll();
        }
    }
}
