package tech.jhipster.lite.generator.server.springboot.user.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@Service
public class SpringbootUserApplicationService {

  private final SpringBootUserService springBootUserService;

  public SpringbootUserApplicationService(SpringBootUserService springBootPropertiesService) {
    this.springBootUserService = springBootPropertiesService;
  }

  public void addJavaUsers(Project project) {
    springBootUserService.addJavaUsers(project);
  }

  public void addJavaAuthority(Project project) {
    springBootUserService.addJavaAuthority(project);
  }

  public void addLiquibaseConfiguration(Project project) {
    springBootUserService.addLiquibaseConfiguration(project);
  }
}
