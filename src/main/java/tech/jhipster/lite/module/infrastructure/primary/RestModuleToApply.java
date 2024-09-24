package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.lite.project.domain.ModuleSlug;

@Schema(name = "ModuleToApply", description = "Information for a module to apply")
record RestModuleToApply(@Schema(description = "Slug of the module to apply") String slug) {
  public static RestModuleToApply from(ModuleSlug moduleSlug) {
    return new RestModuleToApply(moduleSlug.get());
  }
}
