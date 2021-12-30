package tech.jhipster.lite.generator.server.springboot.consul.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.consul.domain.ConsulService;

@Service
public class ConsulApplicationService {

  private final ConsulService consulService;

  public ConsulApplicationService(ConsulService consulService) {
    this.consulService = consulService;
  }

  public void init(Project project) {
    consulService.init(project);
  }

  public void addDependencies(Project project) {
    consulService.addDependencies(project);
  }

  public void addProperties(Project project) {
    consulService.addProperties(project);
  }
}
