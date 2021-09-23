package tech.jhipster.forge.generator.springboot.domain;

import static tech.jhipster.forge.common.domain.Constants.*;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.io.File;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.generator.maven.domain.MavenService;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

public class SpringBootDomainService {

  public static final String SOURCE = "springboot";

  private final ProjectRepository projectRepository;
  private final MavenService mavenService;

  public SpringBootDomainService(ProjectRepository projectRepository, MavenService mavenService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
  }

  public void addSpringBoot(Project project) {
    addSpringBootParent(project);
    addSpringBootDependencies(project);
    addSpringBootMavenPlugin(project);
    addMainApp(project);
    addApplicationProperties(project);
  }

  public void addSpringBootParent(Project project) {
    project.addConfig("springBootVersion", SpringBoot.getVersion());

    Parent parent = Parent
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-parent")
      .version(project.getConfig("springBootVersion").orElse(SpringBoot.SPRING_BOOT_VERSION))
      .build();

    mavenService.addParent(project, parent);
  }

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

  public void addSpringBootMavenPlugin(Project project) {
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenService.addPlugin(project, plugin);
  }

  public void addMainApp(Project project) {
    project.addDefaultConfig("packageName");
    project.addDefaultConfig("baseName");

    String baseName = project.getConfig("baseName").orElse("jhipster");
    String packageName = project.getConfig("packageName").orElse("com.mycompany.myapp");
    String className = WordUtils.upperFirst(baseName);
    project.addConfig("mainClass", className);

    String pathPackageName = packageName.replaceAll("\\.", File.separator);

    projectRepository.template(project, SOURCE, "MainApp.java", getPath(MAIN_JAVA, pathPackageName), className + "App.java");
    projectRepository.template(project, SOURCE, "MainAppIT.java", getPath(TEST_JAVA, pathPackageName), className + "AppIT.java");
  }

  public void addApplicationProperties(Project project) {
    project.addDefaultConfig("baseName");

    projectRepository.template(project, SOURCE, "application.properties", getPath(MAIN_RESOURCES, "config"));
  }
}
