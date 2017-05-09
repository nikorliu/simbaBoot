package com.simba.permission.dao;

import java.util.List;

import com.simba.permission.model.OrgExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface OrgExtDao {

	List<String> showAllColumns();

	void addColumn(String column);

	OrgExt get(int orgID);

	void add(OrgExt orgExt);

	void update(OrgExt orgExt);
}
