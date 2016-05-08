package net.chrisrichardson.eventstore.examples.management.restaurant.commandside.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import net.chrisrichardson.eventstore.jdbc.config.JdbcEventStoreConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsCommandsideConfiguration.class, JdbcEventStoreConfiguration.class, WebConfiguration.class})
public class RestaurantControllerIntegrationTestConfiguration {

}
