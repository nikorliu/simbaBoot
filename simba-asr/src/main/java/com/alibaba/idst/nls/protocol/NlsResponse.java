package com.alibaba.idst.nls.protocol;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class NlsResponse {
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	private String version = "4.0";
	private String id;
	private String status = "1";

	private int status_code = 200;

	private String finish = "0";

	private Boolean bstream_attached = Boolean.valueOf(false);

	public JsonObject jsonResults = null;
	private String errorMassage;
	private int errorCode;

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String sid) {
		this.id = sid;
	}

	public String getStatus() {
		return this.status;
	}

	public String getFinish() {
		return this.finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getAsr_ret() {
		if ((this.jsonResults == null) || (this.jsonResults.get("asr_out") == null)) {
			return null;
		}

		return this.jsonResults.get("asr_out").getAsJsonObject().toString();
	}

	public String getDs_ret() {
		if ((this.jsonResults == null) || (this.jsonResults.get("ds_out") == null)) {
			return null;
		}

		return this.jsonResults.get("ds_out").getAsJsonObject().toString();
	}

	public String getGds_ret() {
		if ((this.jsonResults == null) || (this.jsonResults.get("gds_out") == null)) {
			return null;
		}

		return this.jsonResults.get("gds_out").getAsJsonObject().toString();
	}

	public String getTts_ret() {
		if ((this.jsonResults == null) || (this.jsonResults.get("tts_out") == null)) {
			return null;
		}

		return this.jsonResults.get("tts_out").getAsJsonObject().toString();
	}

	public String getExt_userResult(String name) {
		if ((this.jsonResults == null) || (this.jsonResults.get(name) == null)) {
			return null;
		}

		return this.jsonResults.get(name).getAsJsonObject().toString();
	}

	public String getErrorMassage() {
		return this.errorMassage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void loadFromJson(JsonObject jsonResult) throws JsonParseException {
		this.version = jsonResult.get("version").getAsString();
		setBstream_attached(Boolean.valueOf(jsonResult.get("bstream_attached").getAsBoolean()));

		if (jsonResult.has("id")) {
			this.id = jsonResult.get("id").getAsString();
		}
		if (jsonResult.has("status")) {
			this.status = jsonResult.get("status").getAsString();
		}
		if (jsonResult.has("status_code")) {
			setStatus_code(jsonResult.get("status_code").getAsInt());
		}
		if (jsonResult.has("finish")) {
			this.finish = jsonResult.get("finish").getAsString();
		}
		if (jsonResult.has("results")) {
			this.jsonResults = jsonResult.get("results").getAsJsonObject();
		}
		if (jsonResult.has("error_code")) {
			setErrorCode(jsonResult.get("error_code").getAsInt());
		}
		if (jsonResult.has("error_message"))
			setErrorMassage(jsonResult.get("error_message").getAsString());
	}

	public Boolean getBstream_attached() {
		return this.bstream_attached;
	}

	public void setBstream_attached(Boolean bstream_attached) {
		this.bstream_attached = bstream_attached;
	}

	public int getStatus_code() {
		return this.status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public static class NlsResponseDeserializer implements JsonDeserializer<NlsResponse> {
		public NlsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			NlsResponse response = new NlsResponse();
			response.loadFromJson(json.getAsJsonObject());
			return response;
		}
	}
}