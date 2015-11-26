package com.t2t.top.devops.service.impl;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.dao.AccountMapper;
import com.t2t.top.devops.model.po.Account;
import com.t2t.top.devops.model.po.AccountExample;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.service.AccountService;
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
class AccountServiceImpl implements AccountService {

    protected Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountMapper accountMapper;

    public void add(Account account) throws ServiceException {
        try {
            if (account.getId() == null || account.getId() == 0) {
                account.setCreateTime(new Date());
                account.setCanUse(2);
                accountMapper.insert(account);
            }else {

            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String ids) throws ServiceException {
        String[] arr = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            Account po = (Account) accountMapper.selectByPrimaryKey(Integer.parseInt(arr[i]));
            accountMapper.deleteByPrimaryKey(po.getId());
        }
    }

    public List query(Account account) throws ServiceException {
        AccountExample e = new AccountExample();
        if (StringUtils.isNotEmpty(account.getName())) {
            e.createCriteria().andNameLike(account.getName());
        }
        return accountMapper.selectByExample(e);
    }

}
