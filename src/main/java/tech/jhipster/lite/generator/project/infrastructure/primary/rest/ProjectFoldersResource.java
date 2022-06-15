package tech.jhipster.lite.generator.project.infrastructure.primary.rest;

import java.util.UUID;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project-folders")
class ProjectFoldersResource {

  @Value("${application.forced-project-folder:}")
  private String forcedProjectFolder;

  @GetMapping
  public String getAvailableFolderName() {
    return rootFolder().replaceAll("([^/])$", "$1/") + UUID.randomUUID();
  }

  private String rootFolder() {
    if (forcedProjectFolder.isBlank()) {
      return SystemUtils.JAVA_IO_TMPDIR;
    }

    return forcedProjectFolder;
  }
}
