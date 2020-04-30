package com.prj.map.custom;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public final class CustomMap<K extends Comparable<K>, V> implements Map<K, V> {

	private K k = null;
	private int n = 16;
	private int bucketsUsed = 0;
	private int buckets = 16;
	private double loadFactor = 0.75;
	private long size;
	private SplayTree<K, V>[] l = new SplayTree[buckets];
	private SplayTree<K, V> st = null;
	private Set<K> keyset = new ConcurrentSkipListSet<K>();

	@Override
	public long size() {
		return size;
	}

	@Override
	public void put(final K k, final V v) {
		this.k = k;
		final int capacity = (int) (buckets * loadFactor);
		if (bucketsUsed == capacity) {
			bucketsUsed = bucketsUsed * 2;
			final SplayTree<K, V>[] l = new SplayTree[bucketsUsed];
			for (int i = 0; i < bucketsUsed / 2; i++) {
				l[i] = this.l[i];
			}
			this.l = l;
			this.buckets = bucketsUsed;
		}

		final int index = hashCode() & (n - 1);
		if (l[index] == null) {
			// insert root
			st = new SplayTree<K, V>();
			st.put(k, v);
			keyset.add(k);
			l[index] = st;
			bucketsUsed++;
		} else {
			st = l[index];
			st.put(k, v);
			keyset.add(k);
			// collision is handled inside SplayTree
		}
		size++;
	}

	@Override
	public V get(K k) {
		this.k = k;
		final int index = hashCode() & (n - 1);
		V v = null;
		if (l[index] != null) {
			v = l[index].get(k);
		}
		return v;
	}

	@Override
	public int hashCode() {
		return this.k.hashCode();
	}

	@Override
	public void remove(K k) {
		this.k = k;
		int index = hashCode() & (n - 1);
		if (l[index] != null) {
			if (l[index].remove(k)) {
				keyset.remove(k);
				size--;
			}
		}
	}

	@Override
	public Set<K> getKeySet() {
		return keyset;
	}
}
