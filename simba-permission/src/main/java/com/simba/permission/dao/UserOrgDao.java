package com.simba.permission.dao;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.UserOrg;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface UserOrgDao {

	void add(UserOrg userOrg);

	void update(UserOrg userOrg);

	void delete(int id);

	List<UserOrg> listAll();

	int count();
	
	List<UserOrg> page(Pager page);

	UserOrg get(int id);
	
	UserOrg getBy(String field, Object value);

	UserOrg getByAnd(String field1, Object value1, String field2, Object value2);

	UserOrg getByOr(String field1, Object value1, String field2, Object value2);

	List<UserOrg> listBy(String field, Object value);

	List<UserOrg> listByAnd(String field1, Object value1, String field2, Object value2);

	List<UserOrg> listByOr(String field1, Object value1, String field2, Object value2);

	List<UserOrg> pageBy(String field, Object value, Pager page);

	List<UserOrg> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<UserOrg> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

	void deleteByUserAccount(String account);

	int countBy(String field, Object value);

}
