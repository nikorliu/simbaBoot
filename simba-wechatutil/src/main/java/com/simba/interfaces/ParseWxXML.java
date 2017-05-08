package com.simba.interfaces;

import javax.xml.xpath.XPathExpressionException;

import org.apache.xerces.dom.DeferredElementImpl;

import com.simba.model.wx.receive.BaseReceiveObject;

public interface ParseWxXML {

	BaseReceiveObject parse(DeferredElementImpl root) throws XPathExpressionException;
}
