package com.topsec.tss.web.action.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.topsec.tss.dao.DataSource;
import com.topsec.tss.dao.DataSourceConstant;
import com.topsec.tss.util.Functions;
import com.topsec.tss.util.PageUtil;

/**
 * @author 重点单位
 */
@SuppressWarnings("unchecked")
public class VipUnitAction extends DispatchAction {

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List list = new ArrayList();

			String json = new Gson().toJson(list);
			System.out.println(json);
			PageUtil.WriteReponseText(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward baselist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			List list = DataSource.list(DataSourceConstant.VIP_SUBINDURY);

			String json = new Gson().toJson(list);
			System.out.println(json);
			PageUtil.WriteReponseText(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward sublist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List list = DataSource.list(DataSourceConstant.VIP_UNIT);

			String json = new Gson().toJson(list);
			System.out.println(json);
			PageUtil.WriteReponseText(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改主行业
	public ActionForward updateBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map map = new HashMap();
			// 增加
			if (Functions.isSpace(request.getParameter("id"))) {
				map.put("id", UUID.randomUUID().toString());
				map.put("_code", map.get("id"));// 行业名称
			}
			// 修改
			if (!Functions.isSpace(request.getParameter("id"))) {
				map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_BASEINDURY);
			}
			map.put("resName", request.getParameter("resName"));// 行业名称
			DataSource.saveOrUpdate(map, DataSourceConstant.VIP_BASEINDURY);
			String xml = PageUtil.toConvert("id", map.get("id"));
			xml += PageUtil.toConvert("code", map.get("_code"));
			xml += PageUtil.toConvert("resName", map.get("resName"));
			xml += PageUtil.toConvert("state", !Functions.isSpace(request.getParameter("id")));// true为编辑,false为添加
			PageUtil.WriteReponseXML(response, "0", "ok", xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改子行业
	public ActionForward updateSub(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map map = new HashMap();
			// 增加
			if (Functions.isSpace(request.getParameter("id"))) {
				map.put("id", UUID.randomUUID().toString());
				map.put("_code", map.get("id"));// 行业名称
			}
			// 修改
			if (!Functions.isSpace(request.getParameter("id"))) {
				map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_SUBINDURY);
			}
			map.put("resName", request.getParameter("resName"));// 行业名称
			DataSource.saveOrUpdate(map, DataSourceConstant.VIP_SUBINDURY);
			String xml = PageUtil.toConvert("id", map.get("id"));
			xml += PageUtil.toConvert("resName", map.get("resName"));
			xml += PageUtil.toConvert("code", map.get("_code"));
			xml += PageUtil.toConvert("state", !Functions.isSpace(request.getParameter("id")));// true为编辑,false为添加
			PageUtil.WriteReponseXML(response, "0", "ok", xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改单位
	public ActionForward updateUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap();
			// 增加
			if (Functions.isSpace(request.getParameter("id"))) {
				map.put("id", UUID.randomUUID().toString());
				map.put("_code", map.get("id"));// 行业名称
			}
			// 修改
			if (!Functions.isSpace(request.getParameter("id"))) {
				map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_UNIT);
			}
			map.put("resName", request.getParameter("resName"));// 行业名称
			map.put("remark", request.getParameter("remark"));// 行业名称
			DataSource.saveOrUpdate(map, DataSourceConstant.VIP_UNIT);
			String xml = PageUtil.toConvert("id", map.get("id"));
			xml += PageUtil.toConvert("resName", map.get("resName"));
			xml += PageUtil.toConvert("code", map.get("_code"));
			xml += PageUtil.toConvert("state", !Functions.isSpace(request.getParameter("id")));// true为编辑,false为添加
			PageUtil.WriteReponseXML(response, "0", "ok", xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward delBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_BASEINDURY);
		DataSource.del(request.getParameter("id"), DataSourceConstant.VIP_BASEINDURY);
		String xml = PageUtil.toConvert("code", map.get("_code"));
		PageUtil.WriteReponseXML(response, "0", "delBase_ok");
		return null;
	}

	public ActionForward delSub(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DataSource.del(request.getParameter("id"), DataSourceConstant.VIP_SUBINDURY);
		PageUtil.WriteReponseXML(response, "0", "delSub_ok");
		return null;
	}

	public ActionForward delUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_UNIT);
		DataSource.del(request.getParameter("id"), DataSourceConstant.VIP_UNIT);
		String xml = PageUtil.toConvert("code", map.get("_code"));
		PageUtil.WriteReponseXML(response, "0", "delUnit_ok", xml);
		return null;
	}

	// 主行业
	public ActionForward toBaseList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("baselist");
	}

	// 子行业
	public ActionForward toSubList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("sublist");
	}

	// 单位
	public ActionForward toUnitList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_UNIT);
		if (map == null) {
			map = new HashMap();
		}
		System.out.println(map);
		request.setAttribute("bean", map);
		return mapping.findForward("unitlist");
	}

	public ActionForward toBaseUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_BASEINDURY);
		if (map == null) {
			map = new HashMap();
		}
		request.setAttribute("bean", map);
		return mapping.findForward("baseupdate");
	}

	public ActionForward toSubUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map map = DataSource.get(request.getParameter("id"), DataSourceConstant.VIP_SUBINDURY);
		if (map == null) {
			map = new HashMap();
		}
		request.setAttribute("bean", map);
		return mapping.findForward("subupdate");
	}

}
