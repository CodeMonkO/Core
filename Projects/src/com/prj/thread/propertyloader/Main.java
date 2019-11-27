package com.prj.thread.propertyloader;

import java.util.Properties;

public class Main implements IProperties {

	public static void main(String[] args) {
		Main m = new Main();
		PropertyWatcher propertyWatcher = new PropertyWatcher("props");
		PropertyLoader propertyLoader = new PropertyLoader(m);
		Thread t1 = new Thread(propertyWatcher);
		Thread t2 = new Thread(propertyLoader);
		t1.start();
		t2.start();
	}

	@Override
	public void getProperties(Properties properties) {
		System.out.println(properties);
	}

}
