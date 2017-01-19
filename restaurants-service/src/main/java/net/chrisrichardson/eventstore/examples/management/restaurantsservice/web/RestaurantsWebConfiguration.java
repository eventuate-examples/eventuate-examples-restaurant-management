package net.chrisrichardson.eventstore.examples.management.restaurantsservice.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend.RestaurantsBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({RestaurantsBackendConfiguration.class, WebConfiguration.class})
public class RestaurantsWebConfiguration {
}
