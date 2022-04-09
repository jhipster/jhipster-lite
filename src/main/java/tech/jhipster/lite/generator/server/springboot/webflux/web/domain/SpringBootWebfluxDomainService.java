package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import static tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebflux.springBootStarterWebfluxDependency;

import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;

public class SpringBootWebfluxDomainService implements SpringBootWebfluxService {

  private final BuildToolService buildToolService;

  public SpringBootWebfluxDomainService(BuildToolService buildToolService) {
    this.buildToolService = buildToolService;
  }

  @Override
  public void addSpringBootWebflux(Project project) {
    buildToolService.addDependency(project, springBootStarterWebfluxDependency());
  }
}
