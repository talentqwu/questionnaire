package com.kam.qs.entity.content;

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
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.entity.common.QRCode;
import com.kam.qs.entity.task.Prize;
import com.kam.qs.entity.task.SubTask;

/**
 * 调查参与者。
 * @author Talent
 */
@Entity
@Table(name = "con_participator")
public class Participator extends AuditEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	@PropertyDef(label = "开放平台帐号")
	@Column(length = 64)
	private String unionid;
	
	@PropertyDef(label = "唯一标识")
	@Column(length = 64)
	private String openid;
	
	@PropertyDef(label = "昵称")
	@Column(name = "nick_name", length = 128)
	private String nickName;
	
	@PropertyDef(label = "性别")
	@Column(length = 16)
	private String sex;
	
	@PropertyDef(label = "省份")
	@Column(length = 64)
	private String province;
	
	@PropertyDef(label = "城市")
	@Column(length = 64)
	private String city;
	
	@PropertyDef(label = "国家")
	@Column(length = 32)
	private String country;
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
	 * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	@PropertyDef(label = "用户头像URL")
	@Column(name = "", length = 256)
	private String headImageUrl;
	
	@PropertyDef(label = "手机号")
	@Column(length = 11)
	private String telphone;
	
	/**
	 * 分享URL后带的一串8位唯一标示
	 */
	@PropertyDef(label = "UUID")
	@Column(length = 8, nullable = false)
	private String uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subtask_id", nullable = false)
	private SubTask subTask;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qrcode_id")
	private QRCode qrcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prize_id")
	private Prize prize;
	
	/**
	 * 有效答卷的得分
	 */
	@PropertyDef(label = "得分")
	@Column(name = "total_score", nullable = false)
	private double totalScore = 0;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "participator")
	private List<Sheet> sheets;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Participator parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Participator> children;

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public SubTask getSubTask() {
		return subTask;
	}

	public void setSubTask(SubTask subTask) {
		this.subTask = subTask;
	}

	public QRCode getQrcode() {
		return qrcode;
	}

	public void setQrcode(QRCode qrcode) {
		this.qrcode = qrcode;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public List<Sheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	public Participator getParent() {
		return parent;
	}

	public void setParent(Participator parent) {
		this.parent = parent;
	}

	public List<Participator> getChildren() {
		return children;
	}

	public void setChildren(List<Participator> children) {
		this.children = children;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
}
