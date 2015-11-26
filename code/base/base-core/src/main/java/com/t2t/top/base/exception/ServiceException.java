package com.t2t.top.base.exception;

/******************************************************************************
 * <p>Description: 业务异常的基类，所有业务异常均从此异常继承</p>
 *****************************************************************************/
public class ServiceException extends BizException {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public ServiceException(int code, String msg) {
        super(code, msg);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
