package tech.jhipster.forge.generator.springboot.domain.service;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootWebService;

public class SpringBootWebDomainService implements SpringBootWebService {

  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public SpringBootWebDomainService(MavenService mavenService, SpringBootService springBootService) {
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Override
  public void addSpringBootWeb(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    mavenService.addDependency(project, dependency);
    springBootService.addProperties(project, "server.port", 8080);
  }
}
