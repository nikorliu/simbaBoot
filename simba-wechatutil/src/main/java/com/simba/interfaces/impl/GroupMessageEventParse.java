package com.simba.interfaces.impl;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.BaseReceiveObject;
import com.simba.model.wx.receive.event.GroupMessageEvent;
import com.simba.util.common.ParseUtil;

public class GroupMessageEventParse implements ParseWxXML {

	@Override
	public BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException {
		GroupMessageEvent event = new GroupMessageEvent();
		event.setToUserName(ParseUtil.getToUserName(root));
		event.setFromUserName(ParseUtil.getFromUserName(root));
		event.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		event.setMsgType(ParseUtil.getMsgType(root));
		event.setEvent(ParseUtil.getValue(root, "Event"));
		event.setEventKey(ParseUtil.getValue(root, "EventKey"));
		event.setStatus(ParseUtil.getValue(root, "Status"));
		event.setCheckState(NumberUtils.toInt(ParseUtil.getValue(root, "CheckState")));
		event.setTotalCount(NumberUtils.toInt(ParseUtil.getValue(root, "TotalCount")));
		event.setFilterCount(NumberUtils.toInt(ParseUtil.getValue(root, "FilterCount")));
		event.setSentCount(NumberUtils.toInt(ParseUtil.getValue(root, "SentCount")));
		event.setErrorCount(NumberUtils.toInt(ParseUtil.getValue(root, "ErrorCount")));
		return event;
	}

}
