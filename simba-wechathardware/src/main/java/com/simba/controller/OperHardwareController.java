package com.simba.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.wxHardware.send.BindForce;
import com.simba.model.wxHardware.send.Device;
import com.simba.model.wxHardware.send.DeviceInfo;
import com.simba.model.wxHardware.send.GetDeviceQrcodeResult;
import com.simba.model.wxHardware.send.StatusResult;
import com.simba.util.send.BindWxUtil;
import com.simba.util.send.NewAuthWxUtil;
import com.simba.util.send.StatusWxUtil;

/**
 * 用来提供给第三方平台的操作Controller
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/operHardware")
public class OperHardwareController {

	private static final Log logger = LogFactory.getLog(OperHardwareController.class);

	@Autowired
	private BindWxUtil bindWxUtil;

	@Autowired
	private StatusWxUtil statusWxUtil;

	@Autowired
	private NewAuthWxUtil newAuthWxUtil;

	/**
	 * 解除设备绑定
	 * 
	 * @param wxUserDeviceId
	 * @param model
	 * @return
	 */
	@RequestMapping("/unbindDevice")
	public String unbindDevice(String openid, String deviceId, ModelMap model) {
		logger.info("**********************收到解除设备绑定的请求**********************");
		BindForce unbind = new BindForce();
		unbind.setOpenid(openid);
		unbind.setDevice_id(deviceId);
		bindWxUtil.unbindForce(unbind);
		model.put("message", FastJsonUtil.toJson(new JsonResult()));
		return "message";
	}

	/**
	 * 查询设备状态
	 * 
	 * @param deviceId
	 * @param model
	 * @return
	 */
	@RequestMapping("/searchDeviceStatus")
	public String searchDeviceStatus(String deviceId, ModelMap model) {
		logger.info("**********************收到 查询设备状态的请求**********************");
		StatusResult result = statusWxUtil.search(deviceId);
		model.put("message", FastJsonUtil.toJson(new JsonResult(result.getStatus(), result.getStatus_info(), 200)));
		return "message";
	}

	/**
	 * 生成二维码和对应的设备id
	 * 
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/genQrcode")
	public String genQrcode(String productId, ModelMap model) {
		logger.info("**********************收到生成二维码和对应的设备id的请求**********************");
		GetDeviceQrcodeResult result = newAuthWxUtil.getDeviceQrcode(productId);
		model.put("message", FastJsonUtil.toJson(new JsonResult(result)));
		return "message";
	}

	/**
	 * 利用deviceid更新设备属性
	 * 
	 * @param device
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateDevicePros")
	public String updateDevicePros(Device device, ModelMap model) {
		logger.info("**********************收到利用deviceid更新设备属性的请求**********************");
		DeviceInfo deviceInfo = new DeviceInfo();
		deviceInfo.setDevice_num("1");
		deviceInfo.setOp_type("1");
		List<Device> device_list = new ArrayList<Device>(1);
		device_list.add(device);
		deviceInfo.setDevice_list(device_list);
		newAuthWxUtil.updateDeviceInfo(deviceInfo);
		model.put("message", FastJsonUtil.toJson(new JsonResult()));
		return "message";
	}

}
