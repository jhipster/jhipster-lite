package tech.jhipster.lite.generator.server.springboot.user.domain;

import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootUserService {
  void addSqlJavaUser(Project project, DatabaseType sqlDatabase);
  void addSqlJavaAuthority(Project project, DatabaseType sqlDatabase);
  void addSqlJavaAuditEntity(Project project, DatabaseType sqlDatabase);
}
