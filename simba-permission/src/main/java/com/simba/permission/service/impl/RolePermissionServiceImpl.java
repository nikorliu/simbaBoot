package com.simba.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.dao.RolePermissionDao;
import com.simba.permission.model.RolePermission;
import com.simba.permission.service.RolePermissionService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public void add(RolePermission rolePermission) {
		rolePermissionDao.add(rolePermission);
	}

	@Override
	public void delete(int id) {
		rolePermissionDao.delete(id);
	}

	@Override
	public RolePermission get(int id) {
		return rolePermissionDao.get(id);
	}

	@Override
	public List<RolePermission> page(Pager page) {
		return rolePermissionDao.page(page);
	}

	@Override
	public int count() {
		return rolePermissionDao.count();
	}

	@Override
	public List<RolePermission> listAll() {
		return rolePermissionDao.listAll();
	}

	@Override
	public void update(RolePermission rolePermission) {
		rolePermissionDao.update(rolePermission);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	public RolePermission getBy(String field, Object value) {
		return rolePermissionDao.getBy(field, value);
	}

	@Override
	public RolePermission getByAnd(String field1, Object value1, String field2, Object value2) {
		return rolePermissionDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public RolePermission getByOr(String field1, Object value1, String field2, Object value2) {
		return rolePermissionDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RolePermission> listBy(String field, Object value) {
		return rolePermissionDao.listBy(field, value);
	}

	@Override
	public List<RolePermission> listByAnd(String field1, Object value1, String field2, Object value2) {
		return rolePermissionDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<RolePermission> listByOr(String field1, Object value1, String field2, Object value2) {
		return rolePermissionDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RolePermission> pageBy(String field, Object value, Pager page) {
		return rolePermissionDao.pageBy(field, value, page);
	}

	@Override
	public List<RolePermission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return rolePermissionDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<RolePermission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return rolePermissionDao.pageByOr(field1, value1, field2, value2, page);
	}
}
