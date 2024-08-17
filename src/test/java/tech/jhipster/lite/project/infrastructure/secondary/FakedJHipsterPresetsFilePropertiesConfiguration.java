package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tech.jhipster.lite.project.domain.resource.JHipsterPresetsFile;

@TestConfiguration
public class FakedJHipsterPresetsFilePropertiesConfiguration {

  @Bean
  JHipsterPresetsFile jhipsterPresetsFile() {
    return new JHipsterPresetsFile("preset.json");
  }
}
