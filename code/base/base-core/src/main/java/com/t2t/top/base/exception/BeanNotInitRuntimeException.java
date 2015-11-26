package com.t2t.top.base.exception;

import com.t2t.top.base.constant.ErrorCodeConstants;

/**
 * 调用时对象不存在异常
 *
 * @author zr_wenshengkun
 */
public class BeanNotInitRuntimeException extends BaseRuntimeException {

    private static final long serialVersionUID = 5832353531613387722L;

    public BeanNotInitRuntimeException() {
        super(ErrorCodeConstants.BEAN_NOT_INIT_EXCEPTION.getCode(), ErrorCodeConstants.BEAN_NOT_INIT_EXCEPTION.getValue());
    }

    public BeanNotInitRuntimeException(String msg) {
        super(ErrorCodeConstants.BEAN_NOT_INIT_EXCEPTION.getCode(), msg);
    }
}
