package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tech.jhipster.lite.project.domain.preset.PresetName;
import tech.jhipster.lite.project.domain.resource.JHipsterPresetFile;

@TestConfiguration
public class FakedJHipsterPresetFilePropertiesConfiguration {

  @Bean
  JHipsterPresetFile jhipsterPresetFile() {
    return new JHipsterPresetFile(PresetName.from("preset.json"));
  }
}
