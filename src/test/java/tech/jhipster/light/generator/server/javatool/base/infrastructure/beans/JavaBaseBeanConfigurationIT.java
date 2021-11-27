package tech.jhipster.light.generator.server.javatool.base.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.server.javatool.base.domain.JavaBaseDomainService;

@IntegrationTest
class JavaBaseBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("javaBaseService")).isNotNull().isInstanceOf(JavaBaseDomainService.class);
  }
}
