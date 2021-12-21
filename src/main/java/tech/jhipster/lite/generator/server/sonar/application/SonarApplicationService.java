package tech.jhipster.lite.generator.server.sonar.application;

import org.springframework.stereotype.Component;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.sonar.domain.SonarService;

@Component
public class SonarApplicationService {

  private final SonarService sonarService;

  public SonarApplicationService(SonarService sonarService) {
    this.sonarService = sonarService;
  }

  public void init(Project project) {
    this.sonarService.init(project);
  }

  void addPropertiesPlugin(Project project) {
    this.sonarService.addPropertiesPlugin(project);
  }

  void addSonarScannerPluginManagement(Project project) {
    this.sonarService.addSonarScannerPluginManagement(project);
  }

  void addPropertiesFile(Project project) {
    this.sonarService.addPropertiesFile(project);
  }

  void addDockerCompose(Project project) {
    this.sonarService.addDockerCompose(project);
  }
}
