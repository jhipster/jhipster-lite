package tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;

@IntegrationTest
class LiquibaseBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("liquibaseService")).isNotNull().isInstanceOf(LiquibaseDomainService.class);
  }
}
