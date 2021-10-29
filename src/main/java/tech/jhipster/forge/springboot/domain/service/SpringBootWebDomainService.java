package tech.jhipster.forge.springboot.domain.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.springboot.domain.model.Dependency;
import tech.jhipster.forge.springboot.domain.usecase.SpringBootService;
import tech.jhipster.forge.springboot.domain.usecase.SpringBootWebService;

public class SpringBootWebDomainService implements SpringBootWebService {

  private final Logger log = LoggerFactory.getLogger(SpringBootWebDomainService.class);

  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public SpringBootWebDomainService(MavenService mavenService, SpringBootService springBootService) {
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Override
  public void init(Project project) {
    addSpringBootWeb(project);
  }

  @Override
  public void addSpringBootWeb(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    mavenService.addDependency(project, dependency);
    springBootService.addProperties(project, "server.port", getServerPort(project));
  }

  @Override
  public void addSpringBootUndertow(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    Dependency tomcat = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-tomcat").build();
    Dependency undertow = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-undertow").build();

    mavenService.addDependency(project, dependency, List.of(tomcat));
    mavenService.addDependency(project, undertow);
    springBootService.addProperties(project, "server.port", getServerPort(project));
  }

  private int getServerPort(Project project) {
    int serverPort;
    try {
      serverPort = project.getIntegerConfig("serverPort").orElse(8080);
    } catch (UnauthorizedValueException e) {
      log.warn("The serverPort config is not valid");
      serverPort = 8080;
    }
    return serverPort;
  }
}
