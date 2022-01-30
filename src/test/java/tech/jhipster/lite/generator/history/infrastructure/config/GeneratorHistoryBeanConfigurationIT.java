package tech.jhipster.lite.generator.history.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryService;

@IntegrationTest
class GeneratorHistoryBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("historyService")).isNotNull().isInstanceOf(GeneratorHistoryService.class);
  }
}
