package tech.jhipster.lite.generator.setup.codespaces.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.setup.codespaces.domain.CodespacesDomainService;

@IntegrationTest
class CodespacesBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("codespacesService")).isNotNull().isInstanceOf(CodespacesDomainService.class);
  }
}
