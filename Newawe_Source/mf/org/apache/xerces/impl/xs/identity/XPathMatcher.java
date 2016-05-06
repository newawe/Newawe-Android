package mf.org.apache.xerces.impl.xs.identity;

import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.xpath.XPath;
import mf.org.apache.xerces.impl.xpath.XPath.LocationPath;
import mf.org.apache.xerces.impl.xpath.XPath.NodeTest;
import mf.org.apache.xerces.impl.xpath.XPath.Step;
import mf.org.apache.xerces.util.IntStack;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public class XPathMatcher {
    protected static final boolean DEBUG_ALL = false;
    protected static final boolean DEBUG_ANY = false;
    protected static final boolean DEBUG_MATCH = false;
    protected static final boolean DEBUG_METHODS = false;
    protected static final boolean DEBUG_METHODS2 = false;
    protected static final boolean DEBUG_METHODS3 = false;
    protected static final boolean DEBUG_STACK = false;
    protected static final int MATCHED = 1;
    protected static final int MATCHED_ATTRIBUTE = 3;
    protected static final int MATCHED_DESCENDANT = 5;
    protected static final int MATCHED_DESCENDANT_PREVIOUS = 13;
    private final int[] fCurrentStep;
    private final LocationPath[] fLocationPaths;
    private final int[] fMatched;
    protected Object fMatchedString;
    private final int[] fNoMatchDepth;
    final QName fQName;
    private final IntStack[] fStepIndexes;

    public XPathMatcher(XPath xpath) {
        this.fQName = new QName();
        this.fLocationPaths = xpath.getLocationPaths();
        this.fStepIndexes = new IntStack[this.fLocationPaths.length];
        for (int i = 0; i < this.fStepIndexes.length; i += MATCHED) {
            this.fStepIndexes[i] = new IntStack();
        }
        this.fCurrentStep = new int[this.fLocationPaths.length];
        this.fNoMatchDepth = new int[this.fLocationPaths.length];
        this.fMatched = new int[this.fLocationPaths.length];
    }

    public boolean isMatched() {
        int i = 0;
        while (i < this.fLocationPaths.length) {
            if ((this.fMatched[i] & MATCHED) == MATCHED && (this.fMatched[i] & MATCHED_DESCENDANT_PREVIOUS) != MATCHED_DESCENDANT_PREVIOUS && (this.fNoMatchDepth[i] == 0 || (this.fMatched[i] & MATCHED_DESCENDANT) == MATCHED_DESCENDANT)) {
                return true;
            }
            i += MATCHED;
        }
        return DEBUG_STACK;
    }

    protected void handleContent(XSTypeDefinition type, boolean nillable, Object value, short valueType, ShortList itemValueType) {
    }

    protected void matched(Object actualValue, short valueType, ShortList itemValueType, boolean isNil) {
    }

    public void startDocumentFragment() {
        this.fMatchedString = null;
        for (int i = 0; i < this.fLocationPaths.length; i += MATCHED) {
            this.fStepIndexes[i].clear();
            this.fCurrentStep[i] = 0;
            this.fNoMatchDepth[i] = 0;
            this.fMatched[i] = 0;
        }
    }

    public void startElement(QName element, XMLAttributes attributes) {
        int i = 0;
        while (i < this.fLocationPaths.length) {
            int startStep = this.fCurrentStep[i];
            this.fStepIndexes[i].push(startStep);
            int[] iArr;
            if ((this.fMatched[i] & MATCHED_DESCENDANT) == MATCHED || this.fNoMatchDepth[i] > 0) {
                iArr = this.fNoMatchDepth;
                iArr[i] = iArr[i] + MATCHED;
            } else {
                if ((this.fMatched[i] & MATCHED_DESCENDANT) == MATCHED_DESCENDANT) {
                    this.fMatched[i] = MATCHED_DESCENDANT_PREVIOUS;
                }
                Step[] steps = this.fLocationPaths[i].steps;
                while (this.fCurrentStep[i] < steps.length && steps[this.fCurrentStep[i]].axis.type == (short) 3) {
                    iArr = this.fCurrentStep;
                    iArr[i] = iArr[i] + MATCHED;
                }
                if (this.fCurrentStep[i] == steps.length) {
                    this.fMatched[i] = MATCHED;
                } else {
                    int descendantStep = this.fCurrentStep[i];
                    while (this.fCurrentStep[i] < steps.length && steps[this.fCurrentStep[i]].axis.type == (short) 4) {
                        iArr = this.fCurrentStep;
                        iArr[i] = iArr[i] + MATCHED;
                    }
                    boolean sawDescendant = this.fCurrentStep[i] > descendantStep ? true : DEBUG_STACK;
                    if (this.fCurrentStep[i] == steps.length) {
                        iArr = this.fNoMatchDepth;
                        iArr[i] = iArr[i] + MATCHED;
                    } else {
                        if ((this.fCurrentStep[i] == startStep || this.fCurrentStep[i] > descendantStep) && steps[this.fCurrentStep[i]].axis.type == (short) 1) {
                            if (matches(steps[this.fCurrentStep[i]].nodeTest, element)) {
                                iArr = this.fCurrentStep;
                                iArr[i] = iArr[i] + MATCHED;
                            } else if (this.fCurrentStep[i] > descendantStep) {
                                this.fCurrentStep[i] = descendantStep;
                            } else {
                                iArr = this.fNoMatchDepth;
                                iArr[i] = iArr[i] + MATCHED;
                            }
                        }
                        if (this.fCurrentStep[i] == steps.length) {
                            if (sawDescendant) {
                                this.fCurrentStep[i] = descendantStep;
                                this.fMatched[i] = MATCHED_DESCENDANT;
                            } else {
                                this.fMatched[i] = MATCHED;
                            }
                        } else if (this.fCurrentStep[i] < steps.length && steps[this.fCurrentStep[i]].axis.type == (short) 2) {
                            int attrCount = attributes.getLength();
                            if (attrCount > 0) {
                                NodeTest nodeTest = steps[this.fCurrentStep[i]].nodeTest;
                                int aIndex = 0;
                                while (aIndex < attrCount) {
                                    attributes.getName(aIndex, this.fQName);
                                    if (matches(nodeTest, this.fQName)) {
                                        iArr = this.fCurrentStep;
                                        iArr[i] = iArr[i] + MATCHED;
                                        if (this.fCurrentStep[i] == steps.length) {
                                            this.fMatched[i] = MATCHED_ATTRIBUTE;
                                            int j = 0;
                                            while (j < i && (this.fMatched[j] & MATCHED) != MATCHED) {
                                                j += MATCHED;
                                            }
                                            if (j == i) {
                                                AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(aIndex).getItem(Constants.ATTRIBUTE_PSVI);
                                                this.fMatchedString = attrPSVI.getActualNormalizedValue();
                                                matched(this.fMatchedString, attrPSVI.getActualNormalizedValueType(), attrPSVI.getItemValueTypes(), DEBUG_STACK);
                                            }
                                        }
                                    } else {
                                        aIndex += MATCHED;
                                    }
                                }
                            }
                            if ((this.fMatched[i] & MATCHED) != MATCHED) {
                                if (this.fCurrentStep[i] > descendantStep) {
                                    this.fCurrentStep[i] = descendantStep;
                                } else {
                                    iArr = this.fNoMatchDepth;
                                    iArr[i] = iArr[i] + MATCHED;
                                }
                            }
                        }
                    }
                }
            }
            i += MATCHED;
        }
    }

    public void endElement(QName element, XSTypeDefinition type, boolean nillable, Object value, short valueType, ShortList itemValueType) {
        for (int i = 0; i < this.fLocationPaths.length; i += MATCHED) {
            this.fCurrentStep[i] = this.fStepIndexes[i].pop();
            if (this.fNoMatchDepth[i] > 0) {
                int[] iArr = this.fNoMatchDepth;
                iArr[i] = iArr[i] - 1;
            } else {
                int j = 0;
                while (j < i && (this.fMatched[j] & MATCHED) != MATCHED) {
                    j += MATCHED;
                }
                if (j >= i && this.fMatched[j] != 0) {
                    if ((this.fMatched[j] & MATCHED_ATTRIBUTE) == MATCHED_ATTRIBUTE) {
                        this.fMatched[i] = 0;
                    } else {
                        handleContent(type, nillable, value, valueType, itemValueType);
                        this.fMatched[i] = 0;
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        String s = super.toString();
        int index2 = s.lastIndexOf(46);
        if (index2 != -1) {
            s = s.substring(index2 + MATCHED);
        }
        str.append(s);
        for (int i = 0; i < this.fLocationPaths.length; i += MATCHED) {
            str.append('[');
            Step[] steps = this.fLocationPaths[i].steps;
            for (int j = 0; j < steps.length; j += MATCHED) {
                if (j == this.fCurrentStep[i]) {
                    str.append('^');
                }
                str.append(steps[j].toString());
                if (j < steps.length - 1) {
                    str.append('/');
                }
            }
            if (this.fCurrentStep[i] == steps.length) {
                str.append('^');
            }
            str.append(']');
            str.append(',');
        }
        return str.toString();
    }

    private String normalize(String s) {
        StringBuffer str = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i += MATCHED) {
            char c = s.charAt(i);
            switch (c) {
                case MetaData.DEFAULT_MAX_ADS /*10*/:
                    str.append("\\n");
                    break;
                default:
                    str.append(c);
                    break;
            }
        }
        return str.toString();
    }

    private static boolean matches(NodeTest nodeTest, QName value) {
        if (nodeTest.type == (short) 1) {
            return nodeTest.name.equals(value);
        }
        if (nodeTest.type != (short) 4 || nodeTest.name.uri == value.uri) {
            return true;
        }
        return DEBUG_STACK;
    }
}
