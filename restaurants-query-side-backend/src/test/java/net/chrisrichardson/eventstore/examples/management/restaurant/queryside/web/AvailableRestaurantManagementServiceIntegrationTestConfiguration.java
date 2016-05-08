package net.chrisrichardson.eventstore.examples.management.restaurant.queryside.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideRedisConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import(RestaurantsQuerysideRedisConfiguration.class)
public class AvailableRestaurantManagementServiceIntegrationTestConfiguration {
}
