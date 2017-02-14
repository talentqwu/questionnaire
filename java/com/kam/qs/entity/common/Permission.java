package com.kam.qs.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.Role;
import com.kam.qs.entity.AbstractEntity;

/**
 * 用户权限。
 * @author Talent
 */
@Entity
@Table(name = "com_permission")
public class Permission extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 使用者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@PropertyDef(label = "角色")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@PropertyDef(label = "顺序")
	@Column(name = "order_")
	private int order = 0;
	
	public Permission() {}
	
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
