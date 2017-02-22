package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TaskStatus {

	EDITING   ("计划中"),
	PUBLISHED ("已发布"),
	FINISHED  ("已归档");

	private String description;
	
	private TaskStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (TaskStatus value : TaskStatus.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
