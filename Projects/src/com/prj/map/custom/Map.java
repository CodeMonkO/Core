package com.prj.map.custom;

import java.util.Set;

public interface Map<K,V> {
	
	void put(K k, V v);
	
	V get(K k);
	
	long size();
	
	void remove(K k);
	
	Set<K> getKeySet();
}
