package com.alibaba.idst.nls.realtime.protocol;

import java.lang.reflect.Type;

import com.google.gson.Gson;
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
	private String version;
	private String request_id;
	private int status_code;
	private String finish = "0";

	public Result result = null;

	public String getFinish() {
		return this.finish;
	}

	public void setFinish(String sign) {
		this.finish = sign;
	}

	public String getRequest_id() {
		return this.request_id;
	}

	public String getVersion() {
		return this.version;
	}

	public String getId() {
		return this.request_id;
	}

	public int getSentenceStatusCode() {
		if (this.result != null) {
			return this.result.getSentenceStatusCode();
		}
		return 0;
	}

	public String getText() {
		if (this.result != null) {
			return this.result.getText();
		}
		return null;
	}

	public int getBegin_time() {
		if (this.result != null) {
			return this.result.getBegin_time();
		}
		return 0;
	}

	public int getEnd_time() {
		if (this.result != null) {
			return this.result.getEnd_time();
		}
		return 0;
	}

	public int getSentence_id() {
		if (this.result != null) {
			return this.result.getSentence_id();
		}
		return 0;
	}

	public String getResult() {
		return new Gson().toJson(this.result);
	}

	public void loadFromJson(JsonObject jsonResult) throws JsonParseException {
		this.version = jsonResult.get("version").getAsString();

		if (jsonResult.has("request_id")) {
			this.request_id = jsonResult.get("request_id").getAsString();
		}
		if (jsonResult.has("status_code")) {
			setStatus_code(jsonResult.get("status_code").getAsInt());
		}
		if (jsonResult.has("finish")) {
			this.finish = jsonResult.get("finish").getAsString();
		}
		if (jsonResult.has("result")) {
			this.result = new Result();
			JsonObject jsonObject = jsonResult.get("result").getAsJsonObject();
			if (jsonObject.has("status_code")) {
				this.result.sentence_status_code = jsonObject.get("status_code").getAsInt();
			}
			if (jsonObject.has("begin_time")) {
				this.result.begin_time = jsonObject.get("begin_time").getAsInt();
			}
			if (jsonObject.has("end_time")) {
				this.result.end_time = jsonObject.get("end_time").getAsInt();
			}
			if (jsonObject.has("sentence_id")) {
				this.result.sentence_id = jsonObject.get("sentence_id").getAsInt();
			}
			if (jsonObject.has("text"))
				this.result.text = jsonObject.get("text").getAsString();
		}
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

	public class Result {
		private int sentence_status_code;
		private int begin_time;
		private int end_time;
		private int sentence_id;
		private String text;

		public Result() {
		}

		public int getSentenceStatusCode() {
			return this.sentence_status_code;
		}

		public String getText() {
			return this.text;
		}

		public int getBegin_time() {
			return this.begin_time;
		}

		public int getEnd_time() {
			return this.end_time;
		}

		public int getSentence_id() {
			return this.sentence_id;
		}
	}
}