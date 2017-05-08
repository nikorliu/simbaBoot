package com.simba.util.receive;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.simba.framework.util.common.ReflectUtil;
import com.simba.framework.util.common.XmlUtil;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.receive.conf.ConfType;

/**
 * 处理收到微信硬件服务器硬件相关消息的工具类
 * 
 * @author caozhejun
 *
 */
@Component
public class DealHardwareUtil {

	@Value("${wx.hardware.deal}")
	private String dealImpl;

	private Map<String, ConfType> typeMap = new HashMap<>();

	@PostConstruct
	private void init() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		InputStream in = DealHardwareUtil.class.getResourceAsStream("/wxHardware.xml");
		Document doc = XmlUtil.parseXMLStream(in);
		Element root = doc.getDocumentElement();
		NodeList nodeList = XmlUtil.selectNodes("/wx/msg", root);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element node = (Element) (nodeList.item(i));
			String type = node.getAttribute("type");
			String parser = node.getAttribute("parser");
			String dealMethod = node.getAttribute("dealMethod");
			ConfType f = new ConfType();
			f.setDealMethod(dealMethod);
			f.setParser(parser);
			f.setType(type);
			typeMap.put(type, f);
		}
	}

	/**
	 * 处理微信硬件服务器发送过来的硬件相关消息
	 * 
	 * @param json
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public String deal(String json)
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Map<String, Object> map = FastJsonUtil.toObject(json, HashMap.class);
		String msgType = map.get("msg_type").toString();
		ConfType conf = typeMap.get(msgType);
		if (conf == null) {
			throw new RuntimeException("配置文件wxHardware.xml中找不到可以处理的配置:" + json);
		}
		String parser = conf.getParser();
		String dealMethod = conf.getDealMethod();
		Object param = FastJsonUtil.toObject(json, Class.forName(parser));
		Object exeResult = ReflectUtil.invokeObjectMethod(dealImpl, dealMethod, param);
		String result = StringUtils.EMPTY;
		if (exeResult != null) {
			result = exeResult.toString();
		}
		return result;
	}
}
