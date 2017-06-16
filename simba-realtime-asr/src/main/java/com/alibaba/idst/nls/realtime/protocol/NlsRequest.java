package com.alibaba.idst.nls.realtime.protocol;

import java.lang.reflect.Type;

import com.alibaba.idst.nls.realtime.utils.RawJsonText;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class NlsRequest {
	private String version = "1.0";

	@Deprecated
	private boolean enableCompress = false;

	private transient Gson gsonCvt = RawJsonText.createGson();

	private RequestSet request = new RequestSet();

	private NlsRequestContext context = new NlsRequestContext();

	public void authorize(String id, String secret) {
		this.context.auth = new NlsRequestAuth();
		if (this.request.app_key != null) {
			Gson gson = new Gson();
			this.context.auth.authbBody = gson.toJson(this.request);
		}

		this.context.auth.Authorize(id, secret);
	}

	public String getFormat() {
		return this.request.format;
	}

	public int getSampleRate() {
		return this.request.sample_rate;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setVocabularyId(String vocabularyId) {
		this.request.vocabulary_id = vocabularyId;
	}

	public String getVocabularyId() {
		return this.request.vocabulary_id;
	}

	public void setUserId(String userId) {
		this.request.user_id = userId;
	}

	public String getUserId() {
		return this.request.user_id;
	}

	public void setAppkey(String appkey) {
		this.request.app_key = appkey;
	}

	public String getAppkey() {
		return this.request.app_key;
	}

	@Deprecated
	public void setEnableCompress(boolean enableCompress) {
		this.enableCompress = enableCompress;
	}

	public boolean isEnableCompress() {
		return this.enableCompress;
	}

	public void setFormat(String format) {
		if ((this.enableCompress) && (format.equals("pcm")))
			this.request.format = "opu";
		else
			this.request.format = format;
	}

	public void setSampleRate(int sampleRate) {
		this.request.sample_rate = sampleRate;
	}

	public void setResponseMode(String responseMode) {
		this.request.response_mode = responseMode;
	}

	public String toJson() {
		String strReq = this.gsonCvt.toJson(this);
		return strReq;
	}

	public class SdkInfo {
		String version = "1.1.0";
		String sdk_type = "java";

		public SdkInfo() {
		}
	}

	public static class RequestSet {
		public String app_key;
		public String format = "pcm";
		public int sample_rate = 16000;
		public String response_mode;
		public String user_id;
		public String vocabulary_id;

		public static class RequestSetSerializer implements JsonSerializer<NlsRequest.RequestSet> {
			public JsonElement serialize(NlsRequest.RequestSet src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jobj = new JsonObject();
				if (src.app_key != null) {
					jobj.add("app_key", context.serialize(src.app_key));
				}

				if (src.format != null) {
					jobj.add("format", context.serialize(src.format));
				}

				if (src.sample_rate != 0) {
					jobj.add("sample_rate", context.serialize(Integer.valueOf(src.sample_rate)));
				}

				if (src.response_mode != null) {
					jobj.add("response_mode", context.serialize(src.response_mode));
				}

				if (src.user_id != null) {
					jobj.add("user_id", context.serialize(src.user_id));
				}

				if (src.vocabulary_id != null) {
					jobj.add("vocabulary_id", context.serialize(src.vocabulary_id));
				}

				return jobj;
			}
		}
	}
}