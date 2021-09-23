package tech.jhipster.forge.generator.springboot.application;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.maven.domain.MavenDomainService;
import tech.jhipster.forge.generator.springboot.domain.SpringBootDomainService;

@Component
public class SpringBootApplicationService {

  private final SpringBootDomainService springBootDomainService;
  private final MavenDomainService mavenDomainService;

  public SpringBootApplicationService(ProjectRepository projectRepository) {
    this.mavenDomainService = new MavenDomainService(projectRepository);
    this.springBootDomainService = new SpringBootDomainService(projectRepository, mavenDomainService);
  }

  public void addSpringBoot(Project project) {
    springBootDomainService.addSpringBoot(project);
  }

  public void addSpringBootParent(Project project) {
    springBootDomainService.addSpringBootParent(project);
  }

  public void addSpringBootDependencies(Project project) {
    springBootDomainService.addSpringBootDependencies(project);
  }

  public void addSpringBootMavenPlugin(Project project) {
    springBootDomainService.addSpringBootMavenPlugin(project);
  }

  public void addMainApp(Project project) {
    springBootDomainService.addMainApp(project);
  }

  public void addApplicationProperties(Project project) {
    springBootDomainService.addApplicationProperties(project);
  }
}
