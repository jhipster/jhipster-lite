package tech.jhipster.lite.generator.history.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryDomainService;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryRepository;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryService;

@Configuration
public class GeneratorHistoryBeanConfiguration {

  private final GeneratorHistoryRepository generatorHistoryLocalRepository;

  public GeneratorHistoryBeanConfiguration(GeneratorHistoryRepository generatorHistoryLocalRepository) {
    this.generatorHistoryLocalRepository = generatorHistoryLocalRepository;
  }

  @Bean
  public GeneratorHistoryService generatorHistoryService() {
    return new GeneratorHistoryDomainService(generatorHistoryLocalRepository);
  }
}
