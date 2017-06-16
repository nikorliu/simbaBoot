package com.alibaba.idst.nls.realtime.protocol;

import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.idst.nls.realtime.utils.Base64Encoder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class NlsRequestAuth {
	private String method = "POST";
	private AuthBody body = null;
	public String authbBody = null;

	public Headers headers = new Headers();

	public void add_Request(String type) {
		if (this.body == null) {
			this.body = new AuthBody();
		}

		this.body.requests.add(type);
	}

	public static String toGMTString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

	private String digestMsg() {
		SecretKeySpec keySpec = new SecretKeySpec(this.headers.ak_secret.getBytes(), "HmacSHA1");

		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		byte[] result = mac.doFinal(getDigestString().getBytes());
		return "Dataplus " + this.headers.ak_id + ":" + Base64Encoder.encode(result);
	}

	public String getDigestString() {
		String all = this.method + "\n";
		all = all + this.headers.accept + "\n";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] result = md.digest(this.authbBody.getBytes());
			all = all + Base64Encoder.encode(result) + "\n";
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		all = all + this.headers.content_type + "\n";
		this.headers.date = new Date();
		all = all + toGMTString(this.headers.date);
		return all;
	}

	public void Authorize(String id, String secret) {
		this.headers.ak_id = id;
		this.headers.ak_secret = secret;
		this.headers.Authorization = digestMsg();
	}

	public static class Headers {
		public String accept = "application/json";
		public String content_type = "application/json";
		public Date date = new Date();
		public String Authorization;
		public String ak_id = "";
		public String ak_secret = "";

		public static class AuthHeadersSerializer implements JsonSerializer<NlsRequestAuth.Headers> {
			public JsonElement serialize(NlsRequestAuth.Headers src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jobj = new JsonObject();

				jobj.add("accept", context.serialize(src.accept));
				jobj.add("date", context.serialize(NlsRequestAuth.toGMTString(src.date)));
				jobj.add("Authorization", new JsonPrimitive(src.Authorization));
				return jobj;
			}
		}
	}

	private static class AuthBody {
		public ArrayList<String> requests = new ArrayList<>();
	}
}