package mf.org.apache.xerces.util;

import java.io.PrintWriter;
import java.util.Hashtable;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMLocatorImpl;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.w3c.dom.DOMError;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.DOMLocator;
import mf.org.w3c.dom.Node;
import org.apache.http.HttpHeaders;

public class DOMErrorHandlerWrapper implements XMLErrorHandler, DOMErrorHandler {
    boolean eStatus;
    public Node fCurrentNode;
    protected final DOMErrorImpl fDOMError;
    protected DOMErrorHandler fDomErrorHandler;
    protected final XMLErrorCode fErrorCode;
    protected PrintWriter fOut;

    private static class DOMErrorTypeMap {
        private static Hashtable fgDOMErrorTypeTable;

        static {
            fgDOMErrorTypeTable = new Hashtable();
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInCDSect"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInContent"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "TwoColonsInQName"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ColonNotLegalWithNS"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInProlog"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "CDEndInContent"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "CDSectUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "DoctypeNotAllowed"), "doctype-not-allowed");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ETagRequired"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ElementUnterminated"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EqRequiredInAttribute"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "OpenQuoteExpected"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "CloseQuoteExpected"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ETagUnterminated"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MarkupNotRecognizedInContent"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "DoctypeIllegalInContent"), "doctype-not-allowed");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInAttValue"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInPI"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInInternalSubset"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "QuoteRequiredInAttValue"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "LessthanInAttValue"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "AttributeValueUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PITargetRequired"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SpaceRequiredInPI"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PIUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ReservedPITarget"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PI_NOT_IN_ONE_ENTITY"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PINotInOneEntity"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EncodingDeclInvalid"), "unsupported-encoding");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EncodingByteOrderUnsupported"), "unsupported-encoding");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInEntityValue"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInExternalSubset"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInIgnoreSect"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInPublicID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInSystemID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SpaceRequiredAfterSYSTEM"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "QuoteRequiredInSystemID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SystemIDUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SpaceRequiredAfterPUBLIC"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "QuoteRequiredInPublicID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PublicIDUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PubidCharIllegal"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SpaceRequiredBetweenPublicAndSystem"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_ROOT_ELEMENT_TYPE_REQUIRED"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "DoctypedeclUnterminated"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PEReferenceWithinMarkup"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENTSPEC_REQUIRED_IN_ELEMENTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ElementDeclUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_CLOSE_PAREN_REQUIRED_IN_MIXED"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MixedContentUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "AttNameRequiredInAttDef"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "AttTypeRequiredInAttDef"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_DUPLICATE_ATTRIBUTE_DEFINITION"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_NAME_REQUIRED_IN_NOTATIONTYPE"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "NotationTypeUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_NMTOKEN_REQUIRED_IN_ENUMERATION"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EnumerationUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_DISTINCT_TOKENS_IN_ENUMERATION"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_DISTINCT_NOTATION_IN_ENUMERATION"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "IncludeSectUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "IgnoreSectUnterminated"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "NameRequiredInPEReference"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "SemicolonRequiredInPEReference"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EntityDeclUnterminated"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_DUPLICATE_ENTITY_DEFINITION"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ExternalIDRequired"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_PUBIDLITERAL_IN_EXTERNALID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_AFTER_PUBIDLITERAL_IN_EXTERNALID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_SYSTEMLITERAL_IN_EXTERNALID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_URI_FRAGMENT_IN_SYSTEMID"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ExternalIDorPublicIDRequired"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "NotationDeclUnterminated"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ReferenceToExternalEntity"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ReferenceToUnparsedEntity"), "wf-invalid-character");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EncodingNotSupported"), "unsupported-encoding");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EncodingRequired"), "unsupported-encoding");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "IllegalQName"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ElementXMLNSPrefix"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "ElementPrefixUnbound"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "AttributePrefixUnbound"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "EmptyPrefixedAttName"), "wf-invalid-character-in-node-name");
            fgDOMErrorTypeTable.put(new XMLErrorCode(XMLMessageFormatter.XML_DOMAIN, "PrefixDeclared"), "wf-invalid-character-in-node-name");
        }

        public static String getDOMErrorType(XMLErrorCode error) {
            return (String) fgDOMErrorTypeTable.get(error);
        }

        private DOMErrorTypeMap() {
        }
    }

    public DOMErrorHandlerWrapper() {
        this.eStatus = true;
        this.fErrorCode = new XMLErrorCode(null, null);
        this.fDOMError = new DOMErrorImpl();
        this.fOut = new PrintWriter(System.err);
    }

    public DOMErrorHandlerWrapper(DOMErrorHandler domErrorHandler) {
        this.eStatus = true;
        this.fErrorCode = new XMLErrorCode(null, null);
        this.fDOMError = new DOMErrorImpl();
        this.fDomErrorHandler = domErrorHandler;
    }

    public void setErrorHandler(DOMErrorHandler errorHandler) {
        this.fDomErrorHandler = errorHandler;
    }

    public DOMErrorHandler getErrorHandler() {
        return this.fDomErrorHandler;
    }

    public void warning(String domain, String key, XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = (short) 1;
        this.fDOMError.fException = exception;
        this.fDOMError.fType = key;
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        DOMErrorImpl dOMErrorImpl2 = this.fDOMError;
        String message = exception.getMessage();
        dOMErrorImpl2.fMessage = message;
        dOMErrorImpl.fRelatedData = message;
        DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUtf16Offset = exception.getCharacterOffset();
            locator.fUri = exception.getExpandedSystemId();
            locator.fRelatedNode = this.fCurrentNode;
        }
        if (this.fDomErrorHandler != null) {
            this.fDomErrorHandler.handleError(this.fDOMError);
        }
    }

    public void error(String domain, String key, XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = (short) 2;
        this.fDOMError.fException = exception;
        this.fDOMError.fType = key;
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        DOMErrorImpl dOMErrorImpl2 = this.fDOMError;
        String message = exception.getMessage();
        dOMErrorImpl2.fMessage = message;
        dOMErrorImpl.fRelatedData = message;
        DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUtf16Offset = exception.getCharacterOffset();
            locator.fUri = exception.getExpandedSystemId();
            locator.fRelatedNode = this.fCurrentNode;
        }
        if (this.fDomErrorHandler != null) {
            this.fDomErrorHandler.handleError(this.fDOMError);
        }
    }

    public void fatalError(String domain, String key, XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = (short) 3;
        this.fDOMError.fException = exception;
        this.fErrorCode.setValues(domain, key);
        String domErrorType = DOMErrorTypeMap.getDOMErrorType(this.fErrorCode);
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        if (domErrorType == null) {
            domErrorType = key;
        }
        dOMErrorImpl.fType = domErrorType;
        dOMErrorImpl = this.fDOMError;
        DOMErrorImpl dOMErrorImpl2 = this.fDOMError;
        String message = exception.getMessage();
        dOMErrorImpl2.fMessage = message;
        dOMErrorImpl.fRelatedData = message;
        DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUtf16Offset = exception.getCharacterOffset();
            locator.fUri = exception.getExpandedSystemId();
            locator.fRelatedNode = this.fCurrentNode;
        }
        if (this.fDomErrorHandler != null) {
            this.fDomErrorHandler.handleError(this.fDOMError);
        }
    }

    public boolean handleError(DOMError error) {
        printError(error);
        return this.eStatus;
    }

    private void printError(DOMError error) {
        int severity = error.getSeverity();
        this.fOut.print("[");
        if (severity == 1) {
            this.fOut.print(HttpHeaders.WARNING);
        } else if (severity == 2) {
            this.fOut.print("Error");
        } else {
            this.fOut.print("FatalError");
            this.eStatus = false;
        }
        this.fOut.print("] ");
        DOMLocator locator = error.getLocation();
        if (locator != null) {
            this.fOut.print(locator.getLineNumber());
            this.fOut.print(":");
            this.fOut.print(locator.getColumnNumber());
            this.fOut.print(":");
            this.fOut.print(locator.getByteOffset());
            this.fOut.print(",");
            this.fOut.print(locator.getUtf16Offset());
            Node node = locator.getRelatedNode();
            if (node != null) {
                this.fOut.print("[");
                this.fOut.print(node.getNodeName());
                this.fOut.print("]");
            }
            String systemId = locator.getUri();
            if (systemId != null) {
                int index = systemId.lastIndexOf(47);
                if (index != -1) {
                    systemId = systemId.substring(index + 1);
                }
                this.fOut.print(": ");
                this.fOut.print(systemId);
            }
        }
        this.fOut.print(":");
        this.fOut.print(error.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }
}
