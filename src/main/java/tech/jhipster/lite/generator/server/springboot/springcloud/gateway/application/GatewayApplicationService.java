package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain.GatewayService;

@Service
public class GatewayApplicationService {

  private final GatewayService gatewayService;

  public GatewayApplicationService(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  public void init(Project project) {
    gatewayService.init(project);
  }
}
