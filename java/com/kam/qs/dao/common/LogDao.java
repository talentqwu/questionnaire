package com.kam.qs.dao.common;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.Log;
import com.kam.util.DoradoHibernateDao;

@Repository
public class LogDao extends DoradoHibernateDao<Log, String> {

	public void saveEntities(List<Log> logs) {
		for (Log log : logs) 
			super.save(log);
	}
	
	public void saveEntities(List<Log> logs, String entityId) {
		for (Log log : logs) {
			log.setEntityId(entityId);
			super.save(log);
		}
	}
	
	public int deleteEntities(String entityId) {
		String hql = "DELETE FROM Log l WHERE l.entityId=?";
		return super.createQuery(hql, entityId).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Log> getByEntityId(String entityId) {
		if (entityId.indexOf(',') >= 0) {
			String hql = "FROM Log l "
					+ "   WHERE l.entityId IN (:entityIds) "
					+ "   ORDER BY l.dateTime";
			String[] entityIds = entityId.split(",");
			Query query = getSession().createQuery(hql);
			query.setParameterList("entityIds", entityIds);
			return query.list();
		} else {
			String hql = "FROM Log l "
					+ "   WHERE l.entityId=? "
					+ "   ORDER BY l.dateTime";
			return super.find(hql, entityId);
		}
	}
	
	public long getCount(String entityId) {
		String hql = "SELECT COUNT(*) FROM Log l where l.entityId=?";
		return (long)createQuery(hql, entityId).list().get(0);
	}
}
