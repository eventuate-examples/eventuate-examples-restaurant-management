package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.RestaurantsViewBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({RestaurantsViewBackendConfiguration.class, WebConfiguration.class})
public class RestaurantsViewWebConfiguration {
}
