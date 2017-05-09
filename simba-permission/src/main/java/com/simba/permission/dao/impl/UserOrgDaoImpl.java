package com.simba.permission.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.UserOrgDao;
import com.simba.permission.model.UserOrg;

/**
 * 
 * 
 * @author caozj
 *  
 */
@Repository
public class UserOrgDaoImpl implements UserOrgDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "userOrg";

	@Override
	public void add(UserOrg userOrg) {
		String sql = "insert into " + table + "( userAccount, orgID) values(?,?)";
		jdbc.updateForBoolean(sql, userOrg.getUserAccount(),userOrg.getOrgID());
	}

	@Override
	public void update(UserOrg userOrg) {
		String sql = "update " + table + " set  userAccount = ? , orgID = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,userOrg.getUserAccount(),userOrg.getOrgID(), userOrg.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<UserOrg> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, UserOrg.class, page);
	}

	@Override
	public List<UserOrg> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, UserOrg.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public UserOrg get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, UserOrg.class, id);
	}
	
	@Override
	public UserOrg getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, UserOrg.class, value);
	}

	@Override
	public UserOrg getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, UserOrg.class, value1, value2);
	}

	@Override
	public UserOrg getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, UserOrg.class, value1, value2);
	}

	@Override
	public List<UserOrg> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, UserOrg.class, value);
	}

	@Override
	public List<UserOrg> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, UserOrg.class, value1, value2);
	}

	@Override
	public List<UserOrg> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, UserOrg.class, value1, value2);
	}

	@Override
	public List<UserOrg> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, UserOrg.class, page, param);
	}

	@Override
	public List<UserOrg> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, UserOrg.class, page, param);
	}

	@Override
	public List<UserOrg> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, UserOrg.class, page, param);
	}

	@Override
	public void deleteByUserAccount(String account) {
		String sql = "delete from " + table + " where userAccount = ? ";
		jdbc.updateForBoolean(sql, account);
	}
	

}
