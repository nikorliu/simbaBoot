package com.simba.registry.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表数据
 * 
 * @author caozj
 *
 */
public class RegistryTableData {

	private Map<String, String> data = null;

	private RegistryTableData() {
		data = new HashMap<>();
	}

	private static final class RegistryTableDataHolder {
		private static final RegistryTableData instance = new RegistryTableData();
	}

	public static RegistryTableData getInstance() {
		return RegistryTableDataHolder.instance;
	}

	public void remove(String key) {
		data.remove(key);
	}

	public void add(String key, String value) {
		data.put(key, value);
	}

	public String get(String key) {
		return data.get(key);
	}
}
