package com.t2t.top.base.exception;

/**
 * WebService异常
 */
public class WsNoticeException extends BizException {

    private static final long serialVersionUID = 1906078484003234438L;

    public WsNoticeException() {
        super();
    }

    public WsNoticeException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public WsNoticeException(int code, String... msgs) {
        super(code, msgs);
    }

    public WsNoticeException(int code, String msg) {
        super(code, msg);
    }

    public WsNoticeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WsNoticeException(String message) {
        super(message);
    }

    public WsNoticeException(Throwable cause) {
        super(cause);
    }

}
