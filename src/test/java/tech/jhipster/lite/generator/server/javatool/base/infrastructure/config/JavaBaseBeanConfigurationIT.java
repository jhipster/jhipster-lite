package tech.jhipster.lite.generator.server.javatool.base.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.javatool.base.domain.JavaBaseDomainService;

@IntegrationTest
class JavaBaseBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("javaBaseService")).isNotNull().isInstanceOf(JavaBaseDomainService.class);
  }
}
