package com.lxy.balancer;

import java.util.Date;

/**
 * User: chenyuehua Date: 13-1-8 Time: 下午5:21
 */
public class AgencyChannelRule {

	/**
	 * 自增ID
	 */
	private Integer id;

	/**
	 * 支付机构代码
	 */
	private String agencyCode;

	/**
	 * 银行、支付机构编码
	 */
	private String channelCode;

	/**
	 * 支付渠道类型
	 */
	private String channelType;

	/**
	 * 权重
	 */
	private Integer weight;

	/**
	 * 扩展规则
	 */
	private String extRule;

	/**
	 * 有效状态
	 */
	private Boolean infoStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Boolean getInfoStatus() {
		return infoStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getExtRule() {
		return extRule;
	}

	public void setExtRule(String extRule) {
		this.extRule = extRule;
	}

	public Boolean isInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Boolean infoStatus) {
		this.infoStatus = infoStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
