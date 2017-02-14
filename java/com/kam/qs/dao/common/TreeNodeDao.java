package com.kam.qs.dao.common;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kam.qs.emnu.TreeNodeCategory;
import com.kam.qs.entity.common.TreeNode;
import com.kam.util.DoradoHibernateDao;

@Repository
public class TreeNodeDao extends DoradoHibernateDao<TreeNode, String> {

	public List<TreeNode> getByUrl(String url) {
		return this.find("FROM TreeNode t WHERE t.url=?", url);
	}
	
	public List<Object[]> getRootByCategory(TreeNodeCategory category) {
		String hql = "SELECT t, "
				+ "          ("
				+ "               SELECT COUNT(*) FROM TreeNode tn "
				+ "               WHERE tn.parent.id=t.id"
				+ "          ) AS childCount "
				+ "   FROM TreeNode t "
				+ "   WHERE t.parent.id is null "
				+ "         AND t.category = ? "
				+ "   ORDER BY t.order";
		return find(hql, category);
	}
	
	public List<Object[]> getRoot() {
		String hql = "SELECT t, "
				+ "          ("
				+ "               SELECT COUNT(*) FROM TreeNode tn "
				+ "               WHERE tn.parent.id=t.id"
				+ "          ) AS childCount "
				+ "   FROM TreeNode t "
				+ "   WHERE t.parent.id is null "
				+ "   ORDER BY t.order";
		return find(hql);
	}
	
	public List<Object[]> getChildrenByParentId(String parentId) {
		String hql = "SELECT t, "
				+ "          ("
				+ "               SELECT COUNT(*) FROM TreeNode tn "
				+ "               WHERE tn.parent.id=t.id"
				+ "          ) AS childCount "
				+ "   FROM TreeNode t "
				+ "   WHERE t.parent.id='" + parentId + "' "
				+ "   ORDER BY t.order";
		return find(hql);
	}
	
	public List<TreeNode> getByCategory(TreeNodeCategory category) {
		String hql = "FROM TreeNode t WHERE t.category=?";
		return find(hql, category);
	}
	
	public long getCountByLabel(TreeNodeCategory category, String label) {
		return (Long)createCriteria(Restrictions.and(Restrictions.eq("label", label), 
				Restrictions.eq("category", category)))
				.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public List<TreeNode> getByLabel(TreeNodeCategory category, String label) {
		return find(createCriteria(Restrictions.and(
				Restrictions.eq("label", label), Restrictions.eq("category", category))).
				addOrder(Order.desc("id")));
	}
}
