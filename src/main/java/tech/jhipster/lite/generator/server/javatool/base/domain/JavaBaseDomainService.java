package tech.jhipster.lite.generator.server.javatool.base.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import java.util.List;
import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class JavaBaseDomainService implements JavaBaseService {

  public static final String SOURCE = "server/javatool/base";
  public static final String ERROR_DOMAIN_PATH = "error/domain";
  private static final String COMMON_DOMAIN_PATH = "common/domain";

  private final ProjectRepository projectRepository;

  public JavaBaseDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addJavaBase(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(DefaultConfig.PACKAGE_PATH);

    addErrors(project, packageNamePath);
    addCollections(project, packageNamePath);
    addAnnotations(project, packageNamePath);
  }

  private void addErrors(Project project, String packageNamePath) {
    List<ProjectFile> errorFiles = JavaBase
      .errorDomainFiles()
      .stream()
      .map(file ->
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, file)
          .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, ERROR_DOMAIN_PATH))
      )
      .toList();

    projectRepository.template(errorFiles);

    List<ProjectFile> testFiles = JavaBase
      .errorDomainTestFiles()
      .stream()
      .map(file ->
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, file)
          .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, ERROR_DOMAIN_PATH))
      )
      .toList();
    projectRepository.template(testFiles);
  }

  private void addCollections(Project project, String packageNamePath) {
    String className = WordUtils.upperFirst(project.getBaseName().orElse("jhipster"));
    project.addConfig("collectionClass", className);

    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(SOURCE, "ProjectCollections.java")
        .withDestination(getPath(MAIN_JAVA, packageNamePath, COMMON_DOMAIN_PATH), className + "Collections.java")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(SOURCE, "ProjectCollectionsTest.java")
        .withDestination(getPath(TEST_JAVA, packageNamePath, COMMON_DOMAIN_PATH), className + "CollectionsTest.java")
    );
  }

  private void addAnnotations(Project project, String packageNamePath) {
    List<ProjectFile> files = JavaBase
      .annotationsFiles()
      .stream()
      .map(file -> ProjectFile.forProject(project).withSource(SOURCE, file).withDestinationFolder(getPath(TEST_JAVA, packageNamePath)))
      .toList();

    projectRepository.template(files);
  }
}
