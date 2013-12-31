package com.lxy.balancer;

import java.util.Date;

/**
 * 业务规则
 * 
 * @User: chenyuehua
 * @Date: 13-1-8
 * @Time: 下午4:13
 */
public class ChannelBusinessRule {

	/**
	 * 自增ID
	 */
	private Integer id;

	/**
	 * 支付请求来源编码
	 */
	private String sourceTypeCode;

	/**
	 * 支付订单类型编码
	 */
	private String payOrderTypeCode;

	/**
	 * 支付方式
	 */
	private String paymentTypeCode;

	/**
	 * 支付卡类型
	 */
	private String cardTypeCode;

	/**
	 * 支付机构号
	 */
	private String agencyCode;

	/**
	 * 支付渠道编码
	 */
	private String channelCode;

	/**
	 * 支付渠道名称
	 */
	private String channelName;

	/**
	 * 支付渠道类型
	 */
	private String channelType;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 有效状态
	 */
	private Boolean infoStatus;

	private Integer weight;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Boolean infoStatus) {
		this.infoStatus = infoStatus;
	}

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceTypeCode() {
		return sourceTypeCode;
	}

	public void setSourceTypeCode(String sourceTypeCode) {
		this.sourceTypeCode = sourceTypeCode;
	}

	public String getPayOrderTypeCode() {
		return payOrderTypeCode;
	}

	public void setPayOrderTypeCode(String payOrderTypeCode) {
		this.payOrderTypeCode = payOrderTypeCode;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getCardTypeCode() {
		return cardTypeCode;
	}

	public void setCardTypeCode(String cardTypeCode) {
		this.cardTypeCode = cardTypeCode;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
