package ${packageName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ${packageName}.dao.${className}Dao;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

/**
 * ${classDesc} Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Dao ${firstLower}Dao;

	@Override
	public void add(${className} ${firstLower}) {
		${firstLower}Dao.add(${firstLower});
	}

	@Override
	public void delete(${idType} id) {
	<#if pageType=="treeTable">
		if (${firstLower}Dao.countBy("parentID", id) > 0) {
			throw new RuntimeException("此记录下有子记录，不能删除，请先删除子记录");
		}
	</#if>	
		${firstLower}Dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ${className} get(${idType} id) {
		return ${firstLower}Dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> page(Pager page) {
		return ${firstLower}Dao.page(page);
	}

	@Override
	@Transactional(readOnly = true)
	public int count() {
		return ${firstLower}Dao.count();
	}
	
	@Override
	@Transactional(readOnly = true)
	public int countBy(String field, Object value){
		return ${firstLower}Dao.countBy(field,value);
	}
	
	@Override
	public void deleteBy(String field, Object value){
		${firstLower}Dao.deleteBy(field,value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> listAll() {
		return ${firstLower}Dao.listAll();
	}

	@Override
	public void update(${className} ${firstLower}) {
		${firstLower}Dao.update(${firstLower});
	}
	
	@Override
	public void batchDelete(List<${idType}> idList) {
		for (${idType} id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public ${className} getBy(String field, Object value) {
		return ${firstLower}Dao.getBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public ${className} getByAnd(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public ${className} getByOr(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.getByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> listBy(String field, Object value) {
		return ${firstLower}Dao.listBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> listByAnd(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> listByOr(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.listByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> pageBy(String field, Object value, Pager page) {
		return ${firstLower}Dao.pageBy(field, value, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return ${firstLower}Dao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${className}> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return ${firstLower}Dao.pageByOr(field1, value1, field2, value2, page);
	}
	
}
