package tech.jhipster.forge.generator.domain.server.springboot.core;

import static tech.jhipster.forge.generator.domain.core.Constants.*;
import static tech.jhipster.forge.generator.domain.core.DefaultConfig.BASE_NAME;
import static tech.jhipster.forge.generator.domain.core.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.forge.generator.domain.core.FileUtils.getPath;
import static tech.jhipster.forge.generator.domain.core.FileUtils.read;
import static tech.jhipster.forge.generator.domain.server.springboot.core.SpringBoot.*;

import java.io.File;
import java.io.IOException;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.domain.buildtool.Dependency;
import tech.jhipster.forge.generator.domain.buildtool.Parent;
import tech.jhipster.forge.generator.domain.buildtool.Plugin;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.core.FileUtils;
import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.domain.core.ProjectRepository;
import tech.jhipster.forge.generator.domain.core.WordUtils;

public class SpringBootDomainService implements SpringBootService {

  public static final String SOURCE = "springboot";

  private final ProjectRepository projectRepository;
  private final MavenService mavenService;

  public SpringBootDomainService(ProjectRepository projectRepository, MavenService mavenService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
  }

  @Override
  public void addProperties(Project project, String key, Object value) {
    try {
      String currentApplicationProperties = read(getPath(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES));
      String propertiesWithNeedle = key + "=" + value + System.lineSeparator() + NEEDLE_APPLICATION_PROPERTIES;
      String updatedApplicationProperties = FileUtils.replace(
        currentApplicationProperties,
        NEEDLE_APPLICATION_PROPERTIES,
        propertiesWithNeedle
      );

      projectRepository.write(project, updatedApplicationProperties, getPath(MAIN_RESOURCES, "config"), APPLICATION_PROPERTIES);
    } catch (IOException e) {
      throw new GeneratorException("Error when adding properties");
    }
  }

  @Override
  public void addPropertiesTest(Project project, String key, Object value) {
    try {
      String currentApplicationProperties = read(getPath(project.getPath(), TEST_RESOURCES, "config", APPLICATION_PROPERTIES));
      String propertiesWithNeedle = key + "=" + value + System.lineSeparator() + NEEDLE_APPLICATION_TEST_PROPERTIES;
      String updatedApplicationProperties = FileUtils.replace(
        currentApplicationProperties,
        NEEDLE_APPLICATION_TEST_PROPERTIES,
        propertiesWithNeedle
      );

      projectRepository.write(project, updatedApplicationProperties, getPath(TEST_RESOURCES, "config"), APPLICATION_PROPERTIES);
    } catch (IOException e) {
      throw new GeneratorException("Error when adding properties");
    }
  }

  @Override
  public void init(Project project) {
    addSpringBootParent(project);
    addSpringBootDependencies(project);
    addSpringBootMavenPlugin(project);
    addMainApp(project);
    addApplicationProperties(project);
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

    mavenService.addParent(project, parent);
  }

  @Override
  public void addSpringBootDependencies(Project project) {
    Dependency springBootStarterDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter")
      .build();
    mavenService.addDependency(project, springBootStarterDependency);

    Dependency commonLangDependency = Dependency.builder().groupId("org.apache.commons").artifactId("commons-lang3").build();
    mavenService.addDependency(project, commonLangDependency);

    Dependency springBootStarterTestDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-test")
      .scope("test")
      .build();
    mavenService.addDependency(project, springBootStarterTestDependency);
  }

  @Override
  public void addSpringBootMavenPlugin(Project project) {
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenService.addPlugin(project, plugin);
  }

  @Override
  public void addMainApp(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);

    String baseName = project.getBaseName().orElse("jhipster");
    String packageName = project.getPackageName().orElse("com.mycompany.myapp");
    String className = WordUtils.upperFirst(baseName);
    project.addConfig("mainClass", className);

    String pathPackageName = packageName.replaceAll("\\.", File.separator);

    projectRepository.template(project, SOURCE, "MainApp.java", getPath(MAIN_JAVA, pathPackageName), className + "App.java");
    projectRepository.template(project, SOURCE, "MainAppIT.java", getPath(TEST_JAVA, pathPackageName), className + "AppIT.java");
  }

  @Override
  public void addApplicationProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application.properties", getPath(MAIN_RESOURCES, "config"));
  }

  @Override
  public void addApplicationTestProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE, "application-test.properties", getPath(TEST_RESOURCES, "config"), "application.properties");
  }
}
