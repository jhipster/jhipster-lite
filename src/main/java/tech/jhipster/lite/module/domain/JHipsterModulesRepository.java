package tech.jhipster.lite.module.domain;

import tech.jhipster.lite.module.domain.resource.JHipsterLandscape;
import tech.jhipster.lite.module.domain.resource.JHipsterModulesResources;

public interface JHipsterModulesRepository {
  JHipsterModulesResources resources();

  JHipsterLandscape landscape();

  void apply(JHipsterModuleChanges changes);

  void applied(JHipsterModuleApplied moduleApplied);
}
