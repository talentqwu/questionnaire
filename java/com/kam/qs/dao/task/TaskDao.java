package com.kam.qs.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.emnu.TaskStatus;
import com.kam.qs.entity.task.Task;
import com.kam.util.DoradoHibernateDao;

@Repository
public class TaskDao extends DoradoHibernateDao<Task, String> {

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

}
