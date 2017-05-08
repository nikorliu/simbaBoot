package com.simba.model.wx.receive.event;

/**
 * 上报地理位置事件
 * 
 * @author caozhejun
 *
 */
public class ReportLocationEvent extends BaseEvent {

	/**
	 * 地理位置纬度
	 */
	private double latitude;

	/**
	 * 地理位置经度
	 */
	private double longitude;

	/**
	 * 地理位置精度
	 */
	private double precision;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportLocationEvent [latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", precision=");
		builder.append(precision);
		builder.append("]");
		return builder.toString();
	}

}
