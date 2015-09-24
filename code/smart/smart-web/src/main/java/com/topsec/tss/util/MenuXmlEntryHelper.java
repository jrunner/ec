package com.topsec.tss.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * @author ypf 菜单XML读取,List集合中的对象为HashMap
 */
public class MenuXmlEntryHelper {

	String filePath = null;

	public MenuXmlEntryHelper(String filePath) {
		this.filePath = filePath;
	}

	public static void main(String args[]) throws DocumentException {
		String filePath = "D:\\menu.xml";
		MenuXmlEntryHelper helper = new MenuXmlEntryHelper(filePath);

		List list = helper.getListByLevel(2, "/base", true);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println("over");
	}

	/**
	 * 根据深度获取节点
	 * 
	 * @param level
	 * @return Object
	 */
	public List getListByLevel(int level, boolean child) throws DocumentException {
		return getListByLevel(level, "", child);
	}

	public List getListByLevel(int level, String name, boolean child) throws DocumentException {
		List list = readElement();
		List listResult = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = (Map) list.get(i);
			if (child) {
				if (Integer.parseInt(map.get("_level")) >= level && map.get("_code").startsWith(name)) {
					listResult.add(map);
				}
			}
			if (!child) {
				if (Integer.parseInt(map.get("_level")) == level && map.get("_pcode").startsWith(name)) {
					listResult.add(map);
				}
			}

		}
		return listResult;
	}

	/**
	 * 获取Element下所有节点
	 * 
	 * @param item
	 *            存放的集合
	 * @param el
	 *            传入的Element
	 */
	public List readElement() throws DocumentException {
		Document doc = ConfigXml.loadXml(filePath);// 读取xml文件
		Element root = doc.getRootElement();// 得到根标签
		List list = new ArrayList();

		for (int i = 0; i < root.elements("entry").size(); i++) {
			Element e = (Element) root.elements().get(i);
			Map map = readAttribute(e);
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据节点获取属性键值对,并放入map集合中
	 * 
	 * @param e
	 *            节点
	 * @return Map
	 */
	private Map readAttribute(Element e) {
		Map<String,String> map = new HashMap();
		for (int i = 0; i < e.attributeCount(); i++) {
			Attribute a = e.attribute(i);
			map.put(a.getName(), a.getText());
			
		}
		String code = map.get("_code");
		map.put("_level", (code.split("/").length)-1 + "");// 深度,跟为0
		if(map.get("_pcode")==null || map.get("_pcode").trim().length()==0){
			String pcode = code.substring(0, code.lastIndexOf("/"));
			map.put("_pcode",pcode);
		}
		return map;
	}
}