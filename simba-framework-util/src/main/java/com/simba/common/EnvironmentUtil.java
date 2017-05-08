package com.simba.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 获取配置值的类
 * 
 * @author caozhejun
 *
 */
@Component
public class EnvironmentUtil {

	@Autowired
	private Environment env;

	/**
	 * 获取配置中对应key的值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return env.getProperty(key);
	}
}
