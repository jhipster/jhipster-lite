package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import java.util.List;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringDoc;

public class SpringDocAssert {

  public static final String SPRING_DOC_CONFIG_JAVA_FILE_NAME = "SpringDocConfiguration.java";

  public static void assertDependencies(Project project) {
    TestUtils.assertFileContent(
      project,
      POM_XML,
      List.of("<springdoc-openapi-ui.version>" + SpringDoc.springDocVersion() + "</springdoc-openapi-ui.version>")
    );
    TestUtils.assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springdoc</groupId>",
        "<artifactId>springdoc-openapi-ui</artifactId>",
        "<version>${springdoc-openapi-ui.version}</version>",
        "</dependency>"
      )
    );
  }

  public static void assertJavaFiles(Project project) {
    String basePackage = project.getPackageName().orElse("com.mycompany.myapp");
    String springDocPackage = basePackage + ".technical.infrastructure.secondary.springdoc";

    String basePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    String springDocPath = getPath(MAIN_JAVA, basePath, "technical/infrastructure/secondary/springdoc");

    String springDocConfigJavaFilePath = getPath(springDocPath, SPRING_DOC_CONFIG_JAVA_FILE_NAME);
    assertFileExist(project, springDocConfigJavaFilePath);
    TestUtils.assertFileContent(project, springDocConfigJavaFilePath, "package " + springDocPackage);
  }

  public static void assertProperties(Project project) {
    TestUtils.assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config/application.properties"),
      List.of("springdoc.swagger-ui.operationsSorter=alpha", "springdoc.swagger-ui.tagsSorter=alpha")
    );
  }

  public static void assertFileContent(Project project, String filename, String expectedValue) {
    String basePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    String springDocPath = getPath(MAIN_JAVA, basePath, "technical/infrastructure/secondary/springdoc");

    TestUtils.assertFileContent(project, getPath(springDocPath, filename), expectedValue);
  }
}
