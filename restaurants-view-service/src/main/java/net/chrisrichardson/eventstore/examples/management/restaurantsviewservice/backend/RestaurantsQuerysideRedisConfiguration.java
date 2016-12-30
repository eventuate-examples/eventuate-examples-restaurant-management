package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis.RestaurantQuerySideRedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RestaurantsQuerysideRedisConfiguration {

  @Bean
  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(factory);
    return template;
  }

  @Bean
  public RedisTemplate<String, RestaurantInfo> restaurantTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, RestaurantInfo> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setDefaultSerializer(new StringRedisSerializer());
    template.setValueSerializer(new JsonRedisRestaurantInfoSerializer());
    return template;
  }

  @Bean
  public RestaurantQuerySideRedisService restaurantInfoUpdateService(RedisTemplate<String, RestaurantInfo> restaurantTemplate, StringRedisTemplate stringRedisTemplat) {
    return new RestaurantQuerySideRedisService(stringRedisTemplat, restaurantTemplate);
  }
}
