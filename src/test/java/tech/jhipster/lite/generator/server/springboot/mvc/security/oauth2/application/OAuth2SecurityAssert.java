package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.DEFAULT_PROVIDER;

import java.util.List;
import java.util.Optional;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Provider;

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

  public static void assertOAuth2ClientProperties(Project project, OAuth2Provider provider, String issuerUri) {
    OAuth2Provider providerFallback = Optional.ofNullable(provider).orElse(DEFAULT_PROVIDER);
    String issuerUriFallback = Optional.ofNullable(issuerUri).orElse(providerFallback.getDefaultIssuerUri());

    List<String> properties =
      switch (providerFallback) {
        case GOOGLE -> List.of(
          "spring.security.oauth2.client.registration.google.client-id=web_app",
          "spring.security.oauth2.client.registration.google.client-secret=web_app"
        );
        case FACEBOOK -> List.of(
          "spring.security.oauth2.client.registration.facebook.client-id=web_app",
          "spring.security.oauth2.client.registration.facebook.client-secret=web_app"
        );
        case GITHUB -> List.of(
          "spring.security.oauth2.client.registration.github.client-id=web_app",
          "spring.security.oauth2.client.registration.github.client-secret=web_app"
        );
        case OKTA -> List.of(
          "spring.security.oauth2.client.provider.okta.issuer-uri=" + issuerUriFallback,
          "spring.security.oauth2.client.registration.okta.client-id=web_app",
          "spring.security.oauth2.client.registration.okta.client-secret=web_app"
        );
        case KEYCLOAK, AUTHO0, OTHER -> {
          String providerId = providerFallback.name().toLowerCase();
          yield List.of(
            "spring.security.oauth2.client.provider." + providerId + ".issuer-uri=" + issuerUriFallback,
            "spring.security.oauth2.client.registration." + providerId + ".client-name=" + providerId,
            "spring.security.oauth2.client.registration." + providerId + ".client-id=web_app",
            "spring.security.oauth2.client.registration." + providerId + ".client-secret=web_app"
          );
        }
      };

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
}
