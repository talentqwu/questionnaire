package com.kam.qs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityUtils;
import com.kam.qs.dao.task.BatchDao;
import com.kam.qs.dao.task.PrizeDao;
import com.kam.qs.dao.task.SubTaskDao;
import com.kam.qs.dao.task.TaskDao;
import com.kam.qs.emnu.BatchAction;
import com.kam.qs.emnu.SubTaskAction;
import com.kam.qs.emnu.TaskAction;
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
	
	@DataResolver
	@Transactional
	public void saveTask(List<Task> datas, Map<String, Object> parameter) {
		TaskAction action = TaskAction.valueOf((String)parameter.get("action"));
		switch (action) {
		case ADJUST:
			break;
		case CANCEL:
			taskDao.persistEntities(datas);
			break;
		case DELETE:
			taskDao.deleteAll(datas.get(0));
			break;
		case PUBLISH:
			break;
		case SAVE:
			taskDao.persistEntities(datas);
			break;
		default:
			break;
		}
	}
	
	@DataResolver
	@Transactional
	public void saveBatch(List<Batch> datas, Map<String, Object> parameter) {
		BatchAction action = BatchAction.valueOf((String)parameter.get("action"));
		switch (action) {
		case ADJUST:
			break;
		case DELETE:
			break;
		case SAVE:
			batchDao.persistEntities(datas);
			break;
		default:
			break;
		}
	}
	
	@DataResolver
	@Transactional
	public void saveSubTask(List<SubTask> datas, Map<String, Object> parameter) {
		SubTaskAction action = SubTaskAction.valueOf((String)parameter.get("action"));
		switch (action) {
		case DELETE:
			subTaskDao.persistEntities(datas);
			break;
		case SAVE:
			subTaskDao.persistEntities(datas);
			break;
		default:
			break;
		}
	}
	
	@DataResolver
	@Transactional
	public void savePrize(List<Prize> datas, Map<String, Object> parameter) {
		// TODO
	}
	
	@DataProvider
	public List<Task> getExecutingTask() throws Exception {
		List<Task> results = new ArrayList<Task>();
		
		List<Object[]> datas = taskDao.getExecuting();
		for (Object[] data : datas) {
			Task entity = EntityUtils.toEntity(data[0]);
			EntityUtils.setValue(entity, "statistics", data[1]);
			EntityUtils.setValue(entity, "template", data[2]);
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
