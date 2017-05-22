package  com.simba.permission.service;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.OrgRole;

/**
 * Service
 * 
 * @author caozj
 * 
 */
public interface OrgRoleService {

	void add(OrgRole orgRole);

	void update(OrgRole orgRole);

	void delete(Integer id);

	List<OrgRole> listAll();

	int count();
	
	int countBy(String field, Object value);
	
	List<OrgRole> page(Pager page);

	OrgRole get(Integer id);
	
	void batchDelete(List<Integer> idList);

	OrgRole getBy(String field, Object value);

	OrgRole getByAnd(String field1, Object value1, String field2, Object value2);

	OrgRole getByOr(String field1, Object value1, String field2, Object value2);

	List<OrgRole> listBy(String field, Object value);

	List<OrgRole> listByAnd(String field1, Object value1, String field2, Object value2);

	List<OrgRole> listByOr(String field1, Object value1, String field2, Object value2);

	List<OrgRole> pageBy(String field, Object value, Pager page);

	List<OrgRole> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

	List<OrgRole> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

}
