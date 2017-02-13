package com.kam.qs.dao.task;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.Task;
import com.kam.util.DoradoHibernateDao;

@Repository
public class TaskDao extends DoradoHibernateDao<Task, String> {

}
