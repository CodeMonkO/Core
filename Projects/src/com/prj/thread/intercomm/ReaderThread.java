package com.prj.thread.intercomm;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReaderThread<V> {

	private final SharedMem<String, Future<V>> sharedMem = (SharedMem<String, Future<V>>) SharedMem.getInstance();

	private static ReaderThread<?> rt = null;

	private ReaderThread() {

	}

	public static ReaderThread<?> getInstance() {
		if (rt == null) {
			rt = new ReaderThread<>();
		}
		return rt;
	}

	private V value;

	public V getValue(String key) throws ReaderThreadException {
		while (!sharedMem.getSharedInstance().get(key).isDone()) {

		}
		try {
			value = sharedMem.getSharedInstance().get(key).get();
		} catch (InterruptedException e) {
			throw new ReaderThreadException(e.getMessage());
		} catch (ExecutionException e) {
			throw new ReaderThreadException(e.getMessage());
		}
		return value;
	}
}

class ReaderThreadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2848879844222158831L;

	public ReaderThreadException(String m) {
		super(m);
	}
}