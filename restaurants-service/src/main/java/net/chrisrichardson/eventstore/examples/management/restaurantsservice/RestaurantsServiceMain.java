package net.chrisrichardson.eventstore.examples.management.restaurantsservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurant.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.eventstore.examples.management.restaurantsservice.web.RestaurantsWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestaurantsWebConfiguration.class,
        EventuateDriverConfiguration.class,
        CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class RestaurantsServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(RestaurantsServiceMain.class, args);
  }
}
