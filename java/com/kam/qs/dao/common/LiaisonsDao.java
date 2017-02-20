package com.kam.qs.dao.common;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.kam.qs.entity.common.Liaisons;
import com.kam.util.DoradoHibernateDao;

@Repository
public class LiaisonsDao extends DoradoHibernateDao<Liaisons, String> {

	public void getByParameter(Page<Liaisons> page, Map<String, Object> parameter) {
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
			if (null != parameter.get("nickName")) {
				criteria.add(Restrictions.ilike("nickName",
						(String) parameter.get("nickName"), MatchMode.ANYWHERE));
			}
		}
		
		find(page, criteria);
	}

	public List<Liaisons> getAllOrderName() {
		String hql = "FROM Liaisons l ORDER BY l.name";
		return find(hql);
	}
}
