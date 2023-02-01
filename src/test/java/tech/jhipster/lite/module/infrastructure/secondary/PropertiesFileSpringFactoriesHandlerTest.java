package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestFileUtils.content;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;

@UnitTest
class PropertiesFileSpringFactoriesHandlerTest {

  @Test
  void shouldCreateUnknownFile() {
    Path factoriesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(factoriesFile);

    handler.set(propertyKey("o.s.c.ApplicationListener"), propertyValue("c.m.m.MyListener1", "c.m.m.MyListener2"));

    assertThat(content(factoriesFile))
      .isEqualTo("""
          o.s.c.ApplicationListener=c.m.m.MyListener1,\\
            c.m.m.MyListener2
          """);
  }

  @Test
  void shouldAppendPropertyToFileWithProperties() {
    Path factoriesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    loadDefaultFactoryProperties(factoriesFile);
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(factoriesFile);

    handler.set(propertyKey("o.s.c.ApplicationListener"), propertyValue("c.m.m.MyListener1", "c.m.m.MyListener2"));

    assertThat(content(factoriesFile))
      .isEqualTo(
        """
        org.springframework.test.context.ContextCustomizerFactory=c.m.m.MyContextCustomizerFactory
        o.s.c.ApplicationListener=c.m.m.MyListener1,\\
          c.m.m.MyListener2
        """
      );
  }

  @Test
  void shouldAppendToExistingProperty() {
    Path factoriesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    loadDefaultFactoryProperties(factoriesFile);
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(factoriesFile);

    handler.set(
      propertyKey("org.springframework.test.context.ContextCustomizerFactory"),
      propertyValue("c.m.m.MyNewContextCustomizerFactory")
    );

    assertThat(content(factoriesFile))
      .isEqualTo(
        """
          org.springframework.test.context.ContextCustomizerFactory=c.m.m.MyContextCustomizerFactory,c.m.m.MyNewContextCustomizerFactory
          """
      );
  }

  @Test
  void shouldAppendNewAndExistingProperties() {
    Path factoriesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    loadDefaultFactoryProperties(factoriesFile);
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(factoriesFile);

    handler.set(
      propertyKey("org.springframework.test.context.ContextCustomizerFactory"),
      propertyValue("c.m.m.MyNewContextCustomizerFactory")
    );
    handler.set(propertyKey("o.s.c.ApplicationListener"), propertyValue("c.m.m.MyListener1"));

    assertThat(content(factoriesFile))
      .isEqualTo(
        """
          org.springframework.test.context.ContextCustomizerFactory=c.m.m.MyContextCustomizerFactory,c.m.m.MyNewContextCustomizerFactory
          o.s.c.ApplicationListener=c.m.m.MyListener1
          """
      );
  }

  private void loadDefaultFactoryProperties(Path factoriesFile) {
    try {
      Files.createDirectories(factoriesFile.getParent());

      Files.copy(Paths.get("src/test/resources/projects/project-with-spring-factories/spring.factories"), factoriesFile);
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }
}
