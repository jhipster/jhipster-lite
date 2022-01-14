package tech.jhipster.lite.generator.server.springboot.common.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootCommonService {
  void addTestLogbackRecorder(Project project);

  void addProperties(Project project, String key, Object value);
  void addPropertiesFast(Project project, String key, Object value);
  void addPropertiesTest(Project project, String key, Object value);
}
