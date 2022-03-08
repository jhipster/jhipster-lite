package tech.jhipster.lite.generator.client.vue.core.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.vue.core.domain.VueDomainService;

@IntegrationTest
class VueBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("vueService")).isNotNull().isInstanceOf(VueDomainService.class);
  }
}
