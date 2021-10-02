package tech.jhipster.forge.generator.springboot.application;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootWebService;

@Component
public class SpringBootWebApplicationService {

  private final SpringBootWebService springBootWebService;

  public SpringBootWebApplicationService(SpringBootWebService springBootWebService) {
    this.springBootWebService = springBootWebService;
  }

  public void addSpringBootWeb(Project project) {
    springBootWebService.addSpringBootWeb(project);
  }
}
