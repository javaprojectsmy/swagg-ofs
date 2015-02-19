package com.tesco.ofs.platform.notification;

import java.util.concurrent.ExecutorService;

public class PlatformExecutorService {
	
	private static ExecutorService platformExecutorService = null;

	public static ExecutorService getPlatformExecutorService() {
		return platformExecutorService;
	}

	public static void setPlatformExecutorService(ExecutorService inPlatformExecutorService) {
		platformExecutorService = inPlatformExecutorService;
	}
	
}
