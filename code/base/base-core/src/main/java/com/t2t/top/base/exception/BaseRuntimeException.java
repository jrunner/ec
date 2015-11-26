package com.t2t.top.base.exception;

import org.apache.commons.lang3.ArrayUtils;

public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 4346150683764930045L;

    private int code;

    private String msg;

    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseRuntimeException(int code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseRuntimeException(int code, String... msgs) {
        super(ArrayUtils.toString(msgs));
        this.code = code;
        this.msg = ArrayUtils.toString(msgs);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
