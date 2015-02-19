package com.tesco.ofs.order.application;

import io.dropwizard.Application;
import io.dropwizard.jersey.validation.ConstraintViolationExceptionMapper;
import io.dropwizard.lifecycle.setup.ExecutorServiceBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;

import com.sun.jersey.api.core.ResourceConfig;
import com.tesco.ofs.order.appconfig.FulfillmentOrderConfiguration;
import com.tesco.ofs.order.resource.FulfillmentOrder;
import com.tesco.ofs.platform.security.authentication.SecurityFilter;
import com.tesco.ofs.platform.trace.exception.handler.OFSConstraintVoilationExceptionMapper;
import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;

public class FulfillmentOrderApplication extends
		Application<FulfillmentOrderConfiguration> {

	private static final OFSPlatformLogger logger = OFSPlatformLogger
			.getLogger(FulfillmentOrderApplication.class);


	public static void main(String[] args) throws Exception {
		new FulfillmentOrderApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<FulfillmentOrderConfiguration> arg0) {

	}

	@Override
	public void run(FulfillmentOrderConfiguration config, Environment env)
			throws Exception {

		// reset the logger initialization from dropwizard
		// to use logger configurations from logback.xml
		logger.setLogbackLogger();

		logger.info("Starting FulfillmentOrderApplication......");

		Boolean isAuthEnabled = new Boolean(config.getEnableAuthentication());
		int expiryTime = new Integer(config.getServiceTokenExpiryTime());
		String identitySvcHost = config.getIdentitySvcHost();
		String identityResource = config.getIdentityResource();

		if (isAuthEnabled)
			env.servlets()
					.addFilter(
							"SecurityFilter",
							new SecurityFilter(expiryTime, identitySvcHost,
									identityResource))
					.addMappingForUrlPatterns(
							EnumSet.allOf(DispatcherType.class), false, "/*");

		ExecutorServiceBuilder executorServiceBuilder = env.lifecycle()
				.executorService("FullfillmentOrder");
		executorServiceBuilder.maxThreads(10);
		FulfillmentOrderExecutorService
				.setFulfillmentOrderExecutorService(executorServiceBuilder.build());

		env.jersey().register(new FulfillmentOrder());

		registerOFSConstraintVoilationExceptionMapper(true, env);
	}

	/*
	 * This method removes the following default
	 * ConstraintVoilationExceptionMapper from dropwizard environment and adds
	 * our custom OFSConstraintVoilationExceptionMapper to dropwizard
	 * environment. We need our own OFSConstraintVoilationExceptionMapper
	 * because default ConstraintVoilationExceptionMapper doesn't give the
	 * validation error response json error ( as defined by IDL)
	 */
	private void registerOFSConstraintVoilationExceptionMapper(
			boolean register, Environment environment) {
		if (register) {
			ResourceConfig config = environment.jersey().getResourceConfig();
			Set<Object> dwSingletons = config.getSingletons();

			ConstraintViolationExceptionMapper constraintViolationExceptionMapper = null;
			for (Object singletons : dwSingletons) {
				if (singletons instanceof ConstraintViolationExceptionMapper
						&& !singletons.getClass().getName()
								.contains("DropwizardResourceConfig")) {
					constraintViolationExceptionMapper = (ConstraintViolationExceptionMapper) singletons;
					break;
				}
			}

			if (constraintViolationExceptionMapper != null) {
				logger.info("Removing ConstraintViolationExceptionMapper to dropwizard environment");
				config.getSingletons().remove(
						constraintViolationExceptionMapper);

				logger.info("Registering custom OFSConstraintViolationExceptionMapper to dropwizard environment");
				environment.jersey().register(
						new OFSConstraintVoilationExceptionMapper());

			}

		}
	}

}
