package com.kam.qs.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;

/**
 * 奖品。
 * @author Talent
 */
@Entity
@Table(name = "com_award")
public class Award extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false)
	private String code;

	@PropertyDef(label = "描述")
	@Column(length = 2048)
	private String description;
	
	@PropertyDef(label = "图片地址")
	@Column(length = 256)
	private String imageUrl;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
