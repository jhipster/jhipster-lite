package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import tech.jhipster.lite.module.domain.preset.Preset;

@Schema(name = "Preset", description = "Information on specific configuration")
record RestPreset(
  @Schema(description = "Name of specific configuration") String name,
  @Schema(description = "Modules to apply of this specific configuration") Collection<RestModuleToApply> modules
) {
  public static RestPreset from(Preset preset) {
    return new RestPreset(preset.name().name(), preset.modules().modules().stream().map(RestModuleToApply::from).toList());
  }
}
