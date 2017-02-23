package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum QuestionScoringMethod {

	BY_WEIGHT   ("按权重"),
	BY_SCRIPT   ("按脚本"),
	NONE        ("无");

	private String description;
	
	private QuestionScoringMethod(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (QuestionScoringMethod value : QuestionScoringMethod.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
