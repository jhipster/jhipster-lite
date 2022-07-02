package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain.SpringdocDomainService;

@IntegrationTest
class SpringdocBeanConfigurationIT {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springdocService")).isNotNull().isInstanceOf(SpringdocDomainService.class);
  }
}
