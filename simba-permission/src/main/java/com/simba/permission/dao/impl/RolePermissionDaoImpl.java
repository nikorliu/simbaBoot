package com.simba.permission.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.RolePermissionDao;
import com.simba.permission.model.RolePermission;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class RolePermissionDaoImpl implements RolePermissionDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "rolePermission";

	@Override
	public void add(RolePermission rolePermission) {
		String sql = "insert into " + table + "( roleName, permissionID) values(?,?)";
		jdbc.updateForBoolean(sql, rolePermission.getRoleName(), rolePermission.getPermissionID());
	}

	@Override
	public void update(RolePermission rolePermission) {
		String sql = "update " + table + " set  roleName = ? , permissionID = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, rolePermission.getRoleName(), rolePermission.getPermissionID(), rolePermission.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<RolePermission> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, RolePermission.class, page);
	}

	@Override
	public List<RolePermission> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, RolePermission.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public RolePermission get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, RolePermission.class, id);
	}

	@Override
	public RolePermission getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, RolePermission.class, value);
	}

	@Override
	public RolePermission getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, RolePermission.class, value1, value2);
	}

	@Override
	public RolePermission getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, RolePermission.class, value1, value2);
	}

	@Override
	public List<RolePermission> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, RolePermission.class, value);
	}

	@Override
	public List<RolePermission> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, RolePermission.class, value1, value2);
	}

	@Override
	public List<RolePermission> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, RolePermission.class, value1, value2);
	}

	@Override
	public List<RolePermission> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, RolePermission.class, page, param);
	}

	@Override
	public List<RolePermission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RolePermission.class, page, param);
	}

	@Override
	public List<RolePermission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RolePermission.class, page, param);
	}

	@Override
	public void deleteByRoleName(String name) {
		String sql = "delete from " + table + " where roleName = ? ";
		jdbc.updateForBoolean(sql, name);
	}

}
