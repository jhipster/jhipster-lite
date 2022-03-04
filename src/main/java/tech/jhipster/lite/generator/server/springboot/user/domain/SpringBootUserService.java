package tech.jhipster.lite.generator.server.springboot.user.domain;

import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootUserService {
  void addUserAndAuthorityEntities(Project project, DatabaseType sqlDatabase);
}
