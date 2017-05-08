package com.simba.model.wx.receive.event;

/**
 * 弹出地理位置选择器的事件推送
 * 
 * @author caozhejun
 *
 */
public class LocationEvent extends BaseEvent {

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

	/**
	 * 朋友圈POI的名字，可能为空
	 */
	private String Poiname;

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

	public String getPoiname() {
		return Poiname;
	}

	public void setPoiname(String poiname) {
		Poiname = poiname;
	}

}
