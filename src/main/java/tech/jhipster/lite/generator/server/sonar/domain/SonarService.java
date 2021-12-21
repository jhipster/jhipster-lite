package tech.jhipster.lite.generator.server.sonar.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SonarService {
  void init(Project project);

  void addPropertiesPlugin(Project project);

  void addSonarScannerPluginManagement(Project project);

  void addPropertiesFile(Project project);

  void addDockerCompose(Project project);
}
