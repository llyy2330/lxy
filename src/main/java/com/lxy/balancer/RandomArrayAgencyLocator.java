package com.lxy.balancer;

import java.util.List;
import java.util.Random;

/**
 * 以随机数方式实现的目标定位器
 * 
 * @User: chenyuehua
 * @Date: 13-1-8
 * @Time: 下午5:36
 */
public class RandomArrayAgencyLocator extends AbstractArrayAgencyLocator {

	private final Random rand = new Random();

	public RandomArrayAgencyLocator() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ChannelBusinessRule getChannelBusinessRuleBySelected(
			List<ChannelBusinessRule> originRules,
			List<ChannelBusinessRule> selectedRules) {
		return selectedRules.get(rand.nextInt(selectedRules.size()));
	}
}
