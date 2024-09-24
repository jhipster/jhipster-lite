package tech.jhipster.lite.module.domain;

import java.util.Collection;
import tech.jhipster.lite.project.domain.preset.Preset;

public interface JHipsterPresetRepository {
  Collection<Preset> getPresets();
}
