package com.topsec.tss.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ConfigXml {
	public static void main(String[] args) throws DocumentException {
		String filepath = "E:\\xml\\geshui.xml";
		Document doc = ConfigXml.loadXml(filepath);
		Element root = doc.getRootElement();
		for (int i = 0; i < root.elements().size(); i++) {
			Element ele = (Element) root.elements().get(i);
			System.out.println(ele.attributeValue("name"));
		}
	}

	public static Document loadXml(String filepath) throws DocumentException {
		Document doc = null;
		SAXReader reader = new SAXReader();
		doc = reader.read(filepath);
		return doc;
	}

	public static void writerXml(String filePath, Document doc, String encode) throws IOException {
		if (encode == null) {
			encode = "UTF-8";
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encode);
		FileOutputStream stream = new FileOutputStream(filePath);
		XMLWriter output = new XMLWriter(stream, format);
		output.write(doc);
		output.flush();
		output.close();
	}
}
