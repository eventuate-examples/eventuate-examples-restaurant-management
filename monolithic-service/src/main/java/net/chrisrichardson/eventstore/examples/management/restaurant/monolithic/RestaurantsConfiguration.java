package net.chrisrichardson.eventstore.examples.management.restaurant.monolithic;

import net.chrisrichardson.eventstore.client.config.EventStoreHttpClientConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideConfiguration.class, RestaurantsCommandsideConfiguration.class, EventStoreHttpClientConfiguration.class, WebConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsConfiguration {
}
