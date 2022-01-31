package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.application.JwtSecurityApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/spring-boot/mvc/security")
@Tag(name = "Spring Boot - MVC")
class JwtSecurityResource {

  private final JwtSecurityApplicationService jwtSecurityApplicationService;

  public JwtSecurityResource(JwtSecurityApplicationService jwtSecurityApplicationService) {
    this.jwtSecurityApplicationService = jwtSecurityApplicationService;
  }

  @Operation(summary = "Add Spring Security JWT")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security JWT")
  @PostMapping("/jwt")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    jwtSecurityApplicationService.init(project);
  }

  @Operation(summary = "Add Basic Auth for Spring Security JWT")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Basic Auth for Spring Security JWT")
  @PostMapping("/jwt/basic-auth")
  @GeneratorStep(id = "jwt-basic-auth")
  public void addBasicAuth(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    jwtSecurityApplicationService.addBasicAuth(project);
  }
}
