package com.kam.qs.dao.task;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.Batch;
import com.kam.util.DoradoHibernateDao;

@Repository
public class BatchDao extends DoradoHibernateDao<Batch, String> {

}
