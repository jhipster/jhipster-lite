package tech.jhipster.lite.generator.server.springboot.logging.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingDomainService;

@IntegrationTest
class SpringBootLoggingBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootLoggingService")).isNotNull().isInstanceOf(SpringBootLoggingDomainService.class);
  }
}
