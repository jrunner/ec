package com.topsec.tss.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Page辅助类
 * 
 * @author ypf
 * 
 */
public class PageUtil {
	public static final String KEY_PAGE = "page";
	public static final String KEY_LIST = "list";
	public static final String SORT_DESC = "desc";
	public static final String SORT_ASC = "asc";

	public static void WriteReponseXML(HttpServletResponse response, String info, String msg, String... strings) {
		response.reset();
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String xml = "<?xml version = \"1.0\" encoding=\"UTF-8\"?>";
		xml += "<response>";
		xml += "<info>" + info + "</info>";
		xml += "<msg>" + msg + "</msg>";
		if (strings.length > 0)
			xml += strings[0];
		xml += "</response>";
		try {
			response.getWriter().write(xml);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void WriteReponseText(HttpServletResponse response, String info) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().write(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String toConvert(Object element, Object msg) {
		if (msg == null || element == null) {
			return "";
		}

		msg = msg.toString().trim();
		element = element.toString().trim().toLowerCase();

		String info = "<" + element + ">" + msg + "</" + element + ">";
		return info;
	}
}
