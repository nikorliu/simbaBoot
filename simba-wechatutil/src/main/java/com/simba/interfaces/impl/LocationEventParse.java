package com.simba.interfaces.impl;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.BaseReceiveObject;
import com.simba.model.wx.receive.event.LocationEvent;
import com.simba.util.common.ParseUtil;

public class LocationEventParse implements ParseWxXML {

	@Override
	public BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException {
		LocationEvent event = new LocationEvent();
		event.setToUserName(ParseUtil.getToUserName(root));
		event.setFromUserName(ParseUtil.getFromUserName(root));
		event.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		event.setMsgType(ParseUtil.getMsgType(root));
		event.setEvent(ParseUtil.getValue(root, "Event"));
		event.setEventKey(ParseUtil.getValue(root, "EventKey"));
		event.setX(NumberUtils.toDouble(ParseUtil.getValue(root, "SendLocationInfo/Location_X")));
		event.setY(NumberUtils.toDouble(ParseUtil.getValue(root, "SendLocationInfo/Location_Y")));
		event.setScale(NumberUtils.toDouble(ParseUtil.getValue(root, "SendLocationInfo/Scale")));
		event.setLabel(ParseUtil.getValue(root, "SendLocationInfo/Label"));
		event.setPoiname(ParseUtil.getValue(root, "SendLocationInfo/Poiname"));
		return event;
	}

}
