package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jhlite-preset-file")
class JHipsterPresetFileProperties {

  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
