package com.kam.qs.entity.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.util.StringHelper;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.qs.entity.common.Permission.Role;

/**
 * Dorado服务信息。
 * @author Talent
 */
@Entity
@Table(name = "com_service")
public class DoradoService extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "服务名称")
	@Column(name = "service_name", length = 128, nullable = false)
	private String serviceName;
	
	@PropertyDef(label = "方法名称")
	@Column(name = "method_name", length = 128, nullable = false)
	private String methodName;
	
	@PropertyDef(label = "描述")
	@Column(length = 512)
	private String description;
	
	@PropertyDef(label = "类型")
	@Column(length = 64, nullable = false)
	private String type;
	
	@PropertyDef(label = "参数清单")
	@Column(length = 512)
	private String parameters;
	
	@PropertyDef(label = "相关视图")
	@Column(name = "relation_view", length = 128)
	private String relationView;
	
	@PropertyDef(label = "返回类型")
	@Column(name = "result_data_type", length = 128)
	private String resultDataType;
	
	@PropertyDef(label = "拦截服务名称")
	@Column(length = 128)
	private String interceptorServiceName;
	
	@PropertyDef(label = "可以使用该功能的角色清单，使用逗号分隔")
	@Column(name = "role_str", length = 1024)
	private String roleStr;
	
	@Transient
	private List<Role> roles;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getResultDataType() {
		return resultDataType;
	}

	public void setResultDataType(String resultDataType) {
		this.resultDataType = resultDataType;
	}

	public String getInterceptorServiceName() {
		return interceptorServiceName;
	}

	public void setInterceptorServiceName(String interceptorServiceName) {
		this.interceptorServiceName = interceptorServiceName;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	public List<Role> getRoles() {
		if (StringHelper.isEmpty(roleStr))
			return null;
		else {
			roles = new ArrayList<Role>();
			String[] items = roleStr.split(",");
			for (String item : items)
				roles.add(Role.valueOf(item));
			return roles;
		}
	}

	public void setRoles(List<Role> roles) {
		if (roles != null && roles.size() > 0) {
			String buffer = "";
			for (Role role : roles)
				buffer += role + ",";
			roleStr = buffer.substring(0, buffer.length() - 1);
		} else
			roleStr = null;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRelationView() {
		return relationView;
	}

	public void setRelationView(String relationView) {
		this.relationView = relationView;
	}
}
