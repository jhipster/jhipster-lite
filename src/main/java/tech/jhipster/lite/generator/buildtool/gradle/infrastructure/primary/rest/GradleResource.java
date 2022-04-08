package tech.jhipster.lite.generator.buildtool.gradle.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.buildtool.gradle.application.GradleApplicationService;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/build-tools/gradle")
@Tag(name = "Build Tool")
class GradleResource {

  private final GradleApplicationService gradleApplicationService;

  public GradleResource(GradleApplicationService gradleApplicationService) {
    this.gradleApplicationService = gradleApplicationService;
  }

  @Operation(summary = "Init", description = "Init Gradle project with build.gradle.kts and wrapper")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing Gradle project")
  @PostMapping
  @GeneratorStep(id = GeneratorAction.MAVEN_JAVA)
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    gradleApplicationService.init(project);
  }

  @Operation(summary = "Add build.gradle.kts")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding build.gradle.kts to project")
  @PostMapping("/build-gradle")
  @GeneratorStep(id = GeneratorAction.MAVEN_JAVA_POM_XML)
  public void addPomXml(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    gradleApplicationService.addBuildGradle(project);
  }

  @Operation(summary = "Add Gradle Wrapper")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Gradle Wrapper to project")
  @PostMapping("/wrapper")
  @GeneratorStep(id = GeneratorAction.MAVEN_WRAPPER)
  public void addMavenWrapper(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    gradleApplicationService.addGradleWrapper(project);
  }
}
