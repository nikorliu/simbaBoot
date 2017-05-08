package com.simba.interfaces.impl;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.BaseReceiveObject;
import com.simba.model.wx.receive.event.VerifyFailEvent;
import com.simba.util.common.ParseUtil;

public class VerifyFailEventParse implements ParseWxXML {

	@Override
	public BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException {
		VerifyFailEvent event = new VerifyFailEvent();
		event.setToUserName(ParseUtil.getToUserName(root));
		event.setFromUserName(ParseUtil.getFromUserName(root));
		event.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		event.setMsgType(ParseUtil.getMsgType(root));
		event.setEvent(ParseUtil.getValue(root, "Event"));
		event.setEventKey(ParseUtil.getValue(root, "EventKey"));
		event.setFailReason(ParseUtil.getValue(root, "FailReason"));
		event.setFailTime(NumberUtils.toLong(ParseUtil.getValue(root, "FailTime")));
		return event;
	}

}
