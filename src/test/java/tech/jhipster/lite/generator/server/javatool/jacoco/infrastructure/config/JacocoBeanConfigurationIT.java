package tech.jhipster.lite.generator.server.javatool.jacoco.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.javatool.jacoco.domain.JacocoDomainService;

@IntegrationTest
class JacocoBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("jacocoService")).isNotNull().isInstanceOf(JacocoDomainService.class);
  }
}
