package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain.Flyway.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class FlywayDomainService implements FlywayService {

  private static final String SQL_INIT_FILE_SOURCE = "server/springboot/dbmigration/flyway";
  private static final String SQL_INIT_FILE_NAME = "V%s__init.sql";

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringBootCommonService springBootCommonService;
  private final Clock clock;

  public FlywayDomainService(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringBootCommonService springBootCommonService,
    Clock clock
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
    this.clock = clock;
  }

  @Override
  public void init(Project project) {
    addFlywayDependency(project);
    addChangelogSql(project);
    addProperties(project);
  }

  @Override
  public void addFlywayDependency(Project project) {
    buildToolService.addProperty(project, "flyway.version", Flyway.flywayVersion());
    buildToolService.addDependency(project, Flyway.flywayDependency());
  }

  @Override
  public void addChangelogSql(Project project) {
    projectRepository.add(
      project,
      getPath(SQL_INIT_FILE_SOURCE, "resources"),
      "V00000000000000__init.sql",
      getPath(MAIN_RESOURCES, DEFAULT_SQL_FILES_FOLDER),
      buildInitFileName()
    );
  }

  @Override
  public void addProperties(Project project) {
    getFlywayProperties().forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
  }

  private TreeMap<String, Object> getFlywayProperties() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.flyway.enabled", DEFAULT_FLYWAY_ENABLED);
    result.put("spring.flyway.locations", DEFAULT_FLYWAY_LOCATIONS);

    return result;
  }

  private String buildInitFileName() {
    LocalDateTime localDateTime = LocalDateTime.now(clock);
    String formattedDate = localDateTime.format(DATE_TIME_FORMATTER);
    return SQL_INIT_FILE_NAME.formatted(formattedDate);
  }
}
