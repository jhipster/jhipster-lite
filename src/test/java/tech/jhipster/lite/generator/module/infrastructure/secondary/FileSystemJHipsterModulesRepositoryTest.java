package tech.jhipster.lite.generator.module.infrastructure.secondary;

import static tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture.*;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;

@UnitTest
@ExtendWith(LogsSpy.class)
class FileSystemJHipsterModulesRepositoryTest {

  private final LogsSpy logs;

  public FileSystemJHipsterModulesRepositoryTest(LogsSpy logs) {
    this.logs = logs;
  }

  @Test
  void shouldApplyModule() {
    JHipsterModule module = module();

    // @formatter:off
    assertThatModuleWithFiles(module, pomFile(), packageJsonFile())
      .createFiles(
        "src/main/java/com/company/myapp/MyApp.java",
        "src/main/java/com/company/myapp/errors/Assert.java",
        "src/main/java/com/company/myapp/errors/AssertionException.java",
        "documentation/cucumber-integration.md",
        ".gitignore"
      )
      .createExecutableFiles(".husky/pre-commit")
      .createFile("src/main/java/com/company/myapp/MyApp.java")
        .containing("com.test.myapp")
        .and()
      .createFile("pom.xml")
        .containing(
          """
                  <dependency>
                    <groupId>org.junit.jupiter</groupId>
                    <artifactId>junit-jupiter-engine</artifactId>
                    <version>${spring-boot.version}</version>
                    <scope>test</scope>
                    <optional>true</optional>
                  </dependency>
              """
        )
        .containing("<spring-boot.version>")
        .containing("</spring-boot.version>")
        .containing("<artifactId>spring-boot-starter</artifactId>")
        .containing("<artifactId>problem-spring-web</artifactId>")
        .notContaining(
          "    <dependency>\n" +
          "      <groupId>org.assertj</groupId>\n" +
          "      <artifactId>assertj-core</artifactId>\n" +
          "      <version>${assertj.version}</version>\n" +
          "      <scope>test</scope>\n" +
          "    </dependency>"
        )
        .and()
      .createFile("package.json")
        .containing("\"scripts\": {\n    \"serve\": \"tikui-core serve\"")
        .containing("\"dependencies\": {\n    \"@angular/animations\": \"")
        .containing("\"devDependencies\": {\n    \"@playwright/test\": \"")
        .and()
      .createFile("src/main/java/com/company/myapp/errors/Assert.java")
        .containing("Dummy replacement")
        .containing("Another dummy replacement")
        .containing("Dummy collection replacement")
        .containing("Another dummy collection replacement")
        .and()
      .createFile("src/main/resources/config/application.properties")
        .containing("springdoc.swagger-ui.operationsSorter=alpha")
        .and()
      .createFile("src/main/resources/config/application-local.properties")
        .containing("springdoc.swagger-ui.tryItOutEnabled=false")
        .and()
      .createFile("src/test/resources/config/application.properties")
        .containing("springdoc.swagger-ui.operationsSorter=test")
        .and()
      .createFile("src/test/resources/config/application-local.properties")
        .containing("springdoc.swagger-ui.tryItOutEnabled=test")
        .and()
      .createFile("README.md")
        .containing("- [Cucumber integration](documentation/cucumber-integration.md)");
    // @formatter:on

    assertPreActions();
    assertPostActions();
  }

  private void assertPreActions() {
    logs.shouldHave(Level.DEBUG, "Applying fixture module");
    logs.shouldHave(Level.DEBUG, "You shouldn't add this by default in your modules :D");
  }

  private void assertPostActions() {
    logs.shouldHave(Level.DEBUG, "Fixture module applied");
    logs.shouldHave(Level.DEBUG, "Applied on");
    logs.shouldHave(Level.DEBUG, System.getProperty("java.io.tmpdir"));
  }
}
