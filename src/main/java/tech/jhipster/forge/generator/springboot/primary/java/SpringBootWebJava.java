package tech.jhipster.forge.generator.springboot.primary.java;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.SpringBootWebApplicationService;

@Component
public class SpringBootWebJava {

  private final SpringBootWebApplicationService springBootWebApplicationService;

  public SpringBootWebJava(SpringBootWebApplicationService springBootWebApplicationService) {
    this.springBootWebApplicationService = springBootWebApplicationService;
  }

  public void addSpringBootWeb(Project project) {
    springBootWebApplicationService.addSpringBootWeb(project);
  }
}
