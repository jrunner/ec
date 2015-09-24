package com.topsec.tss.util;

/**
 * 
 * 系统使用到的全局变更名.
 * 
 * Your class Detail description,end with '.'.
 * 
 * @title ApplicationReferent
 * @package com.topsec.tss.core.web.util
 * @author baiyanwei
 * @version
 * @date 2014-5-15
 * 
 */
public enum ApplicationReferent {

    appLogCfgPath("application.loggingCfgPath"),
    appServiceCfgPath("application.serviceCfgPath"),
    appStartupTime("application.startupTime"),
    sessionCurrentUser("session.current.user"),
    sessionCurrentGroups("session.current.groups"),
    sessionCurrentRoles("session.current.roles"),
    sessionCurrentPermissions("session.current.permissions"),
    sessionCurrentHasPermissionMenu("session.current.hasPermissionMenu"),
    sessionCurrentUserRelateCorporationBean("session.current.user.relateCorporationBean");

    private final String _parameterName;

    private ApplicationReferent(String parameterName) {

        this._parameterName = parameterName;
    }

    public String value() {

        return this._parameterName;
    }

    /**
     * 取得对应的参数名称.
     * 
     * @return
     */
    public String paremeterName() {

        return this._parameterName;
    }

    @Override
    public String toString() {

        return _parameterName;
    }
}
