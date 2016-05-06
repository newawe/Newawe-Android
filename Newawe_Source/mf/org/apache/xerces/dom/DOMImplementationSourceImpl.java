package mf.org.apache.xerces.dom;

import com.astuetz.pagerslidingtabstrip.C0302R;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.StringTokenizer;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DOMImplementationList;
import mf.org.w3c.dom.DOMImplementationSource;

public class DOMImplementationSourceImpl implements DOMImplementationSource {
    public DOMImplementation getDOMImplementation(String features) {
        DOMImplementation impl = CoreDOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }
        impl = DOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }
        return null;
    }

    public DOMImplementationList getDOMImplementationList(String features) {
        DOMImplementation impl = CoreDOMImplementationImpl.getDOMImplementation();
        ArrayList implementations = new ArrayList();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }
        impl = DOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }
        return new DOMImplementationListImpl(implementations);
    }

    boolean testImpl(DOMImplementation impl, String features) {
        StringTokenizer st = new StringTokenizer(features);
        String feature = null;
        if (st.hasMoreTokens()) {
            feature = st.nextToken();
        }
        while (feature != null) {
            boolean isVersion = false;
            String version;
            if (st.hasMoreTokens()) {
                version = st.nextToken();
                switch (version.charAt(0)) {
                    case XML11Char.MASK_XML11_CONTENT_INTERNAL /*48*/:
                    case C0302R.styleable.Theme_actionButtonStyle /*49*/:
                    case MetaData.DEFAULT_HTML_3D_PROBABILITY_3D /*50*/:
                    case C0302R.styleable.Theme_buttonBarButtonStyle /*51*/:
                    case C0302R.styleable.Theme_selectableItemBackground /*52*/:
                    case C0302R.styleable.Theme_selectableItemBackgroundBorderless /*53*/:
                    case C0302R.styleable.Theme_borderlessButtonStyle /*54*/:
                    case C0302R.styleable.Theme_dividerVertical /*55*/:
                    case C0302R.styleable.Theme_dividerHorizontal /*56*/:
                    case C0302R.styleable.Theme_activityChooserViewStyle /*57*/:
                        isVersion = true;
                        break;
                }
            }
            version = null;
            if (isVersion) {
                if (!impl.hasFeature(feature, version)) {
                    return false;
                }
                if (st.hasMoreTokens()) {
                    feature = st.nextToken();
                } else {
                    feature = null;
                }
            } else if (!impl.hasFeature(feature, null)) {
                return false;
            } else {
                feature = version;
            }
        }
        return true;
    }
}
