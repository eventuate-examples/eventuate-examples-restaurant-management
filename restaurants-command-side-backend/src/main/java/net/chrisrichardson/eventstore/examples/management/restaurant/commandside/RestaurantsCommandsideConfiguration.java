package net.chrisrichardson.eventstore.examples.management.restaurant.commandside;

import net.chrisrichardson.eventstore.EventStore;
import net.chrisrichardson.eventstore.repository.AggregateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RestaurantsCommandsideConfiguration {

    @Bean
    public AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository(EventStore eventStore) {
        return new AggregateRepository<>(RestaurantAggregate.class, eventStore);
    }

    @Bean
    public RestaurantService restaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository) {
        return new RestaurantService(restaurantAggregateRepository);
    }
}
