package com.simba.registry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.jdbc.Pager;
import com.simba.registry.dao.RegistryTypeDao;
import com.simba.registry.model.RegistryType;
import com.simba.registry.service.RegistryTypeService;

/**
 * 注册类型 Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class RegistryTypeServiceImpl implements RegistryTypeService {

	@Autowired
	private RegistryTypeDao registryTypeDao;

	@Override
	public void add(RegistryType registryType) {
		registryTypeDao.add(registryType);
	}

	@Override
	public void delete(int id) {
		if (registryTypeDao.countBy("parentID", id) > 0) {
			throw new RuntimeException("此记录下有子记录，不能删除，请先删除子记录");
		}
		registryTypeDao.delete(id);
	}

	@Override
	public RegistryType get(int id) {
		return registryTypeDao.get(id);
	}

	@Override
	public List<RegistryType> page(Pager page) {
		return registryTypeDao.page(page);
	}

	@Override
	public int count() {
		return registryTypeDao.count();
	}

	@Override
	public int countBy(String field, Object value) {
		return registryTypeDao.countBy(field, value);
	}

	@Override
	public List<RegistryType> listAll() {
		return registryTypeDao.listAll();
	}

	@Override
	public void update(RegistryType registryType) {
		registryTypeDao.update(registryType);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public RegistryType getBy(String field, Object value) {
		return registryTypeDao.getBy(field, value);
	}

	@Override
	public RegistryType getByAnd(String field1, Object value1, String field2, Object value2) {
		return registryTypeDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public RegistryType getByOr(String field1, Object value1, String field2, Object value2) {
		return registryTypeDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryType> listBy(String field, Object value) {
		return registryTypeDao.listBy(field, value);
	}

	@Override
	public List<RegistryType> listByAnd(String field1, Object value1, String field2, Object value2) {
		return registryTypeDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryType> listByOr(String field1, Object value1, String field2, Object value2) {
		return registryTypeDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryType> pageBy(String field, Object value, Pager page) {
		return registryTypeDao.pageBy(field, value, page);
	}

	@Override
	public List<RegistryType> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return registryTypeDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<RegistryType> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return registryTypeDao.pageByOr(field1, value1, field2, value2, page);
	}

}
