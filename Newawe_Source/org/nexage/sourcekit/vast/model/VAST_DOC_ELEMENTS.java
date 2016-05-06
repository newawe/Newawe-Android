package org.nexage.sourcekit.vast.model;

import org.apache.http.cookie.ClientCookie;

public enum VAST_DOC_ELEMENTS {
    vastVersion("2.0"),
    vasts("VASTS"),
    vastAdTagURI("VASTAdTagURI"),
    vastVersionAttribute(ClientCookie.VERSION_ATTR);
    
    private String value;

    private VAST_DOC_ELEMENTS(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
