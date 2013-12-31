package com.lxy.balancer;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮回调度算法实现的支付机构定位器
 * 
 * @User: chenyuehua
 * @Date: 13-1-9
 * @Time: 上午11:44
 */
public class RoundRobinArrayAgencyLoctor extends AbstractArrayAgencyLocator {

    private SoftLimitMRUCache<String, AtomicInteger> indexMap = new SoftLimitMRUCache<String, AtomicInteger>();

    public RoundRobinArrayAgencyLoctor() {
        super();
    }

    /**
     * {@inheritDoc}
     * @param originRules
     * @param selectedRules  可选的规则，
     * @return
     */
    @Override
    public ChannelBusinessRule getChannelBusinessRuleBySelected(List<ChannelBusinessRule> originRules,
            List<ChannelBusinessRule> selectedRules) {

        AtomicInteger index = getIndex(getKeyByRules(originRules));
        
        return selectedRules.get(Math.abs(index.getAndIncrement() % selectedRules.size()));
    }

    /**
     * @param originRules
     * @return
     */
    private String getKeyByRules(List<ChannelBusinessRule> originRules) {
        Collections.sort(originRules, new BusinessRuleCompare());

        StringBuilder sb = new StringBuilder(20);

        for (ChannelBusinessRule rule : originRules) {
            String key = BizUtils.createChannelBizRuleUniqKey(rule);
            sb.append(key + ':');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private AtomicInteger getIndex(String key) {
        AtomicInteger index = indexMap.get(key);
        if (index == null) {
            index = new AtomicInteger(0);
            indexMap.putIfAbsent(key, index);
        }
        return index;
    }
}
