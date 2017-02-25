package com.kam.qs.entity.exampool;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.emnu.QuestionShowType;
import com.kam.qs.emnu.QuestionType;
import com.kam.qs.entity.AuditEntity;
import com.kam.util.CommonUtils;

/**
 * 问卷题目。
 * 每道题目得分的计算公式为：答案的权重之和*题目实例分值。
 * @author Talent
 */
@Entity
@Table(name = "exm_question")
public class Question  extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false)
	private String code = "Q_" + CommonUtils.generateShortUuid();
	
	@PropertyDef(label = "问题")
	@Column(length = 2048, nullable = false)
	private String question;
	
	@PropertyDef(label = "问题类型")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@PropertyDef(label = "显示类型")
	@Column(name = "show_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestionShowType showType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Question parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Question> children;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	private List<Answer> answers;
	
	public Question() {}
	
	public Question(String question, QuestionType type, QuestionShowType showType) {
		this.question = question;
		this.type = type;
		this.showType = showType;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Question getParent() {
		return parent;
	}

	public void setParent(Question parent) {
		this.parent = parent;
	}

	public List<Question> getChildren() {
		return children;
	}

	public void setChildren(List<Question> children) {
		this.children = children;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionShowType getShowType() {
		return showType;
	}

	public void setShowType(QuestionShowType showType) {
		this.showType = showType;
	}
}
