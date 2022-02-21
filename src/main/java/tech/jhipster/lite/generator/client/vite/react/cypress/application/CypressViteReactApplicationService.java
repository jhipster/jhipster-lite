package tech.jhipster.lite.generator.client.vite.react.cypress.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vite.react.cypress.domain.CypressViteReactService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class CypressViteReactApplicationService {

  private final CypressViteReactService cypressViteReactService;

  public CypressViteReactApplicationService(CypressViteReactService cypressViteReactService) {
    this.cypressViteReactService = cypressViteReactService;
  }

  public void init(Project project) {
    cypressViteReactService.init(project);
  }
}
