package com.simba.permission.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户扩展对象
 * 
 * @author caozj
 *
 */
public class UserExt implements Serializable {

	private static final long serialVersionUID = 7433216043865737231L;

	private String userAccount;

	private Map<String, String> extMap;

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserExt [userAccount=");
		builder.append(userAccount);
		builder.append(", extMap=");
		builder.append(extMap);
		builder.append("]");
		return builder.toString();
	}

}
