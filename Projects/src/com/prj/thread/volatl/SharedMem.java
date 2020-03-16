package com.prj.thread.volatl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SharedMem<K, V> {

	private static SharedMem<?, ?> sharedMem = null;

	private SharedMem() {

	}

	public static SharedMem<?, ?> getInstance() {
		if (sharedMem == null) {
			sharedMem = new SharedMem<>();
		}
		return sharedMem;
	}

	private volatile Map<K, V> sharedInstance = new ConcurrentHashMap<>();//visibility main memory

	public Map<K, V> getSharedInstance() {
		return sharedInstance;
	}

	public void setSharedInstance(final Map<K, V> sharedInstance) {
		this.sharedInstance = sharedInstance;
	}
}