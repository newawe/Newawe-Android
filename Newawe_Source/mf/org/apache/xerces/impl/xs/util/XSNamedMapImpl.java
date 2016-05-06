package mf.org.apache.xerces.impl.xs.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import mf.javax.xml.namespace.QName;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSObject;
import org.apache.commons.lang.StringUtils;

public class XSNamedMapImpl extends AbstractMap implements XSNamedMap {
    public static final XSNamedMapImpl EMPTY_MAP;
    XSObject[] fArray;
    private Set fEntrySet;
    int fLength;
    final SymbolHash[] fMaps;
    final int fNSNum;
    final String[] fNamespaces;

    /* renamed from: mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl.1 */
    class C08851 extends AbstractSet {
        private final /* synthetic */ XSNamedMapEntry[] val$entries;
        private final /* synthetic */ int val$length;

        /* renamed from: mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl.1.1 */
        class C08841 implements Iterator {
            private int index;
            private final /* synthetic */ XSNamedMapEntry[] val$entries;
            private final /* synthetic */ int val$length;

            C08841(int i, XSNamedMapEntry[] xSNamedMapEntryArr) {
                this.val$length = i;
                this.val$entries = xSNamedMapEntryArr;
                this.index = 0;
            }

            public boolean hasNext() {
                return this.index < this.val$length;
            }

            public Object next() {
                if (this.index < this.val$length) {
                    XSNamedMapEntry[] xSNamedMapEntryArr = this.val$entries;
                    int i = this.index;
                    this.index = i + 1;
                    return xSNamedMapEntryArr[i];
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        C08851(int i, XSNamedMapEntry[] xSNamedMapEntryArr) {
            this.val$length = i;
            this.val$entries = xSNamedMapEntryArr;
        }

        public Iterator iterator() {
            return new C08841(this.val$length, this.val$entries);
        }

        public int size() {
            return this.val$length;
        }
    }

    private static final class XSNamedMapEntry implements Entry {
        private final QName key;
        private final XSObject value;

        public XSNamedMapEntry(QName key, XSObject value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object value) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry e = (Entry) o;
            Object otherKey = e.getKey();
            Object otherValue = e.getValue();
            if (this.key == null) {
                if (otherKey != null) {
                    return false;
                }
            } else if (!this.key.equals(otherKey)) {
                return false;
            }
            if (this.value == null) {
                if (otherValue != null) {
                    return false;
                }
            } else if (!this.value.equals(otherValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(String.valueOf(this.key));
            buffer.append('=');
            buffer.append(String.valueOf(this.value));
            return buffer.toString();
        }
    }

    static {
        EMPTY_MAP = new XSNamedMapImpl(new XSObject[0], 0);
    }

    public XSNamedMapImpl(String namespace, SymbolHash map) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        this.fNamespaces = new String[]{namespace};
        this.fMaps = new SymbolHash[]{map};
        this.fNSNum = 1;
    }

    public XSNamedMapImpl(String[] namespaces, SymbolHash[] maps, int num) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        this.fNamespaces = namespaces;
        this.fMaps = maps;
        this.fNSNum = num;
    }

    public XSNamedMapImpl(XSObject[] array, int length) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        if (length == 0) {
            this.fNamespaces = null;
            this.fMaps = null;
            this.fNSNum = 0;
            this.fArray = array;
            this.fLength = 0;
            return;
        }
        this.fNamespaces = new String[]{array[0].getNamespace()};
        this.fMaps = null;
        this.fNSNum = 1;
        this.fArray = array;
        this.fLength = length;
    }

    public synchronized int getLength() {
        if (this.fLength == -1) {
            this.fLength = 0;
            for (int i = 0; i < this.fNSNum; i++) {
                this.fLength += this.fMaps[i].getLength();
            }
        }
        return this.fLength;
    }

    public XSObject itemByName(String namespace, String localName) {
        int i = 0;
        while (i < this.fNSNum) {
            if (!isEqual(namespace, this.fNamespaces[i])) {
                i++;
            } else if (this.fMaps != null) {
                return (XSObject) this.fMaps[i].get(localName);
            } else {
                for (int j = 0; j < this.fLength; j++) {
                    XSObject ret = this.fArray[j];
                    if (ret.getName().equals(localName)) {
                        return ret;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public synchronized XSObject item(int index) {
        XSObject xSObject;
        if (this.fArray == null) {
            getLength();
            this.fArray = new XSObject[this.fLength];
            int pos = 0;
            for (int i = 0; i < this.fNSNum; i++) {
                pos += this.fMaps[i].getValues(this.fArray, pos);
            }
        }
        if (index < 0 || index >= this.fLength) {
            xSObject = null;
        } else {
            xSObject = this.fArray[index];
        }
        return xSObject;
    }

    static boolean isEqual(String one, String two) {
        if (one != null) {
            return one.equals(two);
        }
        return two == null;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public Object get(Object key) {
        if (!(key instanceof QName)) {
            return null;
        }
        QName name = (QName) key;
        String namespaceURI = name.getNamespaceURI();
        if (StringUtils.EMPTY.equals(namespaceURI)) {
            namespaceURI = null;
        }
        return itemByName(namespaceURI, name.getLocalPart());
    }

    public int size() {
        return getLength();
    }

    public synchronized Set entrySet() {
        if (this.fEntrySet == null) {
            int length = getLength();
            XSNamedMapEntry[] entries = new XSNamedMapEntry[length];
            for (int i = 0; i < length; i++) {
                XSObject xso = item(i);
                entries[i] = new XSNamedMapEntry(new QName(xso.getNamespace(), xso.getName()), xso);
            }
            this.fEntrySet = new C08851(length, entries);
        }
        return this.fEntrySet;
    }
}
