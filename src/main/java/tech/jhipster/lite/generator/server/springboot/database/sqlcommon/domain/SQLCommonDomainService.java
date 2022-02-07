package tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain;

import java.util.Map;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class SQLCommonDomainService implements SQLCommonService {

  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public SQLCommonDomainService(BuildToolService buildToolService, SpringBootCommonService springBootCommonService) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public void addTestcontainers(Project project, String database, Map<String, Object> properties) {
    this.buildToolService.getVersion(project, "testcontainers")
      .ifPresentOrElse(
        version -> {
          Dependency dependency = Dependency
            .builder()
            .groupId("org.testcontainers")
            .artifactId(database)
            .version("\\${testcontainers.version}")
            .scope("test")
            .build();
          buildToolService.addProperty(project, "testcontainers.version", version);
          buildToolService.addDependency(project, dependency);

          springBootCommonService.addPropertiesTestComment(project, "Database Configuration");
          properties.forEach((k, v) -> springBootCommonService.addPropertiesTest(project, k, v));
          springBootCommonService.addPropertiesTestNewLine(project);
        },
        () -> {
          throw new GeneratorException("Testcontainers version not found");
        }
      );
  }
}
