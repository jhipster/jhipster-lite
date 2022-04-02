package tech.jhipster.lite.generator.client.common.cypress.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.common.cypress.domain.CypressDomainService;

@IntegrationTest
class CypressBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("cypressService")).isNotNull().isInstanceOf(CypressDomainService.class);
  }
}
