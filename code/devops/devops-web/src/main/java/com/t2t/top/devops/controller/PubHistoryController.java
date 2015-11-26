package com.t2t.top.devops.controller;

import com.t2t.top.base.controller.BaseController;
import com.t2t.top.base.exception.BizException;
import com.t2t.top.base.model.dto.ResponseDto;
import com.t2t.top.devops.constant.OperCodeConstants;
import com.t2t.top.devops.model.dto.PubHistoryDto;
import com.t2t.top.devops.model.po.PubHistory;
import com.t2t.top.devops.service.PubHistoryService;
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
@RequestMapping("/pub")
public class PubHistoryController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(PubHistoryController.class);

    @Autowired
    private PubHistoryService pubHistoryService;

    @RequestMapping("/query")
    @ResponseBody
    public ResponseDto query(PubHistoryDto dto) throws BizException {
        List<PubHistory> list = pubHistoryService.query(dto);
        return ResponseDto.bulidSuccessResult().setData(list);
    }

    @RequestMapping("/update")
    public String update(PubHistoryDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            dto.validate();
            pubHistoryService.add(dto);
        } catch (BizException e) {
            responseDto = ResponseDto.bulidFailResult().setData(e.getMessage());
            getRequest().setAttribute("dto", responseDto);
            return "common/error";
        }
        return "redirect:/jsp/pub/list.jsp";
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseDto del(PubHistoryDto dto) throws BizException {
        ResponseDto responseDto = ResponseDto.bulidSuccess(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            pubHistoryService.delete(dto.getIds());
        } catch (BizException e) {
            return ResponseDto.bulidFailResult().setData(e.getMessage());
        }
        return responseDto.setData(OperCodeConstants.SAY_DEL_SECCESS);
    }


}
