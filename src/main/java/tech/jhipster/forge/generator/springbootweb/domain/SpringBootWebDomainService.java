package tech.jhipster.forge.generator.springbootweb.domain;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.maven.domain.MavenService;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.springboot.domain.SpringBootService;

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
