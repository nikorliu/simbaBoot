package com.simba.permission.service;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.UserOrg;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface UserOrgService {

	void add(UserOrg userOrg);

	void update(UserOrg userOrg);

	void delete(int id);

	List<UserOrg> listAll();

	int count();

	List<UserOrg> page(Pager page);

	UserOrg get(int id);

	void batchDelete(List<Integer> idList);

	int countBy(String field, Object value);

	UserOrg getBy(String field, Object value);

	UserOrg getByAnd(String field1, Object value1, String field2, Object value2);

	UserOrg getByOr(String field1, Object value1, String field2, Object value2);

	List<UserOrg> listBy(String field, Object value);

	List<UserOrg> listByAnd(String field1, Object value1, String field2, Object value2);

	List<UserOrg> listByOr(String field1, Object value1, String field2, Object value2);

	List<UserOrg> pageBy(String field, Object value, Pager page);

	List<UserOrg> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<UserOrg> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);
}
