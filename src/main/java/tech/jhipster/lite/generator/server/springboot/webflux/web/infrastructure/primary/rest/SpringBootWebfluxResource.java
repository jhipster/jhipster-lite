package tech.jhipster.lite.generator.server.springboot.webflux.web.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.webflux.web.application.SpringBootWebfluxApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/spring-boot")
@Tag(name = "Spring Boot - Webflux")
class SpringBootWebfluxResource {

  private final SpringBootWebfluxApplicationService springBootWebfluxApplicationService;

  public SpringBootWebfluxResource(SpringBootWebfluxApplicationService springBootWebfluxApplicationService) {
    this.springBootWebfluxApplicationService = springBootWebfluxApplicationService;
  }

  @Operation(summary = "Add Spring Boot Webflux with Netty")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Boot Webflux with Netty")
  @PostMapping("/reactive-servers/netty")
  @GeneratorStep(id = GeneratorAction.SPRINGBOOT_WEBFLUX_NETTY)
  public void addSpringBootWebflux(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootWebfluxApplicationService.addSpringBootWebflux(project);
  }
}
