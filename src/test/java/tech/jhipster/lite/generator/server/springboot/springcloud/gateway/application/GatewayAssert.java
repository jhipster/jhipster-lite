package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import java.util.List;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;

public class GatewayAssert {

  public static void assertDependencies(Project project) {
    assertFileContent(project, "pom.xml", "<spring-cloud.version>");

    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.springframework.cloud</groupId>",
        "<artifactId>spring-cloud-dependencies</artifactId>",
        "<version>${spring-cloud.version}</version>",
        "<scope>import</scope>",
        "<type>pom</type>",
        "</dependency>"
      )
    );
    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.springframework.cloud</groupId>",
        "<artifactId>spring-cloud-starter-bootstrap</artifactId>",
        "</dependency>"
      )
    );
    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.springframework.cloud</groupId>",
        "<artifactId>spring-cloud-starter-gateway</artifactId>",
        "</dependency>"
      )
    );
  }

  public static void assertProperties(Project project) {
    assertFileExist(project, MAIN_RESOURCES, "config/bootstrap.properties");
    assertFileExist(project, TEST_RESOURCES, "config/bootstrap.properties");

    String baseName = project.getBaseName().orElseThrow();
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config/bootstrap.properties"),
      List.of(
        "spring.application.name=" + baseName,
        "spring.cloud.gateway.discovery.locator.enabled=true",
        "spring.cloud.gateway.discovery.locator.lower-case-service-id=true",
        "spring.cloud.gateway.discovery.locator.predicates[0].name: Path",
        "spring.cloud.gateway.discovery.locator.predicates[0].args[pattern]: \"'/services/'+serviceId.toLowerCase()+'/**'\"",
        "spring.cloud.gateway.discovery.locator.filters[0].name: RewritePath",
        "spring.cloud.gateway.discovery.locator.filters[0].args[regexp]: \"'/services/' + serviceId.toLowerCase() + '/(?<remaining>.*)'\"",
        "spring.cloud.gateway.discovery.locator.filters[0].args[replacement]: \"'/${remaining}'\""
      )
    );

    assertFileContent(
      project,
      getPath(TEST_RESOURCES, "config/bootstrap.properties"),
      "spring.cloud.gateway.discovery.locator.enabled=false"
    );
  }

  public static void assertJavaFiles(Project project) {
    String packageNamePath = project.getPackageNamePath().orElse(DefaultConfig.PACKAGE_PATH);
    String packageName = project.getPackageName().orElse(DefaultConfig.DEFAULT_PACKAGE_NAME);

    String primaryResourcePath = getPath(packageNamePath, "/technical/infrastructure/primary/rest");
    assertFileExist(project, getPath(MAIN_JAVA, primaryResourcePath, "GatewayResource.java"));
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, primaryResourcePath, "GatewayResource.java"),
      "package " + packageName + ".technical.infrastructure.primary.rest;"
    );

    String primaryResourceVMPath = getPath(packageNamePath, "/technical/infrastructure/primary/rest/vm");
    assertFileExist(project, getPath(MAIN_JAVA, primaryResourceVMPath, "RouteVM.java"));
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, primaryResourceVMPath, "RouteVM.java"),
      "package " + packageName + ".technical.infrastructure.primary.rest.vm;"
    );
  }
}
