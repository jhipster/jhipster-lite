package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2SecurityDomainService;

@IntegrationTest
class OAuth2SecurityBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("oauth2SecurityService")).isNotNull().isInstanceOf(OAuth2SecurityDomainService.class);
  }
}
