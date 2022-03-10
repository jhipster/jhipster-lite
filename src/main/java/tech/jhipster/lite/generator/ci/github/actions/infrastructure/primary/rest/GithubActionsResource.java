package tech.jhipster.lite.generator.ci.github.actions.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.ci.github.actions.application.GithubActionsApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/github-actions/")
@Tag(name = "GitHub Actions")
class GithubActionsResource {

  private final GithubActionsApplicationService githubActionsApplicationService;

  GithubActionsResource(GithubActionsApplicationService githubActionsApplicationService) {
    this.githubActionsApplicationService = githubActionsApplicationService;
  }

  @Operation(summary = "Init Github Actions YML files")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding GitHub Actions")
  @PostMapping("/maven")
  @GeneratorStep(id = "github-actions")
  public void addGitHubActionsForMaven(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    githubActionsApplicationService.init(project);
  }
}
