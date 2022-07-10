package tech.jhipster.lite.generator.server.springboot.core.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class SpringBootCoreModuleFactoryTest {

  private static final SpringBootCoreModuleFactory factory = new SpringBootCoreModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .createFile("pom.xml")
      .containing(
        """
              <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <scope>import</scope>
                <type>pom</type>
              </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-configuration-processor</artifactId>
              <optional>true</optional>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.apache.commons</groupId>
              <artifactId>commons-lang3</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
            </dependency>
        """
      )
      .containing(
        """
                <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <version>${spring-boot.version}</version>
                  <executions>
                    <execution>
                      <goals>
                        <goal>repackage</goal>
                      </goals>
                    </execution>
                  </executions>
                  <configuration>
                    <mainClass>${start-class}</mainClass>
                  </configuration>
                </plugin>
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
              </plugin>
        """
      )
      .and()
      .createFile("src/main/java/com/jhipster/test/MyappApp.java")
      .containing("class MyappApp")
      .and()
      .createFiles("src/test/java/com/jhipster/test/MyappAppTest.java", "src/test/java/com/jhipster/test/IntegrationTest.java")
      .createFiles(
        "src/main/resources/config/application.properties",
        "src/main/resources/config/application-local.properties",
        "src/test/resources/config/application.properties"
      )
      .createFiles("src/test/resources/logback.xml", "src/main/resources/logback-spring.xml");
  }
}
