package com.prj.thread.propertyloader;

import java.util.Properties;

public class PropertyLoader implements Runnable {

	private IProperties iProperties;

	public PropertyLoader(IProperties object) {
		this.iProperties = object;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				Properties properties = FileOperation.getInstance().getProperties();
				if (properties != null && !properties.isEmpty()) {
					this.iProperties.getProperties(properties);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
