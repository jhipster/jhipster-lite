package tech.jhipster.forge.springboot.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.springboot.domain.usecase.PsqlService;

@Service
public class PsqlApplicationService {

  private final PsqlService psqlService;

  public PsqlApplicationService(PsqlService psqlService) {
    this.psqlService = psqlService;
  }

  public void init(Project project) {
    psqlService.init(project);
  }

  public void addSpringDataJpa(Project project) {
    psqlService.addSpringDataJpa(project);
  }

  public void addPostgreSQLDriver(Project project) {
    psqlService.addPostgreSQLDriver(project);
  }

  public void addHikari(Project project) {
    psqlService.addHikari(project);
  }

  public void addHibernateCore(Project project) {
    psqlService.addHibernateCore(project);
  }

  public void addDockerCompose(Project project) {
    psqlService.addDockerCompose(project);
  }

  public void addDialectJava(Project project) {
    psqlService.addDialectJava(project);
  }

  public void addProperties(Project project) {
    psqlService.addProperties(project);
  }

  public void addTestContainers(Project project) {
    psqlService.addTestcontainers(project);
  }
}
