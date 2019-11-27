package com.prj.thread.logger;

public class Node<T> {
	
	private Node<T> prev;
	
	private T log;
	
	private Node<T> next;
	
	private int index;

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	public T getLog() {
		return log;
	}

	public void setLog(T log) {
		this.log = log;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
