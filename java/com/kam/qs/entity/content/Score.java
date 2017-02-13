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
 * 每位参与者的每题答案与分数。
 * @author Talent
 */
@Entity
@Table(name = "con_score")
public class Score  extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 由答案代码组成，多选题则使用||分隔项次，对于有附加输入项的答案，则使用答案代码+!!+内容组成
	 * 单选题：答案代码
	 * 多选题：答案代码A||答案代码B||...
	 * 多选题带附加输入项：答案代码A||答案代码B||答案代码C!!附加内容||...
	 */
	@PropertyDef(label = "答案")
	@Column(length = 1024)
	private String answer;
	
	@PropertyDef(label = "得分")
	@Column(nullable = false)
	private double score = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sheet_id", nullable = false)
	private Sheet sheet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Instance question;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Instance getQuestion() {
		return question;
	}

	public void setQuestion(Instance question) {
		this.question = question;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
