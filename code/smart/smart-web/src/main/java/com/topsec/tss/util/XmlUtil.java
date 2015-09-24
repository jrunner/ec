package com.topsec.tss.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmlUtil {

	/**
	 * 加载XML文件
	 */
	public Document loadXml(String filepath) throws FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = null;
		reader.setEncoding("UTF-8");
		doc = reader.read(new FileInputStream(filepath));
		return doc;
	}

	/**
	 * 写文件
	 */
	public static void writerXml(String filePath, Document doc) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		FileOutputStream stream = new FileOutputStream(filePath);
		XMLWriter output = new XMLWriter(stream, format);
		output.write(doc);
		output.flush();
		output.close();
	}

	/**
	 * 得到schedule中的trigger名和group名
	 */
	public Element getJobKeys(String xpath) throws FileNotFoundException, DocumentException {
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.substring(0, path.indexOf("WEB-INF"));
		Document doc = loadXml(path + "scheduleConfig.xml");
		Element ele = (Element) doc.selectObject(xpath);
		return ele;
	}

	/**
	 * 修改schedule中的trigger名和group名
	 */
	public void updateJobKeys(Map<String, String> param) throws DocumentException, IOException {
		String path = XmlUtil.class.getResource("/").getPath();
		Document doc = loadXml(path + "scheduleConfig.xml");
		Element ele = (Element) doc.selectObject("/schedules/schedule[@id='" + param.get("id") + "']");
		if(param.containsKey("status")) {
			ele.element("state").setText(param.get("status"));
		}else {
			ele.element("group-name").setText(param.get("group-name"));
			ele.element("job-name").setText(param.get("job-name"));
			ele.element("display-name").setText(param.get("display-name"));
			ele.element("cron").setText(param.get("cron"));
			ele.element("job-class").setText(param.get("job-class"));
			//ele.element("state").setText(param.get("status"));
		}
		writerXml(path + "scheduleConfig.xml", doc);

	}

	/**
	 * 获取任务列表
	 */
	public Map getList(String id) throws FileNotFoundException, DocumentException {
		String path = XmlUtil.class.getResource("/").getPath() + "scheduleConfig.xml";
		Document doc = new XmlUtil().loadXml(path);
		Map taskMap = new HashMap();
		if(!"".equals(id) && null != id) {
			Element ele = (Element) doc.selectObject("/schedules/schedule[@id='" + id + "']");
			Map map = setValue(ele);
			taskMap.put(map.get("id"), map);
		}else {
			List items = doc.getRootElement().elements();
			for (int i = 0; i < items.size(); i++) {
				Element ele = (Element) items.get(i);
				Map map = setValue(ele);
				taskMap.put(map.get("id"), map);
			}
		}
		return taskMap;
	}
	
	public static Map setValue(Element element) {
		Map map = new HashMap();
		if (element.element("display-name") != null)
			map.put("display-name", element.element("display-name").getTextTrim());
		if (element.element("group-name") != null)
			map.put("group-name", element.element("group-name").getTextTrim());
		if (element.element("job-name") != null)
			map.put("job-name", element.element("job-name").getTextTrim());
		if (element.element("cron") != null)
			map.put("cron", element.element("cron").getTextTrim());
		if (element.element("state") != null)
			map.put("status", element.element("state").getTextTrim());
		if (element.element("job-class") != null)
			map.put("job-class", element.element("job-class").getTextTrim());
		map.put("id", element.attribute("id").getText());
		return map;
	}
	/*public static void main(String[] args) {
		try {
			Map map = new XmlUtil().getList(null);
			for(Object key : map.keySet()) {
				Map<String ,String> list = (Map<String ,String>)map.get(key);
				System.out.println("==="+list.get("group-name"));
				Map<String ,String> m = (Map<String ,String>)map.get(key);
				for (Map.Entry<String ,String> entry : m.entrySet()) {   
		            //System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());   
		        }  
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}*/
}
