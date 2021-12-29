package tech.jhipster.lite.generator.server.springboot.cache.simple.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.cache.common.domain.SpringBootCacheService;
import tech.jhipster.lite.generator.server.springboot.cache.simple.domain.SpringBootCacheSimpleDomainService;
import tech.jhipster.lite.generator.server.springboot.cache.simple.domain.SpringBootCacheSimpleService;

@Configuration
public class SpringBootCacheSimpleBeanConfiguration {

  private final SpringBootCacheService springBootCacheService;

  public SpringBootCacheSimpleBeanConfiguration(SpringBootCacheService springBootCacheService) {
    this.springBootCacheService = springBootCacheService;
  }

  @Bean
  public SpringBootCacheSimpleService springBootCacheSimpleService() {
    return new SpringBootCacheSimpleDomainService(springBootCacheService);
  }
}
