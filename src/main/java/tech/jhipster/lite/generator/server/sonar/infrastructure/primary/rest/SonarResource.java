package tech.jhipster.lite.generator.server.sonar.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.sonar.application.SonarApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/sonar")
@Tag(name = "Sonar")
class SonarResource {

  private final SonarApplicationService sonarApplicationService;

  public SonarResource(SonarApplicationService sonarApplicationService) {
    this.sonarApplicationService = sonarApplicationService;
  }

  @Operation(summary = "Add Sonar configuration to inspect code quality")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Sonar configuration")
  @PostMapping("/java-backend")
  @GeneratorStep(id = "sonar-java-backend")
  public void addSonarJavaBackend(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    sonarApplicationService.addSonarJavaBackend(project);
  }
}
