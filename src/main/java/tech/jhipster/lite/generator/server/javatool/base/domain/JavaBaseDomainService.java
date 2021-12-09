package tech.jhipster.lite.generator.server.javatool.base.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class JavaBaseDomainService implements JavaBaseService {

  public static final String SOURCE = "server/javatool/base";

  private final ProjectRepository projectRepository;

  public JavaBaseDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String errorDomainPath = "error/domain";

    projectRepository.template(project, SOURCE, "Assert.java", getPath(MAIN_JAVA, packageNamePath, errorDomainPath));
    projectRepository.template(
      project,
      SOURCE,
      "MissingMandatoryValueException.java",
      getPath(MAIN_JAVA, packageNamePath, errorDomainPath)
    );
    projectRepository.template(project, SOURCE, "UnauthorizedValueException.java", getPath(MAIN_JAVA, packageNamePath, errorDomainPath));

    projectRepository.template(project, SOURCE, "UnitTest.java", getPath(TEST_JAVA, packageNamePath));
    projectRepository.template(project, SOURCE, "ReplaceCamelCase.java", getPath(TEST_JAVA, packageNamePath));

    projectRepository.template(project, SOURCE, "AssertTest.java", getPath(TEST_JAVA, packageNamePath, errorDomainPath));
    projectRepository.template(
      project,
      SOURCE,
      "MissingMandatoryValueExceptionTest.java",
      getPath(TEST_JAVA, packageNamePath, errorDomainPath)
    );
    projectRepository.template(
      project,
      SOURCE,
      "UnauthorizedValueExceptionTest.java",
      getPath(TEST_JAVA, packageNamePath, errorDomainPath)
    );
  }
}
