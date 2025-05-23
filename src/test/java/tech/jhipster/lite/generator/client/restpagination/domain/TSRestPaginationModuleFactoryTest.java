package tech.jhipster.lite.generator.client.restpagination.domain;

import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.*;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class TSRestPaginationModuleFactoryTest {

  private static final TSRestPaginationModuleFactory factory = new TSRestPaginationModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFiles("documentation/rest-page.md")
      .hasFiles("src/main/webapp/app/shared/pagination/infrastructure/secondary/RestPage.ts")
      .hasFiles("src/test/webapp/unit/shared/pagination/infrastructure/secondary/RestPage.spec.ts");
  }
}
