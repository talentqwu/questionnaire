package com.kam.qs.dao.exampool;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.exampool.Answer;
import com.kam.util.DoradoHibernateDao;

@Repository
public class AnswerDao extends DoradoHibernateDao<Answer, String> {

	public List<Answer> getByQuestion(String questionId) {
		String hql = "FROM Answer a "
				+ "   WHERE a.question.id = ? "
				+ "   ORDER BY a.order";
		return find(hql, questionId);
	}

}
