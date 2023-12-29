package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPluginConfiguration;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPluginExecutions;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;

public interface AddJavaBuildPlugin {
  JavaBuildPlugin javaBuildPlugin();

  default Optional<VersionSlug> versionSlug() {
    return javaBuildPlugin().versionSlug();
  }

  default Optional<JavaBuildPluginConfiguration> configuration() {
    return javaBuildPlugin().configuration();
  }

  default Optional<JavaBuildPluginExecutions> executions() {
    return javaBuildPlugin().executions();
  }

  default DependencyId dependencyId() {
    return javaBuildPlugin().dependencyId();
  }
}
