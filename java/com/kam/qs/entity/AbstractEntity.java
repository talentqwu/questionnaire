package com.kam.qs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * 所有实体类的抽象类，只有一个属性ID。
 * @author Talent
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 因为NC中的主键为字串，故与NC保持一致
	 */
	@PropertyDef(label = "ID")
	@Id
	@Column(nullable = false, length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
