package com.kam.qs.util;

import com.bstek.dorado.core.EngineStartupListener;
import com.kam.qs.service.CommonService;
import com.kam.qs.service.ExampoolService;

/**
 * 用于Dorado引擎启动时完成树形菜单及系统设置档案初始化。
 */
public class Starter extends EngineStartupListener {

	private CommonService commonService;
	private ExampoolService exampoolService;
	
	public void setCommonService(CommonService service) {
		this.commonService = service;
	}

	public void setExampoolService(ExampoolService exampoolService) {
		this.exampoolService = exampoolService;
	}

	@Override
	public void onStartup() throws Exception {
		commonService.initArchives();
		commonService.initTreeNode();
		commonService.initIndustries();
		commonService.initRegions();
		
		exampoolService.initQuestion();
		exampoolService.initTemplate();
	}
}
