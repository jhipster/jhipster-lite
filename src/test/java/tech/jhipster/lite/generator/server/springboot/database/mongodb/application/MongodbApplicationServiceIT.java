package tech.jhipster.lite.generator.server.springboot.database.mongodb.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_CONFIGURATION;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_TEST_CONFIGURATION;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class MongodbApplicationServiceIT {

  @Autowired
  MongodbApplicationService mongodbApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    mongodbApplicationService.init(project);

    assertFileContent(project, POM_XML, springBootStarterDataMongodb());
    assertFileContent(project, POM_XML, mongodbDriver());

    assertFileExist(project, "src/main/docker/mongodb.yml");
    assertFileContent(project, "src/main/docker/mongodb.yml", "- 127.0.0.1:27017:27017");

    String packageNamePath = getPath("com/mycompany/myapp");
    String mongodbPath = getPath("com/mycompany/myapp/technical/infrastructure/secondary/mongodb");
    assertFileExist(project, getPath(MAIN_JAVA, mongodbPath, "MongodbDatabaseConfiguration.java"));
    assertFileExist(project, getPath(MAIN_JAVA, mongodbPath, "JSR310DateConverters.java"));
    assertFileExist(project, getPath(TEST_JAVA, mongodbPath, "JSR310DateConvertersTest.java"));
    assertFileExist(project, getPath(TEST_JAVA, packageNamePath, "MongodbTestContainerExtension.java"));
    assertFileExist(project, getPath(TEST_JAVA, packageNamePath, "TestContainersSpringContextCustomizerFactory.java"));
    assertFileContent(
      project,
      getPath(TEST_JAVA, packageNamePath, "IntegrationTest.java"),
      "import org.junit.jupiter.api.extension.ExtendWith;"
    );
    assertFileContent(
      project,
      getPath(TEST_JAVA, packageNamePath, "IntegrationTest.java"),
      "@ExtendWith(MongodbTestContainerExtension.class)"
    );

    assertFileExist(project, getPath(TEST_RESOURCES, "META-INF", "spring.factories"));
    assertFileContent(
      project,
      getPath(TEST_RESOURCES, "META-INF", "spring.factories"),
      "org.springframework.test.context.ContextCustomizerFactory = com.mycompany.myapp.TestContainersSpringContextCustomizerFactory"
    );

    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES),
      List.of("spring.data.mongodb.database=jhipster", "spring.data.mongodb.uri=mongodb://localhost:27017")
    );
    assertFileContent(project, POM_XML, "<testcontainers.version>");
    assertFileContent(project, POM_XML, "</testcontainers.version>");
    assertFileContent(project, POM_XML, testcontainers());
    assertLoggerInConfig(project);
  }

  @Test
  @DisplayName("should add Spring Data MongoDB dependency")
  void shouldAddSpringDataMongodb() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    mongodbApplicationService.addSpringDataMongodb(project);

    assertFileContent(project, POM_XML, springBootStarterDataMongodb());
  }

  @Test
  @DisplayName("should add mongodb driver")
  void shouldAddMongodbDriver() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    mongodbApplicationService.addMongodbDriver(project);

    assertFileContent(project, POM_XML, mongodbDriver());
  }

  @Test
  @DisplayName("should add Docker compose")
  void shouldAddDockerCompose() {
    Project project = tmpProject();

    mongodbApplicationService.addDockerCompose(project);

    assertFileExist(project, "src/main/docker/mongodb.yml");
    assertFileContent(project, "src/main/docker/mongodb.yml", "- 127.0.0.1:27017:27017");
  }

  @Test
  @DisplayName("should add Java files")
  void shouldAddJavaFiles() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "chips");
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");

    mongodbApplicationService.addJavaFiles(project);

    assertFileExist(
      project,
      getPath(MAIN_JAVA, "tech/jhipster/chips", "/technical/infrastructure/secondary/mongodb/JSR310DateConverters.java")
    );
    assertFileExist(
      project,
      getPath(TEST_JAVA, "tech/jhipster/chips", "/technical/infrastructure/secondary/mongodb/JSR310DateConvertersTest.java")
    );
    assertFileExist(project, getPath(TEST_JAVA, "tech/jhipster/chips", "MongodbTestContainerExtension.java"));
    assertFileExist(project, getPath(TEST_JAVA, "tech/jhipster/chips", "TestContainersSpringContextCustomizerFactory.java"));
  }

  @Test
  @DisplayName("should add Configuration files")
  void shouldAddConfigurationFiles() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "chips");

    mongodbApplicationService.addConfigurationFiles(project);

    assertFileExist(project, getPath(TEST_RESOURCES, "META-INF", "spring.factories"));
  }

  @Test
  @DisplayName("should add properties")
  void shouldAddProperties() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");
    project.addConfig(BASE_NAME, "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    mongodbApplicationService.addProperties(project);

    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES),
      List.of("spring.data.mongodb.database=chips", "spring.data.mongodb.uri=mongodb://localhost:27017")
    );
  }

  @Test
  @DisplayName("should add logging configuration")
  void shouldAddLoggingConfiguration() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");
    project.addConfig(BASE_NAME, "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    mongodbApplicationService.addLogger(project);

    assertLoggerInConfig(project);
  }

  @Test
  @DisplayName("should add testcontainers")
  void shouldAddTestcontainers() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");
    project.addConfig(BASE_NAME, "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.addApplicationTestProperties(project);

    mongodbApplicationService.addTestContainers(project);

    assertFileContent(project, POM_XML, "<testcontainers.version>");
    assertFileContent(project, POM_XML, "</testcontainers.version>");
    assertFileContent(project, POM_XML, testcontainers());
  }

  private void assertLoggerInConfig(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, LOGGING_CONFIGURATION),
      List.of("<logger name=\"org.reflections\" level=\"WARN\" />", "<logger name=\"org.mongodb.driver\" level=\"WARN\" />")
    );

    assertFileContent(
      project,
      getPath(TEST_RESOURCES, LOGGING_TEST_CONFIGURATION),
      List.of(
        "<logger name=\"org.reflections\" level=\"WARN\" />",
        "<logger name=\"org.mongodb.driver\" level=\"WARN\" />",
        "<logger name=\"com.github.dockerjava\" level=\"WARN\" />",
        "<logger name=\"org.testcontainers\" level=\"WARN\" />"
      )
    );
  }

  private List<String> springBootStarterDataMongodb() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-data-mongodb</artifactId>",
      "</dependency>"
    );
  }

  private List<String> mongodbDriver() {
    return List.of("<dependency>", "<groupId>org.mongodb</groupId>", "<artifactId>mongodb-driver-sync</artifactId>", "</dependency>");
  }

  private List<String> testcontainers() {
    return List.of(
      "<dependency>",
      "<groupId>org.testcontainers</groupId>",
      "<artifactId>mongodb</artifactId>",
      "<version>${testcontainers.version}</version>",
      "<scope>test</scope>",
      "</dependency>"
    );
  }
}
