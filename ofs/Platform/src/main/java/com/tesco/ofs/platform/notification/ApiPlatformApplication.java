package com.tesco.ofs.platform.notification;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.lifecycle.setup.ExecutorServiceBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApiPlatformApplication extends Application<Configuration> {

	
    public static void main(String[] args) throws Exception {
        new ApiPlatformApplication().run(args);
    }

	@Override
	public void initialize(Bootstrap<Configuration> arg0) {
		
		
	}

	@Override
	public void run(Configuration config, Environment env) throws Exception {
		ExecutorServiceBuilder executorServiceBuilder = env.lifecycle()
				.executorService("Platform");
		executorServiceBuilder.maxThreads(10);
		PlatformExecutorService
				.setPlatformExecutorService(executorServiceBuilder.build());

		env.jersey().register(NotificationHandler.class);
	}

}