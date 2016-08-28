package net.chrisrichardson.eventstore.examples.management.restaurant.monolithic.main;

import net.chrisrichardson.eventstore.examples.management.restaurant.monolithic.RestaurantsConfiguration;
import org.springframework.boot.SpringApplication;

public class RestaurantsMonolithicMain {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsConfiguration.class, args);
    }
}
