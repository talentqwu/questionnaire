package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SubTaskAction {

	SAVE     ("保存"),
	DELETE   ("删除");

	private String description;
	
	private SubTaskAction(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (SubTaskAction value : SubTaskAction.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
