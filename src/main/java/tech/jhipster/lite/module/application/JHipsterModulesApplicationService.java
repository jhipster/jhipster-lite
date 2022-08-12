package tech.jhipster.lite.module.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.git.domain.GitRepository;
import tech.jhipster.lite.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.module.domain.JHipsterModulesApplyer;
import tech.jhipster.lite.module.domain.JHipsterModulesRepository;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesCurrentVersionsRepository;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscape;
import tech.jhipster.lite.module.domain.resource.JHipsterModulesResources;

@Service
public class JHipsterModulesApplicationService {

  private final JHipsterModulesRepository modules;
  private final JHipsterModulesApplyer applyer;

  public JHipsterModulesApplicationService(
    JHipsterModulesRepository modules,
    JavaDependenciesCurrentVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies,
    GitRepository git
  ) {
    this.modules = modules;

    applyer = new JHipsterModulesApplyer(modules, currentVersions, projectDependencies, git);
  }

  public void apply(JHipsterModuleToApply module) {
    applyer.apply(module);
  }

  public JHipsterModulesResources resources() {
    return modules.resources();
  }

  public JHipsterLandscape landscape() {
    return modules.landscape();
  }
}
