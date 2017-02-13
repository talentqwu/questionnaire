package com.kam.qs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * 包含稽核数据的抽象类。
 * @author Talent
 */
@MappedSuperclass
public abstract class AuditEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "建立时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@PropertyDef(label = "建立人")
	@Column(length = 64)
	private String creator;

	@PropertyDef(label = "修改时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@PropertyDef(label = "修改人")
	@Column(length = 64)
	private String modifier;
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}
