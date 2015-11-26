package com.t2t.top.devops.controller;

import com.t2t.top.base.controller.BaseController;
import com.t2t.top.base.exception.BizException;
import com.t2t.top.base.model.dto.ResponseDto;
import com.t2t.top.devops.constant.OperCodeConstants;
import com.t2t.top.devops.model.dto.MenuDto;
import com.t2t.top.devops.model.po.Menu;
import com.t2t.top.devops.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yangpengfei
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("/query")
    @ResponseBody
    public ResponseDto query(MenuDto dto) throws BizException {
        List<Menu> list = menuService.query(dto);
        return ResponseDto.bulidSuccessResult().setData(list);
    }

    @RequestMapping("/update")
    public String update(MenuDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            dto.validate();
            menuService.add(dto);
        } catch (BizException e) {
            responseDto = ResponseDto.bulidFailResult().setData(e.getMessage());
            getRequest().setAttribute("dto", responseDto);
            return "common/error";
        }
        return "redirect:/jsp/menu/list.jsp";
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseDto del(MenuDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            menuService.delete(dto.getIds());
        } catch (BizException e) {
            return ResponseDto.bulidFailResult().setData(e.getMessage());
        }
        return responseDto.setData(OperCodeConstants.ISSUE_DEL_SECCESS);
    }


}
