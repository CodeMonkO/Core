package com.prj.thread.logger;

public class Constants {
	
	public enum Constant{
		BUFFER_SIZE(100),
		THREAD_WAIT(1000),
		THREAD_SLEEP(10);
		
		int value;
		
		Constant(final int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		
	}

}
