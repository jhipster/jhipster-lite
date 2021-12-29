package tech.jhipster.lite.generator.server.springboot.cache.simple.infrastructure.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.server.springboot.cache.simple.domain.SpringBootCacheSimpleDomainService;

@IntegrationTest
class SpringBootCacheSimpleConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("springBootCacheSimpleService"))
      .isNotNull()
      .isInstanceOf(SpringBootCacheSimpleDomainService.class);
  }
}
