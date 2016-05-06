package com.Newawe.configuration;

import java.io.Serializable;

public class IncludeScriptConfigEntity implements Serializable {
    private final String file;
    private final String pattern;
    private final String regex;

    public IncludeScriptConfigEntity(String pattern, String regex, String file) {
        this.file = file;
        this.pattern = pattern;
        this.regex = regex;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getRegex() {
        return this.regex;
    }

    public String getFile() {
        return this.file;
    }
}
