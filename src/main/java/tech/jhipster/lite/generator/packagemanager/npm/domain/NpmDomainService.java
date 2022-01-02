package tech.jhipster.lite.generator.packagemanager.npm.domain;

import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.Project;

public class NpmDomainService implements NpmService {

  private final CommandRepository commandRepository;

  public NpmDomainService(CommandRepository commandRepository) {
    this.commandRepository = commandRepository;
  }

  @Override
  public void addDependency(Project project, String dependency, String version) {
    // Need to be implemented
  }

  @Override
  public void addDevDependency(Project project, String dependency, String version) {
    // Need to be implemented
  }

  @Override
  public void addScript(Project project, String name, String cmd) {
    // Need to be implemented
  }

  @Override
  public void install(Project project) {
    this.commandRepository.npmInstall(project);
  }

  @Override
  public void prettify(Project project) {
    this.commandRepository.npmPrettierFormat(project);
  }
}
