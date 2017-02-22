package com.kam.qs.dao.content;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.content.Participator;
import com.kam.util.DoradoHibernateDao;

@Repository
public class ParticipatorDao extends DoradoHibernateDao<Participator, String> {

	public List<Object[]> getBySubTask(String subTaskId) {
		String hql = "SELECT p,"
				+ "          parent.nickName"
				+ "   FROM Participator p "
				+ "   LEFT JOIN p.parent AS parent"
				+ "   WHERE p.subTask.id = ?"
				+ "   ORDER BY p.nickName";
		return find(hql, subTaskId);
	}

}
