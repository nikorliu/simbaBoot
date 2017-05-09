package com.simba.permission.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.OrgExtDao;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.OrgExtDesc;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class OrgExtDaoImpl implements OrgExtDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "orgExt";

	@Override
	public List<String> showAllColumns() {
		String sql = "select column_name from information_schema.COLUMNS where table_name = ?";
		return jdbc.queryForStrings(sql, table);
	}

	@Override
	public void addColumn(String column) {
		String sql = "ALTER TABLE " + table + " ADD " + column + " varchar(256)";
		jdbc.updateForBoolean(sql);
	}

	@Override
	public OrgExt get(int orgID) {
		String sql = "select * from " + table + " where id = ? ";
		Map<String, Object> ext = jdbc.queryForMap(sql, orgID);
		OrgExt orgExt = new OrgExt();
		orgExt.setId(orgID);
		Map<String, String> extMap = new HashMap<>();
		orgExt.setExtMap(extMap);
		if (ext != null && ext.size() > 0) {
			ext.forEach((key, value) -> {
				extMap.put(key, value + "");
			});
		}
		return orgExt;
	}

	@Override
	public void add(OrgExt orgExt) {
		String sql = "insert into " + table + "(id";
		Map<String, String> extMap = orgExt.getExtMap();
		String columns = "";
		String params = "values(?";
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		StatementParameter param = new StatementParameter();
		param.setInt(orgExt.getId());
		for (String key : descMap.keySet()) {
			columns += "," + key;
			param.set(extMap.get(key));
			params += ",?";
		}
		sql = sql + columns + ")" + params + ")";
		jdbc.updateForBoolean(sql, param);
	}

	@Override
	public void update(OrgExt orgExt) {
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		if (descMap.size() == 0) {
			return;
		}
		String sql = "update " + table + " set ";
		Map<String, String> extMap = orgExt.getExtMap();
		StatementParameter param = new StatementParameter();
		for (String key : descMap.keySet()) {
			sql += key + " = ?,";
			param.set(extMap.get(key));
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " where id = ? ";
		param.setInt(orgExt.getId());
		jdbc.updateForBoolean(sql, param);
	}
}
