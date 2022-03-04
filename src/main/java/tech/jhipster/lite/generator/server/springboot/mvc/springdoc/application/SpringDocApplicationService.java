package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringDocService;

@Service
public class SpringDocApplicationService {

  private final SpringDocService springDocService;

  public SpringDocApplicationService(SpringDocService springDocService) {
    this.springDocService = springDocService;
  }

  public void init(Project project) {
    springDocService.init(project);
  }

  public void initWithSecurityJWT(Project project) {
    springDocService.initWithSecurityJWT(project);
  }
}
