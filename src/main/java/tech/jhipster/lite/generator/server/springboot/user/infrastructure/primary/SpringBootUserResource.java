package tech.jhipster.lite.generator.server.springboot.user.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.web.application.SpringBootMvcApplicationService;
import tech.jhipster.lite.generator.server.springboot.user.application.SpringbootUserApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/user")
@Tag(name = "Spring Boot - User")
class SpringBootUserResource {

  private final SpringbootUserApplicationService springBootUserApplicationService;

  public SpringBootUserResource(SpringbootUserApplicationService springBootUserApplicationService) {
    this.springBootUserApplicationService = springBootUserApplicationService;
  }

  @Operation(summary = "Add Spring Boot User and authority")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Boot users and authority")
  public void addSpringBootUsers(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootUserApplicationService.addJavaUsers(project);
    springBootUserApplicationService.addLiquibaseConfiguration(project);
  }
}
