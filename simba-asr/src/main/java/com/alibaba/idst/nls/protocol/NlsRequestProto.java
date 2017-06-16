package com.alibaba.idst.nls.protocol;

public class NlsRequestProto {
	public static final String VERSION10 = "1.0";
	public static final String VERSION20 = "2.0";
	public static final String VERSION30 = "3.0";
	public static final String VERSION40 = "4.0";
	private String app_id = null;
	private String app_user_id = null;
	private String app_version = null;
	private String service_id;
	private String service_version;
	private String device_type = null;
	private String device_id = null;
	private String device_system_locale = null;
	private String device_timezone = null;
	private String device_uuid = null;
	private String device_imei = null;
	private String device_mac = null;
	private String device_brand = null;
	private String device_model = null;
	private String os_type = null;
	private String os_version = null;
	private String network_type = null;

	public String getApp_user_id() {
		return this.app_user_id;
	}

	public String getApp_version() {
		return this.app_version;
	}

	public String getDevice_type() {
		return this.device_type;
	}

	public String getDevice_id() {
		return this.device_id;
	}

	public String getDevice_system_locale() {
		return this.device_system_locale;
	}

	public String getDevice_timezone() {
		return this.device_timezone;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public void setApp_user_id(String app_user_id) {
		this.app_user_id = app_user_id;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public void setDevice_system_locale(String device_system_locale) {
		this.device_system_locale = device_system_locale;
	}

	public void setDevice_timezone(String device_timezone) {
		this.device_timezone = device_timezone;
	}

	public String getApp_id() {
		return this.app_id;
	}

	public String getService_id() {
		return this.service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_version() {
		return this.service_version;
	}

	public void setService_version(String service_version) {
		this.service_version = service_version;
	}

	public String getDevice_uuid() {
		return this.device_uuid;
	}

	public void setDevice_uuid(String device_uuid) {
		this.device_uuid = device_uuid;
	}

	public String getDevice_imei() {
		return this.device_imei;
	}

	public void setDevice_imei(String device_imei) {
		this.device_imei = device_imei;
	}

	public String getDevice_mac() {
		return this.device_mac;
	}

	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}

	public String getDevice_brand() {
		return this.device_brand;
	}

	public void setDevice_brand(String device_brand) {
		this.device_brand = device_brand;
	}

	public String getDevice_model() {
		return this.device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getOs_type() {
		return this.os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getOs_version() {
		return this.os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getNetwork_type() {
		return this.network_type;
	}

	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}
}