package com.simba.model.pay.unifiedorder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.simba.framework.util.common.XmlUtil;

/**
 * 统一下单请求对象
 * 
 * @author caozhejun
 *
 */
@XStreamAlias("xml")
public class UnifiedOrderReq {

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
	 * 商品简单描述
	 */
	private String body;

	/**
	 * 单品优惠字段(暂未上线)
	 */
	private String detail;

	/**
	 * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
	 */
	private String attach;

	/**
	 * 商户系统内部订单号，要求32个字符内、且在同一个商户号下唯一
	 */
	private String out_trade_no;

	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	private String fee_type = "CNY";

	/**
	 * 订单总金额，单位为分
	 */
	private int total_fee;

	/**
	 * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 */
	private String spbill_create_ip;

	/**
	 * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 */
	private String time_start;

	/**
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010(
	 * 最短失效时间间隔必须大于5分钟)
	 */
	private String time_expire;

	/**
	 * 商品标记，使用代金券或立减优惠功能时需要的参数
	 */
	private String goods_tag;

	/**
	 * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 */
	private String notify_url;

	/**
	 * 取值如下：JSAPI，NATIVE，APP等
	 */
	private String trade_type;

	/**
	 * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	 */
	private String product_id;

	/**
	 * 上传此参数no_credit--可限制用户不能使用信用卡支付
	 */
	private String limit_pay;

	/**
	 * trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
	 */
	private String openid;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String toXML() {
		return XmlUtil.fromObject(this);
	}

	@Override
	public String toString() {
		return toXML();
	}

}
