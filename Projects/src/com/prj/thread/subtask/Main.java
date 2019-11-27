package com.prj.thread.subtask;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static CyclicBarrier newBarrier = new CyclicBarrier(4); 
    
	public static void main(String[] a) {
		
		Compute<String> compute = new Compute<>();
		Thread t1Compute = new Thread(compute);
		t1Compute.start();
	}
}
