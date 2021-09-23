package tech.jhipster.forge.generator.maven.application;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.maven.domain.MavenDomainService;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

@Component
public class MavenApplicationService {

  private final MavenDomainService mavenDomainService;

  public MavenApplicationService(ProjectRepository projectRepository) {
    this.mavenDomainService = new MavenDomainService(projectRepository);
  }

  public void initPomXml(Project project) {
    mavenDomainService.initPomXml(project);
  }

  public void addMavenWrapper(Project project) {
    mavenDomainService.addMavenWrapper(project);
  }

  public void addParent(Project project, Parent parent) {
    mavenDomainService.addParent(project, parent);
  }

  public void addDependency(Project project, Dependency dependency) {
    mavenDomainService.addDependency(project, dependency);
  }

  public void addPlugin(Project project, Plugin plugin) {
    mavenDomainService.addPlugin(project, plugin);
  }
}
