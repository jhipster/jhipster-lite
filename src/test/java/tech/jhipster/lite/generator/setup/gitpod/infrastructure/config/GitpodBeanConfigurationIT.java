package tech.jhipster.lite.generator.setup.gitpod.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.setup.gitpod.domain.GitpodDomainService;

@IntegrationTest
class GitpodBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("gitpodService")).isNotNull().isInstanceOf(GitpodDomainService.class);
  }
}
