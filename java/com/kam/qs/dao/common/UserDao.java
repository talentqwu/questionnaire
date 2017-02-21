package com.kam.qs.dao.common;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.User;
import com.kam.util.DoradoHibernateDao;

@Repository
public class UserDao extends DoradoHibernateDao<User, String> {

	public User getByCode(String code) {
		String hql = "FROM User u "
				+ "   WHERE u.code=?";
		return findUnique(hql, code);
	}

	public List<User> getByParameter(Map<String, Object> parameter) {
		Criteria criteria = createCriteria();
		if (parameter != null) {
			if (null != parameter.get("name")) {
				criteria.add(Restrictions.ilike("name",
						(String) parameter.get("name"), MatchMode.ANYWHERE));
			}
			if (null != parameter.get("telphone")) {
				criteria.add(Restrictions.ilike("telphone",
						(String) parameter.get("telphone"), MatchMode.ANYWHERE));
			}
			if (null != parameter.get("code")) {
				criteria.add(Restrictions.ilike("code",
						(String) parameter.get("code"), MatchMode.ANYWHERE));
			}
		}
		
		return find(criteria);
	}
}
