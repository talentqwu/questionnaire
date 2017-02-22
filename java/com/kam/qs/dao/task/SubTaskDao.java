package com.kam.qs.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.SubTask;
import com.kam.util.DoradoHibernateDao;

@Repository
public class SubTaskDao extends DoradoHibernateDao<SubTask, String> {

	public List<Object[]> getByBatch(String batchId) {
		String hql = "SELECT s,"
				+ "          s.unit,"
				+ "          s.statistics"
				+ "   FROM SubTask s"
				+ "   WHERE s.batch.id = ?"
				+ "   ORDER BY s.unit.name";
		return find(hql, batchId);
	}

}
