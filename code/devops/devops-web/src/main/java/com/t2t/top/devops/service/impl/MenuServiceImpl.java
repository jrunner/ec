package com.t2t.top.devops.service.impl;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.dao.MenuMapper;
import com.t2t.top.devops.model.po.Menu;
import com.t2t.top.devops.model.po.MenuExample;
import com.t2t.top.devops.service.MenuService;
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
class MenuServiceImpl implements MenuService {

    protected Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuMapper menuMapper;

    public void add(Menu menu) throws ServiceException {
        try {
            if (menu.getId() == null || menu.getId() == 0) {
                menu.setCreateTime(new Date());
                menu.setCanUse(2);
                menuMapper.insert(menu);
            }else {

            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String ids) throws ServiceException {
        String[] arr = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            Menu po = (Menu) menuMapper.selectByPrimaryKey(Integer.parseInt(arr[i]));
            menuMapper.deleteByPrimaryKey(po.getId());
        }
    }

    public List query(Menu menu) throws ServiceException {
        MenuExample e = new MenuExample();
        if (StringUtils.isNotEmpty(menu.getName())) {
            e.createCriteria().andNameLike(menu.getName());
        }
        return menuMapper.selectByExample(e);
    }

}
