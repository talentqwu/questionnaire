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
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.entity.common.Image;

/**
 * 调查问卷模板。
 * @author Talent
 */
@Entity
@Table(name = "exm_template")
public class Template extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "名称")
	@Column(length = 256, nullable = false)
	private String name;
	
	@PropertyDef(label = "标题")
	@Column(length = 256, nullable = false)
	private String title;
	
	@PropertyDef(label = "描述")
	@Column(length = 2048)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private Image image;
	
	@PropertyDef(label = "图片地址")
	@Column(length = 256)
	private String imageUrl;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "template")
	private List<Instance> questions;

	@PropertyDef(label = "是否发布")
	@Column(nullable = false)
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean published = false;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Instance> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Instance> questions) {
		this.questions = questions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
