package net.chrisrichardson.eventstore.examples.management.restaurant.queryside.web;

import net.chrisrichardson.eventstore.EventStore;
import net.chrisrichardson.eventstore.client.config.EventStoreHttpClientConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantAggregate;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantCommand;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantService;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideConfiguration;
import net.chrisrichardson.eventstore.jdbc.config.JdbcEventStoreConfiguration;
import net.chrisrichardson.eventstore.repository.AggregateRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideConfiguration.class, RestaurantsCommandsideConfiguration.class, EventStoreHttpClientConfiguration.class})
@EnableAutoConfiguration
public class RestaurantQuerySideControllerIntegrationTestConfiguration {
    @Bean
    public AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository(EventStore eventStore) {
        return new AggregateRepository<>(RestaurantAggregate.class, eventStore);
    }

    @Bean
    public RestaurantService restaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository) {
        return new RestaurantService(restaurantAggregateRepository);
    }
}
