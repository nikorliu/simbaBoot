package com.simba.permission.dao;

import java.util.List;

import com.simba.permission.model.UserExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface UserExtDao {

	List<String> showAllColumns();

	void addColumn(String column);

	UserExt get(String userAccount);

	void delete(String account);

	void add(UserExt userExt);

	void update(UserExt userExt);
}
