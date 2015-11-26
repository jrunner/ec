package com.t2t.top.devops.service;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.model.po.PubHistory;

import java.util.List;

/**
 * @author yangpengfei
 */
public interface PubHistoryService {

    public void add(PubHistory pubHistory) throws ServiceException;

    public List query(PubHistory pubHistory) throws ServiceException;

    public void delete(String ids) throws ServiceException;
}
