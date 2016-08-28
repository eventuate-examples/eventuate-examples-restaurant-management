package net.chrisrichardson.eventstore.examples.management.restaurant.commandside.main;

import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsServiceCommandsideConfiguration;
import org.springframework.boot.SpringApplication;

public class RestaurantsCommandsideMain {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsServiceCommandsideConfiguration.class, args);
    }
}
