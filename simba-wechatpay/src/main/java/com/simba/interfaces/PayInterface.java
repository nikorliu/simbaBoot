package com.simba.interfaces;

import com.simba.model.pay.result.PayResult;
import com.simba.model.pay.unifiedorder.UnifiedOrderReq;

/**
 * 微信支付需要实现的接口
 * 
 * @author caozhejun
 *
 */
public interface PayInterface {

	/**
	 * 处理支付通知结果
	 * 
	 * @param payResult
	 */
	void dealResult(PayResult payResult);

	/**
	 * 处理订单
	 * 
	 * @param req
	 */
	void dealOrder(UnifiedOrderReq req);
}
