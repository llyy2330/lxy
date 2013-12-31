package com.lxy.balancer;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;

/**
 * 这是一个根据数组方式实现的支付机构定位器，子类应该按具体算法实现规则定位
 * 
 * @User: chenyuehua
 * @Date: 13-1-9
 * @Time: 下午1:32
 */
public abstract class AbstractArrayAgencyLocator implements AgencyLocator {

	public ChannelBusinessRule getChannelBusinessRule(
			List<ChannelBusinessRule> originRules) {
		if (originRules == null || originRules.isEmpty())
			throw new RuntimeException("getChannelBusinessRule");

		List<ChannelBusinessRule> selectedRules = new ArrayList<ChannelBusinessRule>(
				150);

		ArrayListMultimap<String, ChannelBusinessRule> weightChannelBusinessRules = businessBaseService
				.getWeightChannelBusinessRuleArrayListMultimap();

		// 选择符合条件的支付机构规则
		for (ChannelBusinessRule rule : originRules) {
			String key = BizUtils.createChannelBizRuleUniqKey(rule);
			selectedRules.addAll(weightChannelBusinessRules.get(key));
		}

		/**
		 * 列表打散
		 */
		// Collections.shuffle(selectedRules);

		// 获取规则信息
		return getChannelBusinessRuleBySelected(originRules, selectedRules);
	}

	/**
	 * 在已经定位好的集合中选择出一个目标{@code ChannelBusinessRule}
	 * 
	 * @return
	 */
	public abstract ChannelBusinessRule getChannelBusinessRuleBySelected(
			List<ChannelBusinessRule> originRules,
			List<ChannelBusinessRule> selectedRules);

	public static void main(String[] args) throws Exception {
		String ip = "127.0.0.1";
	}
}
