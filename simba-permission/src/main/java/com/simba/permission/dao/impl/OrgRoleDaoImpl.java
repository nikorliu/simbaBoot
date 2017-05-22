package com.simba.permission.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.OrgRoleDao;
import com.simba.permission.model.OrgRole;

/**
 * Dao实现类
 * 
 * @author caozj
 * 
 */
@Repository
public class OrgRoleDaoImpl implements OrgRoleDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "orgRole";

	@Override
	public void add(OrgRole orgRole) {
		String sql = "insert into " + table + "( orgID, roleName) values(?,?)";
		jdbc.updateForBoolean(sql, orgRole.getOrgID(), orgRole.getRoleName());
	}

	@Override
	public void update(OrgRole orgRole) {
		String sql = "update " + table + " set  orgID = ? , roleName = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, orgRole.getOrgID(), orgRole.getRoleName(), orgRole.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<OrgRole> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, OrgRole.class, page);
	}

	@Override
	public List<OrgRole> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, OrgRole.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public OrgRole get(Integer id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, OrgRole.class, id);
	}

	@Override
	public OrgRole getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, OrgRole.class, value);
	}

	@Override
	public OrgRole getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, OrgRole.class, value1, value2);
	}

	@Override
	public OrgRole getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, OrgRole.class, value1, value2);
	}

	@Override
	public List<OrgRole> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, OrgRole.class, value);
	}

	@Override
	public List<OrgRole> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, OrgRole.class, value1, value2);
	}

	@Override
	public List<OrgRole> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, OrgRole.class, value1, value2);
	}

	@Override
	public List<OrgRole> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, OrgRole.class, page, param);
	}

	@Override
	public List<OrgRole> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, OrgRole.class, page, param);
	}

	@Override
	public List<OrgRole> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, OrgRole.class, page, param);
	}

	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

	@Override
	public void deleteByOrgID(int orgID) {
		String sql = "delete from " + table + " where orgID = ? ";
		jdbc.updateForBoolean(sql, orgID);
	}

}
