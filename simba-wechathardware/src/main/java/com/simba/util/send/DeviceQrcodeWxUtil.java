package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wxHardware.send.DeviceQrcode;
import com.simba.model.wxHardware.send.DeviceQrcodeResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxHardwareConstantData;

/**
 * 设备二维码工具类(1、第三方调用该服务，需要获得设备功能权限。
 * 2、建议deviceid为英文字母、下划线、数字三类字符的串或者组合，不带其他标点符号，以免json串解析失败
 * 3、二维码的生成有可能失败，因此请求的devcie num和响应的devcie num不一定相等；如果不相等，第三方需要核对下请求中哪些device
 * id没有生成成功
 * 4、响应中的ticket为二维码的生成串，第三方需要用这些串来生成二维码（点阵图），为了提高二维码的扫码成功率，我们建议第三方：使用qrencode库，
 * QR码版本5，纠错等级为Q级，容错率不低于20%
 * 5、返回的ticket字符串，会带有json的敏感字符，因此，公众平台对于敏感字符做了转义（如：/字符会被转义成\/），第三方需要将这些敏感字符转义回来
 * 6、设备二维码ticket生成需要耗费系统资源，因此，建议公众号开发者一次操作不超过5个)(公众平台返回的二维码生成串形式如：
 * http://we.qq.com/d/QRCODE_TICKET（其中QRCODE_TICKET是微信生产的二维码ticket），
 * 第三方可以自行选择是否在公众平台返回的二维码的基础之上追加自定义的数据，是否追加自定义数据是可选的，由第三方自行决定。
 * 追加自定义数据的方法：在公众平台的二维码后追加#3RD_DEFINE_DATA（其中，#作为分隔符，3RD_DEFINE_DATA是第三方自定义数据），
 * 追加后的二维码形式如：http://we.qq.com/d/QRCODE_TICKET#3RD_DEFINE_DATA 。
 * 在用户扫描绑定设备的时候，公众平台会把二维码中的3RD_DEFINE_DATA使用base64编码，放到bind消息中，推送给第三方（详情参考1.2章节
 * 消息接口：“绑定/解绑”设备）。)
 * 
 * @author caozhejun
 *
 */
@Component
public class DeviceQrcodeWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	/**
	 * 获取设备二维码
	 * 
	 * @param code
	 */
	public DeviceQrcodeResult send(DeviceQrcode code) {
		String url = WxHardwareConstantData.createQrcodeUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(code);
		return postJsonUtil.postJson(url, json, "获取设备二维码", DeviceQrcodeResult.class);
	}
}
