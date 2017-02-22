package com.kam.qs.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.Prize;
import com.kam.util.DoradoHibernateDao;

@Repository
public class PrizeDao extends DoradoHibernateDao<Prize, String> {

	public List<Object[]> getByTask(String taskId) {
		String hql = "SELECT p,"
				+ "          p.award"
				+ "   FROM Prize p"
				+ "   WHERE p.task.id = ?"
				+ "   ORDER BY p.name";
		return find(hql, taskId);
	}

}
