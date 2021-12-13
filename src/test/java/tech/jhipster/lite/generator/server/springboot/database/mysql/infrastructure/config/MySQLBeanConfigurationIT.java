package tech.jhipster.lite.generator.server.springboot.database.mysql.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQLDomainService;

@IntegrationTest
class MySQLBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("mysqlService")).isNotNull().isInstanceOf(MySQLDomainService.class);
  }
}
