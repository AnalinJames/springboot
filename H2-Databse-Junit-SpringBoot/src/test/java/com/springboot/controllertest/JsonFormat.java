package com.springboot.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormat {
	
	public static String getJsonFormat(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = " ";
		if (object != null) {
			try {
				jsonStr = objectMapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
			}
		}
		return jsonStr;
	}

}
