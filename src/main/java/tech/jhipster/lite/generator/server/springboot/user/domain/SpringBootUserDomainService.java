package tech.jhipster.lite.generator.server.springboot.user.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService.*;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;

public class SpringBootUserDomainService implements SpringBootUserService {

  public static final String SOURCE = "server/springboot/user";
  public static final String TARGET_JAVA = "technical/infrastructure/secondary/user";
  public static final String TARGET_RESOURCE = "config/liquibase";
  public static final String USER_FOLDER = LIQUIBASE_CHANGELOG + "users";

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

    projectRepository.template(project, getPath(packageNamePath, "src"), "User.java", getPath(MAIN_JAVA, "User.java", LIQUIBASE_SOURCES));
    projectRepository.template(
      project,
      getPath(packageNamePath, "src"),
      "UserConstants.java",
      getPath(MAIN_JAVA, "UserConstants.java", LIQUIBASE_SOURCES)
    );
  }

  @Override
  public void addLiquibaseConfiguration(Project project) {
    // Update liquibase master file
    liquibaseDomainService.addChangelogXml(project, USER_FOLDER, "users.xml");

    // Copy liquibase files
    projectRepository.add(project, getPath(SOURCE, "resources"), "user.xml", getPath(MAIN_RESOURCES, TARGET_RESOURCE + "/" + USER_FOLDER));
    projectRepository.add(project, getPath(SOURCE, "resources"), "users.csv", getPath(MAIN_RESOURCES, TARGET_RESOURCE + "/" + USER_FOLDER));
    projectRepository.add(
      project,
      getPath(SOURCE, "resources"),
      "user_authority.csv",
      getPath(MAIN_RESOURCES, TARGET_RESOURCE + "/" + USER_FOLDER)
    );
    projectRepository.add(
      project,
      getPath(SOURCE, "resources"),
      "authority.csv",
      getPath(MAIN_RESOURCES, TARGET_RESOURCE + "/" + USER_FOLDER)
    );
  }
}
