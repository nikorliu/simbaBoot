package com.alibaba.idst.nls.realtime.utils;

import java.lang.reflect.Type;

import com.alibaba.idst.nls.realtime.protocol.NlsRequest;
import com.alibaba.idst.nls.realtime.protocol.NlsRequestAuth;
import com.alibaba.idst.nls.realtime.protocol.NlsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RawJsonText {
	public String text = null;

	public RawJsonText(String str) {
		this.text = str;
	}

	public static Gson createGson(Boolean pretty) {
		GsonBuilder gsonbuilder = new GsonBuilder();
		gsonbuilder.disableHtmlEscaping();
		gsonbuilder.registerTypeAdapter(NlsResponse.class, new NlsResponse.NlsResponseDeserializer());
		gsonbuilder.registerTypeAdapter(RawJsonText.class, new RawJsonDataSerializer());
		gsonbuilder.registerTypeAdapter(NlsRequest.RequestSet.class, new NlsRequest.RequestSet.RequestSetSerializer());
		gsonbuilder.registerTypeAdapter(NlsRequestAuth.Headers.class, new NlsRequestAuth.Headers.AuthHeadersSerializer());
		if (pretty.booleanValue()) {
			gsonbuilder.setPrettyPrinting();
		}

		return gsonbuilder.create();
	}

	public static Gson createGson() {
		return createGson(Boolean.valueOf(false));
	}

	private static class RawJsonDataSerializer implements JsonSerializer<RawJsonText> {
		public JsonElement serialize(RawJsonText src, Type typeOfSrc, JsonSerializationContext context) {
			JsonParser parser = new JsonParser();
			return parser.parse(src.text).getAsJsonObject();
		}
	}
}