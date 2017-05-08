package com.simba.interfaces.impl;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.BaseReceiveObject;
import com.simba.model.wx.receive.msg.ImageMessage;
import com.simba.util.common.ParseUtil;

public class ImageParse implements ParseWxXML {

	@Override
	public BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException {
		ImageMessage message = new ImageMessage();
		message.setToUserName(ParseUtil.getToUserName(root));
		message.setFromUserName(ParseUtil.getFromUserName(root));
		message.setMsgId(ParseUtil.getMsgId(root));
		message.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		message.setMsgType(ParseUtil.getMsgType(root));
		message.setMediaId(ParseUtil.getValue(root, "MediaId"));
		message.setPicUrl(ParseUtil.getValue(root, "PicUrl"));
		return message;
	}

}
