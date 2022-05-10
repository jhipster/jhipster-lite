package tech.jhipster.lite.generator.client.angular.common.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.angular.common.domain.AngularCommonService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class AngularCommonApplicationService {

  private final AngularCommonService angularCommonService;

  public AngularCommonApplicationService(AngularCommonService angularCommonService) {
    this.angularCommonService = angularCommonService;
  }

  public void addImports(Project project, String filePath, String imports) {
    angularCommonService.addImports(project, filePath, imports);
  }

  public void addConstants(Project project, String filePath, String constants) {
    angularCommonService.addConstants(project, filePath, constants);
  }

  public void addDeclarations(Project project, String filePath, String declarations) {
    angularCommonService.addDeclarations(project, filePath, declarations);
  }

  public void addProviders(Project project, String filePath, String providers) {
    angularCommonService.addProviders(project, filePath, providers);
  }

  public void addEnvVariables(Project project, String envFilePath, String values) {
    angularCommonService.addEnvVariables(project, envFilePath, values);
  }

  public void prependHtml(Project project, String filePath, String html, String inHtmlTagRegex) {
    angularCommonService.prependHtml(project, filePath, html, inHtmlTagRegex);
  }
}
