package com.t2t.top.devops.model.dto;

import com.t2t.top.base.exception.BizException;
import com.t2t.top.devops.model.po.Issue;

import java.util.Date;

/**
 * Created by yangpengfei on 2015/11/13.
 */
public class IssueDto extends Issue {


    public void validate() throws BizException {
        if (getTitle() == null) {
            throw new BizException("标题不允许为空!");
        }
        if (getBugLevel() == null) {
            throw new BizException("级别不允许为空!");
        }
        if (getDealTime() == null) {
            throw new BizException("处理时间不允许为空!");
        }
        if (getDealOwner() == null) {
            throw new BizException("处理人不允许为空!");
        }
        if (getDescription() == null) {
            throw new BizException("功能描述不允许为空!");
        }
        if (getSolution() == null) {
            throw new BizException("解决方案不允许为空!");
        }
    }

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
