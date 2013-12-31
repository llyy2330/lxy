package com.lxy.balancer;

import java.util.List;

/**
 * 支付机构定位接口 User: chenyuehua Date: 13-1-8 Time: 下午5:01
 */
public interface AgencyLocator {

	/**
	 * 返回定位好的业务规则
	 * 
	 * @return
	 */
	public ChannelBusinessRule getChannelBusinessRule(
			List<ChannelBusinessRule> channelBusinessRules);
}
