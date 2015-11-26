package com.t2t.top.base.exception;

import org.apache.commons.lang3.ArrayUtils;

public class BizException extends Exception {
    private static final long serialVersionUID = -3378987151853736093L;
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    public BizException() {
        super();
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(int code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BizException(int code, String... msgs) {
        super(ArrayUtils.toString(msgs));
        this.code = code;
        this.msg = ArrayUtils.toString(msgs);
    }

    /**
     * 获取code
     *
     * @return code code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取msg
     *
     * @return msg msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置msg
     *
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
