package com.simba.model.wx.receive.msg;

/**
 * 地理位置消息
 * 
 * @author caozhejun
 *
 */
public class LocationMessage extends BaseMessage {

	/**
	 * X坐标信息
	 */
	private double x;

	/**
	 * Y坐标信息
	 */
	private double y;

	/**
	 * 精度，可理解为精度或者比例尺、越精细的话 scale越高
	 */
	private double scale;

	/**
	 * 地理位置的字符串信息
	 */
	private String label;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LocationMessage [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", scale=");
		builder.append(scale);
		builder.append(", label=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}

}
