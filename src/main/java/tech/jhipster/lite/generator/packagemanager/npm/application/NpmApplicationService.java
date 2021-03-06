package tech.jhipster.lite.generator.packagemanager.npm.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmRepository;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class NpmApplicationService {

  private final NpmService npmService;
  private final NpmRepository npmRepository;

  public NpmApplicationService(NpmService npmService, NpmRepository npmRepository) {
    this.npmService = npmService;
    this.npmRepository = npmRepository;
  }

  public void addDependency(Project project, String dependency, String version) {
    this.npmService.addDependency(project, dependency, version);
  }

  public void addDevDependency(Project project, String dependency, String version) {
    this.npmService.addDevDependency(project, dependency, version);
  }

  public void addScript(Project project, String name, String cmd) {
    this.npmService.addScript(project, name, cmd);
  }

  public void install(Project project) {
    npmRepository.npmInstall(project);
  }

  public void prettify(Project project) {
    npmRepository.npmPrettierFormat(project);
  }
}
