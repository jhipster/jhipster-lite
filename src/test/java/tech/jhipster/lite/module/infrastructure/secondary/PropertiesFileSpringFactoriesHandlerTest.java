package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
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

    assertThat(content(factoriesFile)).contains("o.s.c.ApplicationListener=c.m.m.MyListener1,c.m.m.MyListener2");
  }

  @Test
  void shouldAppendPropertyToFileWithProperties() {
    Path propertiesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    loadDefaultProperties(propertiesFile);
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(propertiesFile);

    handler.set(propertyKey("o.s.c.ApplicationListener"), propertyValue("c.m.m.MyListener1", "c.m.m.MyListener2"));

    assertThat(content(propertiesFile))
      .contains("org.springframework.test.context.ContextCustomizerFactory=c.m.m.MyContextCustomizerFactory")
      .endsWith("\no.s.c.ApplicationListener=c.m.m.MyListener1,c.m.m.MyListener2");
  }

  @Test
  void shouldAppendToExistingProperty() {
    Path propertiesFile = Paths.get(TestFileUtils.tmpDirForTest(), "src/test/resources/META-INF/spring.factories");
    loadDefaultProperties(propertiesFile);
    PropertiesFileSpringFactoriesHandler handler = new PropertiesFileSpringFactoriesHandler(propertiesFile);

    handler.set(
      propertyKey("org.springframework.test.context.ContextCustomizerFactory"),
      propertyValue("c.m.m.MyNewContextCustomizerFactory")
    );

    assertThat(content(propertiesFile))
      .contains(
        "org.springframework.test.context.ContextCustomizerFactory=c.m.m.MyContextCustomizerFactory,c.m.m.MyNewContextCustomizerFactory"
      );
  }

  //todo renommer properties en factories
  private void loadDefaultProperties(Path propertiesFile) {
    try {
      Files.createDirectories(propertiesFile.getParent());

      Files.copy(Paths.get("src/test/resources/projects/project-with-spring-factories/spring.factories"), propertiesFile);
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }

  // todo factoriser
  private static String content(Path file) {
    try {
      return Files.readString(file);
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    }
  }
}
