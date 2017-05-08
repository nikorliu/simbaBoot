package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.SendStatus;
import com.simba.model.wxHardware.send.SendStatusResult;
import com.simba.model.wxHardware.send.StatusResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 状态查询工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class StatusWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private GetUtil getUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 设备状态查询
	 * 
	 * @param deviceId
	 * @return
	 */
	public StatusResult search(String deviceId) {
		String url = WxHardwareConstantData.searchStatusUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&device_id=" + deviceId;
		return getUtil.get(url, "设备状态查询", StatusResult.class);
	}

	/**
	 * 第三方主动发送设备状态消息给微信终端
	 * 
	 * @param status
	 * @return
	 */
	public SendStatusResult send(SendStatus status) {
		String url = WxHardwareConstantData.sendStatusUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(status);
		return postJsonUtil.postJson(url, json, "第三方主动发送设备状态消息给微信终端", SendStatusResult.class);
	}
}
