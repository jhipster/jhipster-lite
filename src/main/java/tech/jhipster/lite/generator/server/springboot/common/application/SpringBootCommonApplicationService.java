package tech.jhipster.lite.generator.server.springboot.common.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@Service
public class SpringBootCommonApplicationService {

  private final SpringBootCommonService springBootCommonService;

  public SpringBootCommonApplicationService(SpringBootCommonService springBootCommonService) {
    this.springBootCommonService = springBootCommonService;
  }

  public void addTestLogbackRecorder(Project project) {
    springBootCommonService.addTestLogbackRecorder(project);
  }

  public void addProperties(Project project, String key, Object value) {
    springBootCommonService.addProperties(project, key, value);
  }

  public void addPropertiesFast(Project project, String key, Object value) {
    springBootCommonService.addPropertiesFast(project, key, value);
  }

  public void addPropertiesTest(Project project, String key, Object value) {
    springBootCommonService.addPropertiesTest(project, key, value);
  }
}
