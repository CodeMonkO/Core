package com.prj.thread.logger;

import java.io.IOException;

public class Consumer<T> implements Runnable {

	LoggerQueue<T> logger = (LoggerQueue<T>) LoggerQueue.getInstance();

	LogWriter<T> logWriter = new LogWriter<T>();

	@Override
	public void run() {
		while (true) {
			T t = null;
			try {
				t = logger.pop();
			} catch (LoggerQueueException e1) {
				e1.printStackTrace();
			}
			if (t != null) {
				try {
					logWriter.write(t);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
