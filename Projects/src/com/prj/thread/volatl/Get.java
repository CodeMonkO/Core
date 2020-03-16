package com.prj.thread.volatl;

public class Get implements Runnable{

	final SharedMem<String, Double> sharedMem = (SharedMem<String, Double>) SharedMem.getInstance();

	@Override
	public void run() {
		System.out.println(sharedMem.getSharedInstance().values());
	}

}
