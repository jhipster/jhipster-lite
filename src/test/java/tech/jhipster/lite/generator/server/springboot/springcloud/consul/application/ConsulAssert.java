package tech.jhipster.lite.generator.server.springboot.springcloud.consul.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain.Consul.getDockerConsulImage;
import static tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain.Consul.getSpringCloudVersion;

import java.util.List;
import tech.jhipster.lite.generator.project.domain.Project;

public class ConsulAssert {

  public static void assertDependencies(Project project) {
    assertFileContent(project, "pom.xml", "<spring-cloud.version>" + getSpringCloudVersion() + "</spring-cloud.version>");

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
        "<artifactId>spring-cloud-starter-consul-discovery</artifactId>",
        "</dependency>"
      )
    );
    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.springframework.cloud</groupId>",
        "<artifactId>spring-cloud-starter-consul-config</artifactId>",
        "</dependency>"
      )
    );
  }

  public static void assertProperties(Project project) {
    assertFileExist(project, MAIN_RESOURCES, "config/bootstrap.properties");
    assertFileExist(project, MAIN_RESOURCES, "config/bootstrap-fast.properties");
    assertFileExist(project, TEST_RESOURCES, "config/bootstrap.properties");

    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config/bootstrap.properties"),
      List.of(
        "spring.cloud.consul.discovery.health-check-path=/management/health",
        "spring.cloud.consul.discovery.tags[0]=version=@project.version@",
        "spring.cloud.consul.discovery.tags[1]=context-path=${server.servlet.context-path:}",
        "spring.cloud.consul.host=localhost",
        "spring.cloud.consul.port=8500"
      )
    );

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/bootstrap-fast.properties"), "spring.cloud.consul.config.fail-fast=false");

    assertFileContent(project, getPath(TEST_RESOURCES, "config/bootstrap.properties"), "spring.cloud.consul.enabled=false");
  }

  public static void assertDockerConsul(Project project) {
    assertFileExist(project, "src/main/docker/consul.yml");
    assertFileContent(project, "src/main/docker/consul.yml", getDockerConsulImage());
    assertFileExist(project, "src/main/docker/central-server-config/application.yml");
  }
}
