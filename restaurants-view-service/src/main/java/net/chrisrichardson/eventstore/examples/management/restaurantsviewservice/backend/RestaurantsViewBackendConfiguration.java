package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis.RestaurantQuerySideRedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideRedisConfiguration.class})
@ComponentScan
@EnableEventHandlers
public class RestaurantsViewBackendConfiguration {


    @Bean
    public RestaurantViewEventSubscriber restaurantQueryWorkflow(RestaurantQuerySideRedisService restaurantQuerySideRedisService) {
        return new RestaurantViewEventSubscriber(restaurantQuerySideRedisService);
    }
}
