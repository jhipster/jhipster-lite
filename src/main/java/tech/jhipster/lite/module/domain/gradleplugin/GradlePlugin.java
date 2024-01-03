package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;

public sealed interface GradlePlugin permits GradleCorePlugin {
  GradlePluginId id();

  Optional<GradlePluginConfiguration> configuration();
}
