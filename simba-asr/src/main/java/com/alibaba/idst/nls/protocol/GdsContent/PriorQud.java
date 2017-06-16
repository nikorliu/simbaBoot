package com.alibaba.idst.nls.protocol.GdsContent;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PriorQud {
	private String action;
	private JsonElement expect;
	private int count;
	private JsonElement data;

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public JsonElement getExpect() {
		return this.expect;
	}

	public void setExpect(JsonElement expect) {
		this.expect = expect;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public JsonElement getData() {
		return this.data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}

	public static class JsonElementSerializer implements JsonSerializer<JsonElement> {
		public JsonElement serialize(JsonElement data, Type typeOfT, JsonSerializationContext context) {
			return data;
		}
	}
}