package com.prj.thread.subtask;

import java.util.concurrent.BrokenBarrierException;

//Main Task to aggregate subtask
public class Compute<T> implements Runnable {

	private T t;

	@Override
	public void run() {
		Compute1<String> c1 = new Compute1<>();
		Compute2<String> c2 = new Compute2<>();
		Compute3<String> c3 = new Compute3<>();

		c1.setT("himanshu");
		c2.setT("TIWARI");
		c3.setT("   BHARAT");
		
		System.out.println(c1.getT()+c2.getT());
		System.out.println(System.currentTimeMillis());
		
		Thread c1Thread = new Thread(c1);
		Thread c2Thread = new Thread(c2);
		Thread c3Thread = new Thread(c3);

		c1Thread.start();
		c2Thread.start();
		c3Thread.start();

		try {
			Main.newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		t = (T) c1.getT().concat(c2.getT()).concat(c3.getT());
		
		System.out.println(t);
		System.out.println(System.currentTimeMillis());
		Main.newBarrier.reset();
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
