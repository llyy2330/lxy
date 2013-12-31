package com.lxy.balancer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: chenyuehua Date: 13-3-28 Time: 下午2:56
 */
public class BusinessRuleCompare implements Comparator<ChannelBusinessRule> {
	public int compare(ChannelBusinessRule o1, ChannelBusinessRule o2) {
		if (o1.getId().intValue() > o2.getId().intValue())
			return 1;
		return -1;
	}

	public static void main(String[] args) {
		ChannelBusinessRule o1 = new ChannelBusinessRule();
		o1.setId(20);

		ChannelBusinessRule o2 = new ChannelBusinessRule();
		o2.setId(10);

		List<ChannelBusinessRule> list = new ArrayList<ChannelBusinessRule>();
		list.add(o1);
		list.add(o2);

		Collections.sort(list, new BusinessRuleCompare());

		for (ChannelBusinessRule o : list) {
			System.out.println(o.getId());
		}

	}
}
