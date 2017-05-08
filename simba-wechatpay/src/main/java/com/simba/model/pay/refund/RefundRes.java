package com.simba.model.pay.refund;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.simba.framework.util.common.XmlUtil;

/**
 * 申请退款响应对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class RefundRes {

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
	 * 商户侧传给微信的退款单号
	 */
	private String out_refund_no;

	/**
	 * 微信退款单号
	 */
	private String refund_id;

	/**
	 * ORIGINAL—原路退款
	 * 
	 * BALANCE—退回到余额
	 * 
	 */
	private String refund_channel;

	/**
	 * 退款总金额,单位为分,可以做部分退款
	 */
	private int refund_fee;

	/**
	 * 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
	 */
	private int settlement_refund_fee;

	/**
	 * 订单总金额，单位为分，只能为整数
	 */
	private int total_fee;

	/**
	 * 去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 */
	private int settlement_total_fee;

	/**
	 * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	private String fee_type = "CNY";

	/**
	 * 现金支付金额，单位为分，只能为整数
	 */
	private int cash_fee;

	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	private String cash_fee_type = "CNY";

	/**
	 * 现金退款金额，单位为分，只能为整数
	 */
	private int cash_refund_fee;

	/**
	 * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金
	 */
	private int coupon_refund_fee;

	/**
	 * 退款代金券使用数量
	 */
	private int coupon_refund_count;

	private List<Coupon> coupons;

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

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public int getSettlement_refund_fee() {
		return settlement_refund_fee;
	}

	public void setSettlement_refund_fee(int settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(int settlement_total_fee) {
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

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public int getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(int cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
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

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * 通过xml组装coupons属性内容
	 * 
	 * @throws XPathExpressionException
	 * @throws DOMException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public void composeCoupons(String xml) throws DOMException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		if (this.coupon_refund_count > 0) {
			this.coupons = Lists.newArrayList();
			Document doc = XmlUtil.parseXMLContent(xml);
			Element root = doc.getDocumentElement();
			for (int i = 0; i < this.coupon_refund_count; i++) {
				String type = ((Element) XmlUtil.selectSingleNode("/xml/coupon_type_" + i, root)).getTextContent();
				String id = ((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_id_" + i, root)).getTextContent();
				String fee = ((Element) XmlUtil.selectSingleNode("/xml/coupon_refund_fee_" + i, root)).getTextContent();
				this.coupons.add(new Coupon(type, id, NumberUtils.toInt(fee)));
			}
		}
	}

	public static class Coupon {
		/**
		 * <pre>
		 * 代金券类型
		 * coupon_type_$n 
		 * 否
		 * String
		 * CASH
		 * <li>CASH--充值代金券
		 * <li>NO_CASH---非充值代金券
		 * 	订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
		 * </pre>
		 */
		private String couponType;

		/**
		 * <pre>
		 * 代金券ID
		 * coupon_refund_id_$n
		 * 否
		 * String(20)
		 * 10000
		 * 代金券ID, $n为下标，从0开始编号
		 * </pre>
		 */
		private String couponId;

		/**
		 * <pre>
		 * 单个代金券支付金额
		 * coupon_refund_fee_$n
		 * 否
		 * Int
		 * 100
		 * 单个代金券支付金额, $n为下标，从0开始编号
		 * </pre>
		 */
		private Integer couponFee;

		public Coupon(String couponType, String couponId, Integer couponFee) {
			this.couponType = couponType;
			this.couponId = couponId;
			this.couponFee = couponFee;
		}

		public String getCouponType() {
			return this.couponType;
		}

		public void setCouponType(String couponType) {
			this.couponType = couponType;
		}

		public String getCouponId() {
			return this.couponId;
		}

		public void setCouponId(String couponId) {
			this.couponId = couponId;
		}

		public Integer getCouponFee() {
			return this.couponFee;
		}

		public void setCouponFee(Integer couponFee) {
			this.couponFee = couponFee;
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RefundRes [return_code=");
		builder.append(return_code);
		builder.append(", return_msg=");
		builder.append(return_msg);
		builder.append(", result_code=");
		builder.append(result_code);
		builder.append(", err_code=");
		builder.append(err_code);
		builder.append(", err_code_des=");
		builder.append(err_code_des);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", device_info=");
		builder.append(device_info);
		builder.append(", mch_id=");
		builder.append(mch_id);
		builder.append(", nonce_str=");
		builder.append(nonce_str);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append(", out_trade_no=");
		builder.append(out_trade_no);
		builder.append(", out_refund_no=");
		builder.append(out_refund_no);
		builder.append(", refund_id=");
		builder.append(refund_id);
		builder.append(", refund_channel=");
		builder.append(refund_channel);
		builder.append(", refund_fee=");
		builder.append(refund_fee);
		builder.append(", settlement_refund_fee=");
		builder.append(settlement_refund_fee);
		builder.append(", total_fee=");
		builder.append(total_fee);
		builder.append(", settlement_total_fee=");
		builder.append(settlement_total_fee);
		builder.append(", fee_type=");
		builder.append(fee_type);
		builder.append(", cash_fee=");
		builder.append(cash_fee);
		builder.append(", cash_fee_type=");
		builder.append(cash_fee_type);
		builder.append(", cash_refund_fee=");
		builder.append(cash_refund_fee);
		builder.append(", coupon_refund_fee=");
		builder.append(coupon_refund_fee);
		builder.append(", coupon_refund_count=");
		builder.append(coupon_refund_count);
		builder.append(", coupons=");
		builder.append(coupons);
		builder.append("]");
		return builder.toString();
	}

}
