package tech.jhipster.lite.generator.client.angular.common.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface AngularCommonService {
  void addImports(Project project, String filePath, String imports);

  void addConstants(Project project, String filePath, String constants);

  void addDeclarations(Project project, String filePath, String declarations);

  void addProviders(Project project, String filePath, String providers);

  void addEnvVariables(Project project, String envFilePath, String values);

  void prependHtml(Project project, String filePath, String html, String inHtmlTagRegex);
}
