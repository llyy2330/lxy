package com.lxy.balancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用map来实现一个LRU(最近最少使用算法)缓存 User: chenyuehua Date: 13-3-7 Time: 上午9:40
 */

public class LRUCacheMap<K, V> extends LinkedHashMap<K, V> {

	private final int maxEntries;

	public LRUCacheMap(int maxEntries) {
		super(maxEntries, .75f, true);
		this.maxEntries = maxEntries;
	}

	protected boolean removeEldestEntry(Map.Entry eldest) {
		return (size() > maxEntries);
	}
}
