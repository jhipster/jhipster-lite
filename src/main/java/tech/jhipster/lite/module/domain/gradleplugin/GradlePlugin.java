package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;

public sealed interface GradlePlugin permits GradleCommunityPlugin, GradleCorePlugin, GradleProfilePlugin {
  Optional<GradlePluginConfiguration> configuration();
}
