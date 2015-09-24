package com.dmis.top.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TemplateUtil {
	private String chartset = "UTF-8";

	public void setChartset(String chartset) {
		if (chartset == null || chartset.length() == 0)
			return;

		this.chartset = chartset;
	}

	// 构建字符流
	public String readTemplate(String pathName) throws IOException {
		FileInputStream inputstream = new FileInputStream(pathName);
		return readTemplate(inputstream);
	}

	// 构建字符流
	public String readTemplate(File file) throws IOException {
		FileInputStream inputstream = new FileInputStream(file);
		return readTemplate(inputstream);
	}

	// 构建字符流
	public String readTemplate(FileInputStream inputstream) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputstream, chartset));
		String str = null;
		while ((str = bufferReader.readLine()) != null) {
			sb.append(str + Constant.LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String pathName = TemplateUtil.class.getResource("").getPath() + "xls.xml";
		System.out.println(new TemplateUtil().readTemplate(pathName));
	}
}
