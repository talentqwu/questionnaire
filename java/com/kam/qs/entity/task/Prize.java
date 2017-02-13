package com.kam.qs.entity.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.entity.common.Award;

/**
 * 调查奖项设置。
 * @author Talent
 */
@Entity
@Table(name = "tsk_prize")
public class Prize extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "代码")
	@Column(length = 256, nullable = false)
	private String code;
	
	@PropertyDef(label = "数量")
	@Column(nullable = false)
	private int number = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "award_id")
	private Award award;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}
}
