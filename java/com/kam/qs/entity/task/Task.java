package com.kam.qs.entity.task;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;
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
	
	@PropertyDef(label = "批数")
	@Column(name = "batch_number", nullable = false)
	private int batchNumber = 1;
	
	@PropertyDef(label = "及格分数")
	@Column(name = "pass_score", nullable = false)
	private double passScore = 92;
	
	@PropertyDef(label = "总得分")
	@Column(name = "total_score", nullable = false)
	private double totalScore = 0;
	
	@PropertyDef(label = "平均分")
	@Column(name = "average_score", nullable = false)
	private double averageScore = 0;
	
	@PropertyDef(label = "最高分")
	@Column(name = "highest_score", nullable = false)
	private double highestScore = 0;
	
	@PropertyDef(label = "最低分")
	@Column(name = "lowest_score", nullable = false)
	private double lowestScore = 0;
	
	@PropertyDef(label = "启用抽奖")
	@Column(name = "enable_prize_draw", nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean enablePrizeDraw = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private Template template;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<Prize> prizes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<SubTask> subTasks;
	
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

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public double getPassScore() {
		return passScore;
	}

	public void setPassScore(double passScore) {
		this.passScore = passScore;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public double getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(double highestScore) {
		this.highestScore = highestScore;
	}

	public double getLowestScore() {
		return lowestScore;
	}

	public void setLowestScore(double lowestScore) {
		this.lowestScore = lowestScore;
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

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
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
}
