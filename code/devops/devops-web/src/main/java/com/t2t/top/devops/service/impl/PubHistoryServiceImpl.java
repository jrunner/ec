package com.t2t.top.devops.service.impl;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.dao.PubHistoryMapper;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.model.po.Menu;
import com.t2t.top.devops.model.po.PubHistory;
import com.t2t.top.devops.model.po.PubHistoryExample;
import com.t2t.top.devops.service.PubHistoryService;
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
class PubHistoryServiceImpl implements PubHistoryService {

    protected Logger logger = LoggerFactory.getLogger(PubHistoryServiceImpl.class);

    @Resource
    private PubHistoryMapper pubHistoryMapper;

    public void add(PubHistory pubHistory) throws ServiceException {
        try {
            if (pubHistory.getId() == null || pubHistory.getId() == 0) {
                pubHistory.setCreateTime(new Date());
                pubHistory.setCanUse(2);
                pubHistoryMapper.insert(pubHistory);
            }else {

            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String ids) throws ServiceException {
        String[] arr = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            PubHistory po = (PubHistory) pubHistoryMapper.selectByPrimaryKey(Integer.parseInt(arr[i]));
            pubHistoryMapper.deleteByPrimaryKey(po.getId());
        }
    }

    public List query(PubHistory pubHistory) throws ServiceException {
        PubHistoryExample e = new PubHistoryExample();
        if (StringUtils.isNotEmpty(pubHistory.getApp())) {
            e.createCriteria().andAppLike(pubHistory.getApp());
        }
        return pubHistoryMapper.selectByExample(e);
    }

}
