package tech.jhipster.lite.generator.server.sonar.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.sonar.application.SonarApplicationService;

@RestController
@RequestMapping("/api/servers/sonar")
@Tag(name = "Sonar")
class SonarResource {

  private final SonarApplicationService sonarApplicationService;

  public SonarResource(SonarApplicationService sonarApplicationService) {
    this.sonarApplicationService = sonarApplicationService;
  }

  @Operation(summary = "Add Sonar configuration to inspect code quality")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while adding Sonar configuration") })
  @PostMapping
  public void addSonar(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    sonarApplicationService.init(project);
  }
}
