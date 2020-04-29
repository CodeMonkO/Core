package com.prj.map.custom;

public class TestCustomMap {

	public static void main(String[] a) {
		CustomMap<Integer, Integer> c = new CustomMap<>();
		for(int l =0; l< 100000; l++) {
			c.put(l,l);
			if(l==100) {
				c.remove(99);
			}
		}
		System.out.println(c.size());
	}
}
