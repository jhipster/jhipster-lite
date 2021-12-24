package tech.jhipster.lite.generator.server.springboot.devtools.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsDomainService;

@IntegrationTest
class DevToolsBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("devToolsService")).isNotNull().isInstanceOf(DevToolsDomainService.class);
  }
}
