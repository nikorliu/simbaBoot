package com.simba.permission.dao;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.OrgRole;

/**
 * Dao
 * 
 * @author caozj
 * 
 */
public interface OrgRoleDao {

	void add(OrgRole orgRole);

	void update(OrgRole orgRole);

	void delete(Integer id);

	List<OrgRole> listAll();

	int count();

	int countBy(String field, Object value);

	List<OrgRole> page(Pager page);

	OrgRole get(Integer id);

	OrgRole getBy(String field, Object value);

	OrgRole getByAnd(String field1, Object value1, String field2, Object value2);

	OrgRole getByOr(String field1, Object value1, String field2, Object value2);

	List<OrgRole> listBy(String field, Object value);

	List<OrgRole> listByAnd(String field1, Object value1, String field2, Object value2);

	List<OrgRole> listByOr(String field1, Object value1, String field2, Object value2);

	List<OrgRole> pageBy(String field, Object value, Pager page);

	List<OrgRole> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<OrgRole> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

	void deleteByOrgID(int orgID);

}
