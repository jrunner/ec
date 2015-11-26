package com.t2t.top.base.constant;

/**
 * Created by yangpengfei
 * <p/>
 * 错误状态码前三位代表分类， 后两位自定义
 * 441xx 内部请求参数
 * 442xx 外部系统业务异常
 * 451xx 内部系统异常
 * 452xx 外部系统异常
 * 460xx 未知异常,扩展异常
 */
public class ErrorCodeConstants {

    public static final Option INTERNAL_SERVER_ERROR = new Option(40000, "系统繁忙，请稍后重试");//服务器内部错误，一般是服务器端出了异常
    public static final Option UNKNOW_ERROR = new Option(40001, "未知异常");
    public static final Option ID_GENERATOR_ERROR = new Option(40002, "调用ID生成服务失败");
    public static final Option PARAM_ERROR = new Option(40003, "请求参数错误");
    public static final Option PARAM_CONVERT_ERROR = new Option(40004, "参数转换失败");
    public final static Option BEAN_NOT_INIT_EXCEPTION = new Option(40005, "对象不能初始化");
    public final static Option NOT_LOGIN_ERROR = new Option(40006, "用户未登录");
}

