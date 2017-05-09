package com.simba.permission.model;

import java.io.Serializable;

/**
 * 用户机构
 * 
 * @author caozj
 *
 */
public class UserOrg implements Serializable {

	private static final long serialVersionUID = -7651592285832404356L;

	private int id;

	/**
	 * 用户账号
	 */
	private String userAccount;

	/**
	 * 机构ID
	 */
	private int orgID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOrg [id=");
		builder.append(id);
		builder.append(", userAccount=");
		builder.append(userAccount);
		builder.append(", orgID=");
		builder.append(orgID);
		builder.append("]");
		return builder.toString();
	}

}
