package tech.jhipster.lite.generator.server.springboot.properties.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Service
public class SpringBootPropertiesApplicationService {

  private final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootPropertiesApplicationService(SpringBootPropertiesService springBootPropertiesService) {
    this.springBootPropertiesService = springBootPropertiesService;
  }

  public void addProperties(Project project, String key, Object value) {
    springBootPropertiesService.addProperties(project, key, value);
  }

  public void addPropertiesFast(Project project, String key, Object value) {
    springBootPropertiesService.addPropertiesFast(project, key, value);
  }

  public void addPropertiesTest(Project project, String key, Object value) {
    springBootPropertiesService.addPropertiesTest(project, key, value);
  }
}
