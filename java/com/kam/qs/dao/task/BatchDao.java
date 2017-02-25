package com.kam.qs.dao.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.Batch;
import com.kam.qs.entity.task.SubTask;
import com.kam.util.DoradoHibernateDao;

@Repository
public class BatchDao extends DoradoHibernateDao<Batch, String> {

	@Resource
	private SubTaskDao subTaskDao;
	
	public List<Batch> getByTask(String taskId) {
		String hql = "FROM Batch b "
				+ "   WHERE b.task.id = ?"
				+ "   ORDER By b.beginDate";
		return find(hql, taskId);
	}

	public void deleteAll(Batch batch) {
		List<SubTask> subTasks = batch.getSubTasks();
		if (subTasks != null)
			for (SubTask subTask : subTasks)
				subTaskDao.deleteAll(subTask);
		
		delete(batch);
	}
	
	public void deleteAll(String batchId) {
		deleteAll(get(batchId));
	}
}
