package com.kam.qs.entity.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.qs.entity.exampool.Answer;

/**
 * 每位参与者的每题答案与分数。
 * @author Talent
 */
@Entity
@Table(name = "con_answer_instance")
public class AnswerInstance  extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_id", nullable = false)
	private Answer answer;
	
	/**
	 * 答案得分*实例定义的总分占比
	 */
	@PropertyDef(label = "得分")
	@Column(nullable = false)
	private double score = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sheet_id", nullable = false)
	private AnswerSheet sheet;
	
	/**
	 * 其他选项用户输入的信息
	 */
	@PropertyDef(label = "其他")
	@Column(length = 1024)
	private String input;
	
	public AnswerSheet getSheet() {
		return sheet;
	}

	public void setSheet(AnswerSheet sheet) {
		this.sheet = sheet;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
