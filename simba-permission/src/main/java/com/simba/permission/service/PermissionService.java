package com.simba.permission.service;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.Permission;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface PermissionService {

	void add(Permission permission);

	void update(Permission permission);

	void delete(int id);

	List<Permission> listAll();

	int count();

	List<Permission> page(Pager page);

	Permission get(int id);

	void batchDelete(List<Integer> idList);

	Permission getBy(String field, Object value);

	Permission getByAnd(String field1, Object value1, String field2, Object value2);

	Permission getByOr(String field1, Object value1, String field2, Object value2);

	List<Permission> listBy(String field, Object value);

	List<Permission> listByAnd(String field1, Object value1, String field2, Object value2);

	List<Permission> listByOr(String field1, Object value1, String field2, Object value2);

	List<Permission> pageBy(String field, Object value, Pager page);

	List<Permission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<Permission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

	List<Permission> listChildren(int parentID);
}
