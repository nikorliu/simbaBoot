package com.simba.util.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.util.Base64;

import com.simba.model.constant.ConstantData;

/**
 * Base64工具类
 * 
 * @author caozhejun
 *
 */
public class Base64Util {

	private static final Log logger = LogFactory.getLog(Base64Util.class);

	/**
	 * 对字符进行base64编码
	 * 
	 * @param content
	 * @return
	 */
	public static String encode(String content) {
		String result = StringUtils.EMPTY;
		try {
			result = new String(Base64.encode(content.getBytes(ConstantData.DEFAULT_CHARSET)), ConstantData.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			logger.error("对字符进行base64编码发生异常", e);
		}
		return result;
	}
}
