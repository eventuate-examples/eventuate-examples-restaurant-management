package net.chrisrichardson.eventstore.examples.management.restaurant.queryside;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.redis.RestaurantQuerySideRedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideRedisConfiguration.class})
@ComponentScan
@EnableEventHandlers
public class RestaurantsQuerysideConfiguration {


    @Bean
    public RestaurantQuerySideWorkflow restaurantQueryWorkflow(RestaurantQuerySideRedisService restaurantQuerySideRedisService) {
        return new RestaurantQuerySideWorkflow(restaurantQuerySideRedisService);
    }
}
