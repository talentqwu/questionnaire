package com.kam.qs.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.Unit;
import com.kam.util.DoradoHibernateDao;

@Repository
public class UnitDao extends DoradoHibernateDao<Unit, String> {

	public List<Object[]> getRoot() {
		String hql = "SELECT u,"
				+ "          u.region AS region,"
				+ "          u.industry AS industry"
				+ "   FROM Unit u "
				+ "   WHERE u.parent IS NULL ORDER BY u.name";
		
		return find(hql);
	}
}
