package com.t2t.top.devops.controller;

import com.t2t.top.base.controller.BaseController;
import com.t2t.top.base.exception.BizException;
import com.t2t.top.base.model.dto.ResponseDto;
import com.t2t.top.base.utils.json.GsonUtils;
import com.t2t.top.devops.constant.OperCodeConstants;
import com.t2t.top.devops.model.dto.IssueDto;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.service.IssueService;
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
@RequestMapping("/issue")
public class IssueController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private IssueService issueService;

    @RequestMapping("/query")
    @ResponseBody
    public ResponseDto query(IssueDto dto) throws BizException {
        List<Issue> list = issueService.query(dto);
        return ResponseDto.bulidSuccessResult().setData(list);
    }

    @RequestMapping("/update")
    public String update(IssueDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            dto.validate();
            issueService.add(dto);
        } catch (BizException e) {
            responseDto = ResponseDto.bulidFailResult().setData(e.getMessage());
            getRequest().setAttribute("dto", responseDto);
            return "common/error";
        }
        return "redirect:/jsp/issue/list.jsp";
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseDto del(IssueDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            issueService.delete(dto.getIds());
        } catch (BizException e) {
            return ResponseDto.bulidFailResult().setData(e.getMessage());
        }
        return responseDto.setData(OperCodeConstants.SAY_DEL_SECCESS);
    }


}
