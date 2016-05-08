package net.chrisrichardson.eventstore.examples.management.restaurant.commandside;

import net.chrisrichardson.eventstore.client.config.EventStoreHttpClientConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsCommandsideConfiguration.class, EventStoreHttpClientConfiguration.class, WebConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsServiceCommandsideConfiguration {
}
