package tech.jhipster.lite.generator.ci.github.actions.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.ci.github.actions.domain.GitHubActionsDomainService;

@IntegrationTest
class GitHubActionsBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("gitHubActionsService")).isNotNull().isInstanceOf(GitHubActionsDomainService.class);
  }
}
