package tech.jhipster.lite.generator.client.vue.pinia.domain;

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
class VuePiniaModuleFactoryTest {

  @InjectMocks
  private VuePiniaModuleFactory factory;

  @Test
  void shouldBuildPiniaModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(
      module,
      packageJsonFile(),
      new ModuleFile("src/test/resources/projects/vue/main.ts.mustache", "src/main/webapp/app/main.ts")
    )
      .hasFile("package.json")
      .containing(nodeDependency("pinia"))
      .containing(nodeDependency("pinia-plugin-persistedstate"))
      .containing(nodeDependency("@pinia/testing"))
      .and()
      .hasFile("src/main/webapp/app/main.ts")
      .containing("import { createPinia } from 'pinia';")
      .containing("import piniaPersist from 'pinia-plugin-persistedstate';")
      .containing(
        """
        const pinia = createPinia();
        pinia.use(piniaPersist);
        app.use(pinia);
        """
      );
  }
}
