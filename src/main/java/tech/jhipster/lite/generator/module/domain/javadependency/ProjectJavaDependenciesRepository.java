package tech.jhipster.lite.generator.module.domain.javadependency;

import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;

public interface ProjectJavaDependenciesRepository {
  ProjectJavaDependencies get(JHipsterProjectFolder folder);
}
