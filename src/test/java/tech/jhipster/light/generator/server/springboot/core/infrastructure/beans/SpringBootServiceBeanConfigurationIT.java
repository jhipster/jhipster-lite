package tech.jhipster.light.generator.server.springboot.core.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.server.springboot.core.domain.SpringBootDomainService;

@IntegrationTest
class SpringBootServiceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootService")).isNotNull().isInstanceOf(SpringBootDomainService.class);
  }
}
