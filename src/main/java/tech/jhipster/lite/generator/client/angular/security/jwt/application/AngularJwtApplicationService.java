package tech.jhipster.lite.generator.client.angular.security.jwt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.angular.security.jwt.domain.AngularJwtService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class AngularJwtApplicationService {

  private final AngularJwtService angularJwtService;

  public AngularJwtApplicationService(AngularJwtService angularJwtService) {
    this.angularJwtService = angularJwtService;
  }

  public void addJwtAngular(Project project) {
    angularJwtService.addJwtAngular(project);
  }
}
