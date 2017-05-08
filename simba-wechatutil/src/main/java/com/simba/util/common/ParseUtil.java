package com.simba.util.common;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;

import com.simba.framework.util.common.XmlUtil;

public class ParseUtil {

	public static String getValue(Element root, String name) throws XPathExpressionException {
		Element e = (Element) XmlUtil.selectSingleNode("/xml/" + name, root);
		String result = null;
		if (e != null) {
			result = e.getTextContent();
		}
		return result;
	}

	public static String getToUserName(Element root) throws XPathExpressionException {
		return getValue(root, "ToUserName");
	}

	public static String getFromUserName(Element root) throws XPathExpressionException {
		return getValue(root, "FromUserName");
	}

	public static String getCreateTime(Element root) throws XPathExpressionException {
		return getValue(root, "CreateTime");
	}

	public static String getMsgType(Element root) throws XPathExpressionException {
		return getValue(root, "MsgType");
	}

	public static String getMsgId(Element root) throws XPathExpressionException {
		return getValue(root, "MsgId");
	}

	public static String getEventKey(Element root) throws XPathExpressionException {
		return getValue(root, "EventKey");
	}

	public static String getEvent(Element root) throws XPathExpressionException {
		return getValue(root, "Event");
	}

	public static String getMediaId(Element root) throws XPathExpressionException {
		return getValue(root, "MediaId");
	}

}
