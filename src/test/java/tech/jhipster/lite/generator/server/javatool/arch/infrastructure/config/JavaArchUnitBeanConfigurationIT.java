package tech.jhipster.lite.generator.server.javatool.arch.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.javatool.arch.domain.JavaArchUnitDomainService;

@IntegrationTest
class JavaArchUnitBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("javaArchUnitService")).isNotNull().isInstanceOf(JavaArchUnitDomainService.class);
  }
}
