package tech.jhipster.lite.generator.server.springboot.localeprofile.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class LocalProfileModuleFactoryTest {

  private static final LocalProfileModuleFactory factory = new LocalProfileModuleFactory();

  @Test
  void shouldBuildModuleForMaven() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
          <profiles>
            <profile>
              <id>local</id>
              <activation>
                <activeByDefault>true</activeByDefault>
              </activation>
              <properties>
                <spring.profiles.active>local</spring.profiles.active>
              </properties>
            </profile>
          </profiles>
        """
      )
      .containing(
        """
                <plugin>
                  <artifactId>maven-resources-plugin</artifactId>
                  <version>${maven-resources-plugin.version}</version>
                  <configuration>
                    <useDefaultDelimiters>false</useDefaultDelimiters>
                    <delimiters>
                      <delimiter>@</delimiter>
                    </delimiters>
                  </configuration>
                </plugin>
        """
      )
      .and()
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        spring:
          profiles:
            active: '@spring.profiles.active@'
        """
      );
  }

  @Test
  void shouldReplaceCIActions() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile(), githubActionsBuild(), gitlabCI())
      .hasFile("pom.xml")
      .and()
      .hasFile(".github/workflows/github-actions.yml")
      .containing(
        """
        mvnw clean verify -P'!local' $MAVEN_CLI_OPTS
        """
      )
      .and()
      .hasFile(".gitlab-ci.yml")
      .containing(
        """
        mvnw clean verify -P'!local' $MAVEN_CLI_OPTS
        """
      );
  }

  private static ModuleFile githubActionsBuild() {
    return file("src/test/resources/projects/ci/github-actions-maven.yml", ".github/workflows/github-actions.yml");
  }

  private static ModuleFile gitlabCI() {
    return file("src/test/resources/projects/ci/.gitlab-ci-maven.yml", ".gitlab-ci.yml");
  }
}
