package com.simba.registry.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.registry.dao.RegistryTableDao;
import com.simba.registry.model.RegistryTable;

/**
 * 注册表 Dao实现类
 * 
 * @author caozj
 *  
 */
@Repository
public class RegistryTableDaoImpl implements RegistryTableDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "registryTable";

	@Override
	public void add(RegistryTable registryTable) {
		String sql = "insert into " + table + "( code, value, typeID, description) values(?,?,?,?)";
		jdbc.updateForBoolean(sql, registryTable.getCode(),registryTable.getValue(),registryTable.getTypeID(),registryTable.getDescription());
	}

	@Override
	public void update(RegistryTable registryTable) {
		String sql = "update " + table + " set  code = ? , value = ? , typeID = ? , description = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,registryTable.getCode(),registryTable.getValue(),registryTable.getTypeID(),registryTable.getDescription(), registryTable.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<RegistryTable> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, RegistryTable.class, page);
	}

	@Override
	public List<RegistryTable> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, RegistryTable.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public RegistryTable get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, RegistryTable.class, id);
	}
	
	@Override
	public RegistryTable getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, RegistryTable.class, value);
	}

	@Override
	public RegistryTable getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, RegistryTable.class, value1, value2);
	}

	@Override
	public RegistryTable getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, RegistryTable.class, value1, value2);
	}

	@Override
	public List<RegistryTable> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, RegistryTable.class, value);
	}

	@Override
	public List<RegistryTable> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, RegistryTable.class, value1, value2);
	}

	@Override
	public List<RegistryTable> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, RegistryTable.class, value1, value2);
	}

	@Override
	public List<RegistryTable> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, RegistryTable.class, page, param);
	}

	@Override
	public List<RegistryTable> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RegistryTable.class, page, param);
	}

	@Override
	public List<RegistryTable> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, RegistryTable.class, page, param);
	}
	
	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

}
