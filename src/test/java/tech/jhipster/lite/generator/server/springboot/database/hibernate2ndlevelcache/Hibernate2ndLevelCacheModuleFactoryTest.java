package tech.jhipster.lite.generator.server.springboot.database.hibernate2ndlevelcache;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.server.springboot.database.hibernate2ndlevelcache.domain.Hibernate2ndLevelCacheModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class Hibernate2ndLevelCacheModuleFactoryTest {

  private static final Hibernate2ndLevelCacheModuleFactory factory = new Hibernate2ndLevelCacheModuleFactory();

  @Test
  void shouldCreateModule() {
    JHipsterModule module = factory.buildModule(properties());

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
              <dependency>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-jcache</artifactId>
              </dependency>
          """
      )
      .and()
      .hasFile(
        "src/main/java/com/jhipster/test/technical/infrastructure/secondary/hibernate2ndlevelcache/Hibernate2ndLevelCacheConfiguration.java"
      )
      .and()
      .hasFile(
        "src/test/java/com/jhipster/test/technical/infrastructure/secondary/hibernate2ndlevelcache/Hibernate2ndLevelCacheConfigurationIT.java"
      )
      .and();
  }

  private JHipsterModuleProperties properties() {
    return JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).basePackage("com.jhipster.test").build();
  }
}
