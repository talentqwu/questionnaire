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
 * 附件。
 * @author Talent
 */
@Entity
@Table(name = "com_image")
public class Image extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "文件名称")
	@Column(length = 256, nullable = false)
	private String name;

	@PropertyDef(label = "文件大小")
	@Column(name = "size_")
	private long size = 0;

	@PropertyDef(label = "附档内容")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
