package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TreeNodeCategory {
	TASK	   ("任务管理"),
	ANALYSIS   ("统计分析"),
	SETTING    ("系统设置"),
	MANAGE     ("系统管理");

	private String description;
	
	private TreeNodeCategory(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (TreeNodeCategory value : TreeNodeCategory.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
