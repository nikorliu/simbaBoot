package com.simba.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.dao.UserOrgDao;
import com.simba.permission.model.UserOrg;
import com.simba.permission.service.UserOrgService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class UserOrgServiceImpl implements UserOrgService {

	@Autowired
	private UserOrgDao userOrgDao;

	@Override
	public void add(UserOrg userOrg) {
		userOrgDao.add(userOrg);
	}

	@Override
	public void delete(int id) {
		userOrgDao.delete(id);
	}

	@Override
	public UserOrg get(int id) {
		return userOrgDao.get(id);
	}

	@Override
	public List<UserOrg> page(Pager page) {
		return userOrgDao.page(page);
	}

	@Override
	public int count() {
		return userOrgDao.count();
	}

	@Override
	public List<UserOrg> listAll() {
		return userOrgDao.listAll();
	}

	@Override
	public void update(UserOrg userOrg) {
		userOrgDao.update(userOrg);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	public UserOrg getBy(String field, Object value) {
		return userOrgDao.getBy(field, value);
	}

	@Override
	public UserOrg getByAnd(String field1, Object value1, String field2, Object value2) {
		return userOrgDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public UserOrg getByOr(String field1, Object value1, String field2, Object value2) {
		return userOrgDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<UserOrg> listBy(String field, Object value) {
		return userOrgDao.listBy(field, value);
	}

	@Override
	public List<UserOrg> listByAnd(String field1, Object value1, String field2, Object value2) {
		return userOrgDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<UserOrg> listByOr(String field1, Object value1, String field2, Object value2) {
		return userOrgDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<UserOrg> pageBy(String field, Object value, Pager page) {
		return userOrgDao.pageBy(field, value, page);
	}

	@Override
	public List<UserOrg> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return userOrgDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<UserOrg> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return userOrgDao.pageByOr(field1, value1, field2, value2, page);
	}
}
