package com.kam.qs.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.emnu.ArchivesType;
import com.kam.qs.entity.common.Archives;
import com.kam.util.DoradoHibernateDao;

@Repository
public class ArchivesDao extends DoradoHibernateDao<Archives, String> {

	public List<Archives> getByType(ArchivesType type) {
		String hql = "FROM Archives a WHERE a.type=? ORDER BY a.order";
		return find(hql, type);
	}
}
