package com.simba.registry.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.cluster.registryTable.RegistryTableClusterData;
import com.simba.cluster.registryTable.RegistryTableClusterExecute;
import com.simba.framework.distributed.ClusterMessage;
import com.simba.framework.distributed.DistributedUtil;
import com.simba.framework.util.jdbc.Pager;
import com.simba.registry.dao.RegistryTableDao;
import com.simba.registry.model.RegistryTable;
import com.simba.registry.model.RegistryTableData;
import com.simba.registry.service.RegistryTableService;

/**
 * 注册表 Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class RegistryTableServiceImpl implements RegistryTableService {

	@Autowired
	private RegistryTableDao registryTableDao;

	@Autowired
	private DistributedUtil distributedUtil;

	@Override
	public void add(RegistryTable registryTable) {
		registryTableDao.add(registryTable);
		executeCluster(new RegistryTableClusterData("add", registryTable.getCode(), registryTable.getValue()));
	}

	@Override
	public void delete(int id) {
		RegistryTable registryTable = this.get(id);
		if (registryTable == null) {
			return;
		}
		registryTableDao.delete(id);
		executeCluster(new RegistryTableClusterData("remove", registryTable.getCode()));
	}

	@Override
	public RegistryTable get(int id) {
		return registryTableDao.get(id);
	}

	@Override
	public List<RegistryTable> page(Pager page) {
		return registryTableDao.page(page);
	}

	@Override
	public int count() {
		return registryTableDao.count();
	}

	@Override
	public int countBy(String field, Object value) {
		return registryTableDao.countBy(field, value);
	}

	@Override
	public List<RegistryTable> listAll() {
		return registryTableDao.listAll();
	}

	@Override
	public void update(RegistryTable registryTable) {
		RegistryTable oldRegistryTable = this.get(registryTable.getId());
		registryTableDao.update(registryTable);
		executeCluster(new RegistryTableClusterData("remove", oldRegistryTable.getCode()));
		executeCluster(new RegistryTableClusterData("add", registryTable.getCode(), registryTable.getValue()));
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public RegistryTable getBy(String field, Object value) {
		return registryTableDao.getBy(field, value);
	}

	@Override
	public RegistryTable getByAnd(String field1, Object value1, String field2, Object value2) {
		return registryTableDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public RegistryTable getByOr(String field1, Object value1, String field2, Object value2) {
		return registryTableDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryTable> listBy(String field, Object value) {
		return registryTableDao.listBy(field, value);
	}

	@Override
	public List<RegistryTable> listByAnd(String field1, Object value1, String field2, Object value2) {
		return registryTableDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryTable> listByOr(String field1, Object value1, String field2, Object value2) {
		return registryTableDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<RegistryTable> pageBy(String field, Object value, Pager page) {
		return registryTableDao.pageBy(field, value, page);
	}

	@Override
	public List<RegistryTable> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return registryTableDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<RegistryTable> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return registryTableDao.pageByOr(field1, value1, field2, value2, page);
	}

	@Override
	@PostConstruct
	public void init() {
		List<RegistryTable> list = registryTableDao.listAll();
		list.forEach((registryTable) -> {
			RegistryTableData.getInstance().add(registryTable.getCode(), registryTable.getValue());
		});
	}

	private void executeCluster(RegistryTableClusterData clustData) {
		distributedUtil
				.executeInCluster(new ClusterMessage(RegistryTableClusterExecute.class.getCanonicalName(), clustData));
	}
}
