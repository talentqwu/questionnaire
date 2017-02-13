package com.kam.qs.entity.common;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AbstractEntity;

/**
 * 每个单位及一包多所使用的二维码。
 * @author Talent
 */
@Entity
@Table(name = "com_qrcode")
public class QRCode extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * URL后带的一串8位唯一标示，于子任务及参与者相对应
	 */
	@PropertyDef(label = "UUID")
	@Column(length = 8, nullable = false)
	private String uuid;
	
	/**
	 * 0:单位，1:参与者
	 */
	@PropertyDef(label = "类型")
	@Column(length = 1, nullable = false)
	private int type = 0;
	
	@PropertyDef(label = "附档内容")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] content;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
