package com.Newawe.suggestions;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SuggestionsHandler extends DefaultHandler {
    private final int CAPACITY_DEFAULT;
    private final String XML_ATTRIBUTE_DATA;
    private final String XML_TAG_SUGGESTION;
    private int capacity;
    private ArrayList<RemoteSuggestionItem> results;

    public SuggestionsHandler() {
        this.CAPACITY_DEFAULT = 6;
        this.XML_TAG_SUGGESTION = "suggestion";
        this.XML_ATTRIBUTE_DATA = "data";
        this.capacity = 6;
        this.results = new ArrayList();
    }

    public ArrayList<RemoteSuggestionItem> getResult() {
        return this.results;
    }

    public void startDocument() throws SAXException {
        super.startDocument();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (this.results.size() < this.capacity && qName != null && attributes != null && qName.equals("suggestion")) {
            String value = attributes.getValue("data");
            if (value != null) {
                this.results.add(new RemoteSuggestionItem(value));
            }
        }
    }

    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
