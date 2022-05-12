package tech.jhipster.lite.generator.setup.gitpod.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.setup.gitpod.application.GitpodApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/developer-tools/gitpod")
@Tag(name = "Gitpod")
class GitpodResource {

  private final GitpodApplicationService gitpodApplicationService;

  public GitpodResource(GitpodApplicationService gitpodApplicationService) {
    this.gitpodApplicationService = gitpodApplicationService;
  }

  @Operation(summary = "Gitpod", description = "Init Gitpod configuration files")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing Gitpod files.")
  @PostMapping
  @GeneratorStep(id = GeneratorAction.GITPOD)
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    gitpodApplicationService.init(project);
  }
}
