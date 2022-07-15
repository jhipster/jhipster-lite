package tech.jhipster.lite.generator.project.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.application.ProjectApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project")
class ProjectResource {

  private final ProjectApplicationService projectApplicationService;

  public ProjectResource(ProjectApplicationService projectApplicationService) {
    this.projectApplicationService = projectApplicationService;
  }

  @PostMapping("/download")
  @Operation(summary = "Download project")
  @ApiResponse(responseCode = "500", description = "An error occurred while downloading project")
  public ResponseEntity<Resource> download(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    byte[] out = projectApplicationService.download(project);
    ByteArrayResource resource = new ByteArrayResource(out);
    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + getZipFilename(project))
      .contentType(MediaType.parseMediaType("application/octet-stream"))
      .contentLength(out.length)
      .header("X-Suggested-Filename", getZipFilename(project))
      .body(resource);
  }

  @Operation(summary = "Get project details")
  @ApiResponse(responseCode = "500", description = "An error occurred while fetching project details")
  @GetMapping("details")
  public ResponseEntity<ProjectDTO> getProjectDetails(
    @Parameter(description = "Project path to get details") @RequestParam(value = "folder") String folder
  ) {
    ProjectDTO projectDTO = ProjectDTO.fromProject(this.projectApplicationService.getProjectDetails(folder));
    return ResponseEntity.ok(projectDTO);
  }

  private String getZipFilename(Project project) {
    return project.getBaseName().orElse("application") + ".zip";
  }
}
