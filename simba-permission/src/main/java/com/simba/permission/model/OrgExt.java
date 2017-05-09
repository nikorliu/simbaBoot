package com.simba.permission.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 机构扩展
 * 
 * @author caozj
 *
 */
public class OrgExt implements Serializable {

	private static final long serialVersionUID = 8868839461469250374L;

	private int id;

	private Map<String, String> extMap;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrgExt [id=");
		builder.append(id);
		builder.append(", extMap=");
		builder.append(extMap);
		builder.append("]");
		return builder.toString();
	}

}
