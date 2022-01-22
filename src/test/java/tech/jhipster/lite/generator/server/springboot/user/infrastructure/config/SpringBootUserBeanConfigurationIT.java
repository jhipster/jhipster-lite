package tech.jhipster.lite.generator.server.springboot.user.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@IntegrationTest
class SpringBootUserBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootUserService")).isNotNull().isInstanceOf(SpringBootUserService.class);
  }
}
