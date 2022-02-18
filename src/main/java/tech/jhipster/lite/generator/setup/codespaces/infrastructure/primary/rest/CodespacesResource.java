package tech.jhipster.lite.generator.setup.codespaces.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.setup.codespaces.application.CodespacesApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/setup")
@Tag(name = "Setup")
class CodespacesResource {

  private final CodespacesApplicationService codespacesApplicationService;

  public CodespacesResource(CodespacesApplicationService codespacesApplicationService) {
    this.codespacesApplicationService = codespacesApplicationService;
  }

  @Operation(summary = "GitHub Codespaces", description = "Init GitHub Codespaces configuration files")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing GitHub Codespace files.")
  @PostMapping("/codespaces")
  @GeneratorStep(id = "github-codespaces")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    codespacesApplicationService.init(project);
  }
}
