package com.kam.qs.entity.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;

/**
 * 参与调查的单位。
 * 
 * @author Talent
 */
@Entity
@Table(name = "com_unit")
public class Unit extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false)
	private String code;
	
	@PropertyDef(label = "人数")
	@Column(name = "people_number", nullable = false)
	private int peopleNumber = 0;
	
	@PropertyDef(label = "允许一包多")
	@Column(name = "enable_share", nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean enableShare = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "industry_id", nullable = false)
	private Industry industry;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "com_unit_liaisions", 
		joinColumns = { @JoinColumn(referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
	private List<Liaisons> liaisonses;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Unit parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Unit> children;

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

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public boolean isEnableShare() {
		return enableShare;
	}

	public void setEnableShare(boolean enableShare) {
		this.enableShare = enableShare;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Liaisons> getLiaisonses() {
		return liaisonses;
	}

	public void setLiaisonses(List<Liaisons> liaisonses) {
		this.liaisonses = liaisonses;
	}

	public Unit getParent() {
		return parent;
	}

	public void setParent(Unit parent) {
		this.parent = parent;
	}

	public List<Unit> getChildren() {
		return children;
	}

	public void setChildren(List<Unit> children) {
		this.children = children;
	}
}
