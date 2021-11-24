package tech.jhipster.light.generator.server.springboot.web.infrastructure.primary.rest;

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
import tech.jhipster.light.generator.server.springboot.web.application.SpringBootWebApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/web")
@Tag(name = "Spring Boot - Server Web")
class SpringBootWebResource {

  private final SpringBootWebApplicationService springBootWebApplicationService;

  public SpringBootWebResource(SpringBootWebApplicationService springBootWebApplicationService) {
    this.springBootWebApplicationService = springBootWebApplicationService;
  }

  @Operation(summary = "Add Spring Boot Web with Tomcat")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while initializing project") })
  @PostMapping("/tomcat")
  public void addSpringBootWeb(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootWebApplicationService.addSpringBootWeb(project);
  }

  @Operation(summary = "Add Spring Boot Web with Undertow")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while initializing project") })
  @PostMapping("/undertow")
  public void addSpringBootUndertow(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootWebApplicationService.addSpringBootUndertow(project);
  }
}
