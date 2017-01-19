package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice;

import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.RestaurantsQuerysideRedisConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({RestaurantsQuerysideRedisConfiguration.class,
        EmbeddedTestAggregateStoreConfiguration.class})
public class AvailableRestaurantManagementServiceIntegrationTestConfiguration {
}
