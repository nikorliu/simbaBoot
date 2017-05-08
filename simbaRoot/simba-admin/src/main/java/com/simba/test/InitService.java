package com.simba.test;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simba.framework.util.jdbc.Jdbc;

@Service
public class InitService {

	private static final Log logger = LogFactory.getLog(InitService.class);

	@Autowired
	private Jdbc jdbc;

	@PostConstruct
	private void init() {
		List<Buss> list = jdbc.queryForList("select * from buss", Buss.class);
		logger.info("*********************" + list.toString());
	}
}
