package com.simba.permission.service;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface OrgService {

	int add(Org org);

	void update(Org org);

	void delete(int id);

	List<Org> listAll();

	int count();

	List<Org> page(Pager page);

	Org get(int id);

	void batchDelete(List<Integer> idList);

	Org getBy(String field, Object value);

	Org getByAnd(String field1, Object value1, String field2, Object value2);

	Org getByOr(String field1, Object value1, String field2, Object value2);

	List<Org> listBy(String field, Object value);

	List<Org> listByAnd(String field1, Object value1, String field2, Object value2);

	List<Org> listByOr(String field1, Object value1, String field2, Object value2);

	List<Org> pageBy(String field, Object value, Pager page);

	List<Org> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<Org> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

	public void checkAndCreateOrgExt();

	List<Org> listChildren(int parentID);

	void add(Org org, OrgExt orgExt);

	OrgExt getOrgExt(int id);

	void update(Org org, OrgExt orgExt);
}
