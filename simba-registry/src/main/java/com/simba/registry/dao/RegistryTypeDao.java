package com.simba.registry.dao;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.registry.model.RegistryType;

/**
 * 注册类型 Dao
 * 
 * @author caozj
 * 
 */
public interface RegistryTypeDao {

	void add(RegistryType registryType);

	void update(RegistryType registryType);

	void delete(int id);

	List<RegistryType> listAll();

	int count();
	
	int countBy(String field, Object value);
	
	List<RegistryType> page(Pager page);

	RegistryType get(int id);
	
	RegistryType getBy(String field, Object value);

	RegistryType getByAnd(String field1, Object value1, String field2, Object value2);

	RegistryType getByOr(String field1, Object value1, String field2, Object value2);

	List<RegistryType> listBy(String field, Object value);

	List<RegistryType> listByAnd(String field1, Object value1, String field2, Object value2);

	List<RegistryType> listByOr(String field1, Object value1, String field2, Object value2);

	List<RegistryType> pageBy(String field, Object value, Pager page);

	List<RegistryType> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<RegistryType> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

}
