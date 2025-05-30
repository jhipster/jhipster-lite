package tech.jhipster.lite.generator.ci.renovate.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.readmeFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class RenovateModuleFactoryTest {

  private final RenovateModuleFactory factory = new RenovateModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModule module = factory.buildModule(properties());

    assertThatModuleWithFiles(module, pomFile(), readmeFile())
      .hasFile("renovate.json")
      .containing("config:recommended")
      .containing("docker-compose");
  }

  private JHipsterModuleProperties properties() {
    return JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();
  }
}
