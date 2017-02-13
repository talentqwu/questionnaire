package com.kam.qs.entity.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.qs.entity.exampool.Instance;

/**
 * 每个单位每一题的统计。
 * @author Talent
 */
@Entity
@Table(name = "con_statistics_detail")
public class StatisticsDetail extends AbstractEntity {

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statistics_id", nullable = false)
	private Statistics statistics;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Instance question;

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

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Instance getQuestion() {
		return question;
	}

	public void setQuestion(Instance question) {
		this.question = question;
	}
}
