package tech.jhipster.lite.generator.module.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleChanges;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesRepository;

@Repository
class FileSystemJHipsterModulesRepository implements JHipsterModulesRepository {

  private final FileSystemJHipsterModuleFiles files;
  private final FileSystemJavaDependenciesCommandsHandler javaDependencies;

  public FileSystemJHipsterModulesRepository(
    FileSystemJHipsterModuleFiles files,
    FileSystemJavaDependenciesCommandsHandler javaDependencies
  ) {
    this.files = files;
    this.javaDependencies = javaDependencies;
  }

  @Override
  public void apply(JHipsterModuleChanges changes) {
    Assert.notNull("changes", changes);

    changes.preActions().apply();

    files.create(changes.projectFolder(), changes.files());
    javaDependencies.handle(changes.indentation(), changes.projectFolder(), changes.javaDependenciesCommands());
  }
}
