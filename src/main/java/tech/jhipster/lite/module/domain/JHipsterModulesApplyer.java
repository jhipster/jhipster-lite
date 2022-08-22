package tech.jhipster.lite.module.domain;

import java.time.Instant;
import java.util.function.Function;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.git.domain.GitRepository;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javadependency.CurrentJavaDependenciesVersions;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesCurrentVersionsRepository;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

public class JHipsterModulesApplyer {

  private final JHipsterModulesRepository modules;
  private final JavaDependenciesCurrentVersionsRepository currentVersions;
  private final ProjectJavaDependenciesRepository projectDependencies;
  private final GitRepository git;

  public JHipsterModulesApplyer(
    JHipsterModulesRepository modules,
    JavaDependenciesCurrentVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies,
    GitRepository git
  ) {
    this.modules = modules;
    this.currentVersions = currentVersions;
    this.projectDependencies = projectDependencies;
    this.git = git;
  }

  public void apply(JHipsterModulesToApply modulesToApply) {
    Assert.notNull("modulesToApply", modulesToApply);

    modules.landscape().sort(modulesToApply.modules()).stream().map(toModuleToApply(modulesToApply)).forEach(this::apply);
  }

  private Function<JHipsterModuleSlug, JHipsterModuleToApply> toModuleToApply(JHipsterModulesToApply modulesToApply) {
    return slug ->
      new JHipsterModuleToApply(modulesToApply.properties(), slug, modules.resources().build(slug, modulesToApply.properties()));
  }

  public void apply(JHipsterModuleToApply moduleToApply) {
    Assert.notNull("moduleToApply", moduleToApply);

    CurrentJavaDependenciesVersions versions = currentVersions.get();

    JHipsterModule module = moduleToApply.module();
    JHipsterModuleChanges changes = JHipsterModuleChanges
      .builder()
      .projectFolder(module.projectFolder())
      .indentation(module.indentation())
      .filesToAdd(module.templatedFiles())
      .filesToDelete(module.filesToDelete())
      .mandatoryReplacements(module.mandatoryReplacements())
      .optionalReplacements(module.optionalReplacements())
      .javaBuildCommands(buildDependenciesChanges(versions, module).merge(buildPluginsChanges(versions, module)))
      .packageJson(module.packageJson())
      .preActions(module.preActions())
      .postActions(module.postActions())
      .springProperties(module.springProperties());

    modules.apply(changes);

    modules.applied(new JHipsterModuleApplied(moduleToApply.properties(), moduleToApply.slug(), Instant.now()));

    commitIfNeeded(moduleToApply);
  }

  private void commitIfNeeded(JHipsterModuleToApply moduleToApply) {
    if (moduleToApply.commitNeeded()) {
      JHipsterProjectFolder projectFolder = moduleToApply.module().projectFolder();

      git.init(projectFolder);
      git.commitAll(projectFolder, commitMessage(moduleToApply));
    }
  }

  private String commitMessage(JHipsterModuleToApply moduleToApply) {
    return new StringBuilder().append("Apply ").append(moduleToApply.slug().get()).append(" module").toString();
  }

  private JavaBuildCommands buildDependenciesChanges(CurrentJavaDependenciesVersions versions, JHipsterModule module) {
    return module.javaDependencies().buildChanges(versions, projectDependencies.get(module.projectFolder()));
  }

  private JavaBuildCommands buildPluginsChanges(CurrentJavaDependenciesVersions versions, JHipsterModule module) {
    return module.javaBuildPlugins().buildChanges(versions);
  }
}
