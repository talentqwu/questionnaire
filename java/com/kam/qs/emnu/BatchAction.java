package com.kam.qs.emnu;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BatchAction {

	SAVE     ("保存"),
	DELETE   ("删除"),
	ADJUST	 ("调整");

	private String description;
	
	private BatchAction(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BatchAction value : BatchAction.values())
			map.put(value.toString(), value.description);
		return map;
	}
}
