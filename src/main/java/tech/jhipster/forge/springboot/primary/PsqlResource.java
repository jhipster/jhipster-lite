package tech.jhipster.forge.springboot.primary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.springboot.application.PsqlApplicationService;

@RestController
@RequestMapping("/api/psql")
@Api(tags = "PostgreSQL")
public class PsqlResource {

  private final PsqlApplicationService psqlApplicationService;

  public PsqlResource(PsqlApplicationService psqlApplicationService) {
    this.psqlApplicationService = psqlApplicationService;
  }

  @ApiOperation("Add PostgreSQL drivers and dependencies, with testcontainers")
  @ApiResponses({ @ApiResponse(code = 500, message = "An error occurred while initializing project") })
  @PostMapping("/init")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    psqlApplicationService.init(project);
  }
}
