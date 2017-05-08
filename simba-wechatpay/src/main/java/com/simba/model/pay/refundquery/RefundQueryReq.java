package com.simba.model.pay.refundquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.simba.framework.util.common.XmlUtil;

/**
 * 退款查询请求对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class RefundQueryReq {

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appId）
	 */
	private String appid;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
	 */
	private String device_info;

	/**
	 * 随机字符串，长度要求在32位以内。
	 */
	private String nonce_str;

	/**
	 * 通过签名算法计算得出的签名值
	 */
	private String sign;

	/**
	 * 签名类型，默认为MD5，支持HMAC-SHA256和MD5
	 */
	private String sign_type = "MD5";

	/**
	 * 微信的订单号，建议优先使用
	 */
	private String transaction_id;

	/**
	 * 商户系统内部的订单号，请确保在同一商户号下唯一。
	 */
	private String out_trade_no;

	/**
	 * 商户侧传给微信的退款单号
	 */
	private String out_refund_no;

	/**
	 * 微信生成的退款单号，在申请退款接口有返回
	 */
	private String refund_id;

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

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String toXML() {
		return XmlUtil.fromObject(this);
	}

	@Override
	public String toString() {
		return toXML();
	}
}
