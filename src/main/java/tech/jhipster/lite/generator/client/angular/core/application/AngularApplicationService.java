package tech.jhipster.lite.generator.client.angular.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenModuleFactory;
import tech.jhipster.lite.generator.client.angular.core.domain.AngularModuleFactory;
import tech.jhipster.lite.generator.client.angular.core.domain.AngularService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class AngularApplicationService {

  private final AngularService angularService;
  private final AngularModuleFactory factory;

  public AngularApplicationService(AngularService angularService) {
    this.angularService = angularService;
    factory = new AngularModuleFactory();
  }

  public void addAngular(Project project) {
    angularService.addAngular(project);
  }

  public JHipsterModule buildInitModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
