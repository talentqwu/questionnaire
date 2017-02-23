package com.kam.qs.dao.exampool;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.exampool.Instance;
import com.kam.util.DoradoHibernateDao;

@Repository
public class InstanceDao extends DoradoHibernateDao<Instance, String> {

	public List<Object[]> getByTemplate(String templateId) {
		String hql = "SELECT i,"
				+ "          i.question"
				+ "   FROM Instance i"
				+ "   WHERE i.template.id = ?"
				+ "   ORDER BY i.order";
		return find(hql, templateId);
	}

}
