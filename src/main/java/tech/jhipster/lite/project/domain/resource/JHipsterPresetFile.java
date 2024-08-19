package tech.jhipster.lite.project.domain.resource;

import tech.jhipster.lite.project.domain.preset.PresetName;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterPresetFile(PresetName name) {
  public JHipsterPresetFile {
    Assert.notNull("name", name);
  }
}
