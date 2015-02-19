package com.tesco.ofs.swagger.app;

import com.tesco.ofs.order.resource.FulfillmentOrder;
import com.tesco.ofs.swagger.config.SwaggerDropwizard;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PlatformApplication extends Application<PlatformConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
    public static void main(String...args) throws Exception {
        new PlatformApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
        swaggerDropwizard.onInitialize(bootstrap);
    }

    @Override
    public void run(PlatformConfiguration configuration, Environment environment) throws Exception {
     
        environment.jersey().register(new  FulfillmentOrder());
        swaggerDropwizard.onRun(configuration, environment, "localhost");
    }
}
