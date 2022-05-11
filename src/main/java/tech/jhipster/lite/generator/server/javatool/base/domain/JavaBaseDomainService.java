package tech.jhipster.lite.generator.server.javatool.base.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
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
    JavaBase
      .errorDomainFiles()
      .forEach(file -> projectRepository.template(project, SOURCE, file, getPath(MAIN_JAVA, packageNamePath, ERROR_DOMAIN_PATH)));

    JavaBase
      .errorDomainTestFiles()
      .forEach(file -> projectRepository.template(project, SOURCE, file, getPath(TEST_JAVA, packageNamePath, ERROR_DOMAIN_PATH)));
  }

  private void addCollections(Project project, String packageNamePath) {
    String className = WordUtils.upperFirst(project.getBaseName().orElse("jhipster"));
    project.addConfig("collectionClass", className);

    projectRepository.template(
      project,
      SOURCE,
      "ProjectCollections.java",
      getPath(MAIN_JAVA, packageNamePath, COMMON_DOMAIN_PATH),
      className + "Collections.java"
    );
    projectRepository.template(
      project,
      SOURCE,
      "ProjectCollectionsTest.java",
      getPath(TEST_JAVA, packageNamePath, COMMON_DOMAIN_PATH),
      className + "CollectionsTest.java"
    );
  }

  private void addAnnotations(Project project, String packageNamePath) {
    JavaBase.annotationsFiles().forEach(file -> projectRepository.template(project, SOURCE, file, getPath(TEST_JAVA, packageNamePath)));
  }
}
