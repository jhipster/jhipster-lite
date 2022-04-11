package tech.jhipster.lite.generator.client.tools.playwright.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.client.tools.playwright.application.PlaywrightApplicationService;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/clients/playwright")
@Tag(name = "E2e")
class PlaywrightResource {

  private final PlaywrightApplicationService playwrightApplicationService;

  public PlaywrightResource(PlaywrightApplicationService playwrightApplicationService) {
    this.playwrightApplicationService = playwrightApplicationService;
  }

  @Operation(summary = "Add Playwright", description = "Add Playwright")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding playwright")
  @PostMapping
  @GeneratorStep(id = GeneratorAction.CLIENT_COMMON_PLAYWRIGHT)
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    playwrightApplicationService.init(project);
  }
}
