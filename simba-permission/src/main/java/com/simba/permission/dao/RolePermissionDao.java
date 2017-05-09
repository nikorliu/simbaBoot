package com.simba.permission.dao;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.RolePermission;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface RolePermissionDao {

	void add(RolePermission rolePermission);

	void update(RolePermission rolePermission);

	void delete(int id);

	List<RolePermission> listAll();

	int count();
	
	List<RolePermission> page(Pager page);

	RolePermission get(int id);
	
	RolePermission getBy(String field, Object value);

	RolePermission getByAnd(String field1, Object value1, String field2, Object value2);

	RolePermission getByOr(String field1, Object value1, String field2, Object value2);

	List<RolePermission> listBy(String field, Object value);

	List<RolePermission> listByAnd(String field1, Object value1, String field2, Object value2);

	List<RolePermission> listByOr(String field1, Object value1, String field2, Object value2);

	List<RolePermission> pageBy(String field, Object value, Pager page);

	List<RolePermission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<RolePermission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

	void deleteByRoleName(String name);

}
