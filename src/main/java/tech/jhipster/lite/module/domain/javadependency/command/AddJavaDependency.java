package tech.jhipster.lite.module.domain.javadependency.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.module.domain.javadependency.GroupId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyType;
import tech.jhipster.lite.module.domain.javadependency.VersionSlug;

public interface AddJavaDependency {
  JavaDependency dependency();

  default GroupId groupId() {
    return dependency().groupId();
  }

  default ArtifactId artifactId() {
    return dependency().artifactId();
  }

  default Optional<VersionSlug> version() {
    return dependency().version();
  }

  default JavaDependencyScope scope() {
    return dependency().scope();
  }

  default boolean optional() {
    return dependency().optional();
  }

  default DependencyId dependencyId() {
    return dependency().id();
  }

  default Optional<JavaDependencyType> dependencyType() {
    return dependency().type();
  }
}
