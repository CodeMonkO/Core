package com.prj.thread.intercomm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorThread<V> extends Thread {

	final SharedMem<String, Future<V>> sharedMem = (SharedMem<String, Future<V>>) SharedMem.getInstance();

	private static final ExecutorService threadpool = Executors.newFixedThreadPool(10);

	private Callable<V> callable;
	
	private String task;
	
	private ExecutorThread(){
		super();
	}

	public ExecutorThread(String task, Callable<V> callable) {
		this.task = task;
		this.callable = callable;
	}

	@Override
	public void run() {
		synchronized (this) {		
			if (callable != null) {
				Future<V> future = threadpool.submit(callable);
				sharedMem.getSharedInstance().put(task, future);
			}
		}
	}
}
