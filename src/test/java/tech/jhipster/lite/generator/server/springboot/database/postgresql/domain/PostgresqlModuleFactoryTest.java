package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleOnProjectWithDefaultPom;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class PostgresqlModuleFactoryTest {

  @Mock
  SpringBootCommonService springBootCommonService;

  @Mock
  SQLCommonService sqlCommonService;

  @Mock
  DockerService dockerService;

  @InjectMocks
  PostgresqlModuleFactory factory;

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(FileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    when(dockerService.getImageNameWithVersion("postgres")).thenReturn(Optional.of("postgres:0.0.0"));
    when(dockerService.getImageVersion("postgres")).thenReturn(Optional.of("0.0.0"));
    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleOnProjectWithDefaultPom(module)
      .createPrefixedFiles(
        "src/main/java/com/jhipster/test/technical/infrastructure/secondary/postgresql",
        "DatabaseConfiguration.java",
        "FixedPostgreSQL10Dialect.java"
      )
      .createFile("documentation/postgresql.md")
      .and()
      .createFile("src/test/java/com/jhipster/test/technical/infrastructure/secondary/postgresql/FixedPostgreSQL10DialectTest.java")
      .and()
      .createFile("pom.xml")
      .containing("<groupId>org.postgresql</groupId>")
      .containing("<artifactId>postgresql</artifactId>")
      .containing("<groupId>com.zaxxer</groupId>")
      .containing("<artifactId>HikariCP</artifactId>")
      .containing("<groupId>org.hibernate</groupId>");
  }
}
