package tech.jhipster.lite.generator.client.react.cypress.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.react.cypress.domain.CypressReactDomainService;

@IntegrationTest
class CypressReactBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("cypressReactService")).isNotNull().isInstanceOf(CypressReactDomainService.class);
  }
}
