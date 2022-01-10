package tech.jhipster.lite.generator.server.springboot.user.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@Service
public class SpringBootUserApplicationService {

  private final SpringBootUserService springBootUserService;

  public SpringBootUserApplicationService(SpringBootUserService springBootPropertiesService) {
    this.springBootUserService = springBootPropertiesService;
  }

  public void addSqlJavaUser(Project project, String sqlDabaseName) {
    springBootUserService.addSqlJavaUser(project, sqlDabaseName);
  }

  public void addSqlJavaAuthority(Project project, String sqlDabaseName) {
    springBootUserService.addSqlJavaAuthority(project, sqlDabaseName);
  }

  public void addSqlJavaAuditEntity(Project project, String sqlDabaseName) {
    springBootUserService.addSqlJavaAuditEntity(project, sqlDabaseName);
  }
}
