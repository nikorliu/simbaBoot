package com.simba.model.pay.unifiedorder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单响应对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class UnifiedOrderRes {

	/**
	 * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
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
	 * 自定义参数，可以为请求支付的终端设备号等
	 */
	private String device_info;

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
	 * 错误代码
	 */
	private String err_code;

	/**
	 * 错误代码描述
	 */
	private String err_code_des;

	/**
	 * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	 */
	private String prepay_id;

	/**
	 * 交易类型，取值为：JSAPI，NATIVE，APP等
	 */
	private String trade_type;

	/**
	 * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
	 */
	private String code_url;

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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnifiedorderRes [return_code=");
		builder.append(return_code);
		builder.append(", return_msg=");
		builder.append(return_msg);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", mch_id=");
		builder.append(mch_id);
		builder.append(", device_info=");
		builder.append(device_info);
		builder.append(", nonce_str=");
		builder.append(nonce_str);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", result_code=");
		builder.append(result_code);
		builder.append(", err_code=");
		builder.append(err_code);
		builder.append(", err_code_des=");
		builder.append(err_code_des);
		builder.append(", prepay_id=");
		builder.append(prepay_id);
		builder.append(", trade_type=");
		builder.append(trade_type);
		builder.append(", code_url=");
		builder.append(code_url);
		builder.append("]");
		return builder.toString();
	}

}
