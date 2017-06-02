package com.simba.util.receive;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.simba.framework.util.common.ReflectUtil;
import com.simba.framework.util.common.XmlUtil;
import com.simba.model.wx.receive.conf.ConfType;
import com.simba.schedule.AsyncUtil;

/**
 * 处理微信发送的消息的工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class DealReceiveUtil {

	@Value("${wx.deal}")
	private String wxDeal;

	@Autowired
	private AsyncUtil asyncUtil;

	private Map<String, Map<String, ConfType>> asyncTypeMap = new HashMap<>();

	private Map<String, Map<String, ConfType>> syncTypeMap = new HashMap<>();

	@PostConstruct
	private void init() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		initAsync();
		initSync();
	}

	private void initSync() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		InputStream in = DealReceiveUtil.class.getResourceAsStream("/wx_sync.xml");
		Document doc = XmlUtil.parseXMLStream(in);
		Element root = doc.getDocumentElement();
		syncTypeMap.put("deviceEvent", parse("/wx/device_events/event", root));
	}

	private void initAsync() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		InputStream in = DealReceiveUtil.class.getResourceAsStream("/wx_async.xml");
		Document doc = XmlUtil.parseXMLStream(in);
		Element root = doc.getDocumentElement();
		asyncTypeMap.put("event", parse("/wx/events/event", root));
		asyncTypeMap.put("msg", parse("/wx/msgs/msg", root));
	}

	private Map<String, ConfType> parse(String xpath, Element root) throws XPathExpressionException {
		Map<String, ConfType> map = new HashMap<>();
		NodeList nodeList = XmlUtil.selectNodes(xpath, root);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element node = (Element) (nodeList.item(i));
			String type = node.getAttribute("type");
			String parser = node.getAttribute("parser");
			String dealMethod = node.getAttribute("dealMethod");
			ConfType f = new ConfType();
			f.setDealMethod(dealMethod);
			f.setParser(parser);
			f.setType(type);
			map.put(type, f);
		}
		return map;
	}

	/**
	 * 处理微信发送的消息
	 * 
	 * @param xml
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public String deal(String xml) throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Document doc = XmlUtil.parseXMLContent(xml);
		Element root = doc.getDocumentElement();
		Element msgType = (Element) XmlUtil.selectSingleNode("/xml/MsgType", root);
		String type = msgType.getTextContent();
		ConfType conf = null;
		boolean async = true;
		if (type.equalsIgnoreCase("event")) {
			Element event = (Element) XmlUtil.selectSingleNode("/xml/Event", root);
			String key = event.getTextContent();
			conf = asyncTypeMap.get("event").get(key);
		} else if (type.equalsIgnoreCase("device_event")) {
			async = false;
			Element event = (Element) XmlUtil.selectSingleNode("/xml/Event", root);
			String key = event.getTextContent();
			conf = syncTypeMap.get("deviceEvent").get(key);
		} else {
			conf = asyncTypeMap.get("msg").get(type);
		}
		if (conf == null) {
			throw new RuntimeException("配置文件中找不到可以处理的配置:" + xml);
		}
		String result = "success";
		String parser = conf.getParser();
		String dealMethod = conf.getDealMethod();
		Object object = ReflectUtil.invokeObjectMethod(parser, "parse", root);
		if (async) {
			asyncUtil.execute(wxDeal, dealMethod, object, xml);
		} else {
			Object res = ReflectUtil.invokeObjectMethod(wxDeal, dealMethod, object, xml);
			if (res != null) {
				result = res.toString();
			}
		}
		return result;
	}
}
