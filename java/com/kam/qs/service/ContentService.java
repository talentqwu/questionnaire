package com.kam.qs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;
import com.kam.qs.dao.content.ParticipatorDao;
import com.kam.qs.entity.content.Participator;

@Component(value = "qs.contentService")
public class ContentService {

	@Resource
	private ParticipatorDao participatorDao;
	
	@DataProvider
	public List<Participator> getParticipatorBySubTask(String subTaskId) throws Exception {
		List<Participator> results = new ArrayList<Participator>();
		
		List<Object[]> datas = participatorDao.getBySubTask(subTaskId);
		for (Object[] data : datas) {
			Participator entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "sharerNickName", data[1]);
			results.add(entity);
		}
		
		return results;
	}
}
