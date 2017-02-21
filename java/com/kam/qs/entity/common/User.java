package com.kam.qs.entity.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.util.StringHelper;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.Role;
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.pojo.Permission;

/**
 * 操作者。
 * @author Talent
 */
@Entity
@Table(name = "com_user")
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "代码")
	@Column(nullable = false, length = 64, unique = true)
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
	
	@PropertyDef(label = "手机号")
	@Column(length = 11, nullable = false, unique = true)
	private String telphone;
	
	@PropertyDef(label = "邮件地址")
	@Column(length = 256, nullable = false, unique = true)
	private String email;
	
	@PropertyDef(label = "验证密钥")
	@Column(name = "secret_key", length = 12, nullable = false)
	private String secretKey = String.valueOf((int)((Math.random()*9+1)*100000));
	
	@PropertyDef(label = "验证密钥生成时间")
	@Column(name = "key_generate_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date keyGenerateTime;
	
	/**
	 * 可以使用该功能的角色清单，使用逗号分隔
	 */
	@Column(name = "role_str", length = 1024)
	private String roleStr;
	
	@Transient
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
		permissions = new ArrayList<Permission>();
		if (StringHelper.isNotEmpty(roleStr)) {
			String[] items = roleStr.split(",");
			for (String item : items)
				permissions.add(new Permission(Role.valueOf(item)));
		}
		
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

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Date getKeyGenerateTime() {
		return keyGenerateTime;
	}

	public void setKeyGenerateTime(Date keyGenerateTime) {
		this.keyGenerateTime = keyGenerateTime;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
}
