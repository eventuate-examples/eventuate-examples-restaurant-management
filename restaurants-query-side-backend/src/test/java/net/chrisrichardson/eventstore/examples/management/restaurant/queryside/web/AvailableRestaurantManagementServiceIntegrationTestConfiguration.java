package net.chrisrichardson.eventstore.examples.management.restaurant.queryside.web;

import io.eventuate.javaclient.spring.jdbc.EventuateJdbcEventStoreConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideRedisConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({RestaurantsQuerysideRedisConfiguration.class, EventuateJdbcEventStoreConfiguration.class})
public class AvailableRestaurantManagementServiceIntegrationTestConfiguration {
}
