package tech.jhipster.lite.generator.server.springboot.devtools.domain;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class DevToolsDomainService implements DevToolsService {

  public static final String SOURCE = "server/springboot/devtools";

  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public DevToolsDomainService(BuildToolService buildToolService, SpringBootPropertiesService springBootPropertiesService) {
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void init(Project project) {
    addSpringBootDevTools(project);
    addProperties(project);
  }

  @Override
  public void addSpringBootDevTools(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-devtools").optional().build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addProperties(Project project) {
    springPropertiesDevTools().forEach((k, v) -> springBootPropertiesService.addProperties(project, k, v));
  }

  private Map<String, Object> springPropertiesDevTools() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.devtools.livereload.enabled", true);
    result.put("spring.devtools.restart.enabled", true);

    return result;
  }
}
