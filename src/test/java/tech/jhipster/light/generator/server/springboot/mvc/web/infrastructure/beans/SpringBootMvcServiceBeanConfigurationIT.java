package tech.jhipster.light.generator.server.springboot.mvc.web.infrastructure.beans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.server.springboot.mvc.web.domain.SpringBootMvcDomainService;

@IntegrationTest
class SpringBootMvcServiceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootMvcService")).isNotNull().isInstanceOf(SpringBootMvcDomainService.class);
  }
}
