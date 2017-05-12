package com.simba.permission.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构扩展描述对象
 * 
 * @author caozj
 *
 */
public class OrgExtDesc {

	private static Map<String, String> descMap = new HashMap<>();

	public static void put(String key, String desc) {
		descMap.put(key, desc);
	}

	public static Map<String, String> getAllDesc() {
		return descMap;
	}
}
