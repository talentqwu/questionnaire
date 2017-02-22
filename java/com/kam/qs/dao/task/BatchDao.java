package com.kam.qs.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.Batch;
import com.kam.util.DoradoHibernateDao;

@Repository
public class BatchDao extends DoradoHibernateDao<Batch, String> {

	public List<Batch> getByTask(String taskId) {
		String hql = "FROM Batch b "
				+ "   WHERE b.task.id = ?"
				+ "   ORDER By b.beginDate";
		return find(hql, taskId);
	}

}
