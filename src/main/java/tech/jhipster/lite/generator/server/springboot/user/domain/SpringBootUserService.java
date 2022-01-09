package tech.jhipster.lite.generator.server.springboot.user.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootUserService {
  void addSqlJavaUser(Project project, String sqlDatabaseName);
  void addSqlJavaAuthority(Project project, String sqlDatabaseName);
  void addSqlJavaAuditEntity(Project project, String sqlDatabaseName);
}
