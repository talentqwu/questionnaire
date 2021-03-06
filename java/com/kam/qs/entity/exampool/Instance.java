package com.kam.qs.entity.exampool;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;

/**
 * 问题实体。
 * @author Talent
 */
@Entity
@Table(name = "exm_instance")
public class Instance extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "序号")
	@Column(name = "order_", nullable = false)
	private int order = 0;
	
	@PropertyDef(label = "总分占比")
	@Column(nullable = false)
	private double proportion = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private Template template;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Instance parent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Instance> children;
	
	public Instance() {}
	
	public Instance( double proportion, Question question, Template template) {
		this.proportion = proportion;
		this.question = question;
		this.template = template;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public double getProportion() {
		return proportion;
	}

	public void setProportion(double proportion) {
		this.proportion = proportion;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Instance getParent() {
		return parent;
	}

	public void setParent(Instance parent) {
		this.parent = parent;
	}

	public List<Instance> getChildren() {
		return children;
	}

	public void setChildren(List<Instance> children) {
		this.children = children;
	}
}
