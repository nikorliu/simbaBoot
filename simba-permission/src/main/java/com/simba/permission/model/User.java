package com.simba.permission.model;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author caozj
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2385355894980010312L;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String pwd;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [account=");
		builder.append(account);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append("]");
		return builder.toString();
	}

}
