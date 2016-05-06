package org.nexage.sourcekit.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.stream.StreamSource;
import mf.javax.xml.validation.SchemaFactory;
import mf.org.apache.xerces.jaxp.validation.XMLSchemaFactory;

public class XmlValidation {
    private static String TAG;

    static {
        TAG = "XmlTools";
    }

    public static boolean validate(InputStream schemaStream, String xml) {
        VASTLog.m3661i(TAG, "Beginning XSD validation.");
        SchemaFactory factory = new XMLSchemaFactory();
        Source schemaSource = new StreamSource(schemaStream);
        try {
            factory.newSchema(schemaSource).newValidator().validate(new StreamSource(new ByteArrayInputStream(xml.getBytes())));
            VASTLog.m3661i(TAG, "Completed XSD validation..");
            return true;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
