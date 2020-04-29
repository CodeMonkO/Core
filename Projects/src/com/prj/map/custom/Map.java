package com.prj.map.custom;

public interface Map<K,V> {
	
	void put(K k, V v);
	
	V get(K k);
	
	long size();
	
	void remove(K k);
}
