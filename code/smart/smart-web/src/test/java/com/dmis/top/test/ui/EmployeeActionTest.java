package com.dmis.top.test.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dmis.top.persistence.Employee;
import com.dmis.top.persistence.RptTemplate;
import com.dmis.top.tool.json.JsonUtil;
import com.dmis.top.util.PageResult;
import com.dmis.top.util.PageUtil;

public class EmployeeActionTest extends BaseAction {

	public static void main(String[] args) {
		EmployeeActionTest t = new EmployeeActionTest();
		t.test_modify();
		t.test_list();
		System.out.println("ok");
	}

	public String test_list() {
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

	public void test_modify() {
		Employee emp = new Employee();
		emp.setResName("杨过");
		emp.setAge("30");
		emp.setCreateTime(new Date().getTime());
		emp.setLastModifyTime(new Date().getTime());
		emp.setNo("30");
		emp.setCurPhone("13439083552");
		emp.setEmpArea("外籍");
		emp.setSalaryArea("北京");
		service.saveOrUpdate(emp);
	}
}
