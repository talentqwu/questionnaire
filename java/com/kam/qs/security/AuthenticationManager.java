package com.kam.qs.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bstek.dorado.core.Context;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.common.UnitDao;
import com.kam.qs.dao.common.UserDao;
import com.kam.qs.entity.common.Permission;
import com.kam.qs.entity.common.Permission.Role;
import com.kam.qs.entity.common.User;
import com.kam.qs.util.Constants;
import com.kam.util.CommonUtils;

public class AuthenticationManager implements com.bstek.dorado.console.authentication.AuthenticationManager {

	private UserDao userDao;
	private UnitDao unitDao;
	
	private void init() {
		try {
			if (userDao == null)
				userDao = (UserDao)Context.getCurrent().getServiceBean("&userDao");
			if (unitDao == null)
				unitDao = (UnitDao)Context.getCurrent().getServiceBean("&unitDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean authenticate(String name, String password) {
		boolean flag = false;

		if (name != null && password != null) {
			init();
			User user = null;
			// 系统默认的管理员
			if (name.equals("administrator")) {
				if (CommonUtils.encoderByMd5(password).equals(Constants.ADMIN_PASSWORD)) {
					user = createAdminUser();
					flag = true;
				}
			} else {
				user = userDao.getByCode(name);
				if (user != null && user.getPassword().equals(CommonUtils.encoderByMd5(password)))
					flag = true;
			} 
			
			if (flag) {
				DoradoContext ctx = DoradoContext.getCurrent();
				ctx.setAttribute(DoradoContext.SESSION, Constants.CURRENT_USER, convertUser(user));
			}
		}

		return flag;
	}

	public boolean isAuthenticated(HttpServletRequest request) {
		Boolean loginStatus = false;

		try {
			loginStatus = request.getSession().getAttribute(
					Constants.CURRENT_USER) == null ? false : true;
		} catch (Exception e) {}

		return loginStatus;
	}

	private User createAdminUser() {
		User user = new User("administrator", "Administrator");
		Permission permission = new Permission(null, Role.ADMIN);
		List<Permission> permissions = new ArrayList<Permission>();
		permissions.add(permission);
		user.setPermissions(permissions);
		
		return user;
	}
	
	private User convertUser(User source) {
		User target = new User();
		
		target.setCode(source.getCode());
		target.setId(source.getId());
		target.setName(source.getName());
		if (source.getPermissions() != null && source.getPermissions().size() > 0) {
			List<Permission> permissions = new ArrayList<Permission>();
			for (Permission permission : source.getPermissions())
				permissions.add(convertPermission(permission));
			target.setPermissions(permissions);
		}
		
		return target;
	}
	
	private Permission convertPermission(Permission source) {
		Permission target = null;
		
		if (source != null) {
			target = new Permission();
			target.setId(source.getId());
			target.setOrder(source.getOrder());
			target.setRole(source.getRole());
		}
		
		return target;
	}
}
