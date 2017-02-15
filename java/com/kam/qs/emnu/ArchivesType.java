package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ArchivesType {

	SERVICE_NAME        ("服务名称"),
	SERVICE_ACTION_TYPE ("服务方法类型");

	private String description;
	
	private ArchivesType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ArchivesType value : ArchivesType.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
