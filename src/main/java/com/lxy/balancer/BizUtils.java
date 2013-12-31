package com.lxy.balancer;


import org.springframework.util.StringUtils;

/**
 * 和业务相关的工具方法
 * User: chenyuehua
 * Date: 13-2-19
 * Time: 下午5:58
 */
public abstract class BizUtils {

    public static final char split = '_';

    /**
     * 使用
     * 支付类型<code>paymentTypeCode</code>、
     * 支付来源<code>sourceTypeCode</code>、
     * 支付订单类型<code>payOrderTypeCode</code>、
     * 卡类型<code>cardTypeCode</code>、
     * 渠道类型(普通、快捷)<code>channelType</code>
     * 支付渠道编码<code>channelCode</code>
     * 进行组织Map,其中Key使用<code>paymentTypeCode_sourceTypeCode_payOrderTypeCode_cardTypeCode_channelType_channelCode</code>
     * 这五个条件,主要是用来优化查询使用
     * @param rule
     * @return
     */
    public static String createChannelBizRuleKey(ChannelBusinessRule rule) {
        StringBuilder keySb = new StringBuilder(64);
        keySb.append(rule.getPaymentTypeCode()).append(split);
        keySb.append(rule.getSourceTypeCode()).append(split);
        keySb.append(rule.getPayOrderTypeCode()).append(split);
        if (StringUtils.hasText(rule.getCardTypeCode()))
            keySb.append(rule.getCardTypeCode()).append(split);
        keySb.append(rule.getChannelType()).append(split);
        keySb.append(rule.getChannelCode());

        return keySb.toString();
    }

    /**
     * 生成一个规则的唯一Key,使用
     * 支付类型<code>paymentTypeCode</code>、
     * 支付来源<code>sourceTypeCode</code>、
     * 支付订单类型<code>payOrderTypeCode</code>、
     * 卡类型<code>cardTypeCode</code>、
     * 渠道类型(普通、快捷)<code>channelType</code>
     * 支付渠道编码<code>channelCode</code>
     * 支付机构代码<code>agencyCode</code>
     * 进行组织Map,其中Key使用<code>paymentTypeCode_sourceTypeCode_payOrderTypeCode_cardTypeCode_channelType_channelCode_agencyCode</code>
     * 这五个条件,主要是用来优化查询使用
     * @param rule
     * @return
     */
    public static String createChannelBizRuleUniqKey(ChannelBusinessRule rule) {
        StringBuilder keySb = new StringBuilder(64);
        keySb.append(rule.getPaymentTypeCode()).append(split);
        keySb.append(rule.getSourceTypeCode()).append(split);
        keySb.append(rule.getPayOrderTypeCode()).append(split);
        if (StringUtils.hasText(rule.getCardTypeCode()))
            keySb.append(rule.getCardTypeCode()).append(split);
        keySb.append(rule.getChannelType()).append(split);
        keySb.append(rule.getChannelCode()).append(split);
        keySb.append(rule.getAgencyCode());
        return keySb.toString();

    }

 
   
}
