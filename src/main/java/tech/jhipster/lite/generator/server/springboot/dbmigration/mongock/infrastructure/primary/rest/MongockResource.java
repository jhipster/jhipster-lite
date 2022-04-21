package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.infrastructure.primary.rest;

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
import tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/spring-boot/database-migration-tools/mongock")
@Tag(name = "Spring Boot - Database Migration")
class MongockResource {

  private final MongockApplicationService mongockApplicationService;

  public MongockResource(MongockApplicationService mongockApplicationService) {
    this.mongockApplicationService = mongockApplicationService;
  }

  @Operation(summary = "Add Mongock")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Mongock")
  @PostMapping
  @GeneratorStep(id = GeneratorAction.MONGOCK)
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mongockApplicationService.init(project);
  }
}
