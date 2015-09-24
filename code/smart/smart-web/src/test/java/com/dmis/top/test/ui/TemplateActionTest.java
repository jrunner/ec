package com.dmis.top.test.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dmis.top.persistence.RptTemplate;
import com.dmis.top.util.PageResult;
import com.dmis.top.util.PageUtil;
import com.google.gson.Gson;

public class TemplateActionTest extends BaseAction {
	private static Logger log = Logger.getLogger(TemplateActionTest.class);

	public static void main(String[] args) {
		TemplateActionTest t = new TemplateActionTest();
		// t.add_test();
		t.list_test();
	}

	/**
	 * 测试通过
	 */
	@SuppressWarnings("deprecation")
	public String list_test() {
		List<?> list = service.list(RptTemplate.class);

		if (request.getParameter("getTemplateAll") != null) {
			String json = new Gson().toJson(list);
			PageUtil.WriteReponseText(response, json);
			return null;
		}

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

		String json = new Gson().toJson(page);
		log.info("PageTotal:" + page.getPageTotal());
		log.info("Total:" + page.getTotal());
		log.info(json.replace(",\"list\":", ",\"rows\":"));
		PageUtil.WriteReponseText(response, json.replace(",\"list\":", ",\"rows\":"));
		return null;
	}

	public void add_test() {
		RptTemplate bean = new RptTemplate();
		bean.setName("test");
		bean.setCreateTime(new Date());
		bean.setLastModifyTime(new Date());
		bean.setType(request.getParameter("type"));
		bean.setFileSize("test".getBytes().length * 1l);
		bean.setFilepath("/test/a");
		service.saveOrUpdate(bean);
		System.out.println("ok." + bean.getId());
	}

	// ///////////////////////////////////////
	public String toUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "template/template/update";
	}

	/**
	 * 进入查看页面
	 */
	@RequestMapping(value = "/to/view")
	public String toView(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			RptTemplate bean = (RptTemplate) service.get(RptTemplate.class, id);
			request.setAttribute("template", bean);
			request.setAttribute("url", "/rpt/TemplateAction_view.do?id=" + id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("txt".equals(request.getParameter("kind"))) {
			return "txt_view";
		}
		return "view";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public String del(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		RptTemplate template = (RptTemplate) service.get(RptTemplate.class, id);
		service.delete(template);

		PageUtil.WriteReponseXML(response, "0", "ok");
		return null;
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			RptTemplate template = (RptTemplate) service.get(RptTemplate.class, id);
			PageUtil.WriteReponseText(response, template.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/savefile")
	public String savefile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
		String tempFileName = file.getOriginalFilename();
		System.out.println("fileName---->" + tempFileName);

		if (tempFileName.isEmpty()) {
			return "";
		}

		RptTemplate bean = new RptTemplate();

		String ext = tempFileName.substring(tempFileName.lastIndexOf("."));
		if (ext == null || ext.length() == 0 || !checkType(ext)) {
			return "error";
		}

		String fileName = request.getParameter("name");
		if (fileName == null || fileName.length() == 0) {
			fileName = tempFileName.substring(0, tempFileName.lastIndexOf("."));
		}

		bean.setName(fileName);
		bean.setCreateTime(new Date());
		bean.setLastModifyTime(new Date());
		bean.setType(request.getParameter("type"));

		return "/success";
	}

	public boolean checkType(String type) {
		List<String> list = new ArrayList<String>();
		list.add(".zip");
		return list.contains(type);
	}
}
