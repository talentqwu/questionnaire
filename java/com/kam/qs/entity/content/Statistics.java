package com.kam.qs.entity.content;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.qs.entity.task.SubTask;

/**
 * 每个单位的统计数据。
 * @author Talent
 */
@Entity
@Table(name = "con_statistics")
public class Statistics extends AbstractEntity {

	private static final long serialVersionUID = 1L;

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
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "statistics")
	private SubTask subTask;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statistics")
	private List<StatisticsDetail> details;

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

	public SubTask getSubTask() {
		return subTask;
	}

	public void setSubTask(SubTask subTask) {
		this.subTask = subTask;
	}

	public List<StatisticsDetail> getDetails() {
		return details;
	}

	public void setDetails(List<StatisticsDetail> details) {
		this.details = details;
	}
}
