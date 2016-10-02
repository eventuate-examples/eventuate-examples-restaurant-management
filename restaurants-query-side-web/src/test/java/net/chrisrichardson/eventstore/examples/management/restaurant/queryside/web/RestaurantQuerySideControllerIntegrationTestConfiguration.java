package net.chrisrichardson.eventstore.examples.management.restaurant.queryside.web;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.jdbc.EventuateJdbcEventStoreConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantAggregate;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantCommand;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantService;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideConfiguration.class, RestaurantsCommandsideConfiguration.class, EventuateJdbcEventStoreConfiguration.class})
@EnableAutoConfiguration
public class RestaurantQuerySideControllerIntegrationTestConfiguration {
    @Bean
    public AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(RestaurantAggregate.class, eventStore);
    }

    @Bean
    public RestaurantService restaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository) {
        return new RestaurantService(restaurantAggregateRepository);
    }
}
