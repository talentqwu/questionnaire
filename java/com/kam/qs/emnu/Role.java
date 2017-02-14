package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Role {
	
	PUBLISH  ("调查发布员"),
	ANALYSIS ("调查分析员"),
	ADMIN	 ("系统管理员"),
	SYSTEM	 ("超级管理员");

	private String description;
	
	private Role(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Role value : Role.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
