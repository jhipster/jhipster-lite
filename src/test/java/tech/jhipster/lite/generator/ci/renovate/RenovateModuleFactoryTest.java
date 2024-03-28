package tech.jhipster.lite.generator.ci.renovate;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.readmeFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.ci.renovate.domain.RenovateModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class RenovateModuleFactoryTest {

  @InjectMocks
  private RenovateModuleFactory factory;

  @Test
  void shouldBuildBackendModule() {
    JHipsterModule module = factory.buildRenovateModule(properties());

    assertThatModuleWithFiles(module, pomFile(), readmeFile())
      .hasFile("renovate.json")
      .containing("config:recommended")
      .containing("maven")
      .containing("maven-wrapper")
      .containing("gradle")
      .containing("gradle-wrapper")
      .containing("npm")
      .containing("dockerfile")
      .containing("docker-compose");
  }

  private JHipsterModuleProperties properties() {
    return JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();
  }
}
