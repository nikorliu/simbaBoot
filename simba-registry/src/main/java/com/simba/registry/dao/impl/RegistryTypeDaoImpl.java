package com.simba.registry.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.registry.dao.RegistryTypeDao;
import com.simba.registry.model.RegistryType;

/**
 * 注册类型 Dao实现类
 * 
 * @author caozj
 *  
 */
@Repository
public class RegistryTypeDaoImpl implements RegistryTypeDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "registryType";

	@Override
	public void add(RegistryType registryType) {
		String sql = "insert into " + table + "( text, parentID) values(?,?)";
		jdbc.updateForBoolean(sql, registryType.getText(),registryType.getParentID());
	}

	@Override
	public void update(RegistryType registryType) {
		String sql = "update " + table + " set  text = ? , parentID = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,registryType.getText(),registryType.getParentID(), registryType.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<RegistryType> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, RegistryType.class, page);
	}

	@Override
	public List<RegistryType> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, RegistryType.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public RegistryType get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, RegistryType.class, id);
	}
	
	@Override
	public RegistryType getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, RegistryType.class, value);
	}

	@Override
	public RegistryType getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, RegistryType.class, value1, value2);
	}

	@Override
	public RegistryType getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, RegistryType.class, value1, value2);
	}

	@Override
	public List<RegistryType> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, RegistryType.class, value);
	}

	@Override
	public List<RegistryType> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, RegistryType.class, value1, value2);
	}

	@Override
	public List<RegistryType> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, RegistryType.class, value1, value2);
	}

	@Override
	public List<RegistryType> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, RegistryType.class, page, param);
	}

	@Override
	public List<RegistryType> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RegistryType.class, page, param);
	}

	@Override
	public List<RegistryType> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RegistryType.class, page, param);
	}
	
	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

}
