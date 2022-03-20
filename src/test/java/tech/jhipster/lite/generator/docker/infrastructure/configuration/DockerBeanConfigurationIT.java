package tech.jhipster.lite.generator.docker.infrastructure.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.docker.domain.DockerDomainService;

@IntegrationTest
class DockerBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("dockerService")).isNotNull().isInstanceOf(DockerDomainService.class);
  }
}
