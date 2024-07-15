package tech.jhipster.lite.project.domain.preset;

import java.util.Collection;
import tech.jhipster.lite.project.domain.ModuleSlug;
import tech.jhipster.lite.shared.error.domain.Assert;

public record Preset(PresetName name, Collection<ModuleSlug> modules) {
  public Preset {
    Assert.notNull("name", name);
    Assert.notEmpty("modules", modules);
  }
}
