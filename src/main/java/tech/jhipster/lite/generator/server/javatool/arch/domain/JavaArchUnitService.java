package tech.jhipster.lite.generator.server.javatool.arch.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface JavaArchUnitService {
  void init(Project project);
  void addHexagobalArchJavaFiles(Project project);
  void addArchUnitMavenPlugin(Project project);
  void addLoggerInConfiguration(Project project);
}
