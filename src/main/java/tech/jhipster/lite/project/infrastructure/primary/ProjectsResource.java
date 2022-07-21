package tech.jhipster.lite.project.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.project.application.ProjectsApplicationService;
import tech.jhipster.lite.project.domain.Project;
import tech.jhipster.lite.project.domain.ProjectPath;

@RestController
@Tag(name = "Project")
@RequestMapping("/api/projects")
class ProjectsResource {

  private final ProjectsApplicationService projects;

  public ProjectsResource(ProjectsApplicationService projects) {
    this.projects = projects;
  }

  @Operation(summary = "Download the project")
  @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<Resource> downloadProject(@Schema(description = "Path of the project to download") @RequestParam("path") String path) {
    Project project = projects.get(new ProjectPath(path));
    String filename = project.name().filename();

    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
      .header("X-Suggested-Filename", filename)
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .contentLength(project.contentLength())
      .body(new ByteArrayResource(project.content()));
  }
}
