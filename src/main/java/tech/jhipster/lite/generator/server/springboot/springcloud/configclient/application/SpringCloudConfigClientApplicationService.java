package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientService;

@Service
public class SpringCloudConfigClientApplicationService {

  private final SpringCloudConfigClientService springCloudConfigClientService;

  public SpringCloudConfigClientApplicationService(SpringCloudConfigClientService springCloudConfigClientService) {
    this.springCloudConfigClientService = springCloudConfigClientService;
  }

  public void init(Project project) {
    this.springCloudConfigClientService.init(project);
  }
}
