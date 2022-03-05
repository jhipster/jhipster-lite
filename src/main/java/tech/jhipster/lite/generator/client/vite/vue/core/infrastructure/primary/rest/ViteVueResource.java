package tech.jhipster.lite.generator.client.vite.vue.core.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.client.vite.vue.core.application.ViteVueApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/vite/vue")
@Tag(name = "Vite - Vue")
class ViteVueResource {

  private final ViteVueApplicationService viteVueApplicationService;

  public ViteVueResource(ViteVueApplicationService viteVueApplicationService) {
    this.viteVueApplicationService = viteVueApplicationService;
  }

  @Operation(summary = "Add Vite+Vue", description = "Add Vite+Vue")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Vite+Vue")
  @PostMapping
  @GeneratorStep(id = "vite-vue")
  public void addViteVue(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    viteVueApplicationService.addViteVue(project);
  }

  @Operation(summary = "Add Vite+Vue with minimal CSS")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Vite+Vue with minimal CSS")
  @PostMapping("/styled")
  @GeneratorStep(id = "vite-vue-styled")
  public void addStyledViteVue(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    viteVueApplicationService.addStyledViteVue(project);
  }
}
