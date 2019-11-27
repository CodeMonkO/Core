package com.prj.thread.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter<T> {

	public void write(T t) throws IOException {
		System.out.println(t);
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("Device.log⁩"), true));
		printWriter.print(t);
		printWriter.printf("\n");
		printWriter.close();
	}
}
