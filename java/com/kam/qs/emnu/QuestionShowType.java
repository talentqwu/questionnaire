package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum QuestionShowType {

	CHECK_BOX       ("单选题"),
	RADIO_GROUP     ("多选题"),
	DROP_DOWN_LIST  ("主试题"),
	NONE            ("无");

	private String description;
	
	private QuestionShowType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (QuestionShowType value : QuestionShowType.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
