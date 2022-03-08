package tech.jhipster.lite.generator.githubactions.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.githubactions.domain.GithubActionsDomainService;
import tech.jhipster.lite.generator.init.domain.InitDomainService;

@IntegrationTest
class GithubActionsBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("githubActionsService")).isNotNull().isInstanceOf(GithubActionsDomainService.class);
  }
}
