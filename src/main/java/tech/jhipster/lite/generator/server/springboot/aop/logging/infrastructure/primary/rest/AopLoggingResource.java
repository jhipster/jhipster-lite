package tech.jhipster.lite.generator.server.springboot.aop.logging.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.aop.logging.application.AopLoggingApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/aop/logging")
@Tag(name = "Spring Boot")
class AopLoggingResource {

  private final AopLoggingApplicationService aopLoggingApplicationService;

  public AopLoggingResource(AopLoggingApplicationService aopLoggingApplicationService) {
    this.aopLoggingApplicationService = aopLoggingApplicationService;
  }

  @Operation(summary = "Add AOP logging")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding AOP logging")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    aopLoggingApplicationService.init(project);
  }
}
