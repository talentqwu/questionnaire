package com.kam.qs.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.ArchivesType;
import com.kam.qs.entity.AbstractEntity;

/**
 * 系统设置档案。
 * @author Talent
 */
@Entity
@Table(name = "com_archives")
public class Archives extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "类型")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ArchivesType type;
	
	@PropertyDef(label = "代码")
	@Column(nullable = false, length = 32)
	private String code;
	
	@PropertyDef(label = "内容")
	@Column(nullable = false, length = 1024)
	private String content;
	
	@PropertyDef(label = "档案描述")
	@Column(length = 1024)
	private String description;

	@PropertyDef(label = "序号")
	@Column(name = "order_", nullable = false)
	private int order = 0;

	public Archives() {}
	
	public Archives(String code, String content, ArchivesType type, int order) {
		this.code = code;
		this.content = content;
		this.type = type;
		this.order = order;
	}
	
	public ArchivesType getType() {
		return type;
	}

	public void setType(ArchivesType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
