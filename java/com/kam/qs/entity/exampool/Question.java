package com.kam.qs.entity.exampool;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.kam.qs.entity.AuditEntity;

/**
 * 问卷题目。
 * 每道题目得分的计算公式为：答案的权重之和*题目实例分值。
 * @author Talent
 */
@Entity
@Table(name = "exm_question")
public class Question  extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "代码")
	@Column(length = 32, nullable = false)
	private String code;
	
	@PropertyDef(label = "问题")
	@Column(length = 2048, nullable = false)
	private String question;
	
	@PropertyDef(label = "问题类型")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Question parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Question> children;
	
	
	public enum QuestionType {
		
		SINGLE_CHOICE   ("单选题"),
		MULTIPLE_CHOICE ("多选题");

		private String description;
		
		private QuestionType(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
		
		public static Map<String, String> toMap() {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (QuestionType value : QuestionType.values())
				map.put(value.toString(), value.description);
			return map;
		}
	}
}
