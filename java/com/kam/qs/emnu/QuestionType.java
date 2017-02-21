package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum QuestionType {
	
	SINGLE_CHOICE   ("单选题"),
	MULTIPLE_CHOICE ("多选题");

	private String description;
	
	private QuestionType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (QuestionType value : QuestionType.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
