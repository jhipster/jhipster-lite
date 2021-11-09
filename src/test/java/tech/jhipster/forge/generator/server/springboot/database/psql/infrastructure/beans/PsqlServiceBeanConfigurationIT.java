package tech.jhipster.forge.generator.server.springboot.database.psql.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlDomainService;

@IntegrationTest
class PsqlServiceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("psqlService")).isNotNull().isInstanceOf(PsqlDomainService.class);
  }
}
