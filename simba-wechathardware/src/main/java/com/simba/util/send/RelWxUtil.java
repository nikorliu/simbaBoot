package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.model.wxHardware.send.SearchDeviceIdResult;
import com.simba.model.wxHardware.send.SearchOpenIDResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 设备用户关系工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class RelWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 获取设备绑定openID
	 * 
	 * @param deviceType
	 * @param deviceId
	 * @return
	 */
	public SearchOpenIDResult search(String deviceType, String deviceId) {
		String url = WxHardwareConstantData.searchOpenIdUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&device_type=" + deviceType + "&device_id=" + deviceId;
		return getUtil.get(url, "获取设备绑定openID", SearchOpenIDResult.class);
	}

	/**
	 * 通过openid获取用户绑定的deviceid
	 * 
	 * @param openid
	 * @return
	 */
	public SearchDeviceIdResult search(String openid) {
		String url = WxHardwareConstantData.searchDeviceIdUrl + "?access_token=" + accessTokenUtil.getAccessToken() + "&openid=" + openid;
		return getUtil.get(url, "通过openid获取用户绑定的deviceid", SearchDeviceIdResult.class);
	}
}
