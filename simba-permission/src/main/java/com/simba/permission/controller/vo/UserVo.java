package com.simba.permission.controller.vo;

import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;

public class UserVo {

	private User user;

	private UserExt userExt;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserExt getUserExt() {
		return userExt;
	}

	public void setUserExt(UserExt userExt) {
		this.userExt = userExt;
	}

}
