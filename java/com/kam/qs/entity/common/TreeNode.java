package com.kam.qs.entity.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.util.StringHelper;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.Role;
import com.kam.qs.emnu.TreeNodeCategory;
import com.kam.qs.entity.AbstractEntity;

/**
 * 树形菜单数据。
 * @author Talent
 */
@Entity
@Table(name = "com_tree_node")
public class TreeNode extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "标签")
	@Column(nullable = false, length = 256)
	private String label;
	
	@PropertyDef(label = "顺序")
	@Column(name = "order_")
	private int order = 0;
	
	@PropertyDef(label = "分类")
	@Column(nullable = false, length = 128)
	@Enumerated(EnumType.STRING)
	private TreeNodeCategory category;
	
	@PropertyDef(label = "图标")
	private String icon;
	
	@PropertyDef(label = "URL")
	@Column(length = 256)
	private String url;
	
	/**
	 * 父亲节点
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private TreeNode parent;
	
	/**
	 * 孩子节点
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value = "order")
	private List<TreeNode> children;
	
	/**
	 * 可以使用该功能的角色清单，使用逗号分隔
	 */
	@Column(name = "role_str", length = 1024)
	private String roleStr;
	
	@Transient
	@PropertyDef(label = "角色清单")
	private List<Role> roles;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public TreeNodeCategory getCategory() {
		return category;
	}

	public void setCategory(TreeNodeCategory category) {
		this.category = category;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	public List<Role> getRoles() {
		roles = new ArrayList<Role>();
		if (StringHelper.isNotEmpty(roleStr)) {
			String[] items = roleStr.split(",");
			for (String item : items)
				roles.add(Role.valueOf(item));
		}
		return roles;
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
}
