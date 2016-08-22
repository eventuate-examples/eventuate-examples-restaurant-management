package net.chrisrichardson.eventstore.examples.management.restaurant.commandside.web;

import io.eventuate.javaclient.spring.jdbc.EventuateJdbcEventStoreConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsCommandsideConfiguration.class, EventuateJdbcEventStoreConfiguration.class, WebConfiguration.class})
@EnableAutoConfiguration
public class RestaurantControllerIntegrationTestConfiguration {

}
