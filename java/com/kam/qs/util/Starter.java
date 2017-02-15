package com.kam.qs.util;

import com.bstek.dorado.core.EngineStartupListener;
import com.kam.qs.service.CommonService;

/**
 * 用于Dorado引擎启动时完成树形菜单及系统设置档案初始化。
 */
public class Starter extends EngineStartupListener {

	private CommonService service;

	public void setService(CommonService service) {
		this.service = service;
	}

	@Override
	public void onStartup() throws Exception {
		service.initArchives();
		service.initTreeNode();
		service.initIndustries();
		service.initRegions();
	}
}
