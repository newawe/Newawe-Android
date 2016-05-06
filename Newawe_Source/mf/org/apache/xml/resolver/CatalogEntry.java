package mf.org.apache.xml.resolver;

import java.util.Hashtable;
import java.util.Vector;

public class CatalogEntry {
    protected static Vector entryArgs;
    protected static Hashtable entryTypes;
    protected static int nextEntry;
    protected Vector args;
    protected int entryType;

    static {
        nextEntry = 0;
        entryTypes = new Hashtable();
        entryArgs = new Vector();
    }

    public static int addEntryType(String name, int numArgs) {
        entryTypes.put(name, new Integer(nextEntry));
        entryArgs.add(nextEntry, new Integer(numArgs));
        nextEntry++;
        return nextEntry - 1;
    }

    public static int getEntryType(String name) throws CatalogException {
        if (entryTypes.containsKey(name)) {
            Integer iType = (Integer) entryTypes.get(name);
            if (iType != null) {
                return iType.intValue();
            }
            throw new CatalogException(3);
        }
        throw new CatalogException(3);
    }

    public static int getEntryArgCount(String name) throws CatalogException {
        return getEntryArgCount(getEntryType(name));
    }

    public static int getEntryArgCount(int type) throws CatalogException {
        try {
            return ((Integer) entryArgs.get(type)).intValue();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public CatalogEntry() {
        this.entryType = 0;
        this.args = null;
    }

    public CatalogEntry(String name, Vector args) throws CatalogException {
        this.entryType = 0;
        this.args = null;
        Integer iType = (Integer) entryTypes.get(name);
        if (iType == null) {
            throw new CatalogException(3);
        }
        int type = iType.intValue();
        try {
            if (((Integer) entryArgs.get(type)).intValue() != args.size()) {
                throw new CatalogException(2);
            }
            this.entryType = type;
            this.args = args;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public CatalogEntry(int type, Vector args) throws CatalogException {
        this.entryType = 0;
        this.args = null;
        try {
            if (((Integer) entryArgs.get(type)).intValue() != args.size()) {
                throw new CatalogException(2);
            }
            this.entryType = type;
            this.args = args;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public int getEntryType() {
        return this.entryType;
    }

    public String getEntryArg(int argNum) {
        try {
            return (String) this.args.get(argNum);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void setEntryArg(int argNum, String newspec) throws ArrayIndexOutOfBoundsException {
        this.args.set(argNum, newspec);
    }
}
