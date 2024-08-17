package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.project.domain.resource.JHipsterPresetFile;

@Configuration
@EnableConfigurationProperties(JHipsterPresetFileProperties.class)
class JHipsterPresetFilePropertiesConfiguration {

  @Bean
  JHipsterPresetFile jhipsterPresetFile() {
    return new JHipsterPresetFile(new JHipsterPresetFileProperties().getName());
  }
}
