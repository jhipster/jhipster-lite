package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.springdoc.application.SpringDocApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/mvc/spring-doc")
@Tag(name = "Spring Boot - MVC")
class SpringDocResource {

  private final SpringDocApplicationService springDocApplicationService;

  public SpringDocResource(SpringDocApplicationService springDocApplicationService) {
    this.springDocApplicationService = springDocApplicationService;
  }

  @Operation(summary = "Add Spring Doc")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Doc")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    this.springDocApplicationService.init(project);
  }
}
