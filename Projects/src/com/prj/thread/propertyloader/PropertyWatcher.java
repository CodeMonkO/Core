package com.prj.thread.propertyloader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class PropertyWatcher implements Runnable {

	private String filePath;

	private FileOperation fileOperation = FileOperation.getInstance();

	public PropertyWatcher(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		while (true) 
		{
			Path path = Paths.get(this.filePath);
			WatchService watcher;
			try {
				watcher = path.getFileSystem().newWatchService();
				path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
				WatchKey watckKey = watcher.take();
				List<WatchEvent<?>> events = watckKey.pollEvents();
				for (WatchEvent<?> event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
						System.out.println("MODIFIED");
						fileOperation.read(this.filePath+"/dynamicprop.properties");//dynamicprop.properties
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (FileOperationException e) {
				e.printStackTrace();
			}
		}
	}

}
