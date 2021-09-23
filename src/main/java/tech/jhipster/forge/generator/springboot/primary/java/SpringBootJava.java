package tech.jhipster.forge.generator.springboot.primary.java;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.SpringBootApplicationService;

@Component
public class SpringBootJava {

  private final SpringBootApplicationService springBootApplicationService;

  public SpringBootJava(SpringBootApplicationService springBootApplicationService) {
    this.springBootApplicationService = springBootApplicationService;
  }

  public void addSpringBoot(Project project) {
    springBootApplicationService.addSpringBoot(project);
  }

  public void addSpringBootParent(Project project) {
    springBootApplicationService.addSpringBootParent(project);
  }

  public void addSpringBootDependencies(Project project) {
    springBootApplicationService.addSpringBootDependencies(project);
  }

  public void addSpringBootMavenPlugin(Project project) {
    springBootApplicationService.addSpringBootMavenPlugin(project);
  }

  public void addMainApp(Project project) {
    springBootApplicationService.addMainApp(project);
  }

  public void addApplicationProperties(Project project) {
    springBootApplicationService.addApplicationProperties(project);
  }
}
