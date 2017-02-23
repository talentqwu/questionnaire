package com.kam.qs.entity.task;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.TaskStatus;
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.entity.content.Statistics;
import com.kam.qs.entity.exampool.Template;

/**
 * 调查任务。
 * @author Talent
 */
@Entity
@Table(name = "tsk_task")
public class Task extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "状态")
	@Column(nullable = false, length = 128)
	@Enumerated(EnumType.STRING)
	public TaskStatus status = TaskStatus.EDITING;
	
	@PropertyDef(label = "开始日期")
	@Column(name = "begin_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date beginDate = new Date();
	
	@PropertyDef(label = "结束日期")
	@Column(name = "end_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate = new Date();
	
	@PropertyDef(label = "启用一包多")
	@Column(name = "enable_share", nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean enableShare = true;
	
	@PropertyDef(label = "一包多数量")
	@Column(name = "share_number", nullable = false)
	private int shareNumber = 5;
	
	@PropertyDef(label = "及格分数")
	@Column(name = "pass_score", nullable = false)
	private double passScore = 92;
	
	@PropertyDef(label = "启用抽奖")
	@Column(name = "enable_prize_draw", nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean enablePrizeDraw = false;
	
	/**
	 * 任务的统计数据
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "statistics_id")
	private Statistics statistics;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private Template template;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<Prize> prizes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<Batch> batchs;

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

	public int getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(int shareNumber) {
		this.shareNumber = shareNumber;
	}

	public double getPassScore() {
		return passScore;
	}

	public void setPassScore(double passScore) {
		this.passScore = passScore;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}

	public List<Batch> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<Batch> batchs) {
		this.batchs = batchs;
	}

	public boolean isEnableShare() {
		return enableShare;
	}

	public void setEnableShare(boolean enableShare) {
		this.enableShare = enableShare;
	}

	public boolean isEnablePrizeDraw() {
		return enablePrizeDraw;
	}

	public void setEnablePrizeDraw(boolean enablePrizeDraw) {
		this.enablePrizeDraw = enablePrizeDraw;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
