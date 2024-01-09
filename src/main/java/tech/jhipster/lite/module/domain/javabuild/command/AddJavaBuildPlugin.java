package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPluginConfiguration;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPluginExecutions;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;

public interface AddJavaBuildPlugin {
  JavaBuildPlugin plugin();

  default Optional<VersionSlug> versionSlug() {
    return plugin().versionSlug();
  }

  default Optional<JavaBuildPluginConfiguration> configuration() {
    return plugin().configuration();
  }

  default Optional<JavaBuildPluginExecutions> executions() {
    return plugin().executions();
  }

  default DependencyId dependencyId() {
    return plugin().dependencyId();
  }

  Optional<JavaDependencyVersion> pluginVersion();
}
