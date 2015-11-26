package com.t2t.top.devops.controller;

import com.t2t.top.base.controller.BaseController;
import com.t2t.top.base.exception.BizException;
import com.t2t.top.base.model.dto.ResponseDto;
import com.t2t.top.devops.constant.OperCodeConstants;
import com.t2t.top.devops.model.dto.AccountDto;
import com.t2t.top.devops.model.po.Account;
import com.t2t.top.devops.service.AccountService;
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
@RequestMapping("/account")
public class AccountController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping("/query")
    @ResponseBody
    public ResponseDto query(AccountDto dto) throws BizException {
        List<Account> list = accountService.query(dto);
        return ResponseDto.bulidSuccessResult().setData(list);
    }

    @RequestMapping("/update")
    public String update(AccountDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            dto.validate();
            accountService.add(dto);
        } catch (BizException e) {
            responseDto = ResponseDto.bulidFailResult().setData(e.getMessage());
            getRequest().setAttribute("dto", responseDto);
            return "common/error";
        }
        return "redirect:/jsp/account/list.jsp";
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseDto del(AccountDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            accountService.delete(dto.getIds());
        } catch (BizException e) {
            return ResponseDto.bulidFailResult().setData(e.getMessage());
        }
        return responseDto.setData(OperCodeConstants.SAY_DEL_SECCESS);
    }


}
