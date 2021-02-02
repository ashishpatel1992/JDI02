package com.flipkart.dropwizard;

import com.flipkart.restcontroller.AdminRESTAPI;
import com.flipkart.restcontroller.ProfessorRESTAPI;
import com.flipkart.restcontroller.StudentRESTAPI;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Service class handles initialization of REST API Classes for DropWizard
 */
public class Service extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        /**
         * Creates service and runs it
         */
        new Service().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    /**
     * Provides configuration files and environment variables as parameters.
     * This function is used to register REST API classes
     * @param configuration
     * @param environment
     * @throws Exception
     */
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new AdminRESTAPI());
        environment.jersey().register(new ProfessorRESTAPI());
        environment.jersey().register(new StudentRESTAPI());
    }
}
