package com.simba.model.wx.receive.conf;

public class ConfType {

	/**
	 * 类型，对应消息的MsgType，事件的Event
	 */
	private String type;

	/**
	 * 解析xml的实现类的全路径
	 */
	private String parser;

	/**
	 * 处理解析后的对象的方法名
	 */
	private String dealMethod;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getDealMethod() {
		return dealMethod;
	}

	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfType [type=");
		builder.append(type);
		builder.append(", parser=");
		builder.append(parser);
		builder.append(", dealMethod=");
		builder.append(dealMethod);
		builder.append("]");
		return builder.toString();
	}

}
