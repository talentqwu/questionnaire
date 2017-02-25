package com.kam.qs.dao.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.kam.qs.emnu.TaskStatus;
import com.kam.qs.entity.task.Batch;
import com.kam.qs.entity.task.Task;
import com.kam.util.DoradoHibernateDao;

@Repository
public class TaskDao extends DoradoHibernateDao<Task, String> {

	@Resource
	private BatchDao batchDao;
	
	public List<Object[]> getExecuting() {
		String hql = "SELECT t,"
				+ "          t.statistics,"
				+ "          t.template"
				+ "   FROM Task t"
				+ "   WHERE t.status = ? "
				+ "         OR t.status = ?"
				+ "   ORDER BY t.beginDate";
		return find(hql, TaskStatus.EDITING, TaskStatus.PUBLISHED);
	}

	public void deleteAll(Task task) {
		task = get(task.getId());
		String hql = null, taskId = task.getId();
		// 删除奖项设置
		hql = "DELETE FROM Prize p WHERE p.task.id = ?";
		createQuery(hql, taskId).executeUpdate();
		
		List<Batch> batchs = task.getBatchs();
		if (batchs != null)
			for (Batch batch : batchs)
				batchDao.deleteAll(batch);
		
		delete(task);
		
		// 删除任务统计明细
		hql = "DELETE FROM StatisticsDetail sd WHERE sd.statistics.id = (SELECT st.statistics.id FROM Task st WHERE st.id = ?)";
		createQuery(hql, taskId).executeUpdate();
		// 删除任务统计
		hql = "DELETE FROM Statistics s WHERE s.id = (SELECT st.statistics.id FROM Task st WHERE st.id = ?)";
		createQuery(hql, taskId).executeUpdate();
	}
	
	public void deleteAll(String taskId) {
		deleteAll(get(taskId));
	}
}
