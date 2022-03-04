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

  public void addUserAndAuthorityEntities(Project project, DatabaseType sqlDatabase) {
    springBootUserService.addUserAndAuthorityEntities(project, sqlDatabase);
  }
}
