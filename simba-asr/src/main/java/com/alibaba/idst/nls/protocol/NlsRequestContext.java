package com.alibaba.idst.nls.protocol;

public class NlsRequestContext {
	public NlsRequestAuth auth = null;

	public LocationInfo location = new LocationInfo();

	public DeviceInfo device = new DeviceInfo();

	public SdkInfo sdk = new SdkInfo();

	public ApplicationData application = new ApplicationData();

	public Debug debug = new Debug();

	public class Debug {
		public boolean GDS_AllResultCode;

		public Debug() {
		}
	}

	public class ApplicationData {
		String application_id;
		String service_id;
		String service_version;
		String user_id;
		String version;

		public ApplicationData() {
		}
	}

	public class SdkInfo {
		String version = "3.2.2";
		String sdk_type = "java";

		public SdkInfo() {
		}
	}

	public static class DeviceInfo {
		public String device_type = null;
		public String device_id = null;
		public String device_uuid = null;
		public String device_imei = null;
		public String device_mac = null;
		public String device_brand = null;
		public String device_model = null;
		public String os_type = null;
		public String os_version = null;
		public String network_type = null;
		public String system_locale = null;
		public String timezone = null;
	}

	public static class LocationInfo {
		public String longitude;
		public String latitude;
		public GeoInfo geo = new GeoInfo();

		public GeoInfo Geo() {
			if (this.geo == null) {
				this.geo = new GeoInfo();
			}
			return this.geo;
		}

		public class GeoInfo {
			public String level1;
			public String level2;
			public String level3;
			public String level4;
			public String level5;

			public GeoInfo() {
			}
		}
	}
}