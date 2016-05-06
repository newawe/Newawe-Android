package org.apache.commons.lang;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang.text.StrBuilder;

public final class CharRange implements Serializable {
    private static final long serialVersionUID = 8270183163158333422L;
    private final char end;
    private transient String iToString;
    private final boolean negated;
    private final char start;

    /* renamed from: org.apache.commons.lang.CharRange.1 */
    static class C09181 {
    }

    private static class CharacterIterator implements Iterator {
        private char current;
        private boolean hasNext;
        private final CharRange range;

        CharacterIterator(CharRange x0, C09181 x1) {
            this(x0);
        }

        private CharacterIterator(CharRange r) {
            this.range = r;
            this.hasNext = true;
            if (!CharRange.access$100(this.range)) {
                this.current = CharRange.access$200(this.range);
            } else if (CharRange.access$200(this.range) != '\u0000') {
                this.current = '\u0000';
            } else if (CharRange.access$300(this.range) == '\uffff') {
                this.hasNext = false;
            } else {
                this.current = (char) (CharRange.access$300(this.range) + 1);
            }
        }

        private void prepareNext() {
            if (CharRange.access$100(this.range)) {
                if (this.current == '\uffff') {
                    this.hasNext = false;
                } else if (this.current + 1 != CharRange.access$200(this.range)) {
                    this.current = (char) (this.current + 1);
                } else if (CharRange.access$300(this.range) == '\uffff') {
                    this.hasNext = false;
                } else {
                    this.current = (char) (CharRange.access$300(this.range) + 1);
                }
            } else if (this.current < CharRange.access$300(this.range)) {
                this.current = (char) (this.current + 1);
            } else {
                this.hasNext = false;
            }
        }

        public boolean hasNext() {
            return this.hasNext;
        }

        public Object next() {
            if (this.hasNext) {
                char cur = this.current;
                prepareNext();
                return new Character(cur);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static boolean access$100(CharRange x0) {
        return x0.negated;
    }

    static char access$200(CharRange x0) {
        return x0.start;
    }

    static char access$300(CharRange x0) {
        return x0.end;
    }

    public static CharRange is(char ch) {
        return new CharRange(ch, ch, false);
    }

    public static CharRange isNot(char ch) {
        return new CharRange(ch, ch, true);
    }

    public static CharRange isIn(char start, char end) {
        return new CharRange(start, end, false);
    }

    public static CharRange isNotIn(char start, char end) {
        return new CharRange(start, end, true);
    }

    public CharRange(char ch) {
        this(ch, ch, false);
    }

    public CharRange(char ch, boolean negated) {
        this(ch, ch, negated);
    }

    public CharRange(char start, char end) {
        this(start, end, false);
    }

    public CharRange(char start, char end, boolean negated) {
        if (start > end) {
            char temp = start;
            start = end;
            end = temp;
        }
        this.start = start;
        this.end = end;
        this.negated = negated;
    }

    public char getStart() {
        return this.start;
    }

    public char getEnd() {
        return this.end;
    }

    public boolean isNegated() {
        return this.negated;
    }

    public boolean contains(char ch) {
        boolean z = ch >= this.start && ch <= this.end;
        return z != this.negated;
    }

    public boolean contains(CharRange range) {
        boolean z = false;
        if (range == null) {
            throw new IllegalArgumentException("The Range must not be null");
        } else if (this.negated) {
            if (!range.negated) {
                if (range.end < this.start || range.start > this.end) {
                    z = true;
                }
                return z;
            } else if (this.start < range.start || this.end > range.end) {
                return false;
            } else {
                return true;
            }
        } else if (range.negated) {
            if (this.start == '\u0000' && this.end == '\uffff') {
                return true;
            }
            return false;
        } else if (this.start > range.start || this.end < range.end) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange other = (CharRange) obj;
        if (this.start == other.start && this.end == other.end && this.negated == other.negated) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.negated ? 1 : 0) + ((this.end * 7) + (this.start + 83));
    }

    public String toString() {
        if (this.iToString == null) {
            StrBuilder buf = new StrBuilder(4);
            if (isNegated()) {
                buf.append('^');
            }
            buf.append(this.start);
            if (this.start != this.end) {
                buf.append('-');
                buf.append(this.end);
            }
            this.iToString = buf.toString();
        }
        return this.iToString;
    }

    public Iterator iterator() {
        return new CharacterIterator(this, null);
    }
}
