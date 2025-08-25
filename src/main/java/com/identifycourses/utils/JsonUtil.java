package com.identifycourses.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.identifycourses.models.FormData;

public class JsonUtil {
	private static final Logger logger = LogManager.getLogger(JsonUtil.class);
			
	private ObjectMapper objectMapper;
	private String filePath;
	
	public JsonUtil(String filePath) {
		this.filePath = filePath;
		this.objectMapper = new ObjectMapper();
	}
	
	public FormData readFormData() {
		try {
			File file = new File(filePath);
			logger.info("Reading data from json file: {}", file.getName());
			return objectMapper.readValue(file, FormData.class);
		} catch(IOException e) {
			logger.error("Failed to load Json file: {}", filePath, e);
		}
		return null;
	}
}
