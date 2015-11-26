package com.t2t.top.devops.interceptor.auth;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
	/**
	 * 是URL跳转还是内容返回
	 * @return
	 */
    AuthResponeType type() default AuthResponeType.RedirectUrl;
    /**
     * 返回的内容
     * @return
     */
    String msg() default "";
    /**
     * 自己配置的URL 如果用动态参数需要占位符{0},{1}
     * @return
     */
    String url() default "";
    /**
     * 配置的URL需要从request里取参数填值，填充的顺序根据数组的下标及url的占位符{0},{1}
     * @return
     */
    String[] paraNames() default "";
    
    /**
     * 配置的URL需要动态获取 url参数优先
     * @return
     */
    String mpKey() default "";
}