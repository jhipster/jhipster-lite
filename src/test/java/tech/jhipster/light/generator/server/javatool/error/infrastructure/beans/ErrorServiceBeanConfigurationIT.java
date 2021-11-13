package tech.jhipster.light.generator.server.javatool.error.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.server.javatool.error.domain.ErrorDomainService;

@IntegrationTest
class ErrorServiceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("errorService")).isNotNull().isInstanceOf(ErrorDomainService.class);
  }
}
