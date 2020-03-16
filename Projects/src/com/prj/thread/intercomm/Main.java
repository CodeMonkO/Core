package com.prj.thread.intercomm;

import java.util.concurrent.Callable;

public class Main {

	static int c;

	public static void main(String[] args) throws InterruptedException, ReaderThreadException {

		for (int i = 0; i < 10; i++) {
			new ExecutorThread<>("Task" + i, new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(1000);
					c++;
					return "Himanshu" + c;
				}
			}).start();
		}
		
		ReaderThread<?> rdInstance = ReaderThread.getInstance();
		System.out.println("Task Pushed");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						System.out.println("Task"+i +" - ---- --- - "+rdInstance.getValue("Task"+i));
					} catch (ReaderThreadException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}