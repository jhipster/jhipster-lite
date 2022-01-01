package tech.jhipster.lite.generator.server.springboot.properties.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootPropertiesService {
  void addProperties(Project project, String key, Object value);

  void addPropertiesFast(Project project, String key, Object value);

  void addPropertiesTest(Project project, String key, Object value);

  void addBootstrapProperties(Project project, String key, Object value);

  void addBootstrapPropertiesFast(Project project, String key, Object value);

  void addBootstrapPropertiesTest(Project project, String key, Object value);
}
