package net.chrisrichardson.eventstore.examples.management.restaurant.commandside;

import io.eventuate.javaclient.spring.httpstomp.EventuateHttpStompClientConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonweb.WebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsCommandsideConfiguration.class, EventuateHttpStompClientConfiguration.class, WebConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsServiceCommandsideConfiguration {
}
