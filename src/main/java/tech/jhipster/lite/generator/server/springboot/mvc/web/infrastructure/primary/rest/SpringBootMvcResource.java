package tech.jhipster.lite.generator.server.springboot.mvc.web.infrastructure.primary.rest;

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

@RestController
@RequestMapping("/api/servers/spring-boot/mvc/web")
@Tag(name = "Spring Boot - MVC")
class SpringBootMvcResource {

  private final SpringBootMvcApplicationService springBootMvcApplicationService;

  public SpringBootMvcResource(SpringBootMvcApplicationService springBootMvcApplicationService) {
    this.springBootMvcApplicationService = springBootMvcApplicationService;
  }

  @Operation(summary = "Add Spring Boot MVC with Tomcat")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Boot MVC with Tomcat")
  @PostMapping("/tomcat")
  public void addSpringBootMvc(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootMvcApplicationService.addSpringBootMvc(project);
  }

  @Operation(summary = "Add Spring Boot MVC with Undertow")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Boot MVC with Undertow")
  @PostMapping("/undertow")
  public void addSpringBootUndertow(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootMvcApplicationService.addSpringBootUndertow(project);
  }

  @Operation(summary = "Add Spring Boot Actuator")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Boot Actuator")
  @PostMapping("/actuator")
  public void addSpringBootActuator(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootMvcApplicationService.addSpringBootActuator(project);
  }
}
