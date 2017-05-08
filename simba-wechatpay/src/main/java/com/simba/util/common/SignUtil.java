package com.simba.util.common;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 签名工具类
 * 
 * @author caozhejun
 *
 */
public class SignUtil {

	private SignUtil() {

	}

	private static final class SignUtilHolder {
		private static final SignUtil instance = new SignUtil();
	}

	public static SignUtil getInstance() {
		return SignUtilHolder.instance;
	}

	/**
	 * 创建微信签名
	 * 
	 * @param params
	 *            参数
	 * @param signKey
	 *            加密秘钥
	 * @return
	 */
	public String createSign(Map<String, String> params, String signKey) {
		SortedMap<String, String> sortedMap = new TreeMap<>(params);
		StringBuilder toSign = new StringBuilder();
		for (String key : sortedMap.keySet()) {
			String value = params.get(key);
			if (StringUtils.isNotEmpty(value) && !"sign".equals(key) && !"key".equals(key)) {
				toSign.append(key + "=" + value + "&");
			}
		}
		toSign.append("key=" + signKey);
		return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
	}
}
