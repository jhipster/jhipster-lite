package tech.jhipster.lite.generator.server.springboot.user.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;

public class SpringBootUserDomainService implements SpringBootUserService {

  public static final String SOURCE = "server/springboot/user";
  public static final String TARGET_JAVA = "technical/infrastructure/secondary/user";
  public static final String TARGET_RESOURCE = "config/liquibase/users";

  private final ProjectRepository projectRepository;
  private final LiquibaseDomainService liquibaseDomainService;

  public SpringBootUserDomainService(ProjectRepository projectRepository, LiquibaseDomainService liquibaseDomainService) {
    this.projectRepository = projectRepository;
    this.liquibaseDomainService = liquibaseDomainService;
  }

  @Override
  public void addJavaUsers(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String userPath = "technical/infrastructure/secondary/user";
    String userDomainPath = userPath + "/domain";

    projectRepository.template(project, SOURCE, "User.java", getPath(MAIN_JAVA, packageNamePath, userDomainPath));
    projectRepository.template(project, SOURCE, "UserConstants.java", getPath(MAIN_JAVA, packageNamePath, userDomainPath));
    projectRepository.template(project, SOURCE, "UserConstants.java", getPath(MAIN_JAVA, packageNamePath, userDomainPath));
    projectRepository.template(project, SOURCE, "UserJpaRepository.java", getPath(MAIN_JAVA, packageNamePath, userPath));
  }

  @Override
  public void addJavaAuthority(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String userPath = "technical/infrastructure/secondary/user";
    String userDomainPath = userPath + "/domain";

    projectRepository.template(project, SOURCE, "AuthorityRepository.java", getPath(MAIN_JAVA, packageNamePath, userPath));
    projectRepository.template(project, SOURCE, "AuthorityRepository.java", getPath(MAIN_JAVA, packageNamePath, userPath));
  }

  @Override
  public void addLiquibaseConfiguration(Project project) {
    // Update liquibase master file
    liquibaseDomainService.addChangelogXml(project, "users", "users.xml");

    // Copy liquibase files
    projectRepository.add(project, SOURCE, "user.xml", getPath(MAIN_RESOURCES, TARGET_RESOURCE));
    projectRepository.add(project, SOURCE, "user.csv", getPath(MAIN_RESOURCES, TARGET_RESOURCE));
    projectRepository.add(project, SOURCE, "user_authority.csv", getPath(MAIN_RESOURCES, TARGET_RESOURCE));
    projectRepository.add(project, SOURCE, "authority.csv", getPath(MAIN_RESOURCES, TARGET_RESOURCE));
  }
}
