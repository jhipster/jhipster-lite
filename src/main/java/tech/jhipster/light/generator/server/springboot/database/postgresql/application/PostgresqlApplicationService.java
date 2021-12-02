package tech.jhipster.light.generator.server.springboot.database.postgresql.application;

import org.springframework.stereotype.Service;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.server.springboot.database.postgresql.domain.PostgresqlService;

@Service
public class PostgresqlApplicationService {

  private final PostgresqlService postgresqlService;

  public PostgresqlApplicationService(PostgresqlService postgresqlService) {
    this.postgresqlService = postgresqlService;
  }

  public void init(Project project) {
    postgresqlService.init(project);
  }

  public void addSpringDataJpa(Project project) {
    postgresqlService.addSpringDataJpa(project);
  }

  public void addPostgreSQLDriver(Project project) {
    postgresqlService.addPostgreSQLDriver(project);
  }

  public void addHikari(Project project) {
    postgresqlService.addHikari(project);
  }

  public void addHibernateCore(Project project) {
    postgresqlService.addHibernateCore(project);
  }

  public void addDockerCompose(Project project) {
    postgresqlService.addDockerCompose(project);
  }

  public void addDialectJava(Project project) {
    postgresqlService.addJavaFiles(project);
  }

  public void addProperties(Project project) {
    postgresqlService.addProperties(project);
  }

  public void addTestContainers(Project project) {
    postgresqlService.addTestcontainers(project);
  }
}
