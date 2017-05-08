package com.simba.interfaces.impl;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.deviceEvent.DeviceEvent;
import com.simba.util.common.ParseUtil;

public class DeviceEventParse implements ParseWxXML {

	@Override
	public DeviceEvent parse(DeferredElementImpl root) throws XPathExpressionException {
		DeviceEvent event = new DeviceEvent();
		event.setToUserName(ParseUtil.getToUserName(root));
		event.setFromUserName(ParseUtil.getFromUserName(root));
		event.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		event.setMsgType(ParseUtil.getMsgType(root));
		event.setDeviceType(ParseUtil.getValue(root, "DeviceType"));
		event.setDeviceId(ParseUtil.getValue(root, "DeviceID"));
		event.setEvent(ParseUtil.getValue(root, "Event"));
		event.setOpType(ParseUtil.getValue(root, "OpType"));
		event.setOpenid(ParseUtil.getValue(root, "OpenID"));
		return event;
	}

}
