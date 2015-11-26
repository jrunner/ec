package com.t2t.top.base.constant;

public class Option {
    private int code;
    private String value;

    public Option(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public int getCode() {
        return code;
    }
}