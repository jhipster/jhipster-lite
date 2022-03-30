package tech.jhipster.lite.generator.init.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Init")
class InitResource {

  private final InitApplicationService initApplicationService;

  public InitResource(InitApplicationService initApplicationService) {
    this.initApplicationService = initApplicationService;
  }

  @Operation(summary = "Init project")
  @ApiResponse(responseCode = "500", description = "An error occurred while initializing project")
  @PostMapping("/init")
  @GeneratorStep(id = "init")
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
  }

  @Operation(summary = "Download project")
  @ApiResponse(responseCode = "500", description = "An error occurred while downloading project")
  @PostMapping("/download")
  @GeneratorStep(id = "download")
  public ResponseEntity<Resource> download(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    byte[] out = initApplicationService.download(project);
    ByteArrayResource resource = new ByteArrayResource(out);
    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + project.getBaseName().orElse("application") + ".zip")
      .contentType(MediaType.parseMediaType("application/octet-stream"))
      .contentLength(out.length)
      .header("X-Suggested-Filename", project.getBaseName().orElse("application") + ".zip")
      .body(resource);
  }
}
