package com.simba.util.common;

/**
 * 微信支付常量类
 * 
 * @author caozhejun
 *
 */
public interface WxPayConstantData {

	/**
	 * 统一下单
	 */
	String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 查询订单
	 */
	String orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 关闭订单
	 */
	String closeorderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";

	/**
	 * 查询退款
	 */
	String refundqueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";

	/**
	 * 下载对账单
	 */
	String downloadbillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";

	/**
	 * 申请退款
	 */
	String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
}
