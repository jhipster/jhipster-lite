package tech.jhipster.light.generator.buildtool.maven.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.buildtool.maven.domain.MavenDomainService;

@IntegrationTest
class MavenBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("mavenService")).isNotNull().isInstanceOf(MavenDomainService.class);
  }
}
