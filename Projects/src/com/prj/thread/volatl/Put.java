package com.prj.thread.volatl;

public class Put implements Runnable {

	final SharedMem<String, Double> sharedMem = (SharedMem<String, Double>) SharedMem.getInstance();

	@Override
	public void run() {
		double k = Math.random();
		System.out.println(k);
		sharedMem.getSharedInstance().put("VAL", k);
	}

}
