package com.simba.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.dao.PermissionDao;
import com.simba.permission.model.Permission;
import com.simba.permission.service.PermissionService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public void add(Permission permission) {
		permissionDao.add(permission);
	}

	@Override
	public void delete(int id) {
		if (permissionDao.countBy("parentID", id) > 0) {
			throw new RuntimeException("权限下有子权限，不能删除，请先删除子权限");
		}
		permissionDao.delete(id);
	}

	@Override
	public Permission get(int id) {
		return permissionDao.get(id);
	}

	@Override
	public List<Permission> page(Pager page) {
		return permissionDao.page(page);
	}

	@Override
	public int count() {
		return permissionDao.count();
	}

	@Override
	public List<Permission> listAll() {
		return permissionDao.listAll();
	}

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public Permission getBy(String field, Object value) {
		return permissionDao.getBy(field, value);
	}

	@Override
	public Permission getByAnd(String field1, Object value1, String field2, Object value2) {
		return permissionDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public Permission getByOr(String field1, Object value1, String field2, Object value2) {
		return permissionDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<Permission> listBy(String field, Object value) {
		return permissionDao.listBy(field, value);
	}

	@Override
	public List<Permission> listByAnd(String field1, Object value1, String field2, Object value2) {
		return permissionDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<Permission> listByOr(String field1, Object value1, String field2, Object value2) {
		return permissionDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<Permission> pageBy(String field, Object value, Pager page) {
		return permissionDao.pageBy(field, value, page);
	}

	@Override
	public List<Permission> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return permissionDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<Permission> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return permissionDao.pageByOr(field1, value1, field2, value2, page);
	}

	@Override
	public List<Permission> listChildren(int parentID) {
		List<Permission> children = this.listBy("parentID", parentID);
		children.forEach((permission) -> {
			int childrenCount = permissionDao.countBy("parentID", permission.getId());
			if (childrenCount > 0) {
				permission.setState("closed");
			} else {
				permission.setState("open");
			}
		});
		return children;
	}
}
