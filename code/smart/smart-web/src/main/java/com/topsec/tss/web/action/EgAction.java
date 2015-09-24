package com.topsec.tss.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.topsec.tss.util.PageUtil;

/**
 * @author ypf
 */
@SuppressWarnings("unchecked")
public class EgAction extends DispatchAction {

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List list = new ArrayList();
			Map m1 = new HashMap();
			m1.put("id", "01");
			m1.put("no", "410311xxxxxxxxxxx");
			m1.put("resName", "ypf");
			list.add(m1);

			String json = new Gson().toJson(list);
			System.out.println(json);
			PageUtil.WriteReponseText(response, json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward toUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("update");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("update");
	}
	
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("update");
	}
	
	

}
