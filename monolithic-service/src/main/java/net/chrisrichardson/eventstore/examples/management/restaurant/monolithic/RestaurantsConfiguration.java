package net.chrisrichardson.eventstore.examples.management.restaurant.monolithic;

import io.eventuate.javaclient.spring.httpstomp.EventuateHttpStompClientConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantsCommandsideConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.RestaurantsQuerysideConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsQuerysideConfiguration.class, RestaurantsCommandsideConfiguration.class, EventuateHttpStompClientConfiguration.class, WebConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsConfiguration {
}
