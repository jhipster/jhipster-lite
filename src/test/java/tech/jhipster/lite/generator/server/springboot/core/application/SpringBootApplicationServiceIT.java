package tech.jhipster.lite.generator.server.springboot.core.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;

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

    assertFileExist(project, getPath(MAIN_JAVA, "com/mycompany/myapp/JhipsterApp.java"));
    assertFileExist(project, getPath(TEST_JAVA, "com/mycompany/myapp/JhipsterAppIT.java"));
    assertFileExist(project, getPath(TEST_JAVA, "com/mycompany/myapp", "IntegrationTest.java"));

    assertFileExist(project, getPath(MAIN_RESOURCES, "config/application.properties"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/application-fast.properties"));
    assertFileExist(project, getPath(TEST_RESOURCES, "config/application.properties"));
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

    assertFileExist(project, getPath(MAIN_RESOURCES, "config/application.properties"));
  }

  @Test
  void shouldAddApplicationFastProperties() {
    Project project = tmpProject();
    initApplicationService.init(project);

    springBootApplicationService.addApplicationFastProperties(project);

    assertFileExist(project, getPath(MAIN_RESOURCES, "config/application-fast.properties"));
  }

  @Test
  void shouldAddApplicationTestProperties() {
    Project project = tmpProject();
    initApplicationService.init(project);

    springBootApplicationService.addApplicationTestProperties(project);

    assertFileExist(project, getPath(TEST_RESOURCES, "config/application.properties"));
  }
}
