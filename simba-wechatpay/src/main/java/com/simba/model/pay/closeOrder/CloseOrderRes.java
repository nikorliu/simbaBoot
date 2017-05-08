package com.simba.model.pay.closeOrder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关闭订单响应对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class CloseOrderRes {

	/**
	 * SUCCESS/FAIL
	 */
	private String return_code;

	/**
	 * 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	 */
	private String return_msg;

	/**
	 * 调用接口提交的公众账号ID
	 */
	private String appid;

	/**
	 * 调用接口提交的商户号
	 */
	private String mch_id;

	/**
	 * 微信返回的随机字符串
	 */
	private String nonce_str;

	/**
	 * 微信返回的签名值
	 */
	private String sign;

	/**
	 * 业务结果 SUCCESS/FAIL
	 */
	private String result_code;

	/**
	 * 业务结果描述
	 */
	private String result_msg;

	/**
	 * 错误代码
	 */
	private String err_code;

	/**
	 * 错误代码描述
	 */
	private String err_code_des;

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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CloseOrderRes [return_code=");
		builder.append(return_code);
		builder.append(", return_msg=");
		builder.append(return_msg);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", mch_id=");
		builder.append(mch_id);
		builder.append(", nonce_str=");
		builder.append(nonce_str);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", result_code=");
		builder.append(result_code);
		builder.append(", result_msg=");
		builder.append(result_msg);
		builder.append(", err_code=");
		builder.append(err_code);
		builder.append(", err_code_des=");
		builder.append(err_code_des);
		builder.append("]");
		return builder.toString();
	}

}
