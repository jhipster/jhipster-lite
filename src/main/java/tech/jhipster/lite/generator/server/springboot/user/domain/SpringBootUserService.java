package tech.jhipster.lite.generator.server.springboot.user.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootUserService {
  void addJavaUsers(Project project);
  void addJavaAuthority(Project project);
  void addJavaAuditEntity(Project project);
  void addLiquibaseConfiguration(Project project);
}
