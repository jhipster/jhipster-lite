package tech.jhipster.light.generator.server.springboot.properties.domain;

import tech.jhipster.light.generator.project.domain.Project;

public interface SpringBootPropertiesService {
  void addProperties(Project project, String key, Object value);
  void addPropertiesFast(Project project, String key, Object value);
  void addPropertiesTest(Project project, String key, Object value);
}
