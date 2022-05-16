package tech.jhipster.lite.generator.project.domain;

import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.error.domain.Assert;

public class ProjectFile {

  private final Project project;
  private final FilePath source;
  private final FilePath destination;

  private ProjectFile(ProjectFileWithSourceBuilder builder, FilePath destination) {
    project = builder.project();
    source = builder.source();
    this.destination = destination;
  }

  public static ProjectFileBuilder forProject(Project project) {
    return new ProjectFileBuilder(project);
  }

  public Project project() {
    return project;
  }

  public FilePath source() {
    return source;
  }

  public FilePath destination() {
    return destination;
  }

  public static class ProjectFileBuilder {

    private final Project project;

    private ProjectFileBuilder(Project project) {
      Assert.notNull("project", project);

      this.project = project;
    }

    public Collection<ProjectFile> all(String sourceFolder, String... files) {
      Assert.notNull("files", files);

      return all(sourceFolder, List.of(files));
    }

    public Collection<ProjectFile> all(String sourceFolder, Collection<String> files) {
      Assert.notNull("files", files);

      return files.stream().map(file -> withSource(sourceFolder, file).withSameDestination()).toList();
    }

    public ProjectFileWithSourceBuilder withSource(String folder, String file) {
      return new ProjectFileWithSourceBuilder(this, new FilePath(folder, file));
    }
  }

  public static class ProjectFileWithSourceBuilder {

    private static final String SAME_DESTINATION_FOLDER = ".";

    private final ProjectFileBuilder fileBuilder;
    private final FilePath source;

    private ProjectFileWithSourceBuilder(ProjectFileBuilder fileBuilder, FilePath source) {
      this.fileBuilder = fileBuilder;
      this.source = source;
    }

    private Project project() {
      return fileBuilder.project;
    }

    private FilePath source() {
      return source;
    }

    public ProjectFile withSameDestination() {
      return new ProjectFile(this, source.withFolder(SAME_DESTINATION_FOLDER));
    }

    public ProjectFile withDestinationFolder(String destinationFolder) {
      return new ProjectFile(this, source.withFolder(destinationFolder));
    }

    public ProjectFile withDestinationFile(String file) {
      return new ProjectFile(this, new FilePath(SAME_DESTINATION_FOLDER, file));
    }

    public ProjectFile withDestination(String folder, String file) {
      return new ProjectFile(this, new FilePath(folder, file));
    }
  }
}
