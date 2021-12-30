package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import java.util.List;
import tech.jhipster.lite.generator.project.domain.Project;

public class OAuth2SecurityAssert {

  private OAuth2SecurityAssert() {}

  // TODO security commons
  public static void assertSecurityDependencies(Project project) {
    assertFileContent(project, POM_XML, securityDependency());
    assertFileContent(project, POM_XML, securityTestDependency());
  }

  public static void assertOAuth2ClientDependencies(Project project) {
    assertFileContent(project, POM_XML, oauth2ClientDependency());
  }

  public static void assertOAuth2ResourceServerDependencies(Project project) {
    assertFileContent(project, POM_XML, oauth2ResourceServerDependency());
  }

  public static void assertOAuth2ClientProperties(Project project, String issuerUri) {
    List<String> properties = List.of(
      "spring.security.oauth2.client.provider.oidc.issuer-uri=" + issuerUri,
      "spring.security.oauth2.client.registration.oidc.client-id=jhipster",
      "spring.security.oauth2.client.registration.oidc.client-secret=jhipster",
      "spring.security.oauth2.client.registration.oidc.scope=openid,profile,email"
    );

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application.properties"), properties);
    assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), properties);
  }

  // TODO security commons
  public static List<String> securityDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-security</artifactId>",
      "</dependency>"
    );
  }

  // TODO security commons
  public static List<String> securityTestDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.security</groupId>",
      "<artifactId>spring-security-test</artifactId>",
      "<scope>test</scope>",
      "</dependency>"
    );
  }

  public static List<String> oauth2ClientDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-oauth2-client</artifactId>",
      "</dependency>"
    );
  }

  public static List<String> oauth2ResourceServerDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>",
      "</dependency>"
    );
  }
}
