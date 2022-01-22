package tech.jhipster.lite.technical.infrastructure.primary.global;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfiguration {

  @Bean
  public Clock getClock() {
    return Clock.systemDefaultZone();
  }
}
