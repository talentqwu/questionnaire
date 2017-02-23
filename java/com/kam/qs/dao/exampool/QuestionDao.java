package com.kam.qs.dao.exampool;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.exampool.Question;
import com.kam.util.DoradoHibernateDao;

@Repository
public class QuestionDao extends DoradoHibernateDao<Question, String> {

	public List<Object[]> getRoot() {
		String hql = "SELECT q,"
				+ "          ("
				+ "              SELECT COUNT(*) FROM Question q1"
				+ "              WHERE q1.parent.id=q.id"
				+ "          ) AS childCount"
				+ "   FROM Question q"
				+ "   WHERE q.parent IS NULL"
				+ "   ORDER BY q.question";
		return find(hql);
	}

	public List<Object[]> getChildrenByParentId(String parentId) {
		String hql = "SELECT q,"
				+ "          q.parent.id AS parentId,"
				+ "          ("
				+ "              SELECT COUNT(*) FROM Question q1"
				+ "              WHERE q1.parent.id=q.id"
				+ "          ) AS childCount"
				+ "   FROM Question q"
				+ "   WHERE q.parent.id = ?"
				+ "   ORDER BY q.question";
		return find(hql, parentId);
	}

}
