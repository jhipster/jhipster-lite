package tech.jhipster.forge.common.secondary;

import static tech.jhipster.forge.common.domain.Constants.RESOURCES;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.Projects;

@Repository
public class ProjectRepository implements Projects {

  private final Logger log = LoggerFactory.getLogger(ProjectRepository.class);

  @Override
  public void create(Project project) {
    FileUtils.createFolder(project.getPath());
  }

  @Override
  public void add(Project project, String source, String file) {
    try {
      log.info("Adding file '{}'", file);
      Files.copy(Path.of(RESOURCES + source + File.separator + file), Path.of(project.getPath() + File.separator + file));
    } catch (IOException ex) {
      log.error("Can't add file '{}':", file, ex);
    }
  }
}
