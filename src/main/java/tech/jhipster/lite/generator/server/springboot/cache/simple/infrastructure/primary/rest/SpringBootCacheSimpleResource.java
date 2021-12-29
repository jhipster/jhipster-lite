package tech.jhipster.lite.generator.server.springboot.cache.simple.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.cache.simple.application.SpringBootCacheSimpleApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/cache/simple")
@Tag(name = "Spring Boot - Cache")
class SpringBootCacheSimpleResource {

  private final SpringBootCacheSimpleApplicationService springBootCacheSimpleApplicationService;

  public SpringBootCacheSimpleResource(SpringBootCacheSimpleApplicationService springBootCacheSimpleApplicationService) {
    this.springBootCacheSimpleApplicationService = springBootCacheSimpleApplicationService;
  }

  @Operation(summary = "Add simple cache")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding simple cache")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootCacheSimpleApplicationService.init(project);
  }
}
