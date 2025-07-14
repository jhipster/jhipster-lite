package tech.jhipster.lite.generator.typescript.sonar.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.typescript.sonar.domain.SonarTypescriptModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SonarTypescriptApplicationService {

  private final SonarTypescriptModuleFactory sonarTypescript;

  public SonarTypescriptApplicationService(DockerImages dockerImages) {
    this.sonarTypescript = new SonarTypescriptModuleFactory(dockerImages);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties project) {
    return sonarTypescript.buildModule(project);
  }
}
