package tech.jhipster.lite.generator.server.springboot.database.postgresql.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.application.PostgresqlApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/databases/postgresql")
@Tag(name = "Spring Boot - Database")
class PostgresqlResource {

  private final PostgresqlApplicationService postgresqlApplicationService;

  public PostgresqlResource(PostgresqlApplicationService postgresqlApplicationService) {
    this.postgresqlApplicationService = postgresqlApplicationService;
  }

  @Operation(summary = "Add PostgreSQL drivers and dependencies, with testcontainers")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding PostgreSQL")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    postgresqlApplicationService.init(project);
  }
}
