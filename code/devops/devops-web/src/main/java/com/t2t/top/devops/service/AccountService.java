package com.t2t.top.devops.service;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.model.po.Account;

import java.util.List;

/**
 * @author yangpengfei
 */
public interface AccountService {

    public void add(Account account) throws ServiceException;

    public List query(Account account) throws ServiceException;

    public void delete(String ids) throws ServiceException;
}
