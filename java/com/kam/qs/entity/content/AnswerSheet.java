package com.kam.qs.entity.content;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;

/**
 * 参与者的答卷。
 * @author Talent
 */
@Entity
@Table(name = "con_answer_sheet")
public class AnswerSheet extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "有效性")
	@Column(nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean valid = true;
	
	@PropertyDef(label = "总得分")
	@Column(name = "total_score", nullable = false)
	private double totalScore = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "participator_id", nullable = false)
	private Participator participator;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sheet")
	private List<AnswerInstance> answerInstances;

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public Participator getParticipator() {
		return participator;
	}

	public void setParticipator(Participator participator) {
		this.participator = participator;
	}

	public List<AnswerInstance> getAnswerInstances() {
		return answerInstances;
	}

	public void setAnswerInstances(List<AnswerInstance> answerInstances) {
		this.answerInstances = answerInstances;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
