package com.prj.thread.logger;

import java.util.Scanner;

public class Main {

	public static void main(String a[]) {
		Log<String> log = new Log<>();
		Consumer<String> consumer = new Consumer<>();
		Thread t1 = new Thread(consumer);
		Thread t2 = new Thread(log);
		Thread t3 = new Thread(log);
		t1.start();
		t2.start();
		t3.start();
		
		for (int i=0; i<100; i++) {
			log.d(String.valueOf(i));
		}
		log.d("I am still at the office");
		Scanner sc = new Scanner(System.in);
		while(true) {
			log.d(sc.next());
		}
	}

}
