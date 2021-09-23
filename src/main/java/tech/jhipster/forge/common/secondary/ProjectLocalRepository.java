package tech.jhipster.forge.common.secondary;

import static tech.jhipster.forge.common.domain.Constants.TEMPLATE_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.getPathOf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.common.utils.MustacheUtils;
import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.error.domain.GeneratorException;

@Repository
public class ProjectLocalRepository implements ProjectRepository {

  private final Logger log = LoggerFactory.getLogger(ProjectLocalRepository.class);

  @Override
  public void create(Project project) {
    try {
      FileUtils.createFolder(project.getPath());
    } catch (IOException ex) {
      throw new GeneratorException("The folder can't be created");
    }
  }

  @Override
  public void add(Project project, String source, String sourceFilename) {
    add(project, source, sourceFilename, ".");
  }

  @Override
  public void add(Project project, String source, String sourceFilename, String destination) {
    add(project, source, sourceFilename, destination, sourceFilename);
  }

  @Override
  public void add(Project project, String source, String sourceFilename, String destination, String destinationFilename) {
    log.info("Adding file '{}'", destinationFilename);
    Path sourcePath = FileUtils.getPathOf(TEMPLATE_RESOURCES, source, sourceFilename);

    try {
      String destinationFolder = FileUtils.getPath(project.getPath(), destination);
      FileUtils.createFolder(destinationFolder);

      Path destinationPath = FileUtils.getPathOf(destinationFolder, destinationFilename);
      Files.copy(sourcePath, destinationPath);
    } catch (IOException ex) {
      throw new GeneratorException("The file '" + destinationFilename + "' can't be added");
    }
  }

  @Override
  public void template(Project project, String source, String sourceFilename) {
    template(project, source, sourceFilename, ".");
  }

  @Override
  public void template(Project project, String source, String sourceFilename, String destination) {
    template(project, source, sourceFilename, destination, MustacheUtils.withoutExt(sourceFilename));
  }

  @Override
  public void template(Project project, String source, String sourceFilename, String destination, String destinationFilename) {
    try {
      log.info("Adding file '{}'", destinationFilename);
      String filename = MustacheUtils.withExt(sourceFilename);
      String result = MustacheUtils.template(FileUtils.getPath(TEMPLATE_RESOURCES, source, filename), project.getConfig());

      String destinationFolder = FileUtils.getPath(project.getPath(), destination);
      FileUtils.createFolder(destinationFolder);

      String destinationTarget = FileUtils.getPath(destinationFolder, destinationFilename);
      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destinationTarget))) {
        bufferedWriter.write(result);
      }
    } catch (IOException ex) {
      throw new GeneratorException("The file '" + destinationFilename + "' can't be added");
    }
  }

  @Override
  public void write(Project project, String text, String destination, String destinationFilename) {
    Assert.notNull("text", text);

    String projectDestination = getPath(project.getPath(), destination);
    String projectDestinationFilename = getPath(projectDestination, destinationFilename);

    try {
      FileUtils.createFolder(projectDestination);
      Files.write(getPathOf(projectDestinationFilename), text.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when writing text to '" + projectDestinationFilename + "'");
    }
  }
}
