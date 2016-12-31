package net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RestaurantsBackendConfiguration {

    @Bean
    public AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(RestaurantAggregate.class, eventStore);
    }

    @Bean
    public RestaurantService restaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> restaurantAggregateRepository) {
        return new RestaurantService(restaurantAggregateRepository);
    }
}
