package com.startapp.android.publish.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public abstract class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1;
    private String errorMessage;
    protected Map<String, String> parameters;
    private boolean validResponse;

    public BaseResponse() {
        this.parameters = new HashMap();
        this.validResponse = true;
        this.errorMessage = null;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public boolean isValidResponse() {
        return this.validResponse;
    }

    public void setValidResponse(boolean validResponse) {
        this.validResponse = validResponse;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "BaseResponse [parameters=" + this.parameters + ", validResponse=" + this.validResponse + ", errorMessage=" + this.errorMessage + "]";
    }
}
