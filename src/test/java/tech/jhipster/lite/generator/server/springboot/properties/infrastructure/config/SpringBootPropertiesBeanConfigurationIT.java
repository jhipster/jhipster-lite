package tech.jhipster.lite.generator.server.springboot.properties.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesDomainService;

@IntegrationTest
class SpringBootPropertiesBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootPropertiesService")).isNotNull().isInstanceOf(SpringBootPropertiesDomainService.class);
  }
}
