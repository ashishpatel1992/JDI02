package com.flipkart.dropwizard;

import com.flipkart.restcontroller.AdminRESTAPI;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Service extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Service().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new AdminRESTAPI());
    }
}
