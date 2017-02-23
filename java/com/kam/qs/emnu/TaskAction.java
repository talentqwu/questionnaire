package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TaskAction {

	SAVE     ("保存"),
	DELETE   ("删除"),
	PUBLISH  ("发布"),
	COPY	 ("复制"),
	ADJUST	 ("调整"),
	CANCEL	 ("取消");

	private String description;
	
	private TaskAction(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (TaskAction value : TaskAction.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
