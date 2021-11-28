package tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.application.LiquibaseApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/databases/migration/liquibase")
@Tag(name = "Spring Boot - Database")
class LiquibaseResource {

  private final LiquibaseApplicationService liquibaseApplicationService;

  public LiquibaseResource(LiquibaseApplicationService liquibaseApplicationService) {
    this.liquibaseApplicationService = liquibaseApplicationService;
  }

  @Operation(summary = "Add Liquibase")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while adding Liquibase") })
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    liquibaseApplicationService.init(project);
  }
}
