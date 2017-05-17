package  ${packageName}.service;

import java.util.List;

import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};

/**
 *${classDesc} Service
 * 
 * @author caozj
 * 
 */
public interface ${className}Service {

	void add(${className} ${firstLower});

	void update(${className} ${firstLower});

	void delete(${idType} id);

	List<${className}> listAll();

	int count();
	
	int countBy(String field, Object value);
	
	List<${className}> page(Pager page);

	${className} get(${idType} id);
	
	void batchDelete(List<${idType}> idList);

	${className} getBy(String field, Object value);

	${className} getByAnd(String field1, Object value1, String field2, Object value2);

	${className} getByOr(String field1, Object value1, String field2, Object value2);

	List<${className}> listBy(String field, Object value);

	List<${className}> listByAnd(String field1, Object value1, String field2, Object value2);

	List<${className}> listByOr(String field1, Object value1, String field2, Object value2);

	List<${className}> pageBy(String field, Object value, Pager page);

	List<${className}> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<${className}> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

}
