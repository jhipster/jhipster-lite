package tech.jhipster.light.generator.server.springboot.core.domain;

import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.*;
import static tech.jhipster.light.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.light.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.light.common.domain.WordUtils;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.light.generator.buildtool.generic.domain.Parent;
import tech.jhipster.light.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.light.generator.project.domain.*;

public class SpringBootDomainService implements SpringBootService {

  public static final String SOURCE = "server/springboot/core";

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
  }

  @Override
  public void addSpringBootParent(Project project) {
    project.addConfig("springBootVersion", SpringBoot.getVersion());

    Parent parent = Parent
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-parent")
      .version((String) project.getConfig("springBootVersion").orElse(SpringBoot.SPRING_BOOT_VERSION))
      .build();

    buildToolService.addParent(project, parent);
  }

  @Override
  public void addSpringBootDependencies(Project project) {
    Dependency springBootStarterDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter")
      .build();
    buildToolService.addDependency(project, springBootStarterDependency);

    Dependency commonLangDependency = Dependency.builder().groupId("org.apache.commons").artifactId("commons-lang3").build();
    buildToolService.addDependency(project, commonLangDependency);

    Dependency springBootStarterTestDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-test")
      .scope("test")
      .build();
    buildToolService.addDependency(project, springBootStarterTestDependency);
  }

  @Override
  public void addSpringBootMavenPlugin(Project project) {
    Plugin plugin = Plugin
      .builder()
      .groupId("org.springframework.boot")
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
    projectRepository.template(project, SOURCE, "MainAppIT.java", getPath(TEST_JAVA, packageNamePath), className + "AppIT.java");
    projectRepository.template(project, SOURCE, "IntegrationTest.java", getPath(TEST_JAVA, packageNamePath));
  }

  @Override
  public void addApplicationProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application.properties", getPath(MAIN_RESOURCES, "config"));
  }

  @Override
  public void addApplicationFastProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application-fast.properties", getPath(MAIN_RESOURCES, "config"));
  }

  @Override
  public void addApplicationTestProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application-test.properties", getPath(TEST_RESOURCES, "config"), "application.properties");
  }
}
