package com.kam.qs.pojo;

import com.kam.qs.emnu.Role;
import com.kam.qs.entity.common.User;

/**
 * 用户权限。
 * @author Talent
 */
public class Permission {

	/**
	 * 使用者
	 */
	private User user;
	
	private Role role;
	
	private int order = 0;
	
	public Permission() {}
	
	public Permission(Role role) {
		this.role = role;
	}
	
	public Permission(User user, Role role) {
		this.role = role;
		this.user = user;
	}
	
	public Permission(User user, Role role, int order) {
		this.role = role;
		this.user = user;
		this.order = order;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
