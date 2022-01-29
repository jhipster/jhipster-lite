package tech.jhipster.lite.generator.server.springboot.user.infrastructure.primary;

import static tech.jhipster.lite.generator.project.domain.DatabaseType.MYSQL;
import static tech.jhipster.lite.generator.project.domain.DatabaseType.POSTGRESQL;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.user.application.SpringBootUserApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/user")
@Tag(name = "Spring Boot - User")
class SpringBootUserResource {

  private final SpringBootUserApplicationService springBootUserApplicationService;

  public SpringBootUserResource(SpringBootUserApplicationService springBootUserApplicationService) {
    this.springBootUserApplicationService = springBootUserApplicationService;
  }

  @Operation(summary = "Add UserEntity, AuthorityEntity and JpaRepository for PostgreSQL")
  @PostMapping("/postgresql")
  @ApiResponse(
    responseCode = "500",
    description = "An error occurred while adding UserEntity, AuthorityEntity and JpaRepository for PostgreSQL"
  )
  public void addUserAndAuthorityEntitiesForPostgreSQL(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    addUserAndAuthorityEntities(project, POSTGRESQL);
  }

  @Operation(summary = "Add UserEntity, AuthorityEntity and JpaRepository for MySQL")
  @PostMapping("/mysql")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding UserEntity, AuthorityEntity and JpaRepository for MySQL")
  public void addUserAndAuthorityEntitiesForMySQL(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    addUserAndAuthorityEntities(project, MYSQL);
  }

  private void addUserAndAuthorityEntities(Project project, DatabaseType sqlDatabase) {
    springBootUserApplicationService.addUserAndAuthorityEntities(project, sqlDatabase);
  }
}
