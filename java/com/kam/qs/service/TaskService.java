package com.kam.qs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;
import com.kam.qs.dao.task.BatchDao;
import com.kam.qs.dao.task.PrizeDao;
import com.kam.qs.dao.task.SubTaskDao;
import com.kam.qs.dao.task.TaskDao;
import com.kam.qs.entity.task.Batch;
import com.kam.qs.entity.task.Prize;
import com.kam.qs.entity.task.SubTask;
import com.kam.qs.entity.task.Task;

@Component(value = "qs.taskService")
public class TaskService {

	@Resource
	private SubTaskDao subTaskDao;
	
	@Resource
	private BatchDao batchDao;
	
	@Resource
	private PrizeDao prizeDao;
	
	@Resource
	private TaskDao taskDao;
	
	@DataProvider
	public List<Task> getExecutingTask() throws Exception {
		List<Task> results = new ArrayList<Task>();
		
		List<Object[]> datas = taskDao.getExecuting();
		for (Object[] data : datas) {
			Task entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "statistics", data[1]);
			EntityUtils.setValue(entity, "template", data[1]);
			results.add(entity);
		}
		
		return results;
	}
	
	@DataProvider
	public List<Prize> getPrizeByTask(String taskId) throws Exception {
		List<Prize> results = new ArrayList<Prize>();
		
		List<Object[]> datas = prizeDao.getByTask(taskId);
		for (Object[] data : datas) {
			Prize entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "award", data[1]);
			results.add(entity);
		}
		
		return results;
	}
	
	@DataProvider
	public List<Batch> getBatchByTask(String taskId) {
		return batchDao.getByTask(taskId);
	}
	
	@DataProvider
	public List<SubTask> getSubTaskByBatch(String batchId) throws Exception {
		List<SubTask> results = new ArrayList<SubTask>();
		
		List<Object[]> datas = subTaskDao.getByBatch(batchId);
		for (Object[] data : datas) {
			SubTask entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "unit", data[1]);
			EntityUtils.setValue(entity, "statistics", data[2]);
			results.add(entity);
		}
		
		return results;
	}
	
}
