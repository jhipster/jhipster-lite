package tech.jhipster.lite.generator.server.javatool.base.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.javatool.base.application.JavaBaseApplicationService;

@RestController
@RequestMapping("/api/servers/java/base")
@Tag(name = "Java")
class JavaBaseResource {

  private final JavaBaseApplicationService javaBaseApplicationService;

  public JavaBaseResource(JavaBaseApplicationService javaBaseApplicationService) {
    this.javaBaseApplicationService = javaBaseApplicationService;
  }

  @Operation(summary = "Add Base classes and Error domain to project")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding base classes and error domain to project")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    javaBaseApplicationService.init(project);
  }
}
