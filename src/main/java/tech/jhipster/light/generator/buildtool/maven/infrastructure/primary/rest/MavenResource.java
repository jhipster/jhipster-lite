package tech.jhipster.light.generator.buildtool.maven.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.light.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.infrastructure.primary.dto.ProjectDTO;

@RestController
@RequestMapping("/api/build-tools/maven")
@Tag(name = "Build Tool")
class MavenResource {

  private final MavenApplicationService mavenApplicationService;

  public MavenResource(MavenApplicationService mavenApplicationService) {
    this.mavenApplicationService = mavenApplicationService;
  }

  @Operation(summary = "Init", description = "Init Maven project with pom.xml and wrapper")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing Maven project")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.init(project);
  }

  @Operation(summary = "Add pom.xml")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding pom.xml to project")
  @PostMapping("/pom-xml")
  public void addPomXml(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.addPomXml(project);
  }

  @Operation(summary = "Add Maven Wrapper")
  @ApiResponses({ @ApiResponse(responseCode = "500", description = "An error occurred while adding Maven Wrapper to project") })
  @PostMapping("/wrapper")
  public void addMavenWrapper(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.addMavenWrapper(project);
  }
}
