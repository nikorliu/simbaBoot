package ${packageName}.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${packageName}.dao.${className}Dao;
import ${packageName}.framework.util.jdbc.Jdbc;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.framework.util.jdbc.StatementParameter;
import ${packageName}.model.${className};

/**
 * ${classDesc} Dao实现类
 * 
 * @author caozj
 *  
 */
@Repository
public class ${className}DaoImpl implements ${className}Dao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "${firstLower}";

	@Override
	public void add(${className} ${firstLower}) {
	<#if idType!="String">
		String sql = "insert into " + table + "(${insertProperties}) values(${propertiesCount})";
		jdbc.updateForBoolean(sql, ${params});
	</#if> 
	<#if idType=="String">
		String sql = "insert into " + table + "(id,${insertProperties}) values(?,${propertiesCount})";	
		jdbc.updateForBoolean(sql, ${firstLower}.getId(), ${params});
	</#if> 
	}

	@Override
	public void update(${className} ${firstLower}) {
		String sql = "update " + table + " set ${updateProperties} where id = ?  ";
		jdbc.updateForBoolean(sql,${params}, ${firstLower}.getId());
	}

	@Override
	public void delete(${idType} id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<${className}> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, ${className}.class, page);
	}

	@Override
	public List<${className}> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, ${className}.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public ${className} get(${idType} id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, ${className}.class, id);
	}
	
	@Override
	public ${className} getBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.query(sql, ${className}.class, value);
	}

	@Override
	public ${className} getByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.query(sql, ${className}.class, value1, value2);
	}

	@Override
	public ${className} getByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.query(sql, ${className}.class, value1, value2);
	}

	@Override
	public List<${className}> listBy(String field, Object value) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		return jdbc.queryForList(sql, ${className}.class, value);
	}

	@Override
	public List<${className}> listByAnd(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		return jdbc.queryForList(sql, ${className}.class, value1, value2);
	}

	@Override
	public List<${className}> listByOr(String field1, Object value1, String field2, Object value2) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		return jdbc.queryForList(sql, ${className}.class, value1, value2);
	}

	@Override
	public List<${className}> pageBy(String field, Object value, Pager page) {
		String sql = "select * from " + table + " where " + field + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value);
		return jdbc.queryForPage(sql, ${className}.class, page, param);
	}

	@Override
	public List<${className}> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, ${className}.class, page, param);
	}

	@Override
	public List<${className}> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
		StatementParameter param = new StatementParameter();
		param.set(value1);
		param.set(value2);
		return jdbc.queryForPage(sql, ${className}.class, page, param);
	}
	
	@Override
	public int countBy(String field, Object value) {
		String sql = "select count(*) from " + table + " where " + field + " = ? ";
		return jdbc.queryForInt(sql, value);
	}

}
