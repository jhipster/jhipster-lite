package tech.jhipster.lite.generator.client.vue.router.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class VueRouterModuleFactoryTest {

  @InjectMocks
  private VueRouterModuleFactory factory;

  @Test
  void shouldBuildVueRouterModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    // @formatter:off
    assertThatModuleWithFiles(module, packageJsonFile(), vitestConfigFile(), appVueFile(), mainVueFile())
      .hasFile("package.json")
      .containing(nodeDependency("vue-router"))
      .and()
      .hasPrefixedFiles("src/main/webapp/app", "router.ts")
      .hasPrefixedFiles("src/main/webapp/app/home","application/HomeRouter.ts")
      .hasFiles("src/test/webapp/unit/router/infrastructure/primary/HomeRouter.spec.ts");
    // @formatter:on
  }
}
