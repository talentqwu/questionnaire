package com.kam.qs.entity.exampool;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.util.CommonUtils;

/**
 * 问卷题目答案。
 * @author Talent
 */
@Entity
@Table(name = "exm_answer")
public class Answer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false)
	private String code = "A_" + CommonUtils.generateShortUuid();
	
	@PropertyDef(label = "顺序")
	@Column(name = "order_")
	private int order;
	
	@PropertyDef(label = "权重")
	@Column(nullable = false)
	private double weight = 1;
	
	@PropertyDef(label = "内容")
	@Column(length = 2048, nullable = false)
	private String content;
	
	/**
	 * 需要用户填写的补充说明
	 */
	@PropertyDef(label = "其他")
	@Column(nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean additional = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	public Answer() {}
	
	public Answer(int order, String content, double weight) {
		this.order = order;
		this.weight = weight;
		this.content = content;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAdditional() {
		return additional;
	}

	public void setAdditional(boolean additional) {
		this.additional = additional;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
