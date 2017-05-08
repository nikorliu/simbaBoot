package com.simba.interfaces.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xerces.dom.DeferredElementImpl;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.simba.framework.util.common.XmlUtil;
import com.simba.interfaces.ParseWxXML;
import com.simba.model.wx.receive.BaseReceiveObject;
import com.simba.model.wx.receive.event.SendPhotoEvent;
import com.simba.util.common.ParseUtil;

public class PhotoEventParse implements ParseWxXML {

	@Override
	public BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException {
		SendPhotoEvent event = new SendPhotoEvent();
		event.setToUserName(ParseUtil.getToUserName(root));
		event.setFromUserName(ParseUtil.getFromUserName(root));
		event.setCreateTime(NumberUtils.toLong(ParseUtil.getCreateTime(root)));
		event.setMsgType(ParseUtil.getMsgType(root));
		event.setEvent(ParseUtil.getValue(root, "Event"));
		event.setEventKey(ParseUtil.getValue(root, "EventKey"));
		event.setCount(NumberUtils.toInt(ParseUtil.getValue(root, "SendPicsInfo/Count")));
		NodeList nodeList = XmlUtil.selectNodes("/xml/SendPicsInfo/PicList/item/PicMd5Sum", root);
		if (nodeList.getLength() > 0) {
			List<String> picMd5List = new ArrayList<>(nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element node = ((Element) (nodeList.item(i)));
				picMd5List.add(node.getTextContent());
			}
			event.setPicMd5List(picMd5List);
		}
		return event;
	}

}
