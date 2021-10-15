package tech.jhipster.forge.generator.springboot.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.usecase.PostgreSQLService;

@Service
public class PostgreSQLApplicationService {

  private final PostgreSQLService postgreSQLService;

  public PostgreSQLApplicationService(PostgreSQLService postgreSQLService) {
    this.postgreSQLService = postgreSQLService;
  }

  public void init(Project project) {
    postgreSQLService.init(project);
  }

  public void addSpringDataJpa(Project project) {
    postgreSQLService.addSpringDataJpa(project);
  }

  public void addPostgreSQLDriver(Project project) {
    postgreSQLService.addPostgreSQLDriver(project);
  }

  public void addHikari(Project project) {
    postgreSQLService.addHikari(project);
  }

  public void addHibernateCore(Project project) {
    postgreSQLService.addHibernateCore(project);
  }

  public void addDockerCompose(Project project) {
    postgreSQLService.addDockerCompose(project);
  }

  public void addDialectJava(Project project) {
    postgreSQLService.addDialectJava(project);
  }

  public void addProperties(Project project) {
    postgreSQLService.addProperties(project);
  }

  public void addTestContainers(Project project) {
    postgreSQLService.addTestcontainers(project);
  }
}
