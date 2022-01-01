package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.infrastructure.primary.rest;

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
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.application.SpringCloudConfigClientApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/spring-cloud/config-client")
@Tag(name = "Spring Boot - Spring Cloud")
class SpringCloudConfigResource {

  private final SpringCloudConfigClientApplicationService springCloudConfigClientApplicationService;

  public SpringCloudConfigResource(SpringCloudConfigClientApplicationService springCloudConfigClientApplicationService) {
    this.springCloudConfigClientApplicationService = springCloudConfigClientApplicationService;
  }

  @Operation(summary = "Add Spring Cloud Config Client")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Cloud Config Client")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springCloudConfigClientApplicationService.init(project);
  }
}
