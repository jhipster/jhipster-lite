package tech.jhipster.lite.generator.server.springboot.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_CONFIGURATION;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_TEST_CONFIGURATION;

import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootDomainService implements SpringBootService {

  public static final String SOURCE = "server/springboot/core";
  public static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public SpringBootDomainService(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Override
  public void init(Project project) {
    addSpringBootParent(project);
    addSpringBootDependencies(project);
    addSpringBootMavenPlugin(project);
    addMainApp(project);
    addApplicationProperties(project);
    addApplicationFastProperties(project);
    addApplicationTestProperties(project);
    addLoggingConfiguration(project);
    addLoggingTestConfiguration(project);
  }

  @Override
  public void addSpringBootParent(Project project) {
    project.addConfig("springBootVersion", SpringBoot.getVersion());

    Parent parent = Parent
      .builder()
      .groupId(SPRINGBOOT_PACKAGE)
      .artifactId("spring-boot-starter-parent")
      .version((String) project.getConfig("springBootVersion").orElse(SpringBoot.SPRING_BOOT_VERSION))
      .build();

    buildToolService.addParent(project, parent);
  }

  @Override
  public void addSpringBootDependencies(Project project) {
    Dependency springBootStarterDependency = Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter").build();
    buildToolService.addDependency(project, springBootStarterDependency);

    Dependency springBootConfigurationProcessor = Dependency
      .builder()
      .groupId(SPRINGBOOT_PACKAGE)
      .artifactId("spring-boot-configuration-processor")
      .optional()
      .build();
    buildToolService.addDependency(project, springBootConfigurationProcessor);

    Dependency commonLangDependency = Dependency.builder().groupId("org.apache.commons").artifactId("commons-lang3").build();
    buildToolService.addDependency(project, commonLangDependency);

    Dependency springBootStarterTestDependency = Dependency
      .builder()
      .groupId(SPRINGBOOT_PACKAGE)
      .artifactId("spring-boot-starter-test")
      .scope("test")
      .build();
    buildToolService.addDependency(project, springBootStarterTestDependency);
  }

  @Override
  public void addSpringBootMavenPlugin(Project project) {
    Plugin plugin = Plugin
      .builder()
      .groupId(SPRINGBOOT_PACKAGE)
      .artifactId("spring-boot-maven-plugin")
      .version("\\${spring-boot.version}")
      .build();
    buildToolService.addProperty(project, "spring-boot", SpringBoot.getVersion());
    buildToolService.addPlugin(project, plugin);
  }

  @Override
  public void addMainApp(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);

    String baseName = project.getBaseName().orElse("jhipster");
    String className = WordUtils.upperFirst(baseName);
    project.addConfig("mainClass", className);

    String packageNamePath = project.getPackageNamePath().orElse("com/mycompany/myapp");

    projectRepository.template(project, SOURCE, "MainApp.java", getPath(MAIN_JAVA, packageNamePath), className + "App.java");
    projectRepository.template(project, SOURCE, "MainAppTest.java", getPath(TEST_JAVA, packageNamePath), className + "AppTest.java");
    projectRepository.template(project, SOURCE, "IntegrationTest.java", getPath(TEST_JAVA, packageNamePath));
  }

  @Override
  public void addApplicationProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
  }

  @Override
  public void addApplicationFastProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application-fast.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
  }

  @Override
  public void addApplicationTestProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(
      project,
      SOURCE,
      "application-test.properties",
      getPath(TEST_RESOURCES, CONFIG_FOLDER),
      "application.properties"
    );
  }

  @Override
  public void addLoggingConfiguration(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    projectRepository.template(project, SOURCE, LOGGING_CONFIGURATION, getPath(MAIN_RESOURCES));
  }

  @Override
  public void addLoggingTestConfiguration(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    projectRepository.template(project, SOURCE, LOGGING_TEST_CONFIGURATION, getPath(TEST_RESOURCES));
  }
}
