package com.kam.qs.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.DoradoService;
import com.kam.util.DoradoHibernateDao;

@Repository
public class DoradoServiceDao extends DoradoHibernateDao<DoradoService, String> {

	@Override
	public List<DoradoService> getAll() {
		String hql = "FROM DoradoService d "
				+ "   ORDER by d.serviceName, d.methodName";
		return this.find(hql);
	}
	
	public DoradoService getByServiceAndMethodName(String serviceName, String methodName) {
		String hql = "FROM DoradoService d "
				+ "   WHERE d.serviceName=? "
				+ "         AND methodName=?";
		return findUnique(hql, serviceName, methodName);
	}
	
	public DoradoService getByDoradoServiceName(String doradoServiceName) {
		String[] items = doradoServiceName.split("#");
		return getByServiceAndMethodName(items[0], items[1]);
	}
}
