package tech.jhipster.lite.generator.client.vite.react.core.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.client.vite.react.core.application.ViteReactApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/vite/react")
@Tag(name = "Vite - React")
class ViteReactResource {

  private final ViteReactApplicationService viteReactApplicationService;

  public ViteReactResource(ViteReactApplicationService viteReactApplicationService) {
    this.viteReactApplicationService = viteReactApplicationService;
  }

  @Operation(summary = "Init Vite+React", description = "Init Vite+React project")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing Vite+React project")
  @PostMapping
  @GeneratorStep(id = "vite-react")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    viteReactApplicationService.addViteReact(project);
  }

  @Operation(summary = "Add Vite+React with minimal CSS")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Vite+React with minimal CSS")
  @PostMapping("/styled")
  @GeneratorStep(id = "vite-react-styled")
  public void addStyledViteVue(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    viteReactApplicationService.addStyledViteReact(project);
  }
}
