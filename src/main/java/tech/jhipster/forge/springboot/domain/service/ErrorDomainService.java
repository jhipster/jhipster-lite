package tech.jhipster.forge.springboot.domain.service;

import static tech.jhipster.forge.common.domain.Constants.MAIN_JAVA;
import static tech.jhipster.forge.common.domain.Constants.TEST_JAVA;
import static tech.jhipster.forge.common.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.springboot.domain.usecase.ErrorService;

public class ErrorDomainService implements ErrorService {

  public static final String SOURCE = "error";

  private final ProjectRepository projectRepository;

  public ErrorDomainService(ProjectRepository projectRepository) {
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
