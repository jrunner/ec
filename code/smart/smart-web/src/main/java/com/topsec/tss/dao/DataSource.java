package com.topsec.tss.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSource {

	private static List list = new ArrayList();

	static {
		list.addAll(JsonSource.getVipTree());
	}

	public static void saveOrUpdate(Map<String, String> map, String type) {
		Map _map = get(map.get("id"), type);
		// 如果查询不到新加,否则更新
		if (_map == null) {
			map.put("type", type);
			list.add(map);
		} else {
			for (String key : map.keySet()) {
				_map.put(key, map.get(key));
			}
		}

	}

	public static void del(String id, String type) {
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (isok(id, type, map)) {
				list.remove(map);
			}
		}
	}

	public static Map get(String id, String type) {
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (isok(id, type, map)) {
				return map;
			}
		}
		return null;
	}

	public static List list() {
		return list;
	}

	public static List list(String type) {
		List rows = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (type.equals(map.get("type"))) {
				rows.add(map);
			}

		}
		return rows;
	}

	public static boolean isok(String id, String type, Map map) {
		return (id.equals(map.get("id").toString()) && type.equals(map.get("type")));
	}

}
