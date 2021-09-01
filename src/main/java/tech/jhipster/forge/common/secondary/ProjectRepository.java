package tech.jhipster.forge.common.secondary;

import static tech.jhipster.forge.common.domain.Constants.TEMPLATE_RESOURCES;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
      Path sourcePath = FileUtils.getPathOf(TEMPLATE_RESOURCES, source, file);
      Path destinationPath = FileUtils.getPathOf(project.getPath(), file);
      Files.copy(sourcePath, destinationPath);
    } catch (IOException ex) {
      log.error("Can't add file '{}':", file, ex);
    }
  }

  @Override
  public void template(Project project, String source, String file) {
    try {
      String result = MustacheUtils.template(FileUtils.getPath(TEMPLATE_RESOURCES, source, file), project);

      String destination = FileUtils.getPath(project.getPath(), file.replaceAll(".mustache", ""));
      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destination))) {
        bufferedWriter.write(result);
      }
    } catch (IOException ex) {
      log.error("Can't template file '{}':", file, ex);
    }
  }
}
