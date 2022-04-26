package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface GatewayService {
  void init(Project project);

  void addDependencies(Project project);
  void addProperties(Project project);
}
