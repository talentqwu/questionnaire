package com.kam.qs.entity.common;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bstek.dorado.annotation.PropertyDef;
import com.kam.qs.entity.AuditEntity;

/**
 * 参与调查的单位联络人。
 * @author Talent
 */
@Entity
@Table(name = "com_liaisons")
public class Liaisons  extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@PropertyDef(label = "姓名")
	@Column(length = 64, nullable = false)
	private String name;
	
	@PropertyDef(label = "职务")
	@Column(length = 64)
	private String duty;
	
	@PropertyDef(label = "手机号")
	@Column(length = 11, nullable = false, unique = true)
	private String telphone;
	
	@PropertyDef(label = "邮件地址")
	@Column(length = 256, nullable = false, unique = true)
	private String email;
	
	@PropertyDef(label = "验证密钥")
	@Column(name = "secret_key", length = 12, nullable = false)
	private String secretKey = String.valueOf((int)((Math.random()*9+1)*100000));
	
	@PropertyDef(label = "验证密钥生成时间")
	@Column(name = "key_generate_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date keyGenerateTime;
	
	/**
	 * 有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	@PropertyDef(label = "开放平台帐号")
	@Column(length = 64, unique = true)
	private String unionid;
	
	@PropertyDef(label = "唯一标识")
	@Column(length = 64, unique = true)
	private String openid;
	
	@PropertyDef(label = "昵称")
	@Column(name = "nick_name", length = 128)
	private String nickName;
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
	 * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	@PropertyDef(label = "用户头像URL")
	@Column(name = "head_img_url", length = 256)
	private String headImageUrl;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "liaisonses")
	private List<Unit> units;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

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

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Date getKeyGenerateTime() {
		return keyGenerateTime;
	}

	public void setKeyGenerateTime(Date keyGenerateTime) {
		this.keyGenerateTime = keyGenerateTime;
	}
}
