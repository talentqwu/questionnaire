package com.kam.qs.dao.common;

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
}
