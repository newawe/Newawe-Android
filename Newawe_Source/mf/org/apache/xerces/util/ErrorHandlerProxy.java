package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class ErrorHandlerProxy implements ErrorHandler {
    protected abstract XMLErrorHandler getErrorHandler();

    public void error(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.error(e);
        } else {
            eh.error(StringUtils.EMPTY, StringUtils.EMPTY, ErrorHandlerWrapper.createXMLParseException(e));
        }
    }

    public void fatalError(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.fatalError(e);
        } else {
            eh.fatalError(StringUtils.EMPTY, StringUtils.EMPTY, ErrorHandlerWrapper.createXMLParseException(e));
        }
    }

    public void warning(SAXParseException e) throws SAXException {
        XMLErrorHandler eh = getErrorHandler();
        if (eh instanceof ErrorHandlerWrapper) {
            ((ErrorHandlerWrapper) eh).fErrorHandler.warning(e);
        } else {
            eh.warning(StringUtils.EMPTY, StringUtils.EMPTY, ErrorHandlerWrapper.createXMLParseException(e));
        }
    }
}
