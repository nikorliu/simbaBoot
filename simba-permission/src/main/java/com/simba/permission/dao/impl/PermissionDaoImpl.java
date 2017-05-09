package com.simba.permission.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.PermissionDao;
import com.simba.permission.model.Permission;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "permission";

	@Override
	public void add(Permission permission) {
		String sql = "insert into " + table + "( text, url, parentID) values(?,?,?)";
		jdbc.updateForBoolean(sql, permission.getText(), permission.getUrl(), permission.getParentID());
	}

	@Override
	public void update(Permission permission) {
		String sql = "update " + table + " set  text = ? , url = ? , parentID = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, permission.getText(), permission.getUrl(), permission.getParentID(), permission.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Permission> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Permission.class, page);
	}

	@Override
	public List<Permission> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Permission.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Permission get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Permission.class, id);
	}

	@Override
	public Permission getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, Permission.class, value);
	}

	@Override
	public Permission getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, Permission.class, value1, value2);
	}

	@Override
	public Permission getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, Permission.class, value1, value2);
	}

	@Override
	public List<Permission> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, Permission.class, value);
	}

	@Override
	public List<Permission> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, Permission.class, value1, value2);
	}

	@Override
	public List<Permission> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, Permission.class, value1, value2);
	}

	@Override
	public List<Permission> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, Permission.class, page, param);
	}

	@Override
	public List<Permission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, Permission.class, page, param);
	}

	@Override
	public List<Permission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, Permission.class, page, param);
	}

	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

}
