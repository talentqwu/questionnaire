package com.kam.qs.entity.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;

/**
 * 操作者。
 * @author Talent
 */
@Entity
@Table(name = "com_user")
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "代码")
	@Column(nullable = false, length = 64)
	private String code;
	
	@PropertyDef(label = "姓名")
	@Column(nullable = false, length = 128)
	private String name;
	
	/**
	 * MD5加密生成
	 */
	@PropertyDef(label = "密码")
	@Column(nullable = false, length = 128)
	private String password;
	
	/**
	 * 权限清单
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Permission> permissions;
	
	public User() {}
	
	public User(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public User(String code, String name, String password) {
		this.code = code;
		this.name = name;
		this.password = password;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
