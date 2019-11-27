package com.prj.thread.subtask;

import java.util.concurrent.BrokenBarrierException;

//Independent SubTask 2
public class Compute2<T> implements Runnable {

	private T t;

	@Override
	public void run() {	
		String s = String.valueOf(t);
		t = (T) s.toLowerCase();
		try {
			Main.newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}


}
