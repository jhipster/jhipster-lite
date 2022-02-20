package tech.jhipster.lite.generator.client.vite.react.core.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.vite.react.core.domain.ViteReactDomainService;

@IntegrationTest
class ViteReactBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("viteReactService")).isNotNull().isInstanceOf(ViteReactDomainService.class);
  }
}
