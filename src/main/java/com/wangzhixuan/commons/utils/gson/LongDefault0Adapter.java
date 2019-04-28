package com.wangzhixuan.commons.utils.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {

	@Override
	public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		try {
			if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {// 定义为int类型,如果后台返回""或者null,则返回0
				return 0l;
			}
		} catch (Exception ignore) {
		}
		try {
			return json.getAsLong();
		} catch (NumberFormatException e) {
			throw new JsonSyntaxException(e);
		}
	}

	@Override
	public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src);
	}
}