package com.topsec.tss.web.action;

import com.google.gson.Gson;
import com.topsec.tss.util.MenuXmlEntryHelper;
import com.topsec.tss.util.PageUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author ypf
 */
@Controller
@RequestMapping(value = "/menu", method = {RequestMethod.POST, RequestMethod.GET})
public class MenuAction {

    public String getBasePath(HttpServletRequest request) {
        return request.getRealPath("/") + "WEB-INF/classes/menu-sh.xml";
    }

    @RequestMapping(value = "/header")
    public String header(HttpServletRequest request, HttpServletResponse response) {
        try {
            MenuXmlEntryHelper helper = new MenuXmlEntryHelper(getBasePath(request));
            List list = helper.getListByLevel(1, false);// 获取一级菜单

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                System.out.println(map);
            }
            request.setAttribute("list", list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "header";
    }

    @RequestMapping(value = "/center")
    public String center(HttpServletRequest request, HttpServletResponse response) {
        try {
            MenuXmlEntryHelper helper = new MenuXmlEntryHelper(getBasePath(request));
            String code = request.getParameter("code");
            List list = helper.getListByLevel(2, code, false);// 获取二级菜单

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                System.out.println(map);
            }
            request.setAttribute("list", list);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "center";
    }

    @RequestMapping(value = "/menuAjax")
    public String menuAjax(HttpServletRequest request, HttpServletResponse response) {
        try {
            MenuXmlEntryHelper helper = new MenuXmlEntryHelper(getBasePath(request));
            String menul2 = request.getParameter("name");
            List list = helper.getListByLevel(2, menul2, true);// 获取二级菜单
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = (Map) list.get(i);
                if (map.get("_code").equals(menul2)) {
                    list.remove(map);
                }
                System.out.println(map);
            }

            String json = new Gson().toJson(list);
            System.out.println(json);
            PageUtil.WriteReponseText(response, json);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
