package com.kam.qs.dao.exampool;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.exampool.Question;
import com.kam.util.DoradoHibernateDao;

@Repository
public class QuestionDao extends DoradoHibernateDao<Question, String> {

}
