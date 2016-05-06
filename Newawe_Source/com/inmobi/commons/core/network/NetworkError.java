package com.inmobi.commons.core.network;

import org.apache.http.HttpStatus;

public class NetworkError {
    private ErrorCode f1223a;
    private String f1224b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        HTTP_NO_CONTENT(HttpStatus.SC_NO_CONTENT),
        HTTP_NOT_MODIFIED(HttpStatus.SC_NOT_MODIFIED),
        HTTP_BAD_REQUEST(HttpStatus.SC_BAD_REQUEST),
        HTTP_SEE_OTHER(HttpStatus.SC_SEE_OTHER),
        HTTP_SERVER_NOT_FOUND(HttpStatus.SC_NOT_FOUND),
        HTTP_MOVED_TEMP(HttpStatus.SC_MOVED_TEMPORARILY),
        HTTP_INTERNAL_SERVER_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
        HTTP_NOT_IMPLEMENTED(HttpStatus.SC_NOT_IMPLEMENTED),
        HTTP_BAD_GATEWAY(HttpStatus.SC_BAD_GATEWAY),
        HTTP_SERVER_NOT_AVAILABLE(HttpStatus.SC_SERVICE_UNAVAILABLE),
        HTTP_GATEWAY_TIMEOUT(HttpStatus.SC_GATEWAY_TIMEOUT),
        HTTP_VERSION_NOT_SUPPORTED(HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED);
        
        private int f1222a;

        private ErrorCode(int i) {
            this.f1222a = i;
        }

        public int getValue() {
            return this.f1222a;
        }

        public static ErrorCode fromValue(int i) {
            for (ErrorCode errorCode : values()) {
                if (errorCode.f1222a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.f1223a = errorCode;
        this.f1224b = str;
    }

    public ErrorCode m1395a() {
        return this.f1223a;
    }

    public String m1396b() {
        return this.f1224b;
    }
}
