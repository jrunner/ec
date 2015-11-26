package com.t2t.top.devops.service;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.model.po.Menu;

import java.util.List;

/**
 * @author yangpengfei
 */
public interface MenuService {

    public void add(Menu menu) throws ServiceException;

    public List query(Menu menu) throws ServiceException;

    public void delete(String ids) throws ServiceException;
}
