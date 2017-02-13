package com.kam.qs.entity.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;


/**
 * 调查任务批次（注开始与结束日期只能在任务的范围内）。
 * @author Talent
 */
@Entity
@Table(name = "tsk_batch")
public class Batch extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "开始日期")
	@Column(name = "begin_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date beginDate = new Date();
	
	@PropertyDef(label = "结束日期")
	@Column(name = "end_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
