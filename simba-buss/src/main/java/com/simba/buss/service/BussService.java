package com.simba.buss.service;

import java.util.List;
import java.util.Map;

import com.simba.buss.model.Buss;
import com.simba.framework.util.jdbc.Pager;

public interface BussService {

	void add(Buss buss);

	void update(Buss buss);

	void delete(String name);

	Buss get(String name);

	List<Buss> listAll();

	Object execute(Map<String, String[]> params, String name);

	void batchDelete(List<String> nameList);

	int count();

	List<Buss> page(Pager pager);
}
