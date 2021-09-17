package tech.jhipster.forge.generator.springboot.secondary;

import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.io.File;
import org.springframework.stereotype.Repository;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.secondary.ProjectRepository;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.generator.maven.domain.Dependency;
import tech.jhipster.forge.generator.maven.domain.Maven;
import tech.jhipster.forge.generator.maven.domain.Parent;
import tech.jhipster.forge.generator.maven.domain.Plugin;
import tech.jhipster.forge.generator.springboot.domain.SpringBoot;
import tech.jhipster.forge.generator.springboot.domain.SpringBoots;

@Repository
public class SpringBootRepository extends ProjectRepository implements SpringBoots {

  public static final String SOURCE = "springboot";

  @Override
  public void addSpringBoot(Project project) {
    addSpringBootParent(project);
    addSpringBootDependencies(project);
    addSpringBootMavenPlugin(project);
    addMainApp(project);
    addApplicationProperties(project);
  }

  @Override
  public void addSpringBootParent(Project project) {
    project.addConfig("springBootVersion", SpringBoot.getVersion());

    Parent parent = Parent
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-parent")
      .version(project.getConfig("springBootVersion").orElse(SpringBoot.SPRING_BOOT_VERSION))
      .build();
    Maven.addParent(project, parent);
  }

  @Override
  public void addSpringBootDependencies(Project project) {
    Dependency springBootStarterDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter")
      .build();
    Maven.addDependency(project, springBootStarterDependency);

    Dependency commonLangDependency = Dependency.builder().groupId("org.apache.commons").artifactId("commons-lang3").build();
    Maven.addDependency(project, commonLangDependency);

    Dependency springBootStarterTestDependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-test")
      .scope("test")
      .build();
    Maven.addDependency(project, springBootStarterTestDependency);
  }

  @Override
  public void addSpringBootMavenPlugin(Project project) {
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    Maven.addPlugin(project, plugin);
  }

  @Override
  public void addMainApp(Project project) {
    project.addDefaultConfig("packageName");
    project.addDefaultConfig("baseName");

    String baseName = project.getConfig("baseName").orElse("jhipster");
    String packageName = project.getConfig("packageName").orElse("com.mycompany.myapp");
    String className = WordUtils.upperFirst(baseName);
    project.addConfig("mainClass", className);

    String pathPackageName = packageName.replaceAll("\\.", File.separator);

    template(project, SOURCE, "MainApp.java", getPath("src/main/java", pathPackageName), className + "App.java");
    template(project, SOURCE, "MainAppIT.java", getPath("src/test/java", pathPackageName), className + "AppIT.java");
  }

  @Override
  public void addApplicationProperties(Project project) {
    project.addDefaultConfig("baseName");

    template(project, SOURCE, "application.properties", getPath("src/main/resources/config"));
  }
}
