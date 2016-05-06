package org.nexage.sourcekit.vast.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpStatus;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.nexage.sourcekit.util.XmlValidation;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.model.VAST_DOC_ELEMENTS;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class VASTProcessor {
    private static final boolean IS_VALIDATION_ON = false;
    private static final int MAX_VAST_LEVELS = 5;
    private static final String TAG = "VASTProcessor";
    private VASTMediaPicker mediaPicker;
    private StringBuilder mergedVastDocs;
    private VASTModel vastModel;

    public VASTProcessor(VASTMediaPicker mediaPicker) {
        this.mergedVastDocs = new StringBuilder(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        this.mediaPicker = mediaPicker;
    }

    public VASTModel getModel() {
        return this.vastModel;
    }

    public int process(String xmlData) {
        VASTLog.m3658d(TAG, "process");
        this.vastModel = null;
        try {
            InputStream is = new ByteArrayInputStream(xmlData.getBytes(Charset.defaultCharset().name()));
            int error = processUri(is, 0);
            try {
                is.close();
            } catch (IOException e) {
            }
            InputStream inputStream;
            if (error != 0) {
                inputStream = is;
                return error;
            }
            Document mainDoc = wrapMergedVastDocWithVasts();
            this.vastModel = new VASTModel(mainDoc);
            if (mainDoc == null) {
                inputStream = is;
                return 3;
            } else if (VASTModelPostValidator.validate(this.vastModel, this.mediaPicker)) {
                return 0;
            } else {
                inputStream = is;
                return MAX_VAST_LEVELS;
            }
        } catch (UnsupportedEncodingException e2) {
            VASTLog.m3660e(TAG, e2.getMessage(), e2);
            return 3;
        }
    }

    private Document wrapMergedVastDocWithVasts() {
        VASTLog.m3658d(TAG, "wrapmergedVastDocWithVasts");
        this.mergedVastDocs.insert(0, "<VASTS>");
        this.mergedVastDocs.append("</VASTS>");
        String merged = this.mergedVastDocs.toString();
        VASTLog.m3662v(TAG, "Merged VAST doc:\n" + merged);
        return XmlTools.stringToDocument(merged);
    }

    private int processUri(InputStream is, int depth) {
        VASTLog.m3658d(TAG, "processUri");
        if (depth >= MAX_VAST_LEVELS) {
            VASTLog.m3659e(TAG, "VAST wrapping exceeded max limit of 5.");
            return 6;
        }
        Document doc = createDoc(is);
        if (doc == null) {
            return 3;
        }
        merge(doc);
        NodeList uriToNextDoc = doc.getElementsByTagName(VAST_DOC_ELEMENTS.vastAdTagURI.getValue());
        if (uriToNextDoc == null || uriToNextDoc.getLength() == 0) {
            return 0;
        }
        VASTLog.m3658d(TAG, "Doc is a wrapper. ");
        String nextUri = XmlTools.getElementValue(uriToNextDoc.item(0));
        VASTLog.m3658d(TAG, "Wrapper URL: " + nextUri);
        try {
            InputStream nextInputStream = new URL(nextUri).openStream();
            int error = processUri(nextInputStream, depth + 1);
            try {
                nextInputStream.close();
                return error;
            } catch (IOException e) {
                return error;
            }
        } catch (Exception e2) {
            VASTLog.m3660e(TAG, e2.getMessage(), e2);
            return 2;
        }
    }

    private Document createDoc(InputStream is) {
        VASTLog.m3658d(TAG, "About to create doc from InputStream");
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            doc.getDocumentElement().normalize();
            VASTLog.m3658d(TAG, "Doc successfully created.");
            return doc;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void merge(Document newDoc) {
        VASTLog.m3658d(TAG, "About to merge doc into main doc.");
        this.mergedVastDocs.append(XmlTools.xmlDocumentToString(newDoc.getElementsByTagName("VAST").item(0)));
        VASTLog.m3658d(TAG, "Merge successful.");
    }

    private boolean validateAgainstSchema(Document doc) {
        VASTLog.m3658d(TAG, "About to validate doc against schema.");
        InputStream stream = VASTProcessor.class.getResourceAsStream("assets/vast_2_0_1_schema.xsd");
        boolean isValid = XmlValidation.validate(stream, XmlTools.xmlDocumentToString(doc));
        try {
            stream.close();
        } catch (IOException e) {
        }
        return isValid;
    }
}
