package com.kam.qs.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.kam.qs.entity.common.Industry;
import com.kam.qs.entity.common.Region;
import com.kam.qs.entity.common.Unit;
import com.kam.util.DoradoHibernateDao;

@Repository
public class UnitDao extends DoradoHibernateDao<Unit, String> {

	public List<Object[]> getRoot() {
		String hql = "SELECT u,"
				+ "          u.region AS region,"
				+ "          u.industry AS industry,"
				+ "          u.parent.id AS parentId,"
				+ "          ("
				+ "              SELECT COUNT(*) FROM Unit u1"
				+ "              WHERE u1.parent.id=u.id"
				+ "          ) AS childCount"
				+ "   FROM Unit u "
				+ "   WHERE u.parent IS NULL "
				+ "   ORDER BY u.name";
		
		return find(hql);
	}
	
	public List<Object[]> getChildrenByParentId(String parentId) {
		String hql = "SELECT u,"
				+ "          u.region AS region,"
				+ "          u.industry AS industry,"
				+ "          u.parent.id AS parentId,"
				+ "          ("
				+ "              SELECT COUNT(*) FROM Unit u1"
				+ "              WHERE u1.parent.id=u.id"
				+ "          ) AS childCount"
				+ "   FROM Unit u "
				+ "   WHERE u.parent.id=? "
				+ "   ORDER BY u.name";
		return find(hql, parentId);
	}

	public List<Object[]> getByParamenter(Map<String, Object> parameter) {
		String hql = "SELECT u,"
				+ "          u.region AS region,"
				+ "          u.industry AS industry,"
				+ "          u.parent.id AS parentId,"
				+ "          ("
				+ "              SELECT COUNT(*) FROM Unit u1"
				+ "              WHERE u1.parent.id=u.id"
				+ "          ) AS childCount"
				+ "   FROM Unit u "
				+ "   WHERE u.parent IS NULL ";
		boolean condition = false;
		String subHql = "";
		String name = (String)parameter.get("name");
		if (!StringUtils.isEmpty(name)) {
			subHql += StringUtils.isEmpty(subHql) ? " u.name LIKE :name" : " OR u.name LIKE :name";
			parameter.put("name", "%" + name + "%");
			condition = true;
		}
		if (parameter.get("industry") != null && ((Industry)parameter.get("industry")).getId() != null) {
			subHql += StringUtils.isEmpty(subHql) ? " u.industry = :industry" : " OR u.industry = :industry";
			condition = true;
		}
		if (parameter.get("region") != null && ((Region)parameter.get("region")).getId() != null) {
			subHql += StringUtils.isEmpty(subHql) ? " u.region = :region" : " OR u.region = :region";
			condition = true;
		}
		
		if (condition) {
			hql += " AND (" + subHql + ") ORDER BY u.name";
			return find(hql, parameter);
		} else
			return getRoot();
	}

	public List<Object[]> getChooseForBatch(String taskId) {
		String hql = "SELECT u,"
				+ "          u.region AS region,"
				+ "          u.industry AS industry"
		        + "   FROM Unit u"
				+ "   WHERE u.id NOT IN ("
				+ "                          SELECT st.unit.id FROM SubTask st"
				+ "                          WHERE st.batch.id IN ("
				+ "                                                   SELECT b.id FROM Batch b"
				+ "                                                   WHERE b.task.id = ?"
				+ "                                               )"
				+ "                     )"
				+ "   ORDER BY u.name";
		return find(hql, taskId);
	}
}
