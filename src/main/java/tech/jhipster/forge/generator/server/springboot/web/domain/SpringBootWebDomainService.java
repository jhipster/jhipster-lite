package tech.jhipster.forge.generator.server.springboot.web.domain;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.project.domain.BuildToolRepository;
import tech.jhipster.forge.generator.project.domain.Dependency;
import tech.jhipster.forge.generator.project.domain.Project;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;

public class SpringBootWebDomainService implements SpringBootWebService {

  private final Logger log = LoggerFactory.getLogger(SpringBootWebDomainService.class);

  public final BuildToolRepository buildToolRepository;
  public final SpringBootService springBootService;

  public SpringBootWebDomainService(BuildToolRepository buildToolRepository, SpringBootService springBootService) {
    this.buildToolRepository = buildToolRepository;
    this.springBootService = springBootService;
  }

  @Override
  public void init(Project project) {
    addSpringBootWeb(project);
  }

  @Override
  public void addSpringBootWeb(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    buildToolRepository.addDependency(project, dependency);
    springBootService.addProperties(project, "server.port", getServerPort(project));
  }

  @Override
  public void addSpringBootUndertow(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    Dependency tomcat = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-tomcat").build();
    Dependency undertow = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-undertow").build();

    buildToolRepository.addDependency(project, dependency, List.of(tomcat));
    buildToolRepository.addDependency(project, undertow);
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
