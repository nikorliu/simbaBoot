package com.simba.permission.model;

public class OrgRole {

	private int id;

	private int orgID;

	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
