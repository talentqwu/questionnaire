package com.kam.qs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.kam.qs.dao.exampool.TemplateDao;
import com.kam.qs.entity.exampool.Template;

@Component(value = "qs.exampoolService")
public class ExampoolService {

	@Resource
	private TemplateDao templateDao;
	
	@DataProvider
	public List<Template> getAllTemplate() {
		return templateDao.getAll();
	}
}
