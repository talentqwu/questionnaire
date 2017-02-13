package com.kam.qs.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;

/**
 * 操作日志（所有类共用）。
 * @author Talent
 */
@Entity
@Table(name = "com_log")
public class Log extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "操作时间")
	@Column(name = "date_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@PropertyDef(label = "操作者姓名")
	@Column(name = "user_name", nullable = false, length = 64)
	private String userName;
	
	@PropertyDef(label = "操作者代码")
	@Column(name = "user_code", nullable = false, length = 64)	
	private String userCode;
	
	@PropertyDef(label = "操作描述")
	@Column(nullable = false, length = 64)
	private String action;
	
	@PropertyDef(label = "备注说明（或审核批注）")
	@Column(length = 1024)
	private String memo;

	@PropertyDef(label = "相关实体ID")
	@Column(name = "entity_id", nullable = false)
	@Index(name = "log_entity_id_idx")
	private String entityId;
	
	public Log() {}
	
	public Log(String entityId, String action) {
		this.action = action;
		this.entityId = entityId;
	}
	
	public Log(String entityId, String action, String memo) {
		this.entityId = entityId;
		this.action = action;
		this.memo = memo;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
}
