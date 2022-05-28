package tech.jhipster.lite.generator.module.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependenciesCurrentVersionsRepository;
import tech.jhipster.lite.generator.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.generator.module.domain.javadependency.command.JavaDependenciesCommands;

public class JHipsterModulesDomainService {

  private final JHipsterModulesRepository modules;
  private final JavaDependenciesCurrentVersionsRepository currentVersions;
  private final ProjectJavaDependenciesRepository projectDependencies;

  public JHipsterModulesDomainService(
    JHipsterModulesRepository modules,
    JavaDependenciesCurrentVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies
  ) {
    this.modules = modules;
    this.currentVersions = currentVersions;
    this.projectDependencies = projectDependencies;
  }

  public void apply(Indentation indentation, JHipsterModule module) {
    Assert.notNull("indentation", indentation);
    Assert.notNull("module", module);

    JHipsterModuleChanges changes = JHipsterModuleChanges
      .builder()
      .projectFolder(module.projectFolder())
      .indentation(indentation)
      .files(module.templatedFiles())
      .replacements(module.replacements())
      .javaDependencies(buildDependenciesChanges(module))
      .preActions(module.preActions());

    modules.apply(changes);
  }

  private JavaDependenciesCommands buildDependenciesChanges(JHipsterModule module) {
    return module.javaDependencies().buildChanges(currentVersions.get(), projectDependencies.get(module.projectFolder()));
  }
}
