package tech.jhipster.lite.generator.server.sonar.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.sonar.domain.Sonar;

@IntegrationTest
class SonarApplicationServiceIT {

  @Autowired
  SonarApplicationService sonarApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    sonarApplicationService.init(project);

    assertFileExist(project, "src/main/docker/sonar.yml");
    assertFileExist(project, "sonar-project.properties");

    assertFileContent(project, POM_XML, "<properties-maven-plugin.version>");
    assertFileContent(project, POM_XML, "</properties-maven-plugin.version>");

    assertFileContent(project, POM_XML, "<sonar-maven-plugin.version>");
    assertFileContent(project, POM_XML, "</sonar-maven-plugin.version>");

    assertFileContent(project, POM_XML, sonarSourcePlugin());

    assertFileContent(project, POM_XML, propertiesPlugin());
  }

  private List<String> propertiesPlugin() {
    return List.of(
      "<plugin>",
      "<groupId>org.codehaus.mojo</groupId>",
      "<artifactId>properties-maven-plugin</artifactId>",
      "<version>${properties-maven-plugin.version}</version>",
      "<executions>",
      "<execution>",
      "<phase>initialize</phase>",
      "<goals>",
      "<goal>read-project-properties</goal>",
      "</goals>",
      "<configuration>",
      "<files>",
      "<file>sonar-project.properties</file>",
      "</files>",
      "</configuration>",
      "</execution>",
      "</executions>",
      "</plugin>"
    );
  }

  private List<String> sonarSourcePlugin() {
    return List.of(
      "<plugin>",
      "<groupId>org.sonarsource.scanner.maven</groupId>",
      "<artifactId>sonar-maven-plugin</artifactId>",
      "<version>${sonar-maven-plugin.version}</version>",
      "</plugin>"
    );
  }
}
