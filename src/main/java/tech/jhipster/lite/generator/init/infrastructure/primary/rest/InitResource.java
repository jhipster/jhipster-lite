package tech.jhipster.lite.generator.init.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Init")
class InitResource {

  private final InitApplicationService initApplicationService;

  public InitResource(InitApplicationService initApplicationService) {
    this.initApplicationService = initApplicationService;
  }

  @Operation(summary = "Init project")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while initializing project") })
  @PostMapping("/init")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
  }
}
