package tech.jhipster.forge.generator.server.springboot.properties.domain;

import tech.jhipster.forge.generator.project.domain.Project;

public interface SpringBootPropertiesService {
  void addProperties(Project project, String key, Object value);
  void addPropertiesTest(Project project, String key, Object value);
}
