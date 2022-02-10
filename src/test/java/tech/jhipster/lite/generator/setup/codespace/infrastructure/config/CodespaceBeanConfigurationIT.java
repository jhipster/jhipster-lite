package tech.jhipster.lite.generator.setup.codespace.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.setup.codespace.domain.CodespaceDomainService;

@IntegrationTest
public class CodespaceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("CodespaceService")).isNotNull().isInstanceOf(CodespaceDomainService.class);
  }
}
