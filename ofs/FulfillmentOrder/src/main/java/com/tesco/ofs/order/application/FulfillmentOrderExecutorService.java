package com.tesco.ofs.order.application;

import java.util.concurrent.ExecutorService;

public class FulfillmentOrderExecutorService {
	
	private static ExecutorService fulfillmentOrderExecutorService = null;

	public static ExecutorService getFulfillmentOrderExecutorService() {
		return fulfillmentOrderExecutorService;
	}

	public static void setFulfillmentOrderExecutorService(ExecutorService inFulfillmentOrderExecutorService) {
		fulfillmentOrderExecutorService = inFulfillmentOrderExecutorService;
	}
	
}
