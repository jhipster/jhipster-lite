package tech.jhipster.lite.generator.module.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.domain.Indentation;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesDomainService;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesRepository;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependenciesCurrentVersionsRepository;
import tech.jhipster.lite.generator.module.domain.javadependency.ProjectJavaDependenciesRepository;

@Service
public class JHipsterModulesApplicationService {

  private final JHipsterModulesDomainService modules;

  public JHipsterModulesApplicationService(
    JHipsterModulesRepository modulesRepository,
    JavaDependenciesCurrentVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies
  ) {
    modules = new JHipsterModulesDomainService(modulesRepository, currentVersions, projectDependencies);
  }

  public void apply(Indentation indentation, JHipsterModule module) {
    modules.apply(indentation, module);
  }
}
