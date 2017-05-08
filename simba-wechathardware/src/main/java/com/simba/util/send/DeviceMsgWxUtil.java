package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.DeviceMsg;
import com.simba.model.wxHardware.send.DeviceMsgResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.Base64Util;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 主动发送消息给设备工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class DeviceMsgWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 发送消息给设备
	 * 
	 * @param msg
	 */
	public void send(DeviceMsg msg) {
		msg.setContent(Base64Util.encode(msg.getContent()));
		String json = FastJsonUtil.toJson(msg);
		String url = WxHardwareConstantData.sendMessageToDeviceUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		postJsonUtil.postJson(url, json, "发送消息给设备", DeviceMsgResult.class);
	}
}
