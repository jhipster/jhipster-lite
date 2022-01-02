package tech.jhipster.lite.generator.packagemanager.npm.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmDomainService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.CommandRepository;

@Configuration
public class NpmBeanConfiguration {

  private final CommandRepository commandRepository;

  public NpmBeanConfiguration(CommandRepository commandRepository) {
    this.commandRepository = commandRepository;
  }

  @Bean
  public NpmService npmService() {
    return new NpmDomainService(commandRepository);
  }
}
