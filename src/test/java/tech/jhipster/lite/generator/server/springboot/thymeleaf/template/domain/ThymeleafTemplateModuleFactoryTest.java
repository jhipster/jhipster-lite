package tech.jhipster.lite.generator.server.springboot.thymeleaf.template.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class ThymeleafTemplateModuleFactoryTest {

  private static final ThymeleafTemplateModuleFactory factory = new ThymeleafTemplateModuleFactory();

  @Test
  void shouldCreateThymeleafTemplateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhipster")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module)
      .hasFiles("src/main/resources/templates/index.html")
      .hasFiles("src/main/resources/templates/layout/main.html")
      .hasFiles("src/main/resources/static/css/application.css");
  }
}
