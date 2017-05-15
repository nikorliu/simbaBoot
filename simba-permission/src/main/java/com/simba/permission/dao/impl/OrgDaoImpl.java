package com.simba.permission.dao.impl;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.OrgDao;
import com.simba.permission.model.Org;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class OrgDaoImpl implements OrgDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "org";

	@Override
	@CacheEvict(cacheNames = { "allOrg", "org" }, allEntries = true)
	public int add(Org org) {
		String sql = "insert into " + table + "( text, parentID,orderNo) values(?,?,?)";
		return NumberUtils
				.toInt(jdbc.updateForGeneratedKey(sql, org.getText(), org.getParentID(), org.getOrderNo()) + "");
	}

	@Override
	public void update(Org org) {
		String sql = "update " + table + " set  text = ? , parentID = ? ,orderNo = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, org.getText(), org.getParentID(), org.getOrderNo(), org.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Org> page(Pager page) {
		String sql = "select * from " + table + " order by orderNo";
		return jdbc.queryForPage(sql, Org.class, page);
	}

	@Override
	@Cacheable(key = "'allOrg'", cacheNames = "allOrg")
	public List<Org> listAll() {
		String sql = "select * from " + table + " order by orderNo";
		return jdbc.queryForList(sql, Org.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	@Cacheable(key = "#p0", cacheNames = "org")
	public Org get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Org.class, id);
	}

	@Override
	public Org getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, Org.class, value);
	}

	@Override
	public Org getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, Org.class, value1, value2);
	}

	@Override
	public Org getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, Org.class, value1, value2);
	}

	@Override
	public List<Org> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? order by orderNo";
		return jdbc.queryForList(sql, Org.class, value);
	}

	@Override
	public List<Org> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? order by orderNo";
		return jdbc.queryForList(sql, Org.class, value1, value2);
	}

	@Override
	public List<Org> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? order by orderNo";
		return jdbc.queryForList(sql, Org.class, value1, value2);
	}

	@Override
	public List<Org> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? order by orderNo";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, Org.class, page, param);
	}

	@Override
	public List<Org> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? order by orderNo";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, Org.class, page, param);
	}

	@Override
	public List<Org> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? order by orderNo";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, Org.class, page, param);
	}

	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

}
