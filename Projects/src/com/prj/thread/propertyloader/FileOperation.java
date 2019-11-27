package com.prj.thread.propertyloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileOperation {

	private Properties properties;

	private static FileOperation fileOperation = null;

	public static FileOperation getInstance() {
		if (fileOperation == null) {
			synchronized (FileOperation.class) {
				fileOperation = new FileOperation();
			}
		}
		return fileOperation;
	}

	public void read(String fileName) throws FileOperationException, IOException {
		synchronized (this) {
			properties = new Properties();
			File f = new File(fileName);
			if (f.exists() && f.isFile()) {
				InputStream in = new FileInputStream(fileName);
				try {
					properties.load(in);
				} catch (IOException e) {
					throw new FileOperationException(e);
				} finally {
					in.close();
				}
			}
		}
	}

	public Properties getProperties() {
		return properties;
	}
}
