package tech.jhipster.lite.generator.setup.codespace.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.setup.codespace.domain.CodespaceService;

@Service
public class CodespaceApplicationService {

  private final CodespaceService codespaceService;

  public CodespaceApplicationService(CodespaceService codespaceService) {
    this.codespaceService = codespaceService;
  }

  public void addJSONcontainer(Project project) {
    codespaceService.addJSON(project);
  }

  public void addDockerfilecontainer(Project project) {
    codespaceService.addDocker(project);
  }

  public void init(Project project) {
    codespaceService.init(project);
  }
}
