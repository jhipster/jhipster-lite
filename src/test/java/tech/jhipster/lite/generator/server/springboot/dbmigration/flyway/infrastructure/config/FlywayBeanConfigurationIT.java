package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain.FlywayService;

@IntegrationTest
class FlywayBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("flywayService")).isNotNull().isInstanceOf(FlywayService.class);
  }
}
