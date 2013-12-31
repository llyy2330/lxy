package com.lxy.balancer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import com.google.common.collect.ArrayListMultimap;

/**
 * @User: chenyuehua
 * @Date: 13-1-9
 * @Time: 下午4:21
 */
public class AgencyLocatorTest {

	@org.junit.Test
	public void testRoundRobinArrayAgencyLoctor() throws Exception {
		AgencyLocator loctor = new RoundRobinArrayAgencyLoctor();
		runLoctor(loctor);
	}

	/*
	 * @org.junit.Test public void testRandomArrayAgencyLocator() throws
	 * Exception { AgencyLocator loctor = new RandomArrayAgencyLocator();
	 * runLoctor(loctor); }
	 * 
	 * @org.junit.Test public void testElectionAgencyLoctor() throws Exception {
	 * AgencyLocator loctor = new ElectionAgencyLoctor(); runLoctor(loctor); }
	 */
	private void runLoctor(AgencyLocator loctor) {

		int totleCount = 100;

		long start = System.currentTimeMillis();

		// loctor.initialize(routerRuleEntity);

		Map<String, Integer> mapCount = new HashMap<String, Integer>();

		for (int i = 0; i < totleCount; i++) {
			// ChannelRequestEntity entity = new ChannelRequestEntity();

			// entity.setRequestId(String.valueOf(i));

			ChannelBusinessRule rule = loctor.getChannelBusinessRule(null);
			String key = rule.getAgencyCode() + '_' + rule.getChannelCode();
			Integer count = mapCount.get(key);
			if (count == null) {
				count = Integer.valueOf(0);
				mapCount.put(key, count);
			} else {
				count = Integer.valueOf(count.intValue() + 1);
				mapCount.put(key, count);
			}
		}

		long end = System.currentTimeMillis();

		long ss = (end - start) / 1000L;
		// long press = totleCount/ss;
		long prems = totleCount / (end - start);

		System.out.println("===========================================");

		System.out.println("作用方式:" + loctor.getClass().getCanonicalName());
		// System.out.println("用时:" + ss + " 秒, 每秒：" + press + " ,毫秒:" + prems);
		System.out.println("用时:" + ss + " ,毫秒:" + prems);

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Integer> entry : mapCount.entrySet()) {
			sb.append("key=" + entry.getKey() + " value=" + entry.getValue()
					+ "\r\n");
		}

		System.out.println(sb.toString());

	}

	@Before
	public void setUp() throws Exception {

		List<AgencyChannelRule> agencyChannelRules = new ArrayList<AgencyChannelRule>();

		AgencyChannelRule billABC = new AgencyChannelRule();
		billABC.setAgencyCode("050");
		billABC.setChannelCode("ABC");
		billABC.setWeight(2);

		AgencyChannelRule chinapnrABC = new AgencyChannelRule();
		chinapnrABC.setAgencyCode("060");
		chinapnrABC.setChannelCode("ABC");
		chinapnrABC.setWeight(2);

		AgencyChannelRule tenpayABC = new AgencyChannelRule();
		tenpayABC.setAgencyCode("070");
		tenpayABC.setChannelCode("ABC");
		tenpayABC.setWeight(6);

		agencyChannelRules.add(billABC);
		agencyChannelRules.add(chinapnrABC);
		agencyChannelRules.add(tenpayABC);

		List<ChannelBusinessRule> channelBusinessRules = new ArrayList<ChannelBusinessRule>();

		ChannelBusinessRule billBusinessRule = new ChannelBusinessRule();
		billBusinessRule.setAgencyCode("050");
		billBusinessRule.setChannelCode("ABC");
		billBusinessRule.setChannelType("1");
		billBusinessRule.setPaymentTypeCode("1");
		billBusinessRule.setSourceTypeCode("1");

		ChannelBusinessRule chinapnrBusinessRule = new ChannelBusinessRule();
		chinapnrBusinessRule.setAgencyCode("060");
		chinapnrBusinessRule.setChannelCode("ABC");
		chinapnrBusinessRule.setChannelType("1");
		chinapnrBusinessRule.setPaymentTypeCode("1");
		chinapnrBusinessRule.setSourceTypeCode("1");

		ChannelBusinessRule tenpayBusinessRule = new ChannelBusinessRule();
		tenpayBusinessRule.setAgencyCode("070");
		tenpayBusinessRule.setChannelCode("ABC");
		tenpayBusinessRule.setChannelType("1");
		tenpayBusinessRule.setPaymentTypeCode("1");
		tenpayBusinessRule.setSourceTypeCode("1");

		channelBusinessRules.add(billBusinessRule);
		channelBusinessRules.add(chinapnrBusinessRule);
		channelBusinessRules.add(tenpayBusinessRule);

		// routerRuleEntity = new RouterRuleEntity(agencyChannelRules,
		// channelBusinessRules);

	}

	// @org.junit.Test
	public void testMulitmap() {
		ArrayListMultimap<String, Object> map = ArrayListMultimap.create();

		Object o = new Object();

		map.put("abc", o);
		map.put("abc", o);
		map.put("def", "def1");

		Collection<Object> col = map.get("abc");

		for (Object s : col) {
			System.out.println("value:" + s);
		}

	}
}
