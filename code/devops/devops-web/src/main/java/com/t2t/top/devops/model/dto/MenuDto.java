package com.t2t.top.devops.model.dto;

import com.t2t.top.base.exception.BizException;
import com.t2t.top.devops.model.po.Account;
import com.t2t.top.devops.model.po.Menu;

/**
 * Created by yangpengfei on 2015/11/13.
 */
public class MenuDto extends Menu {

    public void validate() throws BizException {

    }

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
