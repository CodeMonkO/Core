package com.prj.thread.volatl;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Put p = new Put();
		Get g = new Get();
		
		for(int i=0; i<10; i++){
			Thread t1 = new Thread(p);
			Thread t2 = new Thread(g);
			t1.start();
			t2.start();
		}
	}

}
