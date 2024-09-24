package tech.jhipster.lite.project.infrastructure.secondary;

import java.util.Collection;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.preset.PresetName;
import tech.jhipster.lite.project.domain.ModulesSlugs;

record PersistedPreset(String name, Collection<String> modules) {
  public Preset toDomain() {
    return new Preset(PresetName.from(name), ModulesSlugs.from(modules));
  }
}
