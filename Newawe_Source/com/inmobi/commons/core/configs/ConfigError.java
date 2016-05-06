package com.inmobi.commons.core.configs;

class ConfigError {
    private ErrorCode f1189a;
    private String f1190b;

    public enum ErrorCode {
        NETWORK_ERROR,
        CONFIG_SERVER_INTERNAL_ERROR,
        PARSING_ERROR
    }

    public ConfigError(ErrorCode errorCode, String str) {
        this.f1189a = errorCode;
        this.f1190b = str;
    }

    public ErrorCode m1329a() {
        return this.f1189a;
    }

    public String m1330b() {
        return this.f1190b;
    }
}
