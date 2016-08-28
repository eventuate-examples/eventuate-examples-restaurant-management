package net.chrisrichardson.eventstore.examples.management.restaurant.queryside.main;

import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsServiceQuerysideConfiguration;
import org.springframework.boot.SpringApplication;

public class RestaurantsQuerysideMain {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsServiceQuerysideConfiguration.class, args);
    }
}
