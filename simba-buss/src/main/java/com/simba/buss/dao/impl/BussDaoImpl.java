package com.simba.buss.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simba.buss.dao.BussDao;
import com.simba.buss.model.Buss;
import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;

@Repository("bussDao")
public class BussDaoImpl implements BussDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "buss";

	@Override
	public void add(Buss buss) {
		String sql = "insert into " + table + "(name,description,script) values(?,?,?)";
		jdbc.updateForBoolean(sql, buss.getName(), buss.getDescription(), buss.getScript());
	}

	@Override
	public void update(Buss buss) {
		String sql = "update " + table + " set description=?,script=? where name=?";
		jdbc.updateForBoolean(sql, buss.getDescription(), buss.getScript(), buss.getName());
	}

	@Override
	public void delele(String name) {
		String sql = "delete from " + table + " where name = ? ";
		jdbc.updateForBoolean(sql, name);
	}

	@Override
	public List<Buss> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Buss.class);
	}

	@Override
	public Buss get(String name) {
		String sql = "select * from " + table + " where name = ? ";
		return jdbc.query(sql, Buss.class, name);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public List<Buss> page(Pager pager) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Buss.class, pager);
	}

}
