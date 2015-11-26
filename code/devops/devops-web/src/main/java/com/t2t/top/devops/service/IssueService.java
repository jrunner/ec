package com.t2t.top.devops.service;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.model.po.Issue;

import java.util.List;

/**
 * @author yangpengfei
 */
public interface IssueService {

    public void add(Issue Issue) throws ServiceException;

    public List query(Issue Issue) throws ServiceException;

    public void delete(String ids) throws ServiceException;
}
