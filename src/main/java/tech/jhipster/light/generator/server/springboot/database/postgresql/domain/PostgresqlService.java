package tech.jhipster.light.generator.server.springboot.database.postgresql.domain;

import tech.jhipster.light.generator.project.domain.Project;

public interface PostgresqlService {
  void init(Project project);

  void addSpringDataJpa(Project project);
  void addPostgreSQLDriver(Project project);
  void addHikari(Project project);
  void addHibernateCore(Project project);
  void addDockerCompose(Project project);
  void addDialectJava(Project project);
  void addProperties(Project project);
  void addTestcontainers(Project project);
}
