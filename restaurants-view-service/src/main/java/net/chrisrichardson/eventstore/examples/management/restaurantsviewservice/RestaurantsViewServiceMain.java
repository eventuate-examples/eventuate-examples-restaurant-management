package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.web.RestaurantsViewWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsViewWebConfiguration.class,
        EventuateDriverConfiguration.class,
        CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(RestaurantsViewServiceMain.class, args);
  }
}
