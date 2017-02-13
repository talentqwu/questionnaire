package com.kam.qs.dao.common;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.Permission;
import com.kam.util.DoradoHibernateDao;

@Repository
public class PermissionDao extends DoradoHibernateDao<Permission, String> {

}
