package tech.jhipster.lite.generator.client.vue.core.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.client.vue.core.application.VueApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/vue")
@Tag(name = "Vue")
class VueResource {

  private final VueApplicationService vueApplicationService;

  public VueResource(VueApplicationService vueApplicationService) {
    this.vueApplicationService = vueApplicationService;
  }

  @Operation(summary = "Add Vue+Vite", description = "Add Vue+Vite")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Vue+Vite")
  @PostMapping
  @GeneratorStep(id = "vue")
  public void addVue(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    vueApplicationService.addVue(project);
  }

  @Operation(summary = "Add Vue+Vite with minimal CSS", description = "Add Vue+Vite with Minimal CSS")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Vue+Vite with minimal CSS")
  @PostMapping("/styled")
  @GeneratorStep(id = "vue-styled")
  public void addStyledVue(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    vueApplicationService.addStyledVue(project);
  }
}
