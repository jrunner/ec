package com.dmis.top.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dmis.top.persistence.RptTemplate;
import com.dmis.top.service.BaseService;
import com.dmis.top.tool.json.JsonUtil;
import com.dmis.top.util.PageResult;
import com.dmis.top.util.PageUtil;

@Controller
@RequestMapping("/info/emp")
public class EmployeeAction {
	private static Logger log = Logger.getLogger(EmployeeAction.class.getName());

	@Resource(name = "baseServiceImpl")
	private BaseService service;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(1);
		List<?> list = service.list(RptTemplate.class);

		List<Map<String, String>> listResult = new ArrayList<Map<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			RptTemplate r = (RptTemplate) list.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", r.getId());
			map.put("name", r.getName());
			map.put("type", r.getType());
			listResult.add(map);
		}
		PageResult page = new PageResult();
		page.setList(listResult);
		page.setTotal(list.size());

		String json = JsonUtil.json(page);
		json = json.replace(",\"list\":", ",\"rows\":");
		log.info("PageTotal:" + page.getPageTotal());
		log.info("Total:" + page.getTotal());
		log.info(json);
		PageUtil.WriteReponseText(response, json);
		return null;
	}
}
