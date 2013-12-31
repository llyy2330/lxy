package com.lxy.balancer;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 软引用的容量有限的最近使用缓存 User: chenyuehua Date: 13-3-7 Time: 上午11:06
 */
public class SoftLimitMRUCache<K, V> implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(SoftLimitMRUCache.class);

	/**
	 * 软件引用数量
	 */
	public static final int DEFAULT_SOFT_REF_COUNT = 2048;

	private int softRefCount;

	private transient LRUCacheMap<K, KeyedSoftReference<V>> softRefCache;
	private transient ReferenceQueue<KeyedSoftReference<V>> referenceQueue;

	private final transient ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public SoftLimitMRUCache() {
		this(DEFAULT_SOFT_REF_COUNT);
	}

	public SoftLimitMRUCache(int softRefCount) {

		if (softRefCount < 1) {
			throw new IllegalArgumentException(
					"Reference counts must be greater than zero");
		}
		this.softRefCount = softRefCount;
		init();
	}

	/**
	 * 初始化缓存
	 */
	private void init() {
		this.softRefCache = new LRUCacheMap<K, KeyedSoftReference<V>>(
				softRefCount);
		this.referenceQueue = new ReferenceQueue<KeyedSoftReference<V>>();
	}

	/**
	 * 根据一个<code>key</code> 获取一个 <code>value</code>
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if (key == null) {
			throw new NullPointerException("Key to get cannot be null");
		}

		Lock writeLock = lock.writeLock();
		writeLock.lock();

		try {
			clearObsoleteReferences();
			KeyedSoftReference<V> ref = softRefCache.get(key);
			return (ref != null) ? ref.get() : null;
		} finally {
			writeLock.unlock();
		}

	}

	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException(getClass().getName()
					+ "does not support null key [" + key + "] or value ["
					+ value + "]");
		}
		Lock writeLock = lock.writeLock();
		writeLock.lock();
		try {
			clearObsoleteReferences();
			KeyedSoftReference<V> ref = softRefCache.put(key,
					new KeyedSoftReference(key, value, referenceQueue));
			return (ref != null) ? ref.get() : null;
		} finally {
			writeLock.unlock();
		}
	}

	public V putIfAbsent(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException(getClass().getName()
					+ "does not support null key [" + key + "] or value ["
					+ value + "]");
		}

		Lock writeLock = lock.writeLock();
		writeLock.lock();

		try {
			clearObsoleteReferences();
			KeyedSoftReference<V> ref = softRefCache.get(key);
			if (ref == null || ref.get() == null) {
				softRefCache.put(key, new KeyedSoftReference(key, value,
						referenceQueue));
				return null;
			} else {
				return ref.get();
			}
		} finally {
			writeLock.unlock();
		}

	}

	public boolean containsKey(K key) {
		Lock readLock = lock.readLock();
		readLock.lock();
		try {
			KeyedSoftReference<V> val = softRefCache.get(key);
			return val == null || val.isEnqueued();
		} finally {
			readLock.unlock();
		}
	}

	public V remove(K key) {
		Lock writeLock = lock.writeLock();
		writeLock.lock();
		try {
			clearObsoleteReferences();
			KeyedSoftReference<V> val = softRefCache.remove(key);
			return val == null ? null : val.get();
		} finally {
			writeLock.unlock();
		}
	}

	public boolean isEmpty() {
		return softRefCache.isEmpty();
	}

	public int size() {
		return softRefCache.size();
	}

	public void clear() {
		Lock writeLock = lock.writeLock();
		writeLock.lock();
		try {
			softRefCache.clear();
		} finally {
			writeLock.unlock();
		}
	}

	private void clearObsoleteReferences() {
		KeyedSoftReference<V> obsoleteRef;
		while ((obsoleteRef = (KeyedSoftReference<V>) referenceQueue.poll()) != null) {
			K key = obsoleteRef.getKey();
			softRefCache.remove(key);
		}
	}

	/**
	 * 
	 * @param <V>
	 */
	private class KeyedSoftReference<V> extends SoftReference<V> {
		private final K key;

		private KeyedSoftReference(K key, V value, ReferenceQueue<V> queue) {
			super(value, queue);
			this.key = key;
		}

		private K getKey() {
			return key;
		}
	}

}
