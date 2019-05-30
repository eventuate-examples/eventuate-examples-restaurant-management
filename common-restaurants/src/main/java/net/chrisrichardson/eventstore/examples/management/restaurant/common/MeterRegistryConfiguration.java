package net.chrisrichardson.eventstore.examples.management.restaurant.common;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterRegistryConfiguration {
  @Bean
  public MeterRegistry meterRegistry() {
    return new SimpleMeterRegistry();
  }
}
