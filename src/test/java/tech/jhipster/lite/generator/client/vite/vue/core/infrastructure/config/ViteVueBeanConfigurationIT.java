package tech.jhipster.lite.generator.client.vite.vue.core.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.vite.vue.core.domain.ViteVueDomainService;

@IntegrationTest
class ViteVueBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("viteVueService")).isNotNull().isInstanceOf(ViteVueDomainService.class);
  }
}
