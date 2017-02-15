package com.kam.qs.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;

/**
 * 参与调查的单位所属行业。
 * @author Talent
 */
@Entity
@Table(name = "com_industry")
public class Industry extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false, unique = true)
	private String name;
	
	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false, unique = true)
	private String code;

	public Industry() {}
	
	public Industry(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
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
}
