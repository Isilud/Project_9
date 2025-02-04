package com.medilabo.noteservice.configuration;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

public class MongoContainerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    static {
        mongoDBContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        TestPropertyValues.of(
                "spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl()).applyTo(context.getEnvironment());
    }
}