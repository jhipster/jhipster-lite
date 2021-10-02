package tech.jhipster.forge.generator.springboot.primary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.MavenApplicationService;

@RestController
@RequestMapping("/api/maven")
@Api(tags = "Maven")
public class MavenResource {

  private final MavenApplicationService mavenApplicationService;

  public MavenResource(MavenApplicationService mavenApplicationService) {
    this.mavenApplicationService = mavenApplicationService;
  }

  @ApiOperation("Init Maven project with pom.xml and wrapper")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/init")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.init(project);
  }

  @ApiOperation("Add pom.xml")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/pom-xml")
  public void addPomXml(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.addPomXml(project);
  }

  @ApiOperation("Add Maven Wrapper")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/wrapper")
  public void addMavenWrapper(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    mavenApplicationService.addMavenWrapper(project);
  }
}
