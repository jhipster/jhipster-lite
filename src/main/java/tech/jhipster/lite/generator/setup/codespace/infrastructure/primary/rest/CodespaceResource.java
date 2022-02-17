package tech.jhipster.lite.generator.setup.codespace.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.setup.codespace.application.CodespaceApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/setup")
@Tag(name = "Setup")
class CodespaceResource {

  private final CodespaceApplicationService codespaceApplicationService;

  public CodespaceResource(CodespaceApplicationService codespaceApplicationService) {
    this.codespaceApplicationService = codespaceApplicationService;
  }

  @Operation(summary = "Init", description = "Init Maven project with pom.xml and wrapper")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing Maven project")
  @PostMapping("/codespaces")
  @GeneratorStep(id = "codespace")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    codespaceApplicationService.init(project);
  }
}
