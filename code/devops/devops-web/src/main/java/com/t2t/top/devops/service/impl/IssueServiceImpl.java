package com.t2t.top.devops.service.impl;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.dao.IssueMapper;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.model.po.IssueExample;
import com.t2t.top.devops.service.IssueService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yangpengfei
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
class IssueServiceImpl implements IssueService {

    protected Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);

    @Resource
    private IssueMapper issueMapper;

    public void add(Issue issue) throws ServiceException {
        try {
            if (issue.getId() == null || issue.getId() == 0) {
                issue.setCreateTime(new Date());
                issue.setCanUse(2);
                issueMapper.insert(issue);
            }else {

            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String ids) throws ServiceException {
        String[] arr = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            Issue po = (Issue) issueMapper.selectByPrimaryKey(Integer.parseInt(arr[i]));
            issueMapper.deleteByPrimaryKey(po.getId());
        }
    }

    public List query(Issue issue) throws ServiceException {
        IssueExample e = new IssueExample();
        if (StringUtils.isNotEmpty(issue.getTitle())) {
            e.createCriteria().andTitleLike(issue.getTitle());
        }
        return issueMapper.selectByExample(e);
    }

}
