package tech.jhipster.lite.generator.buildtool.gradle.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleDomainService;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class GradleBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("gradleService")).isNotNull().isInstanceOf(GradleDomainService.class);
  }
}
