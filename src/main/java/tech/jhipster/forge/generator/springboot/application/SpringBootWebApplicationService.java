package tech.jhipster.forge.generator.springboot.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootWebService;

@Service
public class SpringBootWebApplicationService {

  private final SpringBootWebService springBootWebService;

  public SpringBootWebApplicationService(SpringBootWebService springBootWebService) {
    this.springBootWebService = springBootWebService;
  }

  public void addSpringBootWeb(Project project) {
    springBootWebService.addSpringBootWeb(project);
  }

  public void addSpringBootUndertow(Project project) {
    springBootWebService.addSpringBootUndertow(project);
  }
}
