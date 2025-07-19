package tech.jhipster.lite.generator.typescript.sonar.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.docker.DockerImageVersion;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SonarTypescriptModuleFactoryTest {

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private SonarTypescriptModuleFactory factory;

  @Test
  void shouldBuildModule() {
    mockSonarqubeImage();

    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    // @formatter:off
    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFile("package.json")
        .containing(nodeDependency("@sonar/scan"))
        .and()
      .hasFile("documentation/sonar.md")
        .containing("npx @sonar/scan -Dsonar.token=$SONAR_TOKEN")
        .and()
      .hasFile("sonar-project.properties")
        .containing("sonar.javascript.lcov.reportPaths=target/test-results/lcov.info")
        .and()
      .hasFile("src/main/docker/sonar.yml")
        .containing("sonarqube:1.1.1")
        .and()
      .hasFile("src/main/docker/sonar/Dockerfile")
        .and()
      .hasFile("src/main/docker/sonar/sonar_generate_token.sh");
    // @formatter:on
  }

  private void mockSonarqubeImage() {
    when(dockerImages.get("sonarqube")).thenReturn(new DockerImageVersion("sonarqube", "1.1.1"));
  }
}
