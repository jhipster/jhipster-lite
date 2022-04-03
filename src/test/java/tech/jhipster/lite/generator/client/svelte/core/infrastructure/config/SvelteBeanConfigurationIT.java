package tech.jhipster.lite.generator.client.svelte.core.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.svelte.core.domain.SvelteDomainService;

@IntegrationTest
class SvelteBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("svelteService")).isNotNull().isInstanceOf(SvelteDomainService.class);
  }
}
