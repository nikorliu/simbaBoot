package com.simba.permission.dao;

import java.util.List;

import com.simba.permission.model.UserRole;

public interface UserRoleDao {

	void add(UserRole userRole);

	void deleteByUserAccount(String userAccount);

	void deleteByRole(String roleName);

	List<UserRole> listByUserAccount(String userAccount);

}
