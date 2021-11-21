package tech.jhipster.light.generator.server.springboot.web.domain;

import static tech.jhipster.light.generator.server.springboot.web.domain.SpringBootWeb.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.light.error.domain.UnauthorizedValueException;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class SpringBootWebDomainService implements SpringBootWebService {

  private final Logger log = LoggerFactory.getLogger(SpringBootWebDomainService.class);

  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootWebDomainService(BuildToolService buildToolService, SpringBootPropertiesService springBootPropertiesService) {
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void init(Project project) {
    addSpringBootWeb(project);
  }

  @Override
  public void addSpringBootWeb(Project project) {
    buildToolService.addDependency(project, springBootStarterWebDependency());
    addSpringfoxDependencyAndProperty(project);

    addMvcPathmatchInProperties(project);
    addServerPortInProperties(project);
  }

  @Override
  public void addSpringBootUndertow(Project project) {
    buildToolService.addDependency(project, springBootStarterWebDependency(), List.of(tomcatDependency()));
    buildToolService.addDependency(project, undertowDependency());
    addSpringfoxDependencyAndProperty(project);

    addMvcPathmatchInProperties(project);
    addServerPortInProperties(project);
  }

  private void addSpringfoxDependencyAndProperty(Project project) {
    buildToolService.addDependency(project, springfoxDependency());
    buildToolService.addProperty(project, "springfox", springfoxVersion());
  }

  private void addMvcPathmatchInProperties(Project project) {
    springBootPropertiesService.addProperties(project, "spring.mvc.pathmatch.matching-strategy", "ant_path_matcher");
    springBootPropertiesService.addPropertiesTest(project, "spring.mvc.pathmatch.matching-strategy", "ant_path_matcher");
  }

  private void addServerPortInProperties(Project project) {
    springBootPropertiesService.addProperties(project, "server.port", getServerPort(project));
    springBootPropertiesService.addPropertiesTest(project, "server.port", 0);
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
