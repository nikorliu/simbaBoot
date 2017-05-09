package com.simba.permission.model;

import java.io.Serializable;

public class RolePermission implements Serializable {

	private static final long serialVersionUID = -6978350619480118473L;

	public RolePermission() {
	}

	public RolePermission(String roleName, int permissionID) {
		this.roleName = roleName;
		this.permissionID = permissionID;
	}

	private int id;

	private String roleName;

	private int permissionID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getPermissionID() {
		return permissionID;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RolePermission [id=");
		builder.append(id);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", permissionID=");
		builder.append(permissionID);
		builder.append("]");
		return builder.toString();
	}

}
