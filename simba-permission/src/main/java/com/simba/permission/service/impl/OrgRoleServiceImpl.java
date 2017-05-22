package com.simba.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.dao.OrgRoleDao;
import com.simba.permission.model.OrgRole;
import com.simba.permission.service.OrgRoleService;

/**
 * Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class OrgRoleServiceImpl implements OrgRoleService {

	@Autowired
	private OrgRoleDao orgRoleDao;

	@Override
	public void add(OrgRole orgRole) {
		orgRoleDao.add(orgRole);
	}

	@Override
	public void delete(Integer id) {
		orgRoleDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public OrgRole get(Integer id) {
		return orgRoleDao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> page(Pager page) {
		return orgRoleDao.page(page);
	}

	@Override
	@Transactional(readOnly = true)
	public int count() {
		return orgRoleDao.count();
	}

	@Override
	@Transactional(readOnly = true)
	public int countBy(String field, Object value) {
		return orgRoleDao.countBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> listAll() {
		return orgRoleDao.listAll();
	}

	@Override
	public void update(OrgRole orgRole) {
		orgRoleDao.update(orgRole);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public OrgRole getBy(String field, Object value) {
		return orgRoleDao.getBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public OrgRole getByAnd(String field1, Object value1, String field2, Object value2) {
		return orgRoleDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public OrgRole getByOr(String field1, Object value1, String field2, Object value2) {
		return orgRoleDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> listBy(String field, Object value) {
		return orgRoleDao.listBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> listByAnd(String field1, Object value1, String field2, Object value2) {
		return orgRoleDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> listByOr(String field1, Object value1, String field2, Object value2) {
		return orgRoleDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> pageBy(String field, Object value, Pager page) {
		return orgRoleDao.pageBy(field, value, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return orgRoleDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrgRole> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return orgRoleDao.pageByOr(field1, value1, field2, value2, page);
	}

}
