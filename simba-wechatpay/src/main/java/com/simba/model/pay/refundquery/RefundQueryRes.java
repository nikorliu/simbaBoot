package com.simba.model.pay.refundquery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.simba.framework.util.common.XmlUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 退款查询响应对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class RefundQueryRes {

	/**
	 * SUCCESS/FAIL
	 */
	private String return_code;

	/**
	 * 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	 */
	private String return_msg;

	/**
	 * SUCCESS/FAIL
	 * 
	 * SUCCESS退款申请接收成功，结果通过退款查询接口查询
	 * 
	 * FAIL
	 * 
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
	 * 调用接口提交的公众账号ID
	 */
	private String appid;

	/**
	 * 自定义参数，可以为请求支付的终端设备号等
	 */
	private String device_info;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 随机字符串，长度要求在32位以内。
	 */
	private String nonce_str;

	/**
	 * 通过签名算法计算得出的签名值
	 */
	private String sign;

	/**
	 * 微信的订单号
	 */
	private String transaction_id;

	/**
	 * 商户系统内部的订单号
	 */
	private String out_trade_no;

	/**
	 * 订单总金额，单位为分，只能为整数
	 */
	private int total_fee;

	/**
	 * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 */
	private Integer settlement_total_fee;

	/**
	 * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	private String fee_type = "CNY";

	/**
	 * 现金支付金额，单位为分，只能为整数
	 */
	private int cash_fee;

	/**
	 * 退款记录数
	 */
	private Integer refund_count;

	private List<RefundRecord> refundRecords;

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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(Integer settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}

	public Integer getRefund_count() {
		return refund_count;
	}

	public void setRefund_count(Integer refund_count) {
		this.refund_count = refund_count;
	}

	public List<RefundRecord> getRefundRecords() {
		return refundRecords;
	}

	public void setRefundRecords(List<RefundRecord> refundRecords) {
		this.refundRecords = refundRecords;
	}

	/**
	 * 组装生成退款记录属性的内容
	 * 
	 * @throws XPathExpressionException
	 * @throws DOMException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public void composeRefundRecords(String xml)
			throws DOMException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		if (this.refund_count != null && this.refund_count > 0) {
			this.refundRecords = new ArrayList<>();
			Document doc = XmlUtil.parseXMLContent(xml);
			Element root = doc.getDocumentElement();
			for (int i = 0; i < this.refund_count; i++) {
				RefundRecord refundRecord = new RefundRecord();
				this.refundRecords.add(refundRecord);
				refundRecord.setOut_refund_no(
						((Element) XmlUtil.selectSingleNode("/xml/out_refund_no_" + i, root)).getTextContent());
				refundRecord.setRefund_id(
						((Element) XmlUtil.selectSingleNode("/xml/refund_id_" + i, root)).getTextContent());
				refundRecord.setRefund_channel(
						((Element) XmlUtil.selectSingleNode("/xml/refund_channel_" + i, root)).getTextContent());
				refundRecord.setRefund_fee(NumberUtils
						.toInt(((Element) XmlUtil.selectSingleNode("/xml/refund_fee_" + i, root)).getTextContent()));
				refundRecord.setSettlement_refund_fee(
						NumberUtils.toInt(((Element) XmlUtil.selectSingleNode("/xml/settlement_refund_fee_" + i, root))
								.getTextContent()));
				refundRecord.setCoupon_type(
						((Element) XmlUtil.selectSingleNode("/xml/coupon_type_" + i, root)).getTextContent());
				refundRecord.setCoupon_refund_fee(NumberUtils.toInt(
						((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_fee_" + i, root)).getTextContent()));
				refundRecord.setCoupon_refund_count(NumberUtils.toInt(
						((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_count_" + i, root)).getTextContent()));
				refundRecord.setRefund_status(
						((Element) XmlUtil.selectSingleNode("/xml/refund_status_" + i, root)).getTextContent());
				refundRecord.setRefund_recv_accout(
						((Element) XmlUtil.selectSingleNode("/xml/refund_recv_accout_" + i, root)).getTextContent());
				if (refundRecord.getCoupon_refund_count() == 0) {
					continue;
				}
				List<RefundRecord.RefundCoupon> coupons = new ArrayList<>();
				for (int j = 0; j < refundRecord.getCoupon_refund_count(); j++) {
					coupons.add(new RefundRecord.RefundCoupon(
							((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_id_" + i + "_" + j, root))
									.getTextContent(),
							NumberUtils.toInt(
									((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_fee_" + i + "_" + j, root))
											.getTextContent())));
				}
			}
		}
	}

	public static class RefundRecord {
		/**
		 * <pre>
		 * 商户退款单号
		 * out_refund_no_$n
		 * 是
		 * String(32)
		 * 1217752501201407033233368018
		 * 商户退款单号
		 * </pre>
		 */
		private String out_refund_no;

		/**
		 * <pre>
		 * 微信退款单号
		 * refund_id_$n
		 * 是
		 * String(28)
		 * 1217752501201407033233368018
		 * 微信退款单号
		 * </pre>
		 */
		private String refund_id;

		/**
		 * <pre>
		 * 退款渠道
		 * refund_channel_$n
		 * 否
		 * String(16)
		 * ORIGINAL
		 * ORIGINAL—原路退款 BALANCE—退回到余额
		 * </pre>
		 */
		private String refund_channel;

		/**
		 * <pre>
		 * 申请退款金额
		 * refund_fee_$n
		 * 是
		 * Int
		 * 100
		 * 退款总金额,单位为分,可以做部分退款
		 * </pre>
		 */
		private Integer refund_fee;

		/**
		 * <pre>
		 * 退款金额
		 * settlement_refund_fee_$n
		 * 否
		 * Int
		 * 100
		 * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
		 * </pre>
		 */
		private Integer settlement_refund_fee;

		/**
		 * <pre>
		 * 退款资金来源
		 * refund_account
		 * 否
		 * String(30)
		 * REFUND_SOURCE_RECHARGE_FUNDS
		 * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户, REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
		 * </pre>
		 */
		private String refund_account;

		/**
		 * <pre>
		 * 代金券类型
		 * coupon_type_$n
		 * 否
		 * Int
		 * CASH
		 * CASH--充值代金券 , NO_CASH---非充值代金券。订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
		 * </pre>
		 */
		private String coupon_type;

		/**
		 * <pre>
		 * 代金券退款金额
		 * coupon_refund_fee_$n
		 * 否
		 * Int
		 * 100
		 * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
		 * </pre>
		 */
		private int coupon_refund_fee;

		/**
		 * <pre>
		 * 退款代金券使用数量
		 * coupon_refund_count_$n
		 * 否
		 * Int
		 * 1
		 * 退款代金券使用数量 ,$n为下标,从0开始编号
		 * </pre>
		 */
		private int coupon_refund_count;

		private List<RefundCoupon> refundCoupons;

		/**
		 * <pre>
		 * 退款状态
		 * refund_status_$n
		 * 是
		 * String(16)
		 * SUCCESS
		 * 退款状态：
		 *  SUCCESS—退款成功，
		 *  FAIL—退款失败，
		 *  PROCESSING—退款处理中，
		 *  CHANGE—转入代发，
		 * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
		 * </pre>
		 */
		private String refund_status;
		/**
		 * <pre>
		 * 退款入账账户
		 * refund_recv_accout_$n
		 * 是
		 * String(64)
		 * 招商银行信用卡0403
		 * 取当前退款单的退款入账方，1）退回银行卡：{银行名称}{卡类型}{卡尾号}，2）退回支付用户零钱:支付用户零钱
		 * </pre>
		 */
		private String refund_recv_accout;

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

		public String getRefund_channel() {
			return refund_channel;
		}

		public void setRefund_channel(String refund_channel) {
			this.refund_channel = refund_channel;
		}

		public Integer getRefund_fee() {
			return refund_fee;
		}

		public void setRefund_fee(Integer refund_fee) {
			this.refund_fee = refund_fee;
		}

		public Integer getSettlement_refund_fee() {
			return settlement_refund_fee;
		}

		public void setSettlement_refund_fee(Integer settlement_refund_fee) {
			this.settlement_refund_fee = settlement_refund_fee;
		}

		public String getRefund_account() {
			return refund_account;
		}

		public void setRefund_account(String refund_account) {
			this.refund_account = refund_account;
		}

		public String getCoupon_type() {
			return coupon_type;
		}

		public void setCoupon_type(String coupon_type) {
			this.coupon_type = coupon_type;
		}

		public int getCoupon_refund_fee() {
			return coupon_refund_fee;
		}

		public void setCoupon_refund_fee(int coupon_refund_fee) {
			this.coupon_refund_fee = coupon_refund_fee;
		}

		public int getCoupon_refund_count() {
			return coupon_refund_count;
		}

		public void setCoupon_refund_count(int coupon_refund_count) {
			this.coupon_refund_count = coupon_refund_count;
		}

		public List<RefundCoupon> getRefundCoupons() {
			return refundCoupons;
		}

		public void setRefundCoupons(List<RefundCoupon> refundCoupons) {
			this.refundCoupons = refundCoupons;
		}

		public String getRefund_status() {
			return refund_status;
		}

		public void setRefund_status(String refund_status) {
			this.refund_status = refund_status;
		}

		public String getRefund_recv_accout() {
			return refund_recv_accout;
		}

		public void setRefund_recv_accout(String refund_recv_accout) {
			this.refund_recv_accout = refund_recv_accout;
		}

		public static class RefundCoupon {
			/**
			 * <pre>
			 * 退款代金券ID
			 * coupon_refund_id_$n_$m
			 * 否
			 * String(20)
			 * 10000
			 * 退款代金券ID, $n为下标，$m为下标，从0开始编号
			 * </pre>
			 */
			private String coupon_refund_id;

			/**
			 * <pre>
			 * 单个退款代金券支付金额
			 * coupon_refund_fee_$n_$m
			 * 否
			 * Int
			 * 100
			 * 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
			 * </pre>
			 */
			private int coupon_refund_fee;

			public String getCoupon_refund_id() {
				return coupon_refund_id;
			}

			public void setCoupon_refund_id(String coupon_refund_id) {
				this.coupon_refund_id = coupon_refund_id;
			}

			public int getCoupon_refund_fee() {
				return coupon_refund_fee;
			}

			public void setCoupon_refund_fee(int coupon_refund_fee) {
				this.coupon_refund_fee = coupon_refund_fee;
			}

			public RefundCoupon(String couponRefundId, Integer couponRefundFee) {
				this.coupon_refund_id = couponRefundId;
				this.coupon_refund_fee = couponRefundFee;
			}

			public RefundCoupon(String couponRefundBatchId, String couponRefundId, Integer couponRefundFee) {
				this.coupon_refund_id = couponRefundId;
				this.coupon_refund_fee = couponRefundFee;
			}
		}

	}
}
