package com.kam.qs.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;

@Entity
@Table(name = "com_calendar_event")
public class CalendarEvent extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "标题")
	@Column(nullable = false, length = 256)
	private String title;
	
	@PropertyDef(label = "开始时间")
	@Column(name = "start_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@PropertyDef(label = "结束时间")
	@Column(name = "end_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@PropertyDef(label = "全天")
	@Column(name = "all_day")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean allDay;
	
	@PropertyDef(label = "标题颜色")
	@Column(length = 8, nullable = false)
	private String color;
	
	@PropertyDef(label = "背景颜色")
	@Column(name = "background_color", nullable = false, length = 8)
	private String backgroundColor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
