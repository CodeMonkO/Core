package com.prj.thread.logger;

public final class LoggerQueue<T> {

	private Node<T> front;

	private Node<T> rear;

	private int capacity;

	private int count;

	private static volatile LoggerQueue<?> loggerQueue = null;

	private LoggerQueue() {
		super();
	}

	public static LoggerQueue<?> getInstance() {
		if (loggerQueue == null) {
			synchronized (LoggerQueue.class) {
				if (loggerQueue == null) {
					loggerQueue = new LoggerQueue<>(Constants.Constant.BUFFER_SIZE.getValue());// Minimum 2
				}
			}
		}
		return loggerQueue;
	}

	private LoggerQueue(final int capacity) {
		this.capacity = capacity;
	}

	private void createNode(int i, T t) {
		Node<T> node = new Node<T>();
		node.setIndex(i);
		node.setLog(t);
		if (rear == null) {
			this.front = node;
			this.rear = node;
		} else {
			node.setPrev(rear);
			this.rear.setNext(node);
			this.rear = node;
		}
	}

	private void removeNode() {
		this.front = this.front.getNext();
	}

	public void push(final T t) throws LoggerQueueException {
		synchronized (this) {
			if (count == capacity) {
				try {
					if (!(Thread.currentThread().getState() == Thread.State.WAITING)) {
						wait(Constants.Constant.THREAD_WAIT.getValue());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (count < capacity) {
				//System.out.println("PU " + t);
				createNode(count, t);
			}
			if(front==null) {
				front = rear;
			}
			count++;
			notify();
			try {
				Thread.sleep(Constants.Constant.THREAD_SLEEP.getValue());
			} catch (InterruptedException e) {
				throw new LoggerQueueException(e.getMessage());
			}
		}
	}

	public T pop() throws LoggerQueueException {
		T t = null;
		synchronized (this) {
			while (count == -1) {
				try {
					if (!(Thread.currentThread().getState() == Thread.State.WAITING)) {
						wait(Constants.Constant.THREAD_WAIT.getValue());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (this.front != null) {
				t = this.front.getLog();
				if (count > 0) {
					removeNode();
				}
				count--;
			} // else No node exist
			notify();
			try {
				Thread.sleep(Constants.Constant.THREAD_SLEEP.getValue());
			} catch (InterruptedException e) {
				throw new LoggerQueueException(e.getMessage());
			}
		}
		return t;
	}

	public int getCapacity() {
		return capacity;
	}

}
