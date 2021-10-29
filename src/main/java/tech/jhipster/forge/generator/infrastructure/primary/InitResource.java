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
import tech.jhipster.forge.generator.application.InitApplicationService;
import tech.jhipster.forge.springboot.primary.ProjectDTO;

@RestController
@RequestMapping("/api/init")
@Api(tags = "Init")
public class InitResource {

  private final InitApplicationService initApplicationService;

  public InitResource(InitApplicationService initApplicationService) {
    this.initApplicationService = initApplicationService;
  }

  @ApiOperation("Init project")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
  }
}
