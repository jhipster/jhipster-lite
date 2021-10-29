package tech.jhipster.forge.generator.infrastructure.primary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.application.SpringBootApplicationService;
import tech.jhipster.forge.generator.application.SpringBootWebApplicationService;

@RestController
@RequestMapping("/api/spring-boot")
@Api(tags = "Spring Boot")
public class SpringBootResource {

  private final SpringBootApplicationService springBootApplicationService;
  private final SpringBootWebApplicationService springBootWebApplicationService;

  public SpringBootResource(
    SpringBootApplicationService springBootApplicationService,
    SpringBootWebApplicationService springBootWebApplicationService
  ) {
    this.springBootApplicationService = springBootApplicationService;
    this.springBootWebApplicationService = springBootWebApplicationService;
  }

  @ApiOperation("Init Spring Boot project with dependencies, App, and properties")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/init")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootApplicationService.init(project);
  }

  @ApiOperation("Add Spring Boot Web (MVC)")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/web")
  public void addSpringBootWeb(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootWebApplicationService.addSpringBootWeb(project);
  }
}
