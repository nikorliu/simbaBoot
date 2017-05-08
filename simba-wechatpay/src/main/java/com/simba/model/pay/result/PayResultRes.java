package com.simba.model.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.simba.framework.util.common.XmlUtil;

/**
 * 支付结果通知响应对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class PayResultRes {

	/**
	 * SUCCESS/FAIL
	 * 
	 * SUCCESS表示商户接收通知成功并校验成功
	 * 
	 */
	private String return_code;

	/**
	 * 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	 */
	private String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String toXML() {
		return XmlUtil.fromObject(this);
	}

	@Override
	public String toString() {
		return toXML();
	}
}
