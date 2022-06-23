package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application.LiquibaseAssertFiles.*;

import java.time.Clock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.module.infrastructure.secondary.TestJHipsterModules;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.application.PostgresqlApplicationService;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application.LiquibaseApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class LiquibaseResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  PostgresqlApplicationService postgresqlApplicationService;

  @Autowired
  LiquibaseApplicationService liquibaseApplicationService;

  @Autowired
  MockMvc mockMvc;

  @SpyBean
  Clock clock;

  @BeforeEach
  void setUp() {
    initClock(clock);
  }

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(project.getFolder())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);
    TestJHipsterModules.applyer().module(postgresqlApplicationService.build(properties)).properties(properties).slug("postgresql").apply();

    mockMvc
      .perform(
        post("/api/servers/spring-boot/database-migration-tools/liquibase")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertDependencies(project);
    assertFilesLiquibaseChangelogMasterXml(project);
    assertFilesLiquibaseJava(project);
  }

  @Test
  void shouldAddUserPostgresql() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());

    Project project = ProjectDTO.toProject(projectDTO);
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(project.getFolder())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);
    TestJHipsterModules.applyer().module(postgresqlApplicationService.build(properties)).properties(properties).slug("postgresql").apply();
    liquibaseApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/database-migration-tools/liquibase/user")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertFilesLiquibaseSqlUser(project);
    assertFilesLiquibaseSqlUserAuthority(project);
  }
}
