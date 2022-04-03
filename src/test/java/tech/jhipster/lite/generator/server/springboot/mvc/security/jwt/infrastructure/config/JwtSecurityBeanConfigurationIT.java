package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityDomainService;

@IntegrationTest
class JwtSecurityBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("jwtSecurityService")).isNotNull().isInstanceOf(JwtSecurityDomainService.class);
  }
}
