package com.topsec.tss.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.t2t.examples.file.FileUtil;

public class JsonSource {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		getVipTree();
	}

	public static List getVipTree() {
		List list = null;
		try {
			InputStream in = JsonSource.class.getResourceAsStream("/tree.txt");
			String json = FileUtil.inputStream2String(in);
			System.out.println(json);
			JsonSource test = new JsonSource();

			list = test.fromJson2List(json);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
				Map map = (Map) list.get(i);
				if ("subinstry".equals(map.get("icon"))) {
					map.put("type", DataSourceConstant.VIP_SUBINDURY);
				}
				if ("baseinstry".equals(map.get("icon"))) {
					map.put("type", DataSourceConstant.VIP_BASEINDURY);
				}
				if ("unit".equals(map.get("icon"))) {
					map.put("type", DataSourceConstant.VIP_UNIT);
				}
			}
			System.out.println("ok tree");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Map fromJson2Map(String keyK, String keyV, String json) {
		Gson gson = new Gson();
		List rows = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		Map map = new HashMap();
		for (int i = 0; i < rows.size(); i++) {
			HashMap row = (HashMap) rows.get(i);
			map.put(row.get(keyK), row.get(keyV));
		}
		return map;
	}

	public Map fromList2Map(String keyK, String keyV, List list) {
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			HashMap row = (HashMap) list.get(i);
			map.put(row.get(keyK), row.get(keyV));
		}
		return map;
	}

	public List fromJson2List(String json) {
		Gson gson = new Gson();
		List list = new ArrayList();
		List rows = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		for (int i = 0; i < rows.size(); i++) {
			HashMap row = (HashMap) rows.get(i);
			list.add(row);
		}
		return list;
	}

}
