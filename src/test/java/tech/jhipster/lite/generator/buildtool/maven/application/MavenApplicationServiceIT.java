package tech.jhipster.lite.generator.buildtool.maven.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.buildtool.maven.application.MavenAssertFiles.*;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.buildtool.maven.domain.Maven;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class MavenApplicationServiceIT {

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Test
  void shouldAddParent() {
    Project project = tmpProjectWithPomXml();

    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
    mavenApplicationService.addParent(project, parent);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<parent>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-parent</artifactId>",
        "<version>2.5.3</version>",
        "<relativePath/>",
        "</parent>"
      )
    );
  }

  @Test
  void shouldNotAddParentWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();

    assertThatThrownBy(() -> mavenApplicationService.addParent(project, parent)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddParentOnlyOneTime() throws Exception {
    Project project = tmpProjectWithPomXml();

    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
    mavenApplicationService.addParent(project, parent);
    mavenApplicationService.addParent(project, parent);

    assertFileContentManyTimes(project, POM_XML, Maven.getParentHeader(parent), 1);
  }

  @Test
  void shouldAddDependency() {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
    mavenApplicationService.addDependency(project, dependency);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter</artifactId>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldNotAddDependencyWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();

    assertThatThrownBy(() -> mavenApplicationService.addDependency(project, dependency)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddDependencyOptional() {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-devtools").optional().build();
    mavenApplicationService.addDependency(project, dependency);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-devtools</artifactId>",
        "<optional>true</optional>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldAddDependencyWithScopeTest() {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-test")
      .scope("test")
      .build();
    mavenApplicationService.addDependency(project, dependency);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-test</artifactId>",
        "<scope>test</scope>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldAddDependencyWithExclusions() {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    Dependency dependencyToExclude = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-tomcat")
      .build();
    mavenApplicationService.addDependency(project, dependency, List.of(dependencyToExclude));

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-web</artifactId>",
        "<exclusions>",
        "<exclusion>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-tomcat</artifactId>",
        "</exclusion>",
        "</exclusions>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldAddDependencyOnlyOneTime() throws Exception {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
    mavenApplicationService.addDependency(project, dependency);
    mavenApplicationService.addDependency(project, dependency);

    assertFileContentManyTimes(
      project,
      POM_XML,
      Maven.getDependencyHeader(dependency, 2).indent(4).replace("\n", System.lineSeparator()),
      1
    );
  }

  @Test
  void shouldAddDependencyManagement() {
    Project project = tmpProjectWithPomXml();

    Dependency dependency = Dependency
      .builder()
      .groupId("org.springframework.cloud")
      .artifactId("spring-cloud-starter-bootstrap")
      .version("\\${spring-cloud.version}")
      .scope("import")
      .type("pom")
      .build();
    mavenApplicationService.addDependencyManagement(project, dependency);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.cloud</groupId>",
        "<artifactId>spring-cloud-starter-bootstrap</artifactId>",
        "<version>${spring-cloud.version}</version>",
        "<scope>import</scope>",
        "<type>pom</type>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldDeleteDependency() {
    Project project = tmpProjectWithPomXml();

    Dependency dependencyToAdd = Dependency
      .builder()
      .groupId("my.group.id")
      .artifactId("my-dependency")
      .version("\\${my-dependency.version}")
      .scope("test")
      .build();

    mavenApplicationService.addDependency(project, dependencyToAdd);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>my.group.id</groupId>",
        "<artifactId>my-dependency</artifactId>",
        "<version>${my-dependency.version}</version>",
        "<scope>test</scope>",
        "</dependency>"
      )
    );

    Dependency dependencyToDelete = Dependency.builder().groupId("my.group.id").artifactId("my-dependency").build();

    mavenApplicationService.deleteDependency(project, dependencyToDelete);

    assertFileNoContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>my.group.id</groupId>",
        "<artifactId>my-dependency</artifactId>",
        "<version>${my-dependency.version}</version>",
        "<scope>test</scope>",
        "</dependency>"
      )
    );
  }

  @Test
  void shouldNotDeleteDependencyWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();

    assertThatThrownBy(() -> mavenApplicationService.deleteDependency(project, dependency)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddPlugin() {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenApplicationService.addPlugin(project, plugin);

    assertFileContent(
      project,
      POM_XML,
      List.of("<plugin>", "<groupId>org.springframework.boot</groupId>", "<artifactId>spring-boot-maven-plugin</artifactId>", "</plugin>")
    );
  }

  @Test
  void shouldAddPluginWithAdditionalElements() {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-maven-plugin")
      .additionalElements("""
      <executions>
        <execution>
          <goal>clean</goal>
        </execution>
      </executions>""")
      .build();
    mavenApplicationService.addPlugin(project, plugin);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<plugin>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-maven-plugin</artifactId>",
        "<executions>",
        "<execution>",
        "<goal>clean</goal>",
        "</execution>",
        "</executions>",
        "</plugin>"
      )
    );
  }

  @Test
  void shouldAddPluginInPluginManagement() {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-maven-plugin")
      .additionalElements("""
      <executions>
        <execution>
          <goal>clean</goal>
        </execution>
      </executions>""")
      .build();
    mavenApplicationService.addPluginManagement(project, plugin);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<plugin>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-maven-plugin</artifactId>",
        "<executions>",
        "<execution>",
        "<goal>clean</goal>",
        "</execution>",
        "</executions>",
        "</plugin>",
        Maven.NEEDLE_PLUGIN_MANAGEMENT
      )
    );
  }

  @Test
  void shouldAddPluginInPluginManagementWithAdditonalElements() {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenApplicationService.addPluginManagement(project, plugin);

    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<plugin>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-maven-plugin</artifactId>",
        "</plugin>",
        Maven.NEEDLE_PLUGIN_MANAGEMENT
      )
    );
  }

  @Test
  void shouldNotAddPluginWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();

    assertThatThrownBy(() -> mavenApplicationService.addPlugin(project, plugin)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddPluginOnlyOneTime() throws Exception {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenApplicationService.addPlugin(project, plugin);
    mavenApplicationService.addPlugin(project, plugin);

    assertFileContentManyTimes(project, POM_XML, Maven.getPluginHeader(plugin), 1);
  }

  @Test
  void shouldAddPluginManagementOnlyOneTime() throws Exception {
    Project project = tmpProjectWithPomXml();

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    mavenApplicationService.addPluginManagement(project, plugin);
    mavenApplicationService.addPluginManagement(project, plugin);

    assertFileContentManyTimes(project, POM_XML, Maven.getPluginManagementHeader(plugin), 1);
  }

  @Test
  void shouldAddPluginManagementWithExistingPlugin() throws Exception {
    Project project = tmpProjectWithPomXml();
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();

    mavenApplicationService.addPlugin(project, plugin);
    mavenApplicationService.addPluginManagement(project, plugin);

    assertFileContentManyTimes(project, POM_XML, Maven.getPluginHeader(plugin), 1);
    assertFileContentManyTimes(project, POM_XML, Maven.getPluginManagementHeader(plugin), 1);
  }

  @Test
  void shouldAddProperty() {
    Project project = tmpProjectWithPomXml();

    mavenApplicationService.addProperty(project, "testcontainers", "1.16.0");

    assertFileContent(project, POM_XML, "    <testcontainers.version>1.16.0</testcontainers.version>");
  }

  @Test
  void shouldNotAddPropertyWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());

    assertThatThrownBy(() -> mavenApplicationService.addProperty(project, "testcontainers", "1.16.0"))
      .isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddPropertyOnlyOneTime() throws Exception {
    Project project = tmpProjectWithPomXml();

    String key = "testcontainers";
    String version = "1.16.0";
    mavenApplicationService.addProperty(project, key, version);
    mavenApplicationService.addProperty(project, key, version);

    assertFileContentManyTimes(project, POM_XML, Maven.getProperty(key, ".*"), 1);
  }

  @Test
  void shouldDeleteProperty() {
    Project project = tmpProjectWithPomXml();

    mavenApplicationService.addProperty(project, "my-key", "1.0");

    assertFileContent(project, POM_XML, "    <my-key.version>1.0</my-key.version>");

    mavenApplicationService.deleteProperty(project, "my-key");

    assertFileNoContent(project, POM_XML, "    <my-key.version>1.0</my-key.version>");
  }

  @Test
  void shouldNotDeletePropertyWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getFolder());

    assertThatThrownBy(() -> mavenApplicationService.deleteProperty(project, "java")).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldInit() {
    Project project = tmpProject();

    mavenApplicationService.init(project);

    assertFilesMaven(project);
    assertFileContent(project, POM_XML, "<name>jhipster</name>");
    assertFileContent(project, POM_XML, "<description>JHipster Project</description>");
  }

  @Test
  void shouldAddPomXml() {
    Project project = tmpProject();

    mavenApplicationService.addPomXml(project);

    assertFilesPomXml(project);
    assertFileContent(project, POM_XML, "<name>jhipster</name>");
    assertFileContent(project, POM_XML, "<description>JHipster Project</description>");
  }

  @Test
  void shouldAddMavenWrapper() {
    Project project = tmpProject();

    mavenApplicationService.addMavenWrapper(project);

    assertFilesMavenWrapper(project);
  }
}
