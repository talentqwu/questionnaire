package com.kam.qs.entity.task;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;
import com.kam.qs.entity.common.QRCode;
import com.kam.qs.entity.common.Unit;
import com.kam.qs.entity.content.Participator;
import com.kam.qs.entity.content.Statistics;
import com.kam.util.CommonUtils;

/**
 * 与每个单位相关的子任务。
 * @author Talent
 */
@Entity
@Table(name = "tsk_sub_task")
public class SubTask extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * URL后带的一串8位唯一标示
	 */
	@PropertyDef(label = "UUID")
	@Column(length = 8, nullable = false)
	private String uuid = CommonUtils.generateShortUuid();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", nullable = false)
	private Unit unit;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subTask")
	private List<Participator> participators;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qrcode_id")
	private QRCode qrcode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statistics_id")
	private Statistics statistics;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Participator> getParticipators() {
		return participators;
	}

	public void setParticipators(List<Participator> participators) {
		this.participators = participators;
	}

	public QRCode getQrcode() {
		return qrcode;
	}

	public void setQrcode(QRCode qrcode) {
		this.qrcode = qrcode;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
