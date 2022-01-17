package tech.jhipster.lite.generator.server.springboot.user.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@Service
public class SpringBootUserApplicationService {

  private final SpringBootUserService springBootUserService;

  public SpringBootUserApplicationService(SpringBootUserService springBootPropertiesService) {
    this.springBootUserService = springBootPropertiesService;
  }

  public void addSqlJavaUser(Project project, DatabaseType sqlDatabase) {
    springBootUserService.addSqlJavaUser(project, sqlDatabase);
  }

  public void addSqlJavaAuthority(Project project, DatabaseType sqlDatabase) {
    springBootUserService.addSqlJavaAuthority(project, sqlDatabase);
  }

  public void addSqlJavaAuditEntity(Project project, DatabaseType sqlDatabase) {
    springBootUserService.addSqlJavaAuditEntity(project, sqlDatabase);
  }
}
