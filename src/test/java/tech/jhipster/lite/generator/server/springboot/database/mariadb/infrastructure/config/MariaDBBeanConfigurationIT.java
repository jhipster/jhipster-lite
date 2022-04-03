package tech.jhipster.lite.generator.server.springboot.database.mariadb.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.database.mariadb.domain.MariaDBDomainService;

@IntegrationTest
class MariaDBBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("mariadbService")).isNotNull().isInstanceOf(MariaDBDomainService.class);
  }
}
