package tech.jhipster.lite.generator.server.springboot.database.mariadb.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleOnProjectWithDefaultPom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.docker.domain.DockerImage;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlModuleFactory;

@UnitTest
@ExtendWith(MockitoExtension.class)
class MariaDBModuleFactoryTest {

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private MariaDBModuleFactory factory;

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(FileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    when(dockerImages.get("mariadb")).thenReturn(new DockerImage("mariadb", "0.0.0"));

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleOnProjectWithDefaultPom(module)
      .createFile("documentation/mariadb.md")
      .and()
      .createPrefixedFiles("src/main/java/com/jhipster/test/technical/infrastructure/secondary/mariadb", "DatabaseConfiguration.java")
      .createPrefixedFiles("src/main/docker", "mariadb.yml")
      .createFile("pom.xml")
      .containing("<groupId>org.springframework.boot</groupId>")
      .containing("<artifactId>spring-boot-starter-data-jpa</artifactId>")
      .containing("<groupId>org.mariadb.jdbc</groupId>")
      .containing("<artifactId>mariadb-java-client</artifactId>")
      .containing("<groupId>com.zaxxer</groupId>")
      .containing("<artifactId>HikariCP</artifactId>")
      .containing("<groupId>org.hibernate</groupId>")
      .containing("<artifactId>hibernate-core</artifactId>")
      .and()
      .createFile("src/main/resources/config/application.properties")
      .containing("spring.datasource.url=jdbc:mariadb://localhost:3306/myapp")
      .containing("spring.datasource.username=root")
      .containing("spring.datasource.password=")
      .containing("spring.datasource.driver-class-name=org.mariadb.jdbc.Driver");
  }
}
