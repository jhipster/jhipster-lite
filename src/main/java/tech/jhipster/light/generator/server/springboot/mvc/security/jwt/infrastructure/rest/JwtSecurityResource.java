package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.light.generator.server.springboot.mvc.security.jwt.application.JwtSecurityApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/mvc/security")
@Tag(name = "Spring Boot - MVC")
class JwtSecurityResource {

  private final JwtSecurityApplicationService jwtSecurityApplicationService;

  public JwtSecurityResource(JwtSecurityApplicationService jwtSecurityApplicationService) {
    this.jwtSecurityApplicationService = jwtSecurityApplicationService;
  }

  @Operation(summary = "Add Spring Security JWT with Basic Auth")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security JWT with Basic Auth") })
  @PostMapping("/jwt")
  public void initBasicAuth(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    jwtSecurityApplicationService.initBasicAuth(project);
  }
}
