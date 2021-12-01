package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.application;

import static tech.jhipster.light.TestUtils.assertFileContent;
import static tech.jhipster.light.TestUtils.assertFileExist;
import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.light.generator.project.domain.Constants.*;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain.JwtSecurity.exceptionTranslatorPatch;
import static tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityDomainService.SECURITY_JWT_PATH;

import java.util.List;
import tech.jhipster.light.generator.project.domain.Project;

public class JwtSecurityAssertFiles {

  private JwtSecurityAssertFiles() {}

  public static void assertPomXmlProperties(Project project) {
    assertFileContent(project, POM_XML, "<jjwt.version>");
    assertFileContent(project, POM_XML, jwtSecurityDependencies());
    assertFileContent(project, POM_XML, jwtSecurityTestDependency());
  }

  public static void assertGitPatch(Project project) {
    assertFileExist(project, ".jhipster", exceptionTranslatorPatch);
    assertFileExist(project, ".jhipster", exceptionTranslatorPatch);
  }

  public static void assertJwtSecurityFilesExists(Project project) {
    List<String> infrastructureConfigJavaFiles = List.of(
      "ApplicationSecurityDefaults.java",
      "ApplicationSecurityProperties.java",
      "CorsFilterConfiguration.java",
      "CorsProperties.java",
      "JWTConfigurer.java",
      "JWTFilter.java",
      "SecurityConfiguration.java",
      "SecurityExceptionTranslator.java",
      "TokenProvider.java"
    );

    String jwtPath = getPath(project.getPackageNamePath().orElse("com/mycompany/myapp"), SECURITY_JWT_PATH);
    String applicationConfigPath = getPath(jwtPath, "application");
    String domainConfigPath = getPath(jwtPath, "domain");
    String infrastructureConfigPath = getPath(jwtPath, "infrastructure/config");
    String infrastructureRestPath = getPath(jwtPath, "/infrastructure/primary/rest");

    // main java files
    assertFileExist(project, getPath(MAIN_JAVA, applicationConfigPath, "SecurityUtils.java"));
    assertFileExist(project, getPath(MAIN_JAVA, domainConfigPath, "AuthoritiesConstants.java"));
    infrastructureConfigJavaFiles.forEach(javaFile -> assertFileExist(project, getPath(MAIN_JAVA, infrastructureConfigPath, javaFile)));
    assertFileExist(project, getPath(MAIN_JAVA, infrastructureRestPath, "AuthenticationResource.java"));
    assertFileExist(project, getPath(MAIN_JAVA, infrastructureRestPath, "LoginDTO.java"));

    // test java files
    assertFileExist(project, getPath(TEST_JAVA, applicationConfigPath, "SecurityUtilsTest.java"));

    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "ApplicationSecurityPropertiesTest.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "CorsFilterConfigurationIT.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "JWTFilterTest.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "TokenProviderTest.java"));

    assertFileExist(project, getPath(TEST_JAVA, infrastructureRestPath, "AuthenticationResourceIT.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureRestPath, "LoginDTOTest.java"));
  }

  public static void assertJwtSecurityProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    List<String> properties = List.of(
      "spring.security.user.name=admin",
      "spring.security.user.password=$2a$12$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O",
      "application.security.authentication.jwt.base64-secret=bXktc2VjcmV0LWtleS13aGljaC1zaG91bGQtYmUtY2hhbmdlZC1pbi1wcm9kdWN0aW9uLWFuZC1iZS1iYXNlNjQtZW5jb2RlZAo=",
      "application.security.authentication.jwt.token-validity-in-seconds=86400",
      "application.security.authentication.jwt.token-validity-in-seconds-for-remember-me=2592000",
      "application.cors.allowed-origins=http://localhost:8100,http://localhost:9000",
      "application.cors.allowed-methods=*",
      "application.cors.allowed-headers=*",
      "application.cors.exposed-headers=Authorization,Link,X-Total-Count,X-" +
      baseName +
      "-alert,X-" +
      baseName +
      "-error,X-" +
      baseName +
      "-params",
      "application.cors.allow-credentials=true",
      "application.cors.max-age=1800"
    );

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application.properties"), properties);
    assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), properties);
  }

  public static List<String> jwtSecurityDependencies() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-security</artifactId>",
      "</dependency>",
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-configuration-processor</artifactId>",
      "<scope>provided</scope>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-api</artifactId>",
      "<version>${jjwt.version}</version>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-impl</artifactId>",
      "<version>${jjwt.version}</version>",
      "<scope>runtime</scope>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-jackson</artifactId>",
      "<version>${jjwt.version}</version>",
      "<scope>runtime</scope>",
      "</dependency>"
    );
  }

  public static List<String> jwtSecurityTestDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.security</groupId>",
      "<artifactId>spring-security-test</artifactId>",
      "<scope>test</scope>",
      "</dependency>"
    );
  }
}
