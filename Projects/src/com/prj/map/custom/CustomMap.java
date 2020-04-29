package com.prj.map.custom;

public class CustomMap<K extends Comparable<K>, V> implements Map<K, V> {

	private K k = null;
	private int n = 16;
	private int buckets = 0;
	private int numBuckets = 16;
	private long size;

	private SplayTree<K, V>[] l = new SplayTree[numBuckets];

	SplayTree<K, V> st = null;

	@Override
	public long size() {
		return size;
	}

	@Override
	public void put(K k, V v) {
		// TODO Auto-generated method stub
		this.k = k;
		int capacity = (int) (numBuckets * 0.75);
		if (buckets == capacity) {
			buckets = buckets * 2;
			SplayTree<K, V>[] l = new SplayTree[buckets];
			for (int i = 0; i < buckets / 2; i++) {
				l[i] = this.l[i];
			}
			this.l = l;
			this.numBuckets = buckets;
		}

		int index = hashCode() & (n - 1);
		if (l[index] == null) {
			// insert root
			st = new SplayTree<K, V>();
			st.put(k, v);
			l[index] = st;
			buckets++;
		} else {
			st = l[index];
			st.put(k, v);
			// collision is handled inside SplayTree
		}
		size++;
	}

	@Override
	public V get(K k) {
		// TODO Auto-generated method stub
		this.k = k;
		int index = hashCode() & (n - 1);
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
		// TODO Auto-generated method stub
		this.k = k;
		int index = hashCode() & (n - 1);
		if (l[index] != null) {
			if(l[index].remove(k));
				size--;
		}
	}

}
