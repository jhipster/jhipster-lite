package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.project.domain.resource.JHipsterPresetsFile;

@Configuration
@EnableConfigurationProperties(JHipsterPresetsFileProperties.class)
class JHipsterPresetsFilePropertiesConfiguration {

  @Bean
  JHipsterPresetsFile jhipsterPresetsFile() {
    return new JHipsterPresetsFile(new JHipsterPresetsFileProperties().getName());
  }
}
