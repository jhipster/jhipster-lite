package tech.jhipster.forge.generator.domain.server.springboot.dbmigration.liquibase;

import static tech.jhipster.forge.generator.domain.core.Constants.*;
import static tech.jhipster.forge.generator.domain.core.FileUtils.getPath;

import tech.jhipster.forge.generator.domain.buildtool.Dependency;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.domain.core.ProjectRepository;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;

public class LiquibaseDomainService implements LiquibaseService {

  public static final String SOURCE = "liquibase";
  public static final String LIQUIBASE_PATH = "technical/secondary/liquibase";

  public final ProjectRepository projectRepository;
  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public LiquibaseDomainService(ProjectRepository projectRepository, MavenService mavenService, SpringBootService springBootService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Override
  public void init(Project project) {
    addLiquibase(project);
    addChangelogMasterXml(project);
    addConfigurationJava(project);
  }

  @Override
  public void addLiquibase(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.liquibase").artifactId("liquibase-core").build();
    mavenService.addDependency(project, dependency);
  }

  @Override
  public void addChangelogMasterXml(Project project) {
    projectRepository.add(project, getPath(SOURCE, "resources"), "master.xml", getPath(MAIN_RESOURCES, "config/liquibase"));
  }

  @Override
  public void addConfigurationJava(Project project) {
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));

    templateToLiquibase(project, packageNamePath, "src", "AsyncSpringLiquibase.java", MAIN_JAVA);
    templateToLiquibase(project, packageNamePath, "src", "LiquibaseConfiguration.java", MAIN_JAVA);
    templateToLiquibase(project, packageNamePath, "src", "SpringLiquibaseUtil.java", MAIN_JAVA);

    templateToLiquibase(project, packageNamePath, "test", "AsyncSpringLiquibaseTest.java", TEST_JAVA);
    templateToLiquibase(project, packageNamePath, "test", "LiquibaseConfigurationIT.java", TEST_JAVA);
    templateToLiquibase(project, packageNamePath, "test", "LogbackRecorder.java", TEST_JAVA);
    templateToLiquibase(project, packageNamePath, "test", "SpringLiquibaseUtilTest.java", TEST_JAVA);
  }

  private void templateToLiquibase(Project project, String source, String type, String sourceFilename, String destination) {
    projectRepository.template(project, getPath(SOURCE, type), sourceFilename, getPath(destination, source, LIQUIBASE_PATH));
  }
}
