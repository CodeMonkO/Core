package com.prj.map.custom;

public class TestCustomMap {

	public static void main(String[] a) {
		Map<Integer, Integer> c = new CustomMap<>();
		for(int l =0; l< 170000; l++) {
			c.put(l,l);
		}
		
		for(Integer m: c.getKeySet()) {
			System.out.println("KEY : "+m+" VAL : "+c.get(m));
		}
		
		System.out.println(c.size());
		
		for(Integer m: c.getKeySet()) {
			c.remove(m);
			System.out.println("KEY : "+m+" REM");
		}
		
		System.out.println(c.size());
	}
}
