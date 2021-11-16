package tech.jhipster.light.generator.server.springboot.core.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.light.TestUtils.*;
import static tech.jhipster.light.generator.buildtool.maven.domain.MavenDomainService.POM_XML;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.error.domain.GeneratorException;
import tech.jhipster.light.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.light.generator.init.application.InitApplicationService;
import tech.jhipster.light.generator.project.domain.Project;

@IntegrationTest
class SpringBootApplicationServiceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    project.addConfig("springBootVersion", "2.5.3");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    mavenApplicationService.addMavenWrapper(project);

    springBootApplicationService.init(project);

    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-parent</artifactId>");
    assertFileContent(project, "pom.xml", "<version>2.5.3</version>");

    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter</artifactId>");
    assertFileContent(project, "pom.xml", "<groupId>org.apache.commons</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>commons-lang3</artifactId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-test</artifactId>");

    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-maven-plugin</artifactId>");

    assertFileExist(project, "src/main/java/com/mycompany/myapp/JhipsterApp.java");
    assertFileExist(project, "src/test/java/com/mycompany/myapp/JhipsterAppIT.java");

    assertFileExist(project, "src/main/resources/config/application.properties");
  }

  @Test
  void shouldAddSpringBootParent() {
    Project project = tmpProject();
    project.addConfig("springBootVersion", "2.5.3");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    springBootApplicationService.addSpringBootParent(project);
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-parent</artifactId>");
    assertFileContent(project, "pom.xml", "<version>2.5.3</version>");

    // add again the parent, with wrong version
    project.addConfig("springBootVersion", "X.X.X");
    springBootApplicationService.addSpringBootParent(project);
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-parent</artifactId>");
    assertFileNoContent(project, "pom.xml", "<version>X.X.X</version>");
  }

  @Test
  void shouldNotAddSpringBootParentWhenNoPomXml() {
    Project project = tmpProject();

    assertThatThrownBy(() -> springBootApplicationService.addSpringBootParent(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddSpringBootDependencies() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    springBootApplicationService.addSpringBootDependencies(project);

    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter</artifactId>");

    assertFileContent(project, "pom.xml", "<groupId>org.apache.commons</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>commons-lang3</artifactId>");

    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-test</artifactId>");
  }

  @Test
  void shouldNotAddSpringBootDependenciesWhenNoPomXml() {
    Project project = tmpProject();

    assertThatThrownBy(() -> springBootApplicationService.addSpringBootDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddSpringBootPlugin() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    springBootApplicationService.addSpringBootMavenPlugin(project);

    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<plugin>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-maven-plugin</artifactId>",
        "<version>${spring-boot.version}</version>",
        "</plugin>"
      )
    );
    assertFileContent(project, POM_XML, "<spring-boot.version>");
    assertFileContent(project, POM_XML, "</spring-boot.version>");
  }

  @Test
  void shouldNotAddSpringBootPluginWhenNoPomXml() {
    Project project = tmpProject();

    assertThatThrownBy(() -> springBootApplicationService.addSpringBootMavenPlugin(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddMainApp() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    springBootApplicationService.addMainApp(project);

    assertFileExist(project, "src/main/java/com/mycompany/myapp/JhipsterApp.java");
    assertFileExist(project, "src/test/java/com/mycompany/myapp/JhipsterAppIT.java");
  }

  @Test
  void shouldAddApplicationProperties() {
    Project project = tmpProject();
    initApplicationService.init(project);

    springBootApplicationService.addApplicationProperties(project);

    assertFileExist(project, "src/main/resources/config/application.properties");
  }

  @Test
  void shouldAddApplicationTestProperties() {
    Project project = tmpProject();
    initApplicationService.init(project);

    springBootApplicationService.addApplicationTestProperties(project);

    assertFileExist(project, "src/test/resources/config/application.properties");
  }
}
