package com.prj.thread.logger;

public class Log<T> implements Runnable {

	private LoggerQueue<T> logger = (LoggerQueue<T>) LoggerQueue.getInstance();

	private T t;

	public Log() {
		super();
	}
	
	public void d(T t) {
		try {
			logger.push(t);
		} catch (LoggerQueueException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (t != null) {
				try {
					logger.push(t);
				} catch (LoggerQueueException e) {
					e.printStackTrace();
				}
				t = null;
			}
		}
	}
}