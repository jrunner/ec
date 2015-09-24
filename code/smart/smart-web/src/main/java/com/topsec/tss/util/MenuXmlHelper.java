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
public class MenuXmlHelper {

	String filePath = null;

	public MenuXmlHelper(String filePath) {
		this.filePath = filePath;
	}

	public static void main(String args[]) throws DocumentException {
		String filePath = "D:\\menu.xml";
		MenuXmlHelper manager = new MenuXmlHelper(filePath);

		List list = manager.getListByLevel(2,"002", true);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println("over");
	}

	/**
	 * 将xml全部读入集合中
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 菜单中所有entry节点
	 */
	public List getList(String filePath) throws DocumentException {
		Document doc = ConfigXml.loadXml(filePath);
		Element root = doc.getRootElement();

		List list = new ArrayList();
		readElement(list, root);
		return list;
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
		List list = getList(filePath);
		List listResult = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = (Map) list.get(i);
			if (child) {
				if (Integer.parseInt(map.get("_level")) >= level && map.get("_code").startsWith(name)) {
					listResult.add(map);
				}
			}
			if (!child) {
				if (Integer.parseInt(map.get("_level")) == level && map.get("_code").startsWith(name)) {
					listResult.add(map);
				}
			}

		}
		return listResult;
	}

	private String defaultFilePath(Element e, String name) {
		if (e == null)
			return "";

		name = e.attributeValue("name") == null ? "" : e.attributeValue("name");

		if (e.getParent() != null && e.getParent().attributeValue("name") != null) {
			name = defaultFilePath(e.getParent(), name) + "/" + name;
		}
		return name;
	}

	/**
	 * 获取Element下所有节点(不保护传入的节点)
	 * 
	 * @param item
	 *            存放的集合
	 * @param el
	 *            传入的Element
	 */
	private void readElement(List item, Element el) {
		List list = el.elements();
		for (int i = 0; i < list.size(); i++) {
			Element e = (Element) list.get(i);
			Map map = readAttribute(e);

			item.add(map);

			if (e.elements().size() > 0) {
				readElement(item, e);
			}
		}
	}

	/**
	 * 根据节点获取属性键值对,并放入map集合中
	 * 
	 * @param e
	 *            节点
	 * @return Map
	 */
	private Map readAttribute(Element e) {
		Map map = new HashMap();
		for (int i = 0; i < e.attributeCount(); i++) {
			Attribute a = e.attribute(i);
			map.put(a.getName(), a.getText());
		}

		map.put("_level", (e.getPath().split("/").length - 2) + "");// 深度,跟为0
		String name = e.attributeValue("name") == null ? "" : e.attributeValue("name");
		String pname = e.getParent().attributeValue("name") == null ? "" : e.getParent().attributeValue("name");
		map.put("_code", defaultFilePath(e, name));
		map.put("_pcode", defaultFilePath(e.getParent(), pname));

		return map;
	}
}