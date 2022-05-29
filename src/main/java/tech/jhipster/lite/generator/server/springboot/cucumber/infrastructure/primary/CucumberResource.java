package tech.jhipster.lite.generator.server.springboot.cucumber.infrastructure.primary;

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
import tech.jhipster.lite.generator.server.springboot.cucumber.application.CucumberApplicationService;
import tech.jhipster.lite.generator.server.springboot.cucumber.domain.CucumberModuleProperties;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@Tag(name = "Spring Boot")
@RequestMapping("/api/servers/spring-boot/cucumber")
class CucumberResource {

  private final CucumberApplicationService cucumber;

  public CucumberResource(CucumberApplicationService cucumber) {
    this.cucumber = cucumber;
  }

  @PostMapping
  @GeneratorStep(id = GeneratorAction.SPRINGBOOT_CUCUMBER)
  @Operation(summary = "Add cucumber integration to project")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding cucumber elements")
  public void addCucumber(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);

    cucumber.add(CucumberModuleProperties.from(project));
  }
}
