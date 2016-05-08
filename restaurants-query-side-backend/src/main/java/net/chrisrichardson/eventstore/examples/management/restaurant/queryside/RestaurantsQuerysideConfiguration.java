package net.chrisrichardson.eventstore.examples.management.restaurant.queryside;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.redis.RestaurantQuerySideRedisService;
import net.chrisrichardson.eventstore.javaapi.consumer.EnableJavaEventHandlers;
import net.chrisrichardson.eventstore.subscriptions.config.EventStoreSubscriptionsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Import({EventStoreSubscriptionsConfiguration.class, RestaurantsQuerysideRedisConfiguration.class})
@ComponentScan
@EnableJavaEventHandlers
public class RestaurantsQuerysideConfiguration {


    @Bean
    public RestaurantQuerySideWorkflow restaurantQueryWorkflow(RestaurantQuerySideRedisService restaurantQuerySideRedisService) {
        return new RestaurantQuerySideWorkflow(restaurantQuerySideRedisService);
    }
}
